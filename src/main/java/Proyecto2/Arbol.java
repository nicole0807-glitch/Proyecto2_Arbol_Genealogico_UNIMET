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

    public NodoArbol buscarMiembro(String apodo) {
        return buscarNodoPorMote(raiz, apodo);
    }

    private NodoArbol buscarNodoPorMote(NodoArbol nodo, String apodo) {
        if (nodo == null) {
            return null;
        }
        if (nodo.mote.equals(apodo)) {
            return nodo;
        }
        if (nodo.hijos != null) {
            for (int i = 0; i < nodo.hijos.length; i++) {
                NodoArbol resultado = buscarNodoPorMote(nodo.hijos[i], apodo);
                if (resultado != null) {
                    return resultado;
                }
            }
        }
        return null;
    }

    public void mostrarEstructura() {
        mostrarEstructuraRecursivo(raiz, 0);
    }

    private void mostrarEstructuraRecursivo(NodoArbol nodo, int nivel) {
        if (nodo != null) {
            for (int i = 0; i < nivel; i++) {
                System.out.print("  ");
            }
            System.out.println(nodo.ofHisName);
            if (nodo.hijos != null) {
                for (NodoArbol hijo : nodo.hijos) {
                    mostrarEstructuraRecursivo(hijo, nivel + 1);
                }
            }
        }
    }

    public void mostrarAntepasados(String apodo) {
        NodoArbol miembro = buscarMiembro(apodo);
        if (miembro != null) {
            mostrarAntepasadosRecursivo(miembro);
        } else {
            System.out.println("Miembro no encontrado.");
        }
    }

    private void mostrarAntepasadosRecursivo(NodoArbol nodo) {
        if (nodo != null) {
            if (nodo.bornTo != null) {
                System.out.println(nodo.bornTo.ofHisName);
                mostrarAntepasadosRecursivo(nodo.bornTo);
            }
        }
    }

    public void buscarPorCargo(String cargo) {
        buscarPorCargoRecursivo(raiz, cargo);
    }

    private void buscarPorCargoRecursivo(NodoArbol nodo, String cargo) {
        if (nodo != null) {
            if (nodo.heldTitle.equals(cargo)) {
                System.out.println(nodo.ofHisName);
            }
            if (nodo.hijos != null) {
                for (NodoArbol hijo : nodo.hijos) {
                    buscarPorCargoRecursivo(hijo, cargo);
                }
            }
        }
    }

    public void listarGeneracionEspecifica(int generacion) {
        listarGeneracionRecursivo(raiz, generacion, 0);
    }

    private void listarGeneracionRecursivo(NodoArbol nodo, int generacion, int nivel) {
        if (nodo != null) {
            if (nivel == generacion) {
                System.out.println(nodo.ofHisName);
            }
            if (nodo.hijos != null) {
                for (NodoArbol hijo : nodo.hijos) {
                    listarGeneracionRecursivo(hijo, generacion, nivel + 1);
                }
            }
        }
    }
}
