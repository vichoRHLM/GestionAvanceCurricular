
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
        Boolean enArreglo = false;
        for (int i = 0; i < alAlumnosInscritos.size();i++){
            if(alAlumnosInscritos.get(i).getsRut().equals(alumnoInscribir.getsRut())){
                enArreglo=true;      
            }
        }
        if (enArreglo == false){
            this.alAlumnosInscritos.add(alumnoInscribir);
            System.out.println("Se agrego el alumno correctamente");
        } else
            System.out.println("El alumno ya se encuentra inscrito");   
    }
    
    
    public void asignarProfesorExistente(String rut, ArrayList <Profesor> listaProfes)
    {
        Profesor profesorAsignar = buscarProfesorPorRut(listaProfes, rut);
        if(profesorAsignar != null){
            this.profesorAsignado = profesorAsignar;
            System.out.println("El profesor "+profesorAsignar.getsNombre()+" "+profesorAsignar.getsApellido()+" se ha asignado correctamente a su nueva asinatura");
        }
        else{
            this.profesorAsignado = null;
        }
    }
    
    public Profesor buscarProfesorPorRut(ArrayList <Profesor> listaProfes, String rut){
        
        for (int i = 0; i < listaProfes.size();i++)
        {
            Profesor profesorBuscado = listaProfes.get(i);
            if(profesorBuscado.getsRut().equals(rut))
                return profesorBuscado;
        }
        return null;
    }
    
    public void crearAlumnoPorConsola(Scanner lector, MallaCurricular mc){
        
        Alumno aAlum = new Alumno(); 
        System.out.println("Ingrese datos del alumno:");
        System.out.println("1.- Rut:(12345678-9)");
        aAlum.setsRut(lector.nextLine());
        System.out.println("2.- Nombre :");
        aAlum.setsNombre(lector.nextLine());
        System.out.println("3.- Apellido :");
        aAlum.setsApellido(lector.nextLine());
        System.out.println("4.- Fecha de Nacimiento :");
        aAlum.setsFechaNacimiento(lector.nextLine());
        System.out.println("5.- Correo Electronico :");
        aAlum.setsCorreoElectronico(lector.nextLine());
        agregarAlumno(aAlum);
                   
        
    }
        
    public void mostrarAlumnosAsignatura(){
        for(int i = 0; i< alAlumnosInscritos.size(); i++)
        {
            System.out.println(alAlumnosInscritos.get(i).getsNombre() + " " + alAlumnosInscritos.get(i).getsApellido());
        }
     
    }


}
