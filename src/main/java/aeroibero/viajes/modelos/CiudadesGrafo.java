package aeroibero.viajes.modelos;

import java.util.ArrayList;

public class CiudadesGrafo {
    public String vector;
    public double peso;

    public CiudadesGrafo() {

    }

    public CiudadesGrafo(String nombre, double peso) {
        this.vector = nombre;
        this.peso = peso;
    }


    @Override
    public String toString() {
        return vector + " " + peso;
    }


    public void fromJson(String cadenaJson) {
        cadenaJson = cadenaJson.replace("\"", "");
        cadenaJson = cadenaJson.replace("{", "");
        cadenaJson = cadenaJson.replace("}", "");

        String[] valores = cadenaJson.split(",");

        int inicio = 0, fin = 0;
        String dato = "", campo = "";
        for (String contenido : valores) {
            inicio = contenido.indexOf(":") + 1;
            fin = contenido.indexOf(":");

            dato = contenido.substring(inicio);
            campo = contenido.substring(0, fin);

            switch (campo) {
                case "vector":
                    vector = dato;
                    break;
                case "peso":
                    peso = Double.parseDouble(dato);
                    break;
            }
        }

    }

    public void fromJsonList(String cadenaJson, ArrayList<CiudadesGrafo> ciudadesGrafos) {


        cadenaJson = cadenaJson.replace("[", "");
        cadenaJson = cadenaJson.replace("]", "");

        String[] valores = cadenaJson.split("},");

        for (String subCadena : valores) {
            CiudadesGrafo nuevaCiudadesGrafo = new CiudadesGrafo();

            System.out.println(subCadena);
            nuevaCiudadesGrafo.fromJson(subCadena);

            ciudadesGrafos.add(nuevaCiudadesGrafo);
        }
    }
}
