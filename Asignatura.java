
package gestionavancecurricular;

import java.util.*;

/**
 * Clase que mantiene la gestion de cada asignatura y sus alumnos
 */
public class Asignatura {
    private String sNombre;
    private String sCodigo;
    private Profesor profesorAsignado;
    private ArrayList<Alumno> alAlumnosInscritos;

    public Asignatura(String sNombre, String sCodigo, Profesor profesorAsignado, ArrayList<Alumno> alumnosInscritos) {
        this.sNombre = sNombre;
        this.sCodigo = sCodigo;
        this.profesorAsignado = profesorAsignado; 
        this.alAlumnosInscritos = new ArrayList <> (alumnosInscritos);    
    }
    
    public Asignatura(String sNombre, String sCodigo, Profesor profesorAsignado) {
        this.sNombre = sNombre;
        this.sCodigo = sCodigo;
        this.profesorAsignado = profesorAsignado; 
        this.alAlumnosInscritos = new ArrayList <>(); 
    }
    
    public Asignatura() {
        this.sNombre = "";
        this.sCodigo = "";
        this.profesorAsignado = new Profesor();
        this.alAlumnosInscritos = new ArrayList <> ();
    }

    public String getNombre() { return sNombre; }
    public void setNombre(String sNombre) { this.sNombre = sNombre; }

    public String getsCodigo() { return sCodigo; }
    public void setsCodigo(String sCodigo) { this.sCodigo = sCodigo; }

    public Profesor getProfesorAsignado() { return profesorAsignado; }
    public void setProfesorAsignado(Profesor profesorAsignado) { this.profesorAsignado = profesorAsignado; }

    public Alumno getAlumnosInscritos(Alumno alumnoInscribir) { return buscarAlumnoPorRut(alumnoInscribir.getsRut()); }
    public ArrayList<Alumno> getAlumnosInscritos() { return new ArrayList<>(alAlumnosInscritos); }
    
    /**
     * Setter que agrega un alumno al arreglo de <code>alAlumnosInscritos</code>.
     * Además se valida que el alumno no se encuentre repetido dentro de 
     * <code>alAlumnosInscritos</code>. (Sobrecargado).
     * @param alumnoInscribir  Objeto de tipo alumno que se desea agregar a <code>alAlumnosInscritos</code>.
     */
    public void setAlumnosInscritos(Alumno alumnoInscribir) {
        if (buscarAlumnoPorRut(alumnoInscribir.getsRut()) == null){
            this.alAlumnosInscritos.add(alumnoInscribir);
            System.out.println("Se agrego el alumno correctamente");
        } else
            System.out.println("El alumno ya se encuentra inscrito"); 
    }
    
    /**
     * Setter que agrega un alumno al arreglo de <code>alAlumnosInscritos</code>. 
     * Además, el método se encarga de validar si el alumnmno esta en 
     * <code>listaAlumnos</code>.(Sobrecargado).
     * @param rutAlumno  Identificador unico del alumno que se desea agregar a <code>alAlumnosInscritos</code>.
     * @param listaAlumnos Lista de alumnos para buscar el alumno que se desea agregar a <code>alAlumnosInscritos</code>.
     */
    public void setAlumnosInscritos(String rutAlumno, ArrayList<Alumno> listaAlumnos) {
        Alumno alumno = buscarAlumnoPorRut(rutAlumno, listaAlumnos);
        if (alumno != null) {
            setAlumnosInscritos(alumno);
        } else {
            System.out.println("No se encontró un alumno con el RUT proporcionado");
        }
    }
    
    /**
     * Método que asigna un objeto de tipo Profesor a <code>profesorAsignado</code>.
     * @param rut Identificador unico del Profesor que se desea agregar a <code>profesorAsignado</code>.
     * @param listaProfes Lista de profesores con datos predefinidos.
     */
    public void asignarProfesorExistente(String rut, ArrayList <Profesor> listaProfes) {
        Profesor profesorAsignar = buscarProfesorPorRut(listaProfes, rut);
        if(profesorAsignar != null){
            this.profesorAsignado = profesorAsignar;
            System.out.println("El profesor "+profesorAsignar.getsNombre()+" "+profesorAsignar.getsApellido()+" se ha asignado correctamente a su nueva asinatura");
        }
        else{
            this.profesorAsignado = null;
        }
    }
    
    /**
     * Método que busca un Profesor según su rut.
     * @param listaProfes Lista de profesores con datos predefinidos.
     * @param rut Identificador unico del Profesor que se quiere buscar.
     * @return Retorna un objeto de tipo Profesor si es que se encontro dentro de <code>listaProfes</code>, si no retorna null.
     */
    private Profesor buscarProfesorPorRut(ArrayList <Profesor> listaProfes, String rut){
        
        for (int i = 0; i < listaProfes.size();i++)
        {
            Profesor profesorBuscado = listaProfes.get(i);
            if(profesorBuscado.getsRut().equals(rut))
                return profesorBuscado;
        }
        return null;
    }
    
    /**
     * Método sobrecargado, que busca un Alumno según su rut.
     * @param rutAlumno Identificador unico del alumno.
     * @return Retorna un objeto de tipo Alumno si es que se encontro dentro de <code>alAlumnosInscritos</code>, si no retorna null.
     */
    private Alumno buscarAlumnoPorRut(String rutAlumno) {
        for (int i = 0; i < alAlumnosInscritos.size(); i++) {
            Alumno alumno = alAlumnosInscritos.get(i);
            if (alumno.getsRut().equals(rutAlumno)) {
                return alumno;
            }
        }
        return null;
    }
    
    /**
     * Método sobrecargado, que busca un Alumno según su rut.
     * @param rutAlumno Identificador unico del alumno.
     * @param listaAlumnos Lista de alumnos de una asignatura X.
     * @return Retorna un objeto de tipo alumno si es que se encuentra dentro de <code>listaAlumnos</code>, si no retorna null.
     */           
    private Alumno buscarAlumnoPorRut(String rutAlumno, ArrayList<Alumno> listaAlumnos) {
        for (int i = 0; i < listaAlumnos.size(); i++) {
            Alumno alumno = listaAlumnos.get(i);
            if (alumno.getsRut().equals(rutAlumno)) {
                return alumno;
            }
        }
        return null;
    }
    
    /**
     * Método que agrega un alumno a <code>alAlumnosInscritos</code> según el rut ingresado por consola.
     * @param lector Objeto de tipo <code>Scanner</code> usado para leer datos ingresados por consola. 
     * @param listaAlumnos Lista de alumnos con datos predefinidos.
     */
    public void agregarAlumnoPorConsola(Scanner lector, ArrayList<Alumno> listaAlumnos){
        String rutNuevo; 
        Alumno alumnoPorAgregar = new Alumno();
        
        do{
            System.out.println("Ingrese rut del alumno que desea agregar a la asignatura : ");
            rutNuevo = lector.nextLine();
            alumnoPorAgregar = buscarAlumnoPorRut(rutNuevo, listaAlumnos);
            
            if(alumnoPorAgregar == null){
                System.out.println("Alumno no encontrado, por favor vuelva a ingresar.");
            }
            
        }while(alumnoPorAgregar == null);
        
        setAlumnosInscritos(alumnoPorAgregar);
                   
    }
    
    /**
     * Método que muestra los alumnos inscritos en la asignatura.
     */
    public void mostrarAlumnosAsignatura(){
        for(int i = 0; i< alAlumnosInscritos.size(); i++)
        {
            System.out.println(alAlumnosInscritos.get(i).getsNombre() + " " + alAlumnosInscritos.get(i).getsApellido());
        }
    }
}
