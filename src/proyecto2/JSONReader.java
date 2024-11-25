/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

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

/**
 * Clase que se encarga de leer un archivo JSON y cargar los datos en un árbol
 * genealógico y una tabla hash.
 */
public class JSONReader {

    public Arbol arbol;
    public HashTable hashTable;

    /**
     * Constructor de la clase JSONReader.
     *
     * @param arbol La estructura de árbol donde se cargarán los datos.
     * @param hashTable La tabla hash donde se almacenarán los nodos.
     */
    public JSONReader(Arbol arbol, HashTable hashTable) {
        this.arbol = arbol;
        this.hashTable = hashTable;
    }

    /**
     * Carga un archivo JSON desde la ruta especificada y analiza su contenido.
     *
     * @param rutaArchivo La ruta del archivo JSON a cargar.
     */
    public void cargarArchivo(String rutaArchivo) {
        String contenidoJson = obtenerContenidoJson(rutaArchivo);
        if (contenidoJson != null) {
            JsonElement elementoJson = JsonParser.parseString(contenidoJson);
            analizarJson(elementoJson);
        }
    }

    /**
     * Obtiene el contenido de un archivo JSON como una cadena.
     *
     * @param ruta La ruta del archivo JSON.
     * @return El contenido del archivo JSON como una cadena, o null si ocurre
     * un error.
     */
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

    /**
     * Analiza un elemento JSON y carga los datos en el árbol y la tabla hash.
     *
     * @param elementoJson El elemento JSON que se va a analizar.
     */
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

                    String padre = "Desconocido";
                    String madre = "Desconocido";
                    for (JsonElement elementoDetalle : arrayDetalles) {
                        JsonObject detalleJson = elementoDetalle.getAsJsonObject();
                        for (String clave : detalleJson.keySet()) {
                            // Solo almacenar el primer valor de "Born to"
                            if (clave.equals("Born to") && padre.equals("Desconocido")) {
                                padre = detalleJson.get(clave).getAsString();
                            } else if (clave.equals("Born to") && madre.equals("Desconocido")) {
                                madre = detalleJson.get(clave).getAsString();

                            }
                            detallesPersonaje.add(clave, detalleJson.get(clave));
                        }
                    }

                    // Atributos requeridos
                    String numeral = obtenerValor(detallesPersonaje, "Of his name", "Desconocido");
//                    String padre = (padre != null) ? padre : "Desconocido";
//                    System.out.println("============" + padre);
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
                        if(padre.equals("Symeon Baratheon")){
                            padre+= ", Second of his name";
                        }
                        papa = arbol.buscarNodoPorNombreCompleto(arbol.raiz, padre);
                    }
                    NodoArbol nuevoIntegrante = new NodoArbol(nombreCompleto, numeral, papa, colorOjos, colorCabello);
                    nuevoIntegrante.mote = mote;
                    nuevoIntegrante.heldTitle = titulo;
                    nuevoIntegrante.wedTo = esposa;
                    nuevoIntegrante.notes = notas;
                    nuevoIntegrante.fate = destino;
                    System.out.println(nuevoIntegrante.nombre);
                    // Agregar a la estructura de datos
                    arbol.agregarMiembro(nuevoIntegrante);
                    hashTable.insertar(nuevoIntegrante.nombre + ", " + nuevoIntegrante.ofHisName + " of His Name", nuevoIntegrante);
                }
            }
        } else {
            System.out.println("El contenido no es un objeto JSON válido.");
        }
    }

    private String obtenerPrimerValor(JsonObject detallesPersonaje, String clave, String valorPorDefecto) {
        if (detallesPersonaje.has(clave)) {
            JsonElement valor = detallesPersonaje.get(clave);
            return valor.getAsString();
        }
        return valorPorDefecto;
    }

    /**
     * Inserta todos los nodos del árbol en la tabla hash.
     */
    public void insertarEnHashTable() {
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
            hashTable.insertar(nodo.nombre + ", " + nodo.ofHisName + " of His Name", nodo);

            // Recorrer los hijos
            if (nodo.hijos != null) {
                for (NodoArbol hijo : nodo.hijos) {
                    insertarEnHashTableRecursivo(hijo);
                }
            }
        }
    }

    /**
     * Obtiene el valor de un atributo de un objeto JSON.
     *
     * @param detalles El objeto JSON que contiene los detalles.
     * @param clave La clave del atributo a obtener.
     * @param valorPorDefecto El valor por defecto a devolver si la clave no
     * existe.
     * @return El valor del atributo, o el valor por defecto si no existe.
     */
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
