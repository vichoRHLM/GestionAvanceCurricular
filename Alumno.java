
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
    
    public void inscribirEnAsignatura(Asignatura asignatura) {
        Inscripcion inscripcion = new Inscripcion(this, asignatura);
        inscripciones.add(inscripcion);
    }

    public void mostrarInscripciones() {
        for (int i = 0; i < inscripciones.size(); i++) {
            Inscripcion inscripcion = inscripciones.get(i); 
            System.out.println(inscripcion.toString());
        }
    }
}