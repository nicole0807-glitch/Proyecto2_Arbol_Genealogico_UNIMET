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

    public NodoArbol[] mostrarAntepasados(String apodo) {
        NodoArbol[] tenedores = new NodoArbol[100];
        NodoArbol miembro = buscarMiembro(apodo);
        if (miembro != null) {
            mostrarAntepasadosRecursivo(miembro, tenedores, 0);
        } else {
            System.out.println("Miembro no encontrado.");
        }
        return tenedores;
    }

    private NodoArbol[] mostrarAntepasadosRecursivo(NodoArbol nodo, NodoArbol[] tenedores, int index) {
        if (nodo != null) {
            if (nodo.bornTo != null) {
                System.out.println(nodo.bornTo.ofHisName);
                tenedores[index] = nodo;
                tenedores = mostrarAntepasadosRecursivo(nodo.bornTo, tenedores, index +1);
            }
        }
        return tenedores;
    }

    public NodoArbol[] buscarPorCargo(String cargo) {
                NodoArbol[] tenedores = new NodoArbol[100];
        return buscarPorCargoRecursivo(raiz, cargo, tenedores, 0);
    }

    private NodoArbol[] buscarPorCargoRecursivo(NodoArbol nodo, String cargo, NodoArbol[] tenedores, int index ) {
        if (nodo != null) {
            if (nodo.heldTitle.equals(cargo)) {
                System.out.println(nodo.ofHisName);
                tenedores[index] = nodo;
            }
            if (nodo.hijos != null) {
                for (NodoArbol hijo : nodo.hijos) {
                    index += 1;
                    tenedores = buscarPorCargoRecursivo(hijo, cargo, tenedores, index);
                }
            }
        }
        return tenedores;
    }

    public NodoArbol[] listarGeneracionEspecifica(int generacion) {
                NodoArbol[] tenedores = new NodoArbol[100];
        return listarGeneracionRecursivo(raiz, generacion, 0, tenedores, 0);
    }

    private NodoArbol[] listarGeneracionRecursivo(NodoArbol nodo, int generacion, int nivel, NodoArbol[] tenedores, int index) {


        if (nodo != null) {
            if (nivel == generacion) {
                System.out.println(nodo.ofHisName);
                tenedores[index] = nodo;

            }
            if (nodo.hijos != null) {
                for (NodoArbol hijo : nodo.hijos) {
                    index ++;
                    tenedores = listarGeneracionRecursivo(hijo, generacion, nivel + 1, tenedores, index);
                }
            }
        }
        return tenedores;
    }
}
