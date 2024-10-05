/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionavancecurricular;

import java.io.*;
import java.util.ArrayList;

public class ManejoCSV {

    public ArrayList<String> leerArchivoCSV(String rutaArchivoCSV) throws IOException {
        ArrayList<String> lineasCSV = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivoCSV))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineasCSV.add(linea);
            }
        }
        return lineasCSV;
    }

    public void escribirArchivoCSV(String rutaArchivoCSV, ArrayList<String> lineasCSV) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivoCSV))) {
            for (int i = 0; i < lineasCSV.size();i++) {
                String linea = lineasCSV.get(i);
                bw.write(linea);
                bw.newLine();
            }
        }
    }
}