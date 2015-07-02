package system.user.model;

import hwcore.modules.java.src.library.database.EntityModel;
import hwcore.modules.java.src.library.database.querybuilders.QueryBuilder;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import system.comment.model.HandlerCommentsQuery;
import system.common.MyQueryHandler;

public class HandlerUserQuery extends MyQueryHandler {

    public HandlerUserQuery(EntityModel model) {
        super(model, UserRS.class);
    }

    public UserRS loadUtente(int id) {
        return (UserRS) this.loadData(
                getQb().qbBuildName(EntityModelUser.I().ID_UTENTE.getPath()).qbCompare(id).toString()
        ).getRecords().get(0);
    }

    public UserRS loadUtente(String email, String password) {
        try {
            return (UserRS) this.loadData(
                    null,
                    getQb().qbBuildName(EntityModelUser.I().EMAIL.getPath())
                    .qbCompare(email).toString(),
                    getQb().qbBuildName(EntityModelUser.I().PASSWORD.getPath())
                    .qbCompare(password).toString()
            ).getRecords().get(0);
        } catch (Exception e) {
            return null;
        }
    }

    public PreparedStatement regUser(String name, String lastName, String bornDate,
            String email, String password) {
           String query = "INSERT INTO user(email, birthdate, name, last_name, tax_code, "
                    + "street, city, cap, country, unread_notifications_number, password)"+
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = getStatement(query);
        try {
            ps.setString(1, email);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            java.sql.Date dbDate = new java.sql.Date(df.parse(bornDate).getTime());
            ps.setDate(2, dbDate);
            ps.setString(3, name);
            ps.setString(4, lastName);
            ps.setString(5, "");
            ps.setString(6, "");
            ps.setString(7, "");
            ps.setInt(8, 0);
            ps.setString(9, "Italia");
            ps.setInt(10, 0);
            ps.setString(11, password);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(HandlerUserQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(HandlerUserQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.executeStatement(ps);
    }

    public boolean deleteUser(int id) {
        String query = "DELETE FROM user WHERE id_user= " + id;

        if (this.execute(query) != null) {
            return true;
        }

        return false;
    }

    public PreparedStatement updateUser(int id, String password, String name, String lastName, String birthDay,
            String city, String cap, String street, String country, String taxCode){
        
          String query = "UPDATE user SET "
                    + "password=?,"
                    + "name=?,"
                    + "last_name=?,"
                    + "birthdate=?,"
                    + "city=?,"
                    + "tax_code=?,"
                    + "cap=?,"
                    + "street=?,"
                    + "country=? "
                    + "WHERE id_user='" + id + "'";
            
            PreparedStatement ps = this.getStatement(query);
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            //Date date = df.parse(birthDay);
            java.sql.Date dbDate = new java.sql.Date(df.parse(birthDay).getTime());
            ps.setString(1, password);
            ps.setString(2, name);
            ps.setString(3, lastName);
            ps.setDate(4, dbDate);
            ps.setString(5, city);
            ps.setString(6, taxCode);
            ps.setInt(7, Integer.parseInt(cap));
            ps.setString(8, street);
            ps.setString(9, country);
        } catch (SQLException ex) {
            Logger.getLogger(HandlerUserQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(HandlerUserQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.executeStatement(ps);
    }

    public void updateSession(int uId, String token) {
        String q = "UPDATE user SET session_token ='" + token + "' WHERE id_user='" + uId + "'";
        this.execute(q);
    }

    public PreparedStatement  insertReportRel(int pId, int aInt) {
        String query = "INSERT INTO user_report_rel (user_id_user, report_id_report)"
                + "VALUES(?,?)";

        PreparedStatement ps = this.getStatement(query);

        try {
            ps.setInt(1, pId);
            ps.setInt(2, aInt);
        } catch (SQLException ex) {
            Logger.getLogger(HandlerCommentsQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.executeStatement(ps);

    }
}
