
package gestionavancecurricular;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
            if(!profesorBuscado.validarRut(rut))
                return null;
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
     * @param listaAlumnos
     * @throws ErrorDeLecturaArchivoException
     */
    public void agregarAlumnoPorConsola(Scanner lector, ArrayList<Alumno> listaAlumnos)throws ErrorDeLecturaArchivoException{
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
        actualizarAsignaturaCSVConAlumno(alumnoPorAgregar.getsRut(), "src\\resource\\asignaturas.csv");
        
    }

    
    private void actualizarAsignaturaCSVConAlumno(String rutAlumnoNuevo, String rutaArchivoCSV)throws ErrorDeLecturaArchivoException{
        // Leer todo el contenido del archivo CSV y almacenarlo en memoria
        List<String> lineasCSV = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivoCSV))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineasCSV.add(linea);
            }
        }catch(IOException e){
            throw new ErrorDeLecturaArchivoException(e);
        }

        // Modificar la línea correspondiente a la asignatura actual
        for (int i = 0; i < lineasCSV.size(); i++) {
            String[] datos = lineasCSV.get(i).split(";", -1); // Preservar campos vacíos
            String nombreAsignatura = datos[0];
            String codigoAsignatura = datos[1];
            String rutsAlumnos = datos.length > 2 ? datos[2] : "";

            if (codigoAsignatura.equals(this.sCodigo)) {
                // Agregar el RUT del alumno al final de la lista, si no está ya incluido
                if (!rutsAlumnos.contains(rutAlumnoNuevo)) {
                    if (!rutsAlumnos.isEmpty()) {
                        rutsAlumnos += ","; // Añadir coma si ya hay otros alumnos
                    }
                    rutsAlumnos += rutAlumnoNuevo;

                    // Actualizar la línea del archivo CSV con los nuevos datos
                    lineasCSV.set(i, nombreAsignatura + ";" + codigoAsignatura + ";" + rutsAlumnos + ";");
                }
            }
        }

        // Sobrescribir el archivo CSV con los cambios realizados
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivoCSV))) {
            for (int j = 0; j < lineasCSV.size();j++ )
            {
                String lin = (String) lineasCSV.get(j);
                bw.write(lin);
                bw.newLine(); // Añadir una nueva línea después de cada entrada
            }
        }catch(IOException e){
            throw new ErrorDeLecturaArchivoException(e);
        }
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

    @Override
    public String toString() {
        return "Asignatura = " + sNombre +  ", Codigo = " + sCodigo + ", profesorAsignado=  " + profesorAsignado.getsNombre() + " " + profesorAsignado.getsApellido();
    }
    
    
}
