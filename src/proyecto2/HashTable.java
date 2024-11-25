/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author kelly
 */
/**
 * Clase que representa una tabla hash para almacenar nodos de un árbol genealógico.
 */
public class HashTable {

    public NodoArbol[] nobleza;
    private int size;
    private int count;

    /**
     * Constructor de la clase HashTable que inicializa la tabla hash.
     * 
     * @param size El tamaño inicial de la tabla hash.
     */
    public HashTable(int size) {
        this.size = size;
        this.count = 0;
        this.nobleza = new NodoArbol[this.size];
        for (int i = 0; i < this.size; i++) {
            this.nobleza[i] = null;
        }
    }

    /**
     * Obtiene el arreglo de nodos de la tabla hash.
     * 
     * @return El arreglo de nodos.
     */
    public NodoArbol[] getNobleza() {
        return nobleza;
    }

    /**
     * Establece el arreglo de nodos de la tabla hash.
     * 
     * @param nobleza El nuevo arreglo de nodos.
     */
    public void setNobleza(NodoArbol[] nobleza) {
        this.nobleza = nobleza;
    }

    /**
     * Obtiene el tamaño de la tabla hash.
     * 
     * @return El tamaño de la tabla hash.
     */
    public int getSize() {
        return size;
    }

    /**
     * Establece el tamaño de la tabla hash.
     * 
     * @param size El nuevo tamaño de la tabla hash.
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Obtiene el número de elementos en la tabla hash.
     * 
     * @return El número de elementos.
     */
    public int getCount() {
        return count;
    }

    /**
     * Establece el número de elementos en la tabla hash.
     * 
     * @param count El nuevo número de elementos.
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Calcula el valor hash para una clave dada.
     * 
     * @param mote La clave para la cual se calculará el hash.
     * @return El valor hash correspondiente a la clave.
     */
    public int hash(String mote) {
        int hash = 0;
        for (int i = 0; i < mote.length(); i++) {
            hash += mote.charAt(i) * (i + 1 + this.getSize());
        }
        return hash % this.getSize();
    }

    /**
     * Inserta un nodo en la tabla hash utilizando una clave.
     * 
     * @param clave La clave bajo la cual se insertará el nodo.
     * @param nodo El nodo que se va a insertar.
     */
    public void insertar(String clave, NodoArbol nodo) {
        int hash = this.hash(clave.toLowerCase());
        if (this.getNobleza()[hash] == nodo) {
            return;
        } else if (this.getNobleza()[hash] == null) {
            this.getNobleza()[hash] = nodo;
        } else if (this.getCount() == this.getSize()) {
            this.agrandarArreglo(clave, nodo);
        } else {
            while (this.getNobleza()[hash] != null) {
                if (hash == this.getSize() - 1) {
                    hash = 0;
                } else {
                    hash++;
                }
            }
            this.getNobleza()[hash] = nodo;
        }
    }

    /**
     * Aumenta el tamaño del arreglo de la tabla hash.
     * 
     * @param clave La clave del nodo que se va a insertar.
     * @param noble El nodo que se va a insertar.
     */
    public void agrandarArreglo(String clave, NodoArbol noble) {
        NodoArbol[] anterior = this.nobleza;
        this.nobleza = new NodoArbol[this.getSize() * 2];
        for (int i = 0; i < this.getSize(); i++) {
            this.nobleza[i] = null;
        }
        int tamaño = this.getSize();
        this.setSize(this.getSize() * 2);

        if (noble.nombre.toLowerCase().equals(clave.toLowerCase())) {
            for (int i = 0; i < tamaño; i++) {
                NodoArbol aux = anterior[i];
                this.insertar(aux.nombre, aux);
            }
        }
        if (noble.mote.toLowerCase().equals (clave.toLowerCase())) {
            for (int i = 0; i < tamaño; i++) {
                NodoArbol aux = anterior[i];
                this.insertar(aux.mote, aux);
            }
        }

        int hash = this.hash(clave.toLowerCase());
        this.insertar(clave, noble);
    }

    /**
     * Busca un nodo en la tabla hash utilizando su mote.
     * 
     * @param mote El mote del nodo a buscar.
     * @return El nodo correspondiente al mote, o null si no se encuentra.
     */
    public NodoArbol buscar(String mote) {
        int hash = this.hash(mote.toLowerCase());
        if (this.getNobleza()[hash].mote.toLowerCase().equals(mote.toLowerCase())) {
            return this.getNobleza()[hash];
        } else {
            int contador = 0;
            while (true) {
                if (contador == this.size) {
                    return null;
                }
                if (this.getNobleza()[hash] != null && this.getNobleza()[hash].mote.toLowerCase().equals(mote.toLowerCase())) {
                    return this.getNobleza()[hash];
                }
                if (hash == this.getSize() - 1) {
                    hash = 0;
                } else {
                    hash++;
                }
                contador++;
            }
        }
    }

    /**
     * Busca un nodo en la tabla hash utilizando su nombre.
     * 
     * @param nombre El nombre del nodo a buscar.
     * @return El nodo correspondiente al nombre, o null si no se encuentra.
     */
    public NodoArbol buscarPorNombre(String nombre) {
        int hash = this.hash(nombre.toLowerCase());
        String[] separado = nombre.split(",");

        if (this.getNobleza()[hash] != null && this.getNobleza()[hash].nombre.trim().toLowerCase().equals(separado[0].trim().toLowerCase()) && separado[1].trim().toLowerCase().contains(this.getNobleza()[hash].ofHisName.trim().toLowerCase())) {
            System.out.println("ENCONTRADO");
            return this.getNobleza()[hash];
        } else {
            int contador = 0;
            while (true) {
                if (contador == this.size) {
                    return null;
                }
                if (this.getNobleza()[hash] != null && this.getNobleza()[hash].nombre.trim().toLowerCase().equals(separado[0].trim().toLowerCase()) && separado[1].trim().toLowerCase().contains(this.getNobleza()[hash].nombre.trim().toLowerCase())) {
                    return this.getNobleza()[hash];
                }
                if (hash == this.getSize() - 1) {
                    hash = 0;
                } else {
                    hash++;
                }
                contador++;
            }
        }
    }

    /**
     * Inserta todos los nodos de un árbol en la tabla hash.
     * 
     * @param arbol El árbol cuyos nodos se van a insertar en la tabla hash.
     */
    public void insertarEnHashTable(Arbol arbol) {
        insertarEnHashTableRecursivo(arbol.raiz);
    }

    /**
     * Inserta un nodo en la tabla hash de manera recursiva.
     * 
     * @param nodo El nodo que se va a insertar.
     */
    private void insertarEnHashTableRecursivo(NodoArbol nodo) {
        if (nodo != null) {
            // Insertamos el nodo en la tabla hash usando su mote como clave
            this.insertar(nodo.nombre + ", " + nodo.ofHisName + " of His Name", nodo);

            // Recorrer los hijos
            if (nodo.hijos != null) {
                for (NodoArbol hijo : nodo.hijos) {
                    insertarEnHashTableRecursivo(hijo);
                }
            }
        }
    }
}