
package gestionavancecurricular;

import java.util.*;

/**
 *
 * @author Sebastian Espinoza Rivera
 * 
 * 
 */
public class Asignatura {
     
    private String sNombre;
    private String sCodigo;
    private Profesor profesorAsignado;
    private ArrayList<Alumno> alAlumnosInscritos;

    public Asignatura(String sNombre, String sCodigo, Profesor profesorAsignado, ArrayList<Alumno> alumnosInscritos) {
        this.sNombre = sNombre;
        this.sCodigo = sCodigo;
        this.profesorAsignado = new Profesor();
        this.profesorAsignado = profesorAsignado; //Posible error
        this.alAlumnosInscritos = new ArrayList <> ();
        this.alAlumnosInscritos.addAll(alumnosInscritos);      
    }
    
  
    public Asignatura() {
        this.sNombre = "";
        this.sCodigo = "";
        this.profesorAsignado = new Profesor();
        this.alAlumnosInscritos = new ArrayList();
    }

    public String getNombre() {
        return sNombre;
    }

    public void setNombre(String sNombre) {
        this.sNombre = sNombre;
    }

    public String getsCodigo() {
        return sCodigo;
    }

    public void setsCodigo(String sCodigo) {
        this.sCodigo = sCodigo;
    }

    public Profesor getProfesorAsignado() {
        return profesorAsignado;
    }

    public void setProfesorAsignado(Profesor profesorAsignado) {
        this.profesorAsignado = profesorAsignado;
    }

    public ArrayList<Alumno> getAlumnosInscritos() {
        return alAlumnosInscritos;
    }

    public void setAlumnosInscritos(ArrayList<Alumno> alAlumnosInscritos) {
        this.alAlumnosInscritos = alAlumnosInscritos;
    }
    
    public void agregarAlumno (Alumno alumnoInscribir){
        this.alAlumnosInscritos.add(alumnoInscribir);
        //Validar 
    }
    
    public void agregarAsignatura(){
        //en proceso...
    }
        
    public void mostrarAsignatura(){
        //en proceso...
        System.out.println(this.sNombre + " " + this.sCodigo);
    }

}
