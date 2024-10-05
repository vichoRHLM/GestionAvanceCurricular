/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionavancecurricular;
public class NotaInvalidaException extends Exception {
    public NotaInvalidaException(String valorInvalido){
        super("Nota fuera del rango válido (1.0 - 7.0) o formato de la nota invalido: " + valorInvalido);
    }
}
