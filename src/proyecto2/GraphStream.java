/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto2;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.Future;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
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
public class GraphStream extends JFrame implements ViewerListener {

    private static Arbol tree;
    private Graph graph;
    private final ViewerPipe fromViewer;

    /**
     * Constructor de la clase GraphStream.
     * 
     * @param tree El árbol genealógico que se va a visualizar.
     */
    public GraphStream(Arbol tree) {
        this.tree = tree;
        initComponents();
        this.setLocationRelativeTo(null);
        graph = new SingleGraph("ARBOL FAMILIAR");

        Viewer viewer = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        View view = viewer.addDefaultView(false);
        viewer.getDefaultView().enableMouseOptions();
        GraphStreamPanel.setLayout(new BorderLayout());
        GraphStreamPanel.add((Component) view, BorderLayout.CENTER);
        GraphStreamPanel.setPreferredSize(new Dimension(2400, 1500));
        GraphStreamPanel.setFocusable(true);
        GraphStreamPanel.requestFocusInWindow();
        GraphStreamPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        fromViewer = viewer.newViewerPipe();
        fromViewer.addViewerListener(this);
        fromViewer.addSink(graph);

        populateGraph(tree.raiz, 0, 0);  // Cambié tree.root a tree.getRaiz()

        PumpViewer();
    }

    /**
     * Población del grafo a partir de un nodo del árbol.
     * 
     * @param nodo El nodo actual del árbol.
     * @param x La posición horizontal del nodo en la visualización.
     * @param y La posición vertical del nodo en la visualización.
     */
    private void populateGraph(NodoArbol nodo, int x, int y) {
        if (nodo == null) {
            return;
        }

        String clave = nodo.nombre + "/" + nodo.ofHisName;
        graph.addNode(clave);
        Node node = graph.getNode(clave);
        node.setAttribute("xyz", x, y, 1);
        node.setAttribute("ui.label", nodo.nombre + ", " + nodo.ofHisName + " of his name");
        node.setAttribute("ui.style", "fill-color: pink; size: 40px; stroke-width: 2px;");
        node.setAttribute("ui.clickable", true);

        // Conectar el nodo con su padre
        if (nodo.bornTo != null) {
            try {
                String clavePadre = nodo.bornTo.nombre + "/" + nodo.bornTo.ofHisName;
                graph.addEdge(clavePadre + "-" + clave, clavePadre, clave);
            } catch (Exception e) {
//                e.printStackTrace();
            }
        }

        // Posicionar los hijos
        if (nodo.hijos != null) {
            int numHijos = nodo.hijos.length;
            int childX = x - (numHijos * 50) / 2 + 25; // Ajustar el desplazamiento inicial
            int childY = y - 100; // Distancia vertical a los hijos

            for (NodoArbol hijo : nodo.hijos) {
                populateGraph(hijo, childX, childY);
                childX += 50; // Espaciado horizontal entre hijos
            }
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
            JOptionPane.showMessageDialog(this, "Datos: " + tree.buscarNodoPorNombreCompleto(tree.raiz, nombre).obtenerDatosNodo());
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

        GraphStreamPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(GraphStreamPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 700));

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
            java.util.logging.Logger.getLogger(GraphStream.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GraphStream.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GraphStream.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GraphStream.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GraphStream(tree).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel GraphStreamPanel;
    // End of variables declaration//GEN-END:variables
}
