/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto2;
package proyecto2_kelly;

/**
 *
 * @author kelly
 */
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JSONReader {

    public Arbol arbol;
    public HashTable hashTable;

    public JSONReader(Arbol arbol, HashTable hashTable) {
        this.arbol = arbol;
        this.hashTable = hashTable;
    }

    public void cargarArchivo(String rutaArchivo) {
        String contenidoJson = obtenerContenidoJson(rutaArchivo);
        if (contenidoJson != null) {
            JsonElement elementoJson = JsonParser.parseString(contenidoJson);
            analizarJson(elementoJson);
        }
    }

    public static String obtenerContenidoJson(String ruta) {
        StringBuilder contenido = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                contenido.append(linea);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
            return null;
        }
        return contenido.toString();
    }

    public void analizarJson(JsonElement elementoJson) {
        if (elementoJson.isJsonObject()) {
            JsonObject objetoJson = elementoJson.getAsJsonObject();
            for (String casa : objetoJson.keySet()) {
                JsonArray listaPersonajes = objetoJson.getAsJsonArray(casa);
                for (JsonElement elementoPersonaje : listaPersonajes) {
                    JsonObject personajeJson = elementoPersonaje.getAsJsonObject();

                    String nombreCompleto = personajeJson.keySet().iterator().next();
                    JsonArray arrayDetalles = personajeJson.getAsJsonArray(nombreCompleto);
                    JsonObject detallesPersonaje = new JsonObject();

                    for (JsonElement elementoDetalle : arrayDetalles) {
                        JsonObject detalleJson = elementoDetalle.getAsJsonObject();
                        for (String clave : detalleJson.keySet()) {
                            detallesPersonaje.add(clave, detalleJson.get(clave));
                        }
                    }

                    // Atributos requeridos
                    String numeral = obtenerValor(detallesPersonaje, "Of his name", "Desconocido");
                    String padre = obtenerValor(detallesPersonaje, "Born to", "Desconocido");
                    String colorOjos = obtenerValor(detallesPersonaje, "Of eyes", "Desconocido");
                    String colorCabello = obtenerValor(detallesPersonaje, "Of hair", "Desconocido");

                    // Atributos opcionales
                    String mote = obtenerValor(detallesPersonaje, "Known throughout as", "Desconocido");
                    String titulo = obtenerValor(detallesPersonaje, "Held title", "Desconocido");
                    String esposa = obtenerValor(detallesPersonaje, "Wed to", "Desconocido");
                    String notas = obtenerValor(detallesPersonaje, "Notes", "Desconocido");
                    String destino = obtenerValor(detallesPersonaje, "Fate", "Desconocido");

                    // Crear nuevo integrante y agregarlo al árbol y a la tabla hash
                    NodoArbol papa = arbol.buscarMiembro(padre);
                    if (papa == null) {
                        papa = arbol.buscarNodoPorNombreCompleto(arbol.raiz, padre);
                    }
                    NodoArbol nuevoIntegrante = new NodoArbol(nombreCompleto, numeral, papa, colorOjos, colorCabello);
                    nuevoIntegrante.mote = mote;
                    nuevoIntegrante.heldTitle = titulo;
                    nuevoIntegrante.wedTo = esposa;
                    nuevoIntegrante.notes = notas;
                    nuevoIntegrante.fate = destino;

                    // Agregar a la estructura de datos
                    arbol.agregarMiembro(nuevoIntegrante);
//                    hashTable.insertar(nuevoIntegrante.nombre + ", " + nuevoIntegrante.ofHisName + " of His Name", nuevoIntegrante);
                }
                this.insertarEnHashTable();
            }
        } else {
            System.out.println("El contenido no es un objeto JSON válido.");
        }
    }

    public void insertarEnHashTable() {
        insertarEnHashTableRecursivo(arbol.raiz);
    }

    private void insertarEnHashTableRecursivo(NodoArbol nodo) {
        if (nodo != null) {
            // Insertamos el nodo en la tabla hash usando su mote como clave
            hashTable.insertar(nodo.nombre + ", " + nodo.ofHisName + " of His Name", nodo);

            // Recorrer los hijos
            if (nodo.hijos != null) {
                for (NodoArbol hijo : nodo.hijos) {
                    insertarEnHashTableRecursivo(hijo);
                }
            }
        }
    }

    private String obtenerValor(JsonObject detalles, String clave, String valorPorDefecto) {
        if (detalles.has(clave)) {
            JsonElement elemento = detalles.get(clave);
            if (elemento.isJsonArray() && elemento.getAsJsonArray().size() > 0) {
                return elemento.getAsJsonArray().get(0).getAsString();
            } else if (elemento.isJsonPrimitive()) {
                return elemento.getAsString();
            }
        }
        return valorPorDefecto;
    }
}
