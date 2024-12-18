/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto2;

import javax.swing.JOptionPane;

/**
 *
 * @author kelly
 */
public class Busqueda extends javax.swing.JFrame {

    static Arbol arbol;
    static HashTable table;

    /**
     * Creates new form Busqueda
     */
    public Busqueda(Arbol arbol, HashTable table) {
        initComponents();
        this.setVisible(true);

        this.arbol = arbol;
        this.table = table;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        nombre = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(51, 51, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 204, 255));
        jButton1.setText("Ver Descendencia");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 310, -1, -1));

        nombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 170, 30));

        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, 250, 30));

        jButton2.setBackground(new java.awt.Color(51, 51, 51));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 204, 255));
        jButton2.setText("Buscar ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, -1, -1));

        jLabel2.setFont(new java.awt.Font("Bookman Old Style", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Busqueda de Personaje");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, -1, -1));

        jButton3.setBackground(new java.awt.Color(51, 51, 51));
        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 204, 255));
        jButton3.setText("ATRAS");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 40, -1, -1));

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Ingrese el nombre o mote");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 510));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String nombre = this.jComboBox1.getSelectedItem().toString();
        Arbol desc = new Arbol();
        this.table = new HashTable(120);
        this.table.insertarEnHashTable(arbol);
//        desc.raiz = table.buscarPorNombre( nombre);
        desc.raiz = arbol.buscarNodoPorNombreCompleto(arbol.raiz, nombre);

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        for (NodoArbol hijo : desc.raiz.hijos) {
            if (hijo != null) {
                System.out.println(hijo.nombre);
            }
        }
        GraphStream gs = new GraphStream(desc);
        gs.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            String nombre = this.nombre.getText().trim().toLowerCase();
            NodoArbol[] nodos = this.arbol.buscarNombre(nombre);
            for (NodoArbol nodo : nodos) {
                if (nodo != null) {
                    this.jComboBox1.addItem(nodo.nombre + ", " + nodo.ofHisName + " of His Name");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Nombre o mote incorrecto");

        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Interfaz i = new Interfaz(arbol, table);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Busqueda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Busqueda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Busqueda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Busqueda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Busqueda(arbol, table).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField nombre;
    // End of variables declaration//GEN-END:variables
}
