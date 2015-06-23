/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hwcore.modules.java.src.library.common;

import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

/**
 *
 * @author giuseppe
 */
public class GuiTools {

    /**
     * Fix min size.
     *
     * @param frame the frame
     * @param restoreDim the restore dim
     */
    public static void fixMinSize(final Window frame, boolean restoreDim) {
        Dimension dim = frame.getSize();
        frame.pack();
        frame.setMinimumSize(frame.getSize());
        if (restoreDim) {
            frame.setSize(dim);
        }

        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                Dimension d = frame.getSize();
                Dimension minD = frame.getMinimumSize();
                if (d.width < minD.width) {
                    d.width = minD.width;
                }
                if (d.height < minD.height) {
                    d.height = minD.height;
                }
                frame.setSize(d);
            }
        });
    }

    /**
     * Recupera l'indice vettoriale della lista applicata al modello della
     * tabella
     *
     * @param jTable la tabella da cui recuperare l'indice
     * @param selectedRow la riga selezionata
     * @return -1 se la riga non esiste, altrimenti restituisce il
     * corrispondente indice del vettore
     */
    public static int getListIndexFromTable(javax.swing.JTable jTable, int selectedRow) {
        int modelRow = 0;

        if (selectedRow < 0 || selectedRow >= jTable.getRowCount()) {
            return -1;
        }

        int viewRow = selectedRow;

        if (viewRow >= 0) {
            modelRow = jTable.convertRowIndexToModel(viewRow);
        }

        return modelRow;
    }

    public static void setWindowCenterPosition(Frame frame) {
        setWindowCenterPosition((Window) frame);
    }

    public static void setWindowCenterPosition(Dialog frame) {
        setWindowCenterPosition((Window) frame);
    }

    /**
     * Imposta la posizione di una qualsiasi finestra al centro dello schermo
     *
     * @param frame il frame da centrare
     */
    public static void setWindowCenterPosition(Window frame) {
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((screenSize.width - frame.getHeight()) / 2, (screenSize.height - frame.getWidth()) / 2, frame.getWidth(), frame.getHeight());
    }

    /**
     * Mostra l'internal frame o la finestra esterna
     *
     * @param iFrame
     * @param frame
     * @param internal
     */
    public static void setWindowMode(JInternalFrame iFrame, JFrame frame, boolean isInternal) {
        // mostra una delle due finestre
        iFrame.setVisible(isInternal);
        frame.setVisible(!isInternal);
        // setta il contentpane all'internal frame o al frame esterno
        Container cPane = frame.getContentPane();
        if (isInternal) {
            iFrame.setContentPane(cPane);
        } else {
            frame.setContentPane(cPane);
        }
        // il frame esterno necessita di un aggiornamento
        frame.getRootPane().updateUI();
    }
}
