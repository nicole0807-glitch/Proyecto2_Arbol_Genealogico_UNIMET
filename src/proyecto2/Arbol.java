/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author kelly
 */
public class Arbol {

    public NodoArbol raiz;

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
            if(padre == null){
                padre = buscarNodoPorNombreCompleto(raiz, nuevoMiembro.bornTo.mote);
            }
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
    
    public NodoArbol[] buscarNombre(String apodo) {
        NodoArbol[] nodos = new NodoArbol[100];
        nodos[0] = this.buscarMiembro(apodo);
        return buscarNodoPorNombre(raiz, apodo, nodos, 1);
    }

    private NodoArbol[] buscarNodoPorNombre(NodoArbol nodo, String apodo, NodoArbol[] nodos, int indice) {
        if (nodo == null) {
            return nodos;
        }
        if (nodo.nombre.trim().toLowerCase().contains(apodo)) {
            nodos[indice] = nodo;
        }
        if (nodo.hijos != null) {
            for (int i = 0; i < nodo.hijos.length; i++) {
                nodos = buscarNodoPorNombre(nodo.hijos[i], apodo, nodos, indice+1);
                
            }
        }
        return nodos;
    }
    
    public NodoArbol buscarNodoPorNombreCompleto(NodoArbol nodo, String nombre) {
        if (nodo == null) {
            return null;
        }
        
        String[] separados = nombre.split(",");
        if (nodo.nombre.trim().equals(separados[0]) && separados[1].trim().toLowerCase().contains(nodo.ofHisName.trim().toLowerCase())) {
            return nodo;
        }
        if (nodo.hijos != null) {
            for (int i = 0; i < nodo.hijos.length; i++) {
                NodoArbol resultado = buscarNodoPorNombreCompleto(nodo.hijos[i], nombre);
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
            System.out.println(nodo.nombre);
            if (nodo.hijos != null) {
                for (NodoArbol hijo : nodo.hijos) {
                    mostrarEstructuraRecursivo(hijo, nivel + 1);
                }
            }
        }
    }

    public NodoArbol[] mostrarAntepasados(String apodo) {
        NodoArbol[] tenedores = new NodoArbol[100];
        NodoArbol miembro = this.buscarNodoPorNombreCompleto(raiz, apodo);
        System.out.println(miembro.nombre + miembro.ofHisName);
        System.out.println("ANTEPASADOS");
        if (miembro != null) {
            tenedores = mostrarAntepasadosRecursivo(miembro, tenedores, 0);
        } else {
            System.out.println("Miembro no encontrado.");
        }
        return tenedores;
    }

    private NodoArbol[] mostrarAntepasadosRecursivo(NodoArbol nodo, NodoArbol[] tenedores, int index) {
        if (nodo != null) {
            if (nodo.bornTo != null) {
                System.out.println("Descendiente: " + nodo.bornTo.nombre);
                tenedores[index] = nodo.bornTo;
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
//                System.out.println(nodo.ofHisName);
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
