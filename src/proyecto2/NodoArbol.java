/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author kelly
 */
public class NodoArbol {
    String nombre;
    String ofHisName;
    NodoArbol bornTo;
    String mote;
    String heldTitle;
    String wedTo;
    String ofEyes;
    String ofHair;
    NodoArbol[] hijos;
    String notes;
    String fate;
    
    public NodoArbol(String ofHisName, NodoArbol padre, String ofEyes, String ofHair){
        this.ofHisName = ofHisName;
        this.bornTo = padre;
        this.ofEyes = ofEyes;
        this.ofHair = ofHair;
    }
    
    
}
