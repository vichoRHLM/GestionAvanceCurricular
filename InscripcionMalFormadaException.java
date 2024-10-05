/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionavancecurricular;


public class InscripcionMalFormadaException extends Exception {
    public InscripcionMalFormadaException(String linea){
        super("Errora de formato en la inscripcion en la linea: " + linea);
    }
}
