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
    
    public NodoArbol(String nombre, String ofHisName, NodoArbol padre, String ofEyes, String ofHair){
        this.nombre = nombre;
        this.ofHisName = ofHisName;
        this.bornTo = padre;
        this.ofEyes = ofEyes;
        this.ofHair = ofHair;
        this.hijos = new NodoArbol[9];
    }

    // Método para obtener todos los datos del nodo como un String
    public String obtenerDatosNodo() {
        StringBuilder datos = new StringBuilder();
        
        // Atributos obligatorios
        datos.append("Nombre: ").append(nombre != null ? nombre : "Desconocido").append("\n");
        datos.append("De su nombre: ").append(ofHisName != null ? ofHisName : "Desconocido").append("\n");
        datos.append("Ojos: ").append(ofEyes != null ? ofEyes : "Desconocido").append("\n");
        datos.append("Cabello: ").append(ofHair != null ? ofHair : "Desconocido").append("\n");
        
        // Atributos opcionales
        datos.append("Mote: ").append(mote != null ? mote : "Desconocido").append("\n");
        datos.append("Título: ").append(heldTitle != null ? heldTitle : "Desconocido").append("\n");
        datos.append("Esposa: ").append(wedTo != null ? wedTo : "Desconocido").append("\n");
        datos.append("Notas: ").append(notes != null ? notes : "Desconocido").append("\n");
        datos.append("Destino: ").append(fate != null ? fate : "Desconocido").append("\n");
        
        return datos.toString();
    }
}
