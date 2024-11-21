/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto2;

/**
 *
 * @author kelly
 */
public class Arbol {

    private NodoArbol raiz;

    public Arbol() {
        this.raiz = null;
    }

    public void agregarMiembro(NodoArbol nuevoMiembro) {
        if (raiz == null) {
            raiz = nuevoMiembro;
        } else {
            if (nuevoMiembro.bornTo == null) {
                return;
            }
            NodoArbol padre = buscarNodoPorMote(raiz, nuevoMiembro.bornTo.mote);
            if (padre != null) {
                nuevoMiembro.bornTo = padre;
                agregarDescendiente(padre, nuevoMiembro);
            }
        }
    }

      private void agregarDescendiente(NodoArbol padre, NodoArbol hijo) {
        if (padre.hijos == null) {
            padre.hijos = new NodoArbol[1];
            padre.hijos[0] = hijo;
        } else {
            NodoArbol[] nuevoArray = new NodoArbol[padre.hijos.length + 1];
            for (int i = 0; i < padre.hijos.length; i++) {
                nuevoArray[i] = padre.hijos[i];
            }
            nuevoArray[padre.hijos.length] = hijo;
            padre.hijos = nuevoArray;
        }
    }
}