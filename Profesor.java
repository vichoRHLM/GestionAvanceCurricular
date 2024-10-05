package gestionavancecurricular;

import java.io.*;
import java.util.*;
/**
 * La clase Profesor tiene como objetivo gestionar la información y 
 * funcionalidades relacionadas con los profesores, particularmente la 
 * asignación de asignaturas y el manejo de su información dentro deL sistema de
 * información.
 */
public class Profesor extends Persona {
    /******ATRIBUTOS******/
    private ArrayList<Asignatura> asignaturasAsignadas;
    /******FIN ATRIBUTOS******/
    
    
    /******CONSTRUCTORES******/
    public Profesor(){
        super();
        this.asignaturasAsignadas = new ArrayList<>();
    }
    
    
    public Profesor(Profesor p){
        super(p.getsNombre(), p.getsApellido(), p.getsFechaNacimiento(), p.getsRut(), p.getsCorreoElectronico());
        this.asignaturasAsignadas = new ArrayList<>(p.obtenerCopiaAsignaturasAsignadas());
    }
    
    
    public Profesor(String sNombre, String sApellido, String sFechaNacimiento, String sRut, String sCorreoElectronico) {
        super(sNombre, sApellido, sFechaNacimiento, sRut, sCorreoElectronico);
        this.asignaturasAsignadas = new ArrayList<>();
    }
    /******FIN CONSTRUCTORES******/
    
    
    /******METODOS******/
    /**
     * El método <code>obtenerAsignaturas</code> busca una asignatura según su 
     * código (único) y la devuelve. El método recorre la lista de asignaturas
     * asignadas del profesor, buscando en cada una de ellas su código.
     * @param codAsignatura Identificador único de una asignatura
     * @return Devuelve el objeto <code>Asignatura</code> que se encontro dentro
     * del arrayList <code>asignaturasAsignadas</code>.
     */
    public Asignatura obtenerAsignaturas(String codAsignatura) {
        for (int i = 0; i < asignaturasAsignadas.size(); i++)
        {
            Asignatura asignaturaAux = (Asignatura) asignaturasAsignadas.get(i);
            if (asignaturaAux.getsCodigo().equals(codAsignatura))
                return asignaturaAux;
        }
        
        return null;
    }
    
    
    
    
    /**
   * El método obtiene una copia de la lista de asignaturas asignadas al profesor.
   * @return Una nueva lista de asignaturas asignadas.
   */  
    public ArrayList<Asignatura> obtenerCopiaAsignaturasAsignadas() {
        return new ArrayList<>(asignaturasAsignadas);
    }
    
    
    
    
    /**
     * El método agrega una aignatura a la lista de asigntauras asignadas del 
     * profesor.
     * @param asignatura Objeto tipo <code>Asignatura</code> que se agregará a 
     * la colección <code>asignaturasAsignadas</code>.
     */
    public void asignarAsignatura(Asignatura asignatura) {
        this.asignaturasAsignadas.add(asignatura);
    }

    
    
    
    /**
     * Método que busca un Profesor según su rut.
     * @param listaProfes Lista de profesores con datos predefinidos.
     * @param rut Identificador unico del Profesor que se quiere buscar.
     * @return Retorna un objeto de tipo Profesor si es que se encontro dentro de <code>listaProfes</code>, si no retorna null.
     */
    public Profesor buscarProfesorPorRut(ArrayList <Profesor> listaProfes, String rut){
        
        if(validarRut(rut)){
            for (int i = 0; i < listaProfes.size();i++)
            {
                Profesor profesorBuscado = listaProfes.get(i);
                
                if(!profesorBuscado.validarRut(rut))
                    return null;
                
                if(profesorBuscado.getsRut().equals(rut))
                    return profesorBuscado;
            }
        }
        
        return null;
       
    }
    
    
    
    
    /**
     * Verifica los datos del profesor y lo agrega a la lista y al archivo CSV si no existe.
     * @param rutProfesor El RUT del profesor.
     * @param nombre El nombre del profesor.
     * @param apellido El apellido del profesor.
     * @param fechaNacimiento La fecha de nacimiento del profesor.
     * @param correo El correo electrónico del profesor.
     * @param listaProfesores Lista de profesores en la que se verificará si ya existe el profesor.
     * @return El objeto profesor si se agregó correctamente, null en caso de error o si ya existía.
     * @throws IOException Si ocurre un error al actualizar el archivo CSV.
     */
    public Profesor verificarYAgregarProfesor(String rutProfesor, String nombre, String apellido, String fechaNacimiento, String correo, ArrayList<Profesor> listaProfesores) throws IOException {
        
        // Verificar si algún campo está vacío o nulo
        if (nombre == null || nombre.trim().isEmpty() ||
            apellido == null || apellido.trim().isEmpty() ||
            fechaNacimiento == null || fechaNacimiento.trim().isEmpty() ||
            correo == null || correo.trim().isEmpty() ||
            rutProfesor == null || rutProfesor.trim().isEmpty()) {
            return null;  // No agregar si hay campos vacíos
        }


        if (!validarRut(rutProfesor)) {
            return null;
        }


        // Verificar si el profesor ya está registrado
        Profesor profesorExistente = buscarProfesorPorRut(listaProfesores, rutProfesor);

        if (profesorExistente != null) {
            return null; // El profesor ya existe
        }


        // Crear nuevo profesor y agregarlo a la lista
        Profesor nuevoProfesor = new Profesor(nombre, apellido, fechaNacimiento, rutProfesor, correo);
        listaProfesores.add(nuevoProfesor);


        // Escribir el nuevo profesor en el archivo CSV
        actualizarArchivoCSV("src\\resource\\profesores.csv", nuevoProfesor);

        return nuevoProfesor;
    }
    
    
    
    
    /**
     * Método para actualizar el archivo CSV con la información de un nuevo profesor
     * @param pathArchivo Ruta del archivo CSV
     * @param profesor Profesor que se añadirá al archivo
     * @throws IOException En caso de error al escribir en el archivo
     */
    private static void actualizarArchivoCSV(String pathArchivo, Profesor profesor) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathArchivo, true))) {
            bw.newLine();
            String linea = String.join(";",
                    profesor.getsNombre(),
                    profesor.getsApellido(),
                    profesor.getsFechaNacimiento(),
                    profesor.getsRut(),
                    profesor.getsCorreoElectronico());
            bw.write(linea);
        }
    }
 
    
    
    
    /**
     * Modifica los datos de un profesor existente en la lista y actualiza 
     * su información en el archivo CSV.
     * @param rut El RUT del profesor que se quiere modificar.
     * @param nombre El nuevo nombre del profesor.
     * @param apellido El nuevo apellido del profesor.
     * @param correo El nuevo correo electrónico del profesor.
     * @param listaProfes Lista de profesores en la que se buscará al profesor 
     * para modificarlo.
     * @return El objeto profesor modificado si se encontró y se modificó 
     * correctamente, null en caso contrario.
     * @throws IOException Si ocurre un error al modificar la información en el 
     * archivo CSV.
     */
    public Profesor modificarProfesor(String rut, String nombre, String apellido, String correo, ArrayList<Profesor> listaProfes) throws IOException {
        // Verificar que todos los campos no sean nulos o vacíos
        if (nombre == null || nombre.trim().isEmpty() ||
            apellido == null || apellido.trim().isEmpty() ||
            correo == null || correo.trim().isEmpty()) {
            return null; // No modificar si faltan datos
        }
        
        
        // Buscar al alumno por RUT
        Profesor profesorMod = buscarProfesorPorRut(listaProfes,rut);
        if (profesorMod!= null) {
            // Modificar características del alumno
            profesorMod.modificarCaracteristica(nombre, apellido, correo);
            // Actualizar el archivo CSV
            modificarProfesorEnCSV("src/resource/profesores.csv", profesorMod);
            
            return profesorMod;
        } else {
            return null; // Retornar null si el alumno no se encuentra
        }
    }
    
    
    
    
    /**
     * Actualiza la información de un profesor en el archivo CSV.
     * @param rutaArchivoCSV La ruta del archivo CSV donde se encuentra la información de los profesores.
     * @param profesorModificado El objeto profesor con los datos modificados.
     * @throws IOException Si ocurre un error al leer o escribir en el archivo CSV.
     */
    public void modificarProfesorEnCSV(String rutaArchivoCSV, Profesor profesorModificado) throws IOException {
        ManejoCSV utilCSV = new ManejoCSV();
        ArrayList<String> lineasCSV = utilCSV.leerArchivoCSV(rutaArchivoCSV);
        
        
        if (actualizarLineasProfesorCSV(lineasCSV, profesorModificado)) {
            utilCSV.escribirArchivoCSV(rutaArchivoCSV, lineasCSV);
        } else {
            System.out.println("No se encontró al profesor en el archivo CSV.");
        }
    }

    
    
    
    /**
     * Método para actualizar la información de un profesor en el CSV
     * @param lineasCSV Lista de líneas del archivo CSV
     * @param profesorModificado Profesor con los datos modificados
     * @return true si el profesor fue modificado correctamente, false si no se encontró
     */
    public boolean actualizarLineasProfesorCSV(ArrayList<String> lineasCSV, Profesor profesorModificado) {
        for (int i = 0; i < lineasCSV.size(); i++) {
            String[] datos = lineasCSV.get(i).split(";", -1); // Preservar campos vacíos
            
            if (datos.length < 5) {
                continue;
            }
            
            if (datos[3].equals(profesorModificado.getsRut())) {
                datos[0] = profesorModificado.getsNombre();
                datos[1] = profesorModificado.getsApellido();
                datos[4] = profesorModificado.getsCorreoElectronico();
                lineasCSV.set(i, String.join(";", datos));
                return true;
            }
        }
        return false;
    }

    
    
    
    /**
     * Elimina un profesor de la lista de profesores y actualiza los archivos CSV.
     * @param rutProfesor El RUT del profesor que se quiere eliminar.
     * @param listaProfesores Lista de profesores de la que se eliminará el profesor.
     * @param listaAsignaturas Lista de asignaturas en las que se reemplazará al profesor eliminado.
     * @return true si el profesor fue eliminado correctamente, false en caso contrario.
     * @throws IOException Si ocurre un error al modificar los archivos CSV.
     */
     public boolean eliminarProfesor(String rutProfesor, ArrayList<Profesor> listaProfesores, ArrayList<Asignatura> listaAsignaturas) throws IOException {
        // Buscar y eliminar el profesor de la lista
        Profesor profesorAEliminar = buscarProfesorPorRut(listaProfesores, rutProfesor);
        if (profesorAEliminar == null) {
            return false;
        }

        listaProfesores.remove(profesorAEliminar);

        ManejoCSV utilCSV = new ManejoCSV();


        // Eliminar del archivo CSV de profesores
        ArrayList<String> lineasCSVProfesores = utilCSV.leerArchivoCSV("src/resource/profesores.csv");
        boolean profesorEncontrado = eliminarLineaProfesorCSV(lineasCSVProfesores, rutProfesor);
        if (!profesorEncontrado) {
            return false;
        }

        utilCSV.escribirArchivoCSV("src/resource/profesores.csv", lineasCSVProfesores);

        // Reemplazar RUT del profesor en el archivo CSV de asignaturas
        ArrayList<String> lineasCSVAsignaturas = utilCSV.leerArchivoCSV("src/resource/asignaturas.csv");
        boolean profesorEliminadoDeAsignaturas = eliminarProfesorDeAsignaturasCSV(lineasCSVAsignaturas, rutProfesor);
        if (profesorEliminadoDeAsignaturas) {
            utilCSV.escribirArchivoCSV("src/resource/asignaturas.csv", lineasCSVAsignaturas);
        }


        // Eliminar profesor de la lista de asignaturas en memoria
        for (Asignatura asignatura : listaAsignaturas) {
            if (asignatura.getProfesorAsignado().getsRut().equals(rutProfesor)) {
                asignatura.setProfesorAsignado(new Profesor("00000000-0", "", "", "", "")); // Reemplazar con un profesor genérico o vacío
            }
        }

        return true; // Retorna true si el profesor fue eliminado correctamente
    }

     
     
    /**
     * Elimina la línea de un profesor en el archivo CSV.
     * @param lineasCSV Lista de líneas del archivo CSV.
     * @param rutProfesor El RUT del profesor que se desea eliminar.
     * @return true si la línea fue eliminada, false si no se encontró al profesor.
     */
    private boolean eliminarLineaProfesorCSV(ArrayList<String> lineasCSV, String rutProfesor) {
        for (int i = 0; i < lineasCSV.size(); i++) {
            String[] datos = lineasCSV.get(i).split(";", -1);
            if (datos.length >= 4 && datos[3].equals(rutProfesor)) {
                lineasCSV.remove(i);
                return true;
            }
        }
        return false;
    }

    
    
    /**
     * Elimina el RUT del profesor asignado a las asignaturas en el archivo CSV.
     * @param lineasCSV Lista de líneas del archivo CSV.
     * @param rutProfesor El RUT del profesor que se eliminará de las asignaturas.
     * @return true si el RUT fue reemplazado correctamente, false si no se encontró.
     */
    private boolean eliminarProfesorDeAsignaturasCSV(ArrayList<String> lineasCSV, String rutProfesor) {
        boolean profesorEliminado = false;
        for (int i = 0; i < lineasCSV.size(); i++) {
            String[] datos = lineasCSV.get(i).split(";", -1);
            if (datos.length >= 3 && datos[2].equals(rutProfesor)) {
                // Reemplazar el RUT del profesor por "00000000-0"
                datos[2] = "00000000-0";
                lineasCSV.set(i, String.join(";", datos)); // Actualizar la línea con el RUT reemplazado
                profesorEliminado = true;
            }
        }
        return profesorEliminado;
    }
    
    
    
    
    /**
     * Método que lista las asignatruras con el método propio toString
     * @return Lista de datos String de asignaturas asignadas
     */
    public String listarAsignaturas(){
        String retornoString;
        retornoString = "";
        
        for(int i = 0 ; i < asignaturasAsignadas.size() ; i++)
            retornoString += asignaturasAsignadas.get(i).toString();
            
        return retornoString;
    }
    
    
    
    
    @Override
    public String toString() {
        return "Nombre profesor = " + this.getsNombre() + " " + this.getsApellido();
    }
    /******FIN METODOS******/
}