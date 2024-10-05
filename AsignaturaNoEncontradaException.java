/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionavancecurricular;


public class AsignaturaNoEncontradaException extends Exception{
     public AsignaturaNoEncontradaException(String codigo) {
        super("La asignatura con código " + codigo + " no se encontró en el archivo CSV.");
    }
}
