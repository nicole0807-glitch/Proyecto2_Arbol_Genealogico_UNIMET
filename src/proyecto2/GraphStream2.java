/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerListener;
import org.graphstream.ui.view.ViewerPipe;

/**
 *
 * @author kelly
 */

/**
 * Clase que representa la visualización de un árbol genealógico utilizando GraphStream.
 */
public class GraphStream2 extends javax.swing.JFrame implements ViewerListener {

    private static NodoArbol[] tree;
    public static Arbol arbol;
    private Graph graph;
    private final ViewerPipe fromViewer;

    /**
     * Constructor de la clase GraphStream2.
     * 
     * @param tree Un arreglo de nodos que representan el árbol genealógico.
     * @param arbol La estructura de árbol que se va a visualizar.
     */
    public GraphStream2(NodoArbol[] tree, Arbol arbol) {
        this.arbol = arbol;
        this.tree = tree;
        initComponents();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        graph = new SingleGraph("ARBOL FAMILIAR");

        Viewer viewer = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        View view = viewer.addDefaultView(false);
        viewer.getDefaultView().enableMouseOptions();
        GraphStreamPanel.setLayout(new BorderLayout());
        GraphStreamPanel.add((Component) view, BorderLayout.CENTER);
        GraphStreamPanel.setPreferredSize(new Dimension(2060, 1200));
        GraphStreamPanel.setFocusable(true);
        GraphStreamPanel.requestFocusInWindow();
        GraphStreamPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        fromViewer = viewer.newViewerPipe();
        fromViewer.addViewerListener(this);
        fromViewer.addSink(graph);

        int offsetX = 100; // Espacio entre nodos en el eje X
        int offsetY = 100; // Espacio entre nodos en el eje Y
        int x = 0; // Coordenada inicial X
        int y = 0; // Coordenada inicial Y

        for (NodoArbol nodo : tree) {
            if (nodo != null) {
                populateGraph(nodo, x, y, null);  // Cambié tree.root a tree.getRaiz()
                x += offsetX; // Incrementar la posición X para el siguiente nodo
                if (x > 2000) { // Si llegamos a un cierto límite, mover a la siguiente fila
                    x = 0; // Reiniciar X
                    y += offsetY; // Incrementar Y para la siguiente fila
                }
            }
        }

        PumpViewer();
    }

    /**
     * Población del grafo a partir de un nodo del árbol.
     * 
     * @param nodo El nodo actual del árbol.
     * @param x La posición horizontal del nodo en la visualización.
     * @param y La posición vertical del nodo en la visualización.
     * @param padre El nodo padre del nodo actual.
     */
    private void populateGraph(NodoArbol nodo, int x, int y, NodoArbol padre) {
        if (nodo == null) {
            return;
        }

        String clave = nodo.nombre + "/" + nodo.ofHisName;
        graph.addNode(clave);
        Node node = graph.getNode(clave);
        node.setAttribute("ui.label", nodo.nombre + ", " + nodo.ofHisName + " of his name");
        node.setAttribute("ui.style", "fill-color: pink; size: 45px; stroke-width: 2px;");
        node.setAttribute("ui.clickable", true);
        node.setAttribute("xyz", x, y, 0); // Establecer la posición del nodo

        if (padre != null) {
            String clavePadre = padre.nombre + "/" + padre.ofHisName;
            graph.addEdge(clavePadre + "-" + clave, clavePadre, clave);
        }
    }

    /**
     * Inicia el proceso de actualización del visualizador en un hilo separado.
     */
    private void PumpViewer() {
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                while (!isCancelled()) {
                    fromViewer.pump();
                    try {
                        Thread.sleep(60);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                return null;
            }
        };
        worker.execute();
    }

    @Override
    public void viewClosed(String id) {
        // Implementar lógica si es necesario
    }

    @Override
    public void buttonPushed(String id) {
        Node node = graph.getNode(id);
        if (node != null) {
            String nombre = (String) node.getAttribute("ui.label");
            JOptionPane.showMessageDialog(this, "Datos: " + arbol.buscarNodoPorNombreCompleto(arbol.raiz, nombre).obtenerDatosNodo());
        } else {
            System.out.println("El nodo no se encontró: " + id);
        }
    }

    @Override
    public void buttonReleased(String string) {
        // Implementar lógica si es necesario
    }

    @Override
    public void mouseOver(String string) {
        // Implementar lógica si es necesario
    }

    @Override
    public void mouseLeft(String string) {
        // Implementar lógica si es necesario
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GraphStreamPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout GraphStreamPanelLayout = new javax.swing.GroupLayout(GraphStreamPanel);
        GraphStreamPanel.setLayout(GraphStreamPanelLayout);
        GraphStreamPanelLayout.setHorizontalGroup(
            GraphStreamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1220, Short.MAX_VALUE)
        );
        GraphStreamPanelLayout.setVerticalGroup(
            GraphStreamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );

        getContentPane().add(GraphStreamPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1220, 730));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(GraphStream2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GraphStream2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GraphStream2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GraphStream2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GraphStream2(tree, arbol).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel GraphStreamPanel;
    // End of variables declaration//GEN-END:variables
}
