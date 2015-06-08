package system.numeriemergenza.view;

import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import system.common.MyJFrame;
import system.numeriemergenza.controller.ControllerJFrameNumeri;

/**
 *
 *
 */
public class JFrameNumeri extends MyJFrame {

    private static final long serialVersionUID = 1L;

    /* Creates new form JFrameNumeri */
    public JFrameNumeri(ControllerJFrameNumeri controller, JInternalFrame iFrame) {
        super(controller, iFrame);
        initComponents();
        this.initialize();
    }

    public void initialize() {
        this.initialize(jButtonOut, jPanelContent, jTableNumeri);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelContent = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableNumeri = new javax.swing.JTable();
        jPanelHeader = new javax.swing.JPanel();
        jPanelRicercaWEST = new javax.swing.JPanel();
        jPanelFooter = new javax.swing.JPanel();
        jPanelEAST = new javax.swing.JPanel();
        jButtonOut = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanelContent.setMinimumSize(new java.awt.Dimension(800, 601));
        jPanelContent.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setPreferredSize(new java.awt.Dimension(454, 405));

        jTableNumeri.setAutoCreateRowSorter(true);
        jTableNumeri.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableNumeri.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jTableNumeri);

        jPanelContent.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanelHeader.setLayout(new java.awt.BorderLayout());

        jPanelRicercaWEST.setMinimumSize(new java.awt.Dimension(200, 32));
        jPanelHeader.add(jPanelRicercaWEST, java.awt.BorderLayout.WEST);

        jPanelContent.add(jPanelHeader, java.awt.BorderLayout.NORTH);

        jPanelFooter.setLayout(new java.awt.BorderLayout());
        jPanelFooter.add(jPanelEAST, java.awt.BorderLayout.EAST);

        jButtonOut.setText("stacca");
        jButtonOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOutActionPerformed(evt);
            }
        });
        jPanelFooter.add(jButtonOut, java.awt.BorderLayout.LINE_START);

        jPanelContent.add(jPanelFooter, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanelContent, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOutActionPerformed
        this.changeFrameState();
}//GEN-LAST:event_jButtonOutActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        controller.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        controller.doDefaultFocus();
    }//GEN-LAST:event_formWindowGainedFocus

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jButtonOut;
    private javax.swing.JPanel jPanelContent;
    private javax.swing.JPanel jPanelEAST;
    private javax.swing.JPanel jPanelFooter;
    private javax.swing.JPanel jPanelHeader;
    private javax.swing.JPanel jPanelRicercaWEST;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableNumeri;
    // End of variables declaration//GEN-END:variables

}
