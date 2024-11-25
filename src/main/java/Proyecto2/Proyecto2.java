/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Proyecto2;

/**
 *
 * @author kelly
 */
public class Proyecto2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        // Crear el árbol
//        Arbol arbol = new Arbol();
//
//        // Crear nodos de ejemplo
//        NodoArbol miembro1 = new NodoArbol("William I", null, "Blue", "Brown");
//        miembro1.mote = "William";
//        miembro1.heldTitle = "King";
//
//        NodoArbol miembro2 = new NodoArbol("William II", miembro1, "Green", "Black");
//        miembro2.mote = "William II";
//        miembro2.heldTitle = "Prince";
//
//        NodoArbol miembro3 = new NodoArbol("William III", miembro2, "Brown", "Blonde");
//        miembro3.mote = "William III";
//        miembro3.heldTitle = "Duke";
//
//        NodoArbol miembro4 = new NodoArbol("Elizabeth", miembro1, "Blue", "Black");
//        miembro4.mote = "Elizabeth";
//        miembro4.heldTitle = "Queen";
//
//        // Agregar miembros al árbol
//        arbol.agregarMiembro(miembro1);
//        arbol.agregarMiembro(miembro2);
//        arbol.agregarMiembro(miembro3);
//        arbol.agregarMiembro(miembro4);
//
//        // Prueba de mostrar la estructura del árbol
//        System.out.println("Estructura del árbol:");
//        arbol.mostrarEstructura();
//        System.out.println();
//
//        // Prueba de búsqueda de miembros
//        System.out.println("Prueba de búsqueda de miembros:");
//        NodoArbol encontrado = arbol.buscarMiembro("William II");
//        if (encontrado != null) {
//            System.out.println("Miembro encontrado: " + encontrado.ofHisName);
//        } else {
//            System.out.println("Miembro no encontrado.");
//        }
//        System.out.println();
//
//        // Prueba de mostrar antepasados
//        System.out.println("Prueba de mostrar antepasados de William III:");
//        NodoArbol[] antepasadosDeWilliamIII = arbol.mostrarAntepasados("William III");
//        System.out.println("Antepasados de William III:");
//        for (NodoArbol antepasado : antepasadosDeWilliamIII) {
//            if (antepasado != null) {
//                System.out.println(antepasado.ofHisName);
//            }
//        }
//        System.out.println();
//
//        // Prueba de búsqueda por cargo
//        System.out.println("Prueba de búsqueda por título 'King':");
//        NodoArbol[] tenedoresDeTitulo = arbol.buscarPorCargo("King");
//        System.out.println("Tenedores del título 'King':");
//        for (NodoArbol tenedor : tenedoresDeTitulo) {
//            if (tenedor != null) {
//                System.out.println(tenedor.ofHisName);
//            }
//        }
//        System.out.println();
//
//        // Prueba de listar integrantes de una generación
//        System.out.println("Prueba de listar integrantes de la generación 1:");
//        NodoArbol[] integrantesGeneracion = arbol.listarGeneracionEspecifica(1);
//        for (NodoArbol integrante : integrantesGeneracion) {
//            if (integrante != null) {
//                System.out.println(integrante.ofHisName);
//            }
//        }
        
        Interfaz i = new Interfaz(new Arbol(), new HashTable(120));
    }

}

    
}
