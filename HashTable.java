/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author kelly
 */
public class HashTable {

    public NodoArbol[] nobleza;
    private int size;
    private int count;

    public HashTable(int size) {
        this.size = size;
        this.count = 0;
        this.nobleza = new NodoArbol[this.size];
        for (int i = 0; i < this.size; i++) {
            this.nobleza[i] = null;
        }
    }

    public NodoArbol[] getNobleza() {
        return nobleza;
    }

    public void setNobleza(NodoArbol[] nobleza) {
        this.nobleza = nobleza;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int hash(String mote) {
        int hash = 0;
        for (int i = 0; i < mote.length(); i++) {
            hash += mote.charAt(i) * (i + 1 + this.getSize());
        }
        return hash % this.getSize();
    }

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
        if (noble.mote.toLowerCase().equals(clave.toLowerCase())) {
            for (int i = 0; i < tamaño; i++) {
                NodoArbol aux = anterior[i];

                this.insertar(aux.mote, aux);
            }
        }

        int hash = this.hash(clave.toLowerCase());
        this.insertar(clave, noble);
    }

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

    public NodoArbol buscarPorNombre(String nombre) {
        int hash = this.hash(nombre.toLowerCase());
        String[] separado = nombre.split(",");
        
        if (this.getNobleza()[hash] != null && this.getNobleza()[hash].nombre.trim().toLowerCase().equals(separado[0].trim().toLowerCase()) && separado[1].trim().toLowerCase().contains(this.getNobleza()[hash].ofHisName.trim().toLowerCase()) ) {
            System.out.println("ENCONTRADO");
            
            return this.getNobleza()[hash];
        } else {
            int contador = 0;
            while (true) {
                if (contador == this.size) {
                    return null;
                }
                if (this.getNobleza()[hash] != null && this.getNobleza()[hash].nombre.trim().toLowerCase().equals(separado[0].trim().toLowerCase()) && separado[1].trim().toLowerCase().contains(this.getNobleza()[hash].nombre.trim().toLowerCase()) ) {
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
    
    public void insertarEnHashTable(Arbol arbol) {
        insertarEnHashTableRecursivo(arbol.raiz);
    }

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
