package hwcore.modules.java.src.library.database;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.commons.lang3.StringUtils;

public class MysqlBackup {

    private static String head = "SET AUTOCOMMIT=0; SET FOREIGN_KEY_CHECKS=0;\n";
    private static String tail = "SET FOREIGN_KEY_CHECKS=1;COMMIT;SET AUTOCOMMIT=1;\n";

    /**
     * permette di abilitare/disabilitare il controllo dell'integrità
     * referenziale durante la fase di creazione, nel caso sia disabilitata è
     * possibile inserire tabelle che hanno riferimenti in qualsiasi ordine.
     */
    public boolean checkConstraint = true;

    private String host, port, user, password, db;
    private ArrayList<String> tables;

    /**
     * boolean installed() verifica se è possibile avviare mysqldump,<br>
     * La verifica viene effettuata avviando "mysqldump --help" e cercando la
     * stringa "mysqldump al suo interno"
     *
     * @return true se il comando è disponibile, false altrimenti
     */
    public boolean installed() {
        try {
            Process run = Runtime.getRuntime().exec("mysqldump --help");
            InputStream in = run.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String out = br.readLine();
            int n = out.indexOf("mysqldump");
            br.close();
            in.close();
            if (n > -1 && n < 2) {
                return true;
            }
        } catch (IOException ex) {
            Logger.getLogger(MysqlBackup.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * @param host
     * @param port
     * @param user
     * @param password
     * @param db
     * @return una stringa con il backup del database, <br> una stringa vuota se
     * scrive sullo stream
     * @throws java.lang.Exception
     *
     * esegue il backup dei dati su stringa,o su uno stream dato come argomento
     * <br>
     * è un overload
     */
    private String getData(String host, String port, String user,
            String password, String db, ArrayList<String> tables) throws Exception {
        return getData(host, port, user, password, db, tables, null);

    }

    private String getData(String host, String port, String user,
            String password, String db, ArrayList<String> tables, OutputStream stream) throws Exception {

        String tab = StringUtils.join(tables.toArray(), password);
        Process run = Runtime.getRuntime().exec(
                "mysqldump --host=" + host + " --port=" + port
                + " --user=" + user + " --password=" + password
                + " --compact --complete-insert --extended-insert "
                + "--skip-comments --skip-triggers " + db + " " + tab);
        InputStream in = run.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder temp = new StringBuilder();
        DataOutputStream output = new DataOutputStream(stream);

        String buffer;

        if (!checkConstraint) {
            output.writeBytes(head);
        }
        while ((buffer = br.readLine()) != null) {
            if (stream != null) {
                output.writeBytes(buffer + "\n");
            } else {
                temp.append(buffer).append("\n");
            }

        }
        if (!checkConstraint) {
            output.writeBytes(tail);
        }

        br.close();
        in.close();

        return temp.toString();//se scrive sullo stream è una stringa vuota
    }

    /**
     *
     * @param host
     * @param port
     * @param user
     * @param password
     * @param db
     * @return una stringa con la struttura del db
     * @throws java.lang.Exception
     *
     * estrae la struttura del db e le stored procedure
     */
    static private String getRoutine(String host, String port, String user,
            String password, String db) throws Exception {
        Process run = Runtime.getRuntime().exec(
                "mysqldump --host=" + host + " --port=" + port
                + " --user=" + user + " --password=" + password
                + " --compact --no-create-info "
                + "--no-data --routines " + db);
        InputStream in = run.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        StringBuilder temp = new StringBuilder();

        String buffer;

        while ((buffer = br.readLine()) != null) {
            temp.append(buffer).append("\n");
        }

        br.close();
        in.close();

        return temp.toString();
    }

    public MysqlBackup(String host, String port, String user,
            String password, String db, ArrayList<String> tables) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
        this.db = db;
        this.tables = tables;
    }

    /**
     * @throws java.lang.Exception
     *
     * Wrapper di getData
     * <strong>
     * LA FUNZIONE NON È OTTIMIZZATA E ALLOCA MOLTA RAM <BR>
     * SERVE SOLO PER FARE TEST
     * </strong>
     */
    public String data_to_string() throws Exception {
        return getData(host, port, user, password, db, tables);
    }

    /**
     *
     * Wrapper di getRoutine()
     *
     * @throws java.lang.Exception
     */
    public String routine_to_string() throws Exception {
        return getRoutine(host, port, user, password, db);
    }

    /**
     *
     * @param nomefile
     * @return true se va bene, false altrimenti Scrive i dati su un file,
     * funzione ottimizzata per allocare meno ram
     */
    public boolean data_to_file(String nomefile) {
        File filedst = new File(nomefile);
        try {
            FileOutputStream dest = new FileOutputStream(filedst);
            getData(host, port, user, password, db, tables, dest);
            dest.flush();
            dest.close();
        } catch (Exception ex) {
            Logger.getLogger(MysqlBackup.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    /**
     *
     * @param nomefile
     * @return true se va bene, false altrimenti Scrive i dati su un file
     */
    public boolean routine_to_file(String nomefile) {
        File filedst = new File(nomefile);
        try {
            FileOutputStream dest = new FileOutputStream(filedst);
            dest.write(routine_to_string().getBytes());
            dest.flush();
            dest.close();
        } catch (Exception ex) {
            Logger.getLogger(MysqlBackup.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    /**
     *
     * @param nomefile
     * @return true se va bene, false altrimenti genera un file zip con
     * all'interno due files: <br>
     * data.sql con i dati <br>
     * routine.sql con la struttura e le procedure
     */
    public boolean all_to_zip(String nomefile) {
        try {
            File filedst = new File(nomefile);
            FileOutputStream dest = new FileOutputStream(filedst);
            ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(dest));
            zip.setMethod(ZipOutputStream.DEFLATED);
            zip.setLevel(Deflater.BEST_COMPRESSION);
            zip.putNextEntry(new ZipEntry("data.sql"));
            getData(host, port, user, password, db, tables, zip);
            zip.putNextEntry(new ZipEntry("routine.sql"));
            zip.write(routine_to_string().getBytes());
            zip.close();
            dest.close();
        } catch (Exception ex) {
            Logger.getLogger(MysqlBackup.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
}
