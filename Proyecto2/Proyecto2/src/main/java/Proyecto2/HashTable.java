/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto2;

/**
 *
 * @author kelly
 */
public class HashTable {

    private NodoArbol[] nobleza;
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

    public void insertar(NodoArbol noble) {
        int hash = this.hash(noble.mote.toLowerCase());
        if (this.getNobleza()[hash] == noble) {
            return;
        }
        else if(this.getNobleza()[hash] == null){
            this.getNobleza()[hash] = noble;
        }
        else if (this.getCount() == this.getSize()) {
            this.agrandarArreglo(noble);
        } else {
            while(this.getNobleza()[hash] != null){
                if(hash == this.getSize() -1){
                    hash = 0;
                }
                else{
                    hash ++;
                }
            }
            this.getNobleza()[hash] = noble;
            
        }
    }

    public void agrandarArreglo(NodoArbol noble) {
        NodoArbol[] anterior = this.nobleza;
        this.nobleza = new NodoArbol[this.getSize() * 2];
        for (int i = 0; i < this.getSize(); i++) {
            this.nobleza[i] = null;
        }
        int tamaño = this.getSize();
        this.setSize(this.getSize() * 2);
        for (int i = 0; i < tamaño; i++) {
            NodoArbol aux = anterior[i];
            this.insertar(aux);
        }

        int hash = this.hash(noble.mote.toLowerCase());
        this.insertar(noble);
    }

    public NodoArbol buscar(String mote) {
        int hash = this.hash(mote.toLowerCase());
        if (this.getNobleza()[hash].mote.toLowerCase().equals(mote.toLowerCase())) {
            return this.getNobleza()[hash];
        } else {
            int contador = 0;
            while(true){
                if(contador == this.size){
                    return null;
                }
                if(this.getNobleza()[hash] != null && this.getNobleza()[hash].mote.toLowerCase().equals(mote.toLowerCase())){
                    return this.getNobleza()[hash];
                }
                if(hash == this.getSize() -1){
                    hash = 0;
                }
                else{
                    hash ++;
                }
                contador++;
            }
        }
    }



}
