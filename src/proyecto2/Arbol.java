/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 * Clase que representa un árbol genealógico.
 */
public class Arbol {

    public NodoArbol raiz;

    /**
     * Constructor de la clase Arbol que inicializa la raíz como nula.
     */
    public Arbol() {
        this.raiz = null;
    }

    /**
     * Agrega un nuevo miembro al árbol.
     * 
     * @param nuevoMiembro El nodo que representa al nuevo miembro a agregar.
     */
    public void agregarMiembro(NodoArbol nuevoMiembro) {
        if (raiz == null) {
            raiz = nuevoMiembro;
        } else {
            if (nuevoMiembro.bornTo == null) {
                System.out.println("lsjjaks");
                return;
            }
            NodoArbol padre = buscarNodoPorMote(raiz, nuevoMiembro.bornTo.mote);
            System.out.println("Padre: " +nuevoMiembro.bornTo.mote );
            if (padre == null) {
                padre = buscarNodoPorNombreCompleto(raiz, nuevoMiembro.bornTo.mote);
            }
            if (padre != null) {
                System.out.println("encontrado");
                nuevoMiembro.bornTo = padre;
                agregarDescendiente(padre, nuevoMiembro);
            }
        }
    }

    /**
     * Agrega un hijo al nodo padre.
     * 
     * @param padre El nodo padre al que se le va a agregar el hijo.
     * @param hijo El nodo hijo que se va a agregar.
     */
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

    /**
     * Busca un miembro del árbol por su apodo.
     * 
     * @param apodo El apodo del miembro a buscar.
     * @return El nodo correspondiente al apodo, o null si no se encuentra.
     */
    public NodoArbol buscarMiembro(String apodo) {
        return buscarNodoPorMote(raiz, apodo);
    }

    /**
     * Busca un nodo en el árbol por su apodo.
     * 
     * @param nodo El nodo desde el cual iniciar la búsqueda.
     * @param apodo El apodo a buscar.
     * @return El nodo correspondiente al apodo, o null si no se encuentra.
     */
    private NodoArbol buscarNodoPorMote(NodoArbol nodo, String apodo) {
        if (nodo == null) {
            return null;
        }
        if (nodo.mote.trim().toLowerCase().equals(apodo.trim().toLowerCase())) {
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

    /**
     * Busca nodos en el árbol por su nombre.
     * 
     * @param apodo El apodo a buscar.
     * @return Un arreglo de nodos que coinciden con el nombre.
     */
    public NodoArbol[] buscarNombre(String apodo) {
        NodoArbol[] nodos = new NodoArbol[100];
        nodos[0] = this.buscarMiembro(apodo);
        return buscarNodoPorNombre(raiz, apodo, nodos, 1);
    }

    /**
     * Busca nodos en el árbol por su nombre.
     * 
     * @param nodo El nodo desde el cual iniciar la búsqueda.
     * @param apodo El apodo a buscar.
     * @param nodos El arreglo donde se almacenarán los nodos encontrados.
     * @param indice El índice actual en el arreglo de nodos.
     * @return El arreglo de nodos encontrados.
     */
    private NodoArbol[] buscarNodoPorNombre(NodoArbol nodo, String apodo, NodoArbol[] nodos, int indice) {
        if (nodo == null) {
            return nodos;
        }
        if (nodo.nombre.trim().toLowerCase().contains(apodo)) {
            nodos[indice] = nodo;
        }
        if (nodo.hijos != null) {
            for (int i = 0; i < nodo.hijos.length; i++) {
                nodos = buscarNodoPorNombre(nodo.hijos[i], apodo, nodos, indice + 1);
            }
        }
        return nodos;
    }

    /**
     * Busca un nodo en el árbol por su nombre completo.
     * 
     * @param nodo El nodo desde el cual iniciar la búsqueda.
     * @param nombre El nombre completo a buscar.
     * @return El nodo correspondiente al nombre completo, o null si no se encuentra.
     */
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

    /**
     * Muestra la estructura del árbol en la consola.
     */
    public void mostrarEstructura() {
        mostrarEstructuraRecursivo(raiz, 0);
    }

    /**
     * Muestra la estructura del árbol de manera recursiva.
     * 
     * @param nodo El nodo actual.
     * @param nivel El nivel actual en la estructura del árbol.
     */
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

    /**
     * Muestra los antepasados de un miembro específico.
     * 
     * @param apodo El apodo del miembro cuyo antepasado se desea mostrar.
     * @return Un arreglo de nodos que representan los antepasados.
     */
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

    /**
     * Muestra los antepasados de un nodo de manera recursiva.
     * 
     * @param nodo El nodo actual.
     * @param tenedores El arreglo donde se almacenarán los antepasados.
     * @param index El índice actual en el arreglo de tenedores.
     * @return El arreglo de tenedores encontrados.
     */
    private NodoArbol[] mostrarAntepasadosRecursivo(NodoArbol nodo, NodoArbol[] tenedores, int index) {
        if (nodo != null) {
            if (nodo.bornTo != null) {
                System.out.println("Descendiente: " + nodo.bornTo.nombre);
                tenedores[index] = nodo.bornTo;
                tenedores = mostrarAntepasadosRecursivo(nodo.bornTo, tenedores, index + 1);
            }
        }
        return tenedores;
    }

    /**
     * Busca miembros por su cargo.
     * 
     * @param cargo El cargo a buscar.
     * @return Un arreglo de nodos que representan los miembros con el cargo especificado.
     */
    public NodoArbol[] buscarPorCargo(String cargo) {
        NodoArbol[] tenedores = new NodoArbol[100];
        return buscarPorCargoRecursivo(raiz, cargo, tenedores, 0);
    }

    /**
     * Busca miembros por su cargo de manera recursiva.
     * 
     * @param nodo El nodo actual.
     * @param cargo El cargo a buscar.
     * @param tenedores El arreglo donde se almacenarán los miembros encontrados.
     * @param index El índice actual en el arreglo de tenedores.
     * @return El arreglo de tenedores encontrados.
     */
    private NodoArbol[] buscarPorCargoRecursivo(NodoArbol nodo, String cargo, NodoArbol[] tenedores, int index) {
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

    /**
     * Lista los miembros de una generación específica.
     * 
     * @param generacion El número de la generación a listar.
     * @return Un arreglo de nodos que representan los miembros de la generación especificada.
     */
    public NodoArbol[] listarGeneracionEspecifica(int generacion) {
        NodoArbol[] tenedores = new NodoArbol[100];
        return listarGeneracionRecursivo(raiz, generacion, 0, tenedores, 0);
    }

    /**
     * Lista los miembros de una generación de manera recursiva.
     * 
     * @param nodo El nodo actual.
     * @param generacion El número de la generación a listar.
     * @param nivel El nivel actual en la estructura del árbol.
     * @param tenedores El arreglo donde se almacenarán los miembros encontrados.
     * @param index El índice actual en el arreglo de tenedores.
     * @return El arreglo de tenedores encontrados.
     */
    private NodoArbol[] listarGeneracionRecursivo(NodoArbol nodo, int generacion, int nivel, NodoArbol[] tenedores, int index) {
        if (nodo != null) {
            if (nivel == generacion) {
                tenedores[index] = nodo;
            }
            if (nodo.hijos != null) {
                for (NodoArbol hijo : nodo.hijos) {
                    index++;
                    tenedores = listarGeneracionRecursivo(hijo, generacion, nivel + 1, tenedores, index);
                }
            }
        }
        return tenedores;
    }
}