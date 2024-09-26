
package gestionavancecurricular;

import java.util.*;
/**
 * Clase que mantiene la información de los alumnos
 */
public class Alumno extends Persona{
    
    private ArrayList<Inscripcion> inscripciones;
    
    public Alumno() {
        super();
        this.inscripciones = new ArrayList<>();
    }
    
    public Alumno(String sNombre, String sApellido, String sFechaNacimiento, String sRut, String sCorreoElectronico) {
        super(sNombre, sApellido, sFechaNacimiento, sRut, sCorreoElectronico);
        this.inscripciones = new ArrayList<>();
    }
    /**
     * Método que recibe y agrega un objeto <code>Asignatura</code> a las 
     * inscripciones del alumno.
     * @param asignatura 
     */
    public void inscribirEnAsignatura(Asignatura asignatura) {
        Inscripcion inscripcion = new Inscripcion(this, asignatura);
        inscripciones.add(inscripcion);
    }
    
    /**
     * Método que recibe un objeto tipo <code>Inscripcion</code> y lo añade a la
     * lista de <code>inscripciones</code>.
     * @param newInc Incripciona que sera añadida a la lista 
     * <code>inscripciones</code>.
     */
    public void añadirNuevaInscripcion(Inscripcion newInc){
        inscripciones.add(newInc);
    }
    
    public void mostrarInscripciones() {
        for (int i = 0; i < inscripciones.size(); i++) {
            Inscripcion inscripcion = inscripciones.get(i); 
            System.out.println(inscripcion.toString());
        }
    }
    
    public ArrayList<Inscripcion> entregarInscripciones(){
        return new ArrayList<>(inscripciones);
    }
    
    private double calcularAvanceCurricular(){
        int cantAprobadas = 0;
        for(int i = 0; i < inscripciones.size(); i++) {
            Inscripcion inscripcion = (Inscripcion) inscripciones.get(i); 
            if(inscripcion.isAprobada()){
                cantAprobadas++;
            }
        }
        return (double) cantAprobadas/23;
    }

    
    @Override
    public String toString() {
        return "Nombre alumno = " + this.getsNombre() + " " + this.getsApellido();
    }
}