
package gestionavancecurricular;

import java.util.*;
import java.io.*;

/**
 * Clase que mantiene la información de los alumnos
 */
public class Alumno extends Persona{
    /******ATRIBUTOS******/
    private ArrayList<Inscripcion> inscripciones;
    /******FIN ATRIBUTOS******/
    
    
    /******CONSTRUCTORES******/
    public Alumno() {
        super();
        this.inscripciones = new ArrayList<>();
    }
    
    
    public Alumno(String sNombre, String sApellido, String sFechaNacimiento, String sRut, String sCorreoElectronico) {
        super(sNombre, sApellido, sFechaNacimiento, sRut, sCorreoElectronico);
        this.inscripciones = new ArrayList<>();
    }
    /******FIN CONSTRUCTORES******/
    
    
    
    /******METODOS******/
    /**
     * Método que recibe y agrega un objeto <code>Asignatura</code> a las 
     * inscripciones del alumno.
     * @param asignatura 
     */
    public void inscribirEnAsignatura(Asignatura asignatura) {
        //Lectura de csv
        asignatura.setAlumnosInscritos(this);
        Inscripcion inscripcion = new Inscripcion(this, asignatura);
        inscripciones.add(inscripcion);
        //Escritura de csv
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
    
    
    
    
    /**
     * Método que muestra por consola las inscripciones del alumno.
     */
    public void mostrarInscripciones() {
        for (int i = 0; i < inscripciones.size(); i++) {
            Inscripcion inscripcion = inscripciones.get(i); 
            System.out.println(inscripcion.toString());
        }
    }
    
    
    
    
    /**
     * Método que entrega una copia de las inscripciones del alumno.
     * @return Copia lista de inscripciones del alumno.
     */
    public ArrayList<Inscripcion> entregarInscripciones(){
        return new ArrayList<>(inscripciones);
    }
    
    
    
    
    /**
     * Método que calcula el avance curricular del alumno en base a las
     * asignaturas aprobadas. El cálculo se basa en la cantidad de asignaturas
     * aprobadas en relación al total de 23 asignaturas en la malla.
     * 
     * @return El porcentaje de avance curricular del alumno.
     */
    public double calcularAvanceCurricular(){
        int cantAprobadas = 0;
        for(int i = 0; i < inscripciones.size(); i++) {
            Inscripcion inscripcion = (Inscripcion) inscripciones.get(i); 
            if(inscripcion.isAprobada()){
                cantAprobadas++;
            }
        }
        return (double) cantAprobadas/23;
    }

    
    
    
    /**
     * Método que entrega la lista de asignaturas en las que el alumno está inscrito.
     * @return Lista de asignaturas inscritas por el alumno.
     */
    public ArrayList<Asignatura> asignaturasInscritas() {
        ArrayList<Asignatura> asgInsc = new ArrayList <>();
        for (int i = 0; i < inscripciones.size(); i++){
            Inscripcion aux = (Inscripcion) inscripciones.get(i);
            if (aux.getCodigoAsignatura().equals(sNombre)){ 
                asgInsc.add(aux.getCopiaAsignatura());
            }
        }    
        return asgInsc;
    }
    
    
    
    
    /**
     * Método sobrecargado, que busca un Alumno según su rut.
     * @param rutAlumno Identificador unico del alumno.
     * @param listaAlumnos Lista de alumnos de una asignatura X.
     * @return Retorna un objeto de tipo alumno si es que se encuentra dentro de <code>listaAlumnos</code>, si no retorna null.
     */           
    public Alumno buscarAlumnoPorRut(String rutAlumno, ArrayList<Alumno> listaAlumnos) {
        if(validarRut(rutAlumno)){
            for (int i = 0; i < listaAlumnos.size(); i++) {
                Alumno alumno = listaAlumnos.get(i);
                if (alumno.getsRut().equals(rutAlumno)) {
                    return alumno;
                }
            }
        }
        return null;
    }
    
    
    
    
    /**
     * Método que verifica si un alumno ya está inscrito. Si no lo está, lo agrega
     * a la lista de alumnos y actualiza el archivo CSV.
     * @param rutAlumno RUT del alumno
     * @param nombre Nombre del alumno
     * @param apellido Apellido del alumno
     * @param fechaNacimiento Fecha de nacimiento del alumno
     * @param correo Correo electrónico del alumno
     * @param listaAlumnos Lista de alumnos
     * @return true si el alumno fue añadido, false si ya existía
     * @throws IOException En caso de error al leer o escribir el archivo
     */
    public Alumno verificarYAgregarAlumno(String rutAlumno, String nombre, String apellido, String fechaNacimiento, 
                                      String correo, ArrayList<Alumno> listaAlumnos) throws IOException {
        // Verificar si algún campo está vacío o nulo
        if (nombre == null || nombre.trim().isEmpty() ||
            apellido == null || apellido.trim().isEmpty() ||
            fechaNacimiento == null || fechaNacimiento.trim().isEmpty() ||
            correo == null || correo.trim().isEmpty() ||
            rutAlumno == null || rutAlumno.trim().isEmpty()) {

            
            // Si falta algún campo, no se añade el alumno
            return null; // Retorna null si algún campo está vacío o nulo
        }
        
        
        if (!validarRut(rutAlumno)) {
                return null;
            }
        
        
        // Verificar si el alumno ya está inscrito
        Alumno alumnoExistente = buscarAlumnoPorRut(rutAlumno, listaAlumnos);
        if (alumnoExistente != null) {
            return null; // El alumno ya existe
        }

        
        // Crear nuevo alumno y agregarlo a la lista
        Alumno nuevoAlumno = new Alumno(nombre, apellido, fechaNacimiento, rutAlumno, correo);
        listaAlumnos.add(nuevoAlumno);
        
        
        // Escribir el nuevo alumno en el archivo CSV
        agregarAlumnoArchivoCSV("src\\resource\\alumnos.csv", nuevoAlumno);

        
        return nuevoAlumno;
    }
    
    
    
    
    /**
     * Método para actualizar el archivo CSV con la información de un nuevo alumno
     * @param pathArchivo Ruta del archivo CSV
     * @param alumno Alumno que se añadirá al archivo
     * @throws IOException En caso de error al escribir en el archivo
     */
    private void agregarAlumnoArchivoCSV(String pathArchivo, Alumno alumno) throws IOException {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathArchivo, true))) {
            // Escribir una nueva línea antes de agregar un nuevo alumno, si no es la primera entrada
            bw.newLine(); // Asegura que haya una línea en blanco antes de escribir el nuevo registro

            
            // Crear la línea del alumno en el formato CSV
            String linea = String.join(";",
                    alumno.getsNombre(),
                    alumno.getsApellido(),
                    alumno.getsFechaNacimiento(),
                    alumno.getsRut(),
                    alumno.getsCorreoElectronico());

            
            bw.write(linea); // Escribir la línea en el archivo
        }
    }
    
    
    
    
    /**
     * Metodo que actualiza el alumno recibido del archivo csv
     * @param lineasCSV
     * @param alumnoModificado 
     */
    public void actualizarLineasAlumnoCSV(ArrayList<String> lineasCSV, Alumno alumnoModificado) {
        for (int i = 0; i < lineasCSV.size(); i++) {
            String[] datos = lineasCSV.get(i).split(";", -1); // Preservar campos vaci­os
            if (datos.length < 5) {
                continue;
            }
            
            
            //Actualizar los datos si se encuentra el rut del alumno
            if (datos[3].equals(alumnoModificado.getsRut())) {
                datos[0] = alumnoModificado.getsNombre();
                datos[1] = alumnoModificado.getsApellido();
                datos[4] = alumnoModificado.getsCorreoElectronico();
                lineasCSV.set(i, String.join(";", datos));
                return;
            }
        }
    }
    
    
    
    
    /**
     * Método que busca y modifica los datos de un alumno por su RUT. Si se encuentra,
     * modifica el nombre, apellido y correo del alumno y actualiza el archivo CSV.
     * @param rut RUT del alumno a modificar.
     * @param nombre Nuevo nombre del alumno.
     * @param apellido Nuevo apellido del alumno.
     * @param correo Nuevo correo electrónico del alumno.
     * @param listaAlumnos Lista de alumnos donde se buscará el alumno a modificar.
     * @return El alumno modificado si se encontró y actualizó, o null si no se encontró.
     * @throws IOException Si ocurre un error al leer o escribir el archivo CSV.
     */
    public Alumno modificarAlumno(String rut, String nombre, String apellido, String correo, ArrayList<Alumno> listaAlumnos) throws IOException {
        // Verificar que todos los campos no sean nulos o vacíos
        if (nombre == null || nombre.trim().isEmpty() ||
            apellido == null || apellido.trim().isEmpty() ||
            correo == null || correo.trim().isEmpty()) {
            return null; // No modificar si faltan datos
        }


        // Buscar al alumno por RUT
        Alumno alumnoMod = buscarAlumnoPorRut(rut, listaAlumnos);
        if (alumnoMod != null) {
            // Modificar características del alumno
            alumnoMod.modificarCaracteristica(nombre, apellido, correo);
            
            
            // Actualizar el archivo CSV
            modificarAlumnoEnCSV("src/resource/alumnos.csv", alumnoMod);
            
            
            return alumnoMod;
        } else {
            return null; // Retornar null si el alumno no se encuentra
        }
    }

    
    
    
    /**
     * Método que actualiza el archivo CSV con los datos modificados de un alumno.
     * @param rutaArchivoCSV Ruta del archivo CSV donde se actualizarán los datos del alumno.
     * @param alumnoModificado Alumno cuyos datos fueron modificados.
     * @throws IOException Si ocurre un error al escribir el archivo CSV.
     */
    public void modificarAlumnoEnCSV(String rutaArchivoCSV, Alumno alumnoModificado) throws IOException{
        ManejoCSV utilCSV = new ManejoCSV();
        ArrayList<String> lineasCSV = utilCSV.leerArchivoCSV(rutaArchivoCSV);
        
        
        //Llamado método auxiliar que actualiza datos alumno en archivo csv
        actualizarLineasAlumnoCSV(lineasCSV, alumnoModificado);


        utilCSV.escribirArchivoCSV(rutaArchivoCSV, lineasCSV);
    }
    
    
    
    
    /**
     * Elimina un alumno de la lista de alumnos y actualiza tanto el archivo CSV de alumnos
     * como el archivo CSV de asignaturas para reflejar este cambio.
     * Además, elimina al alumno de las asignaturas en memoria.
     * 
     * @param rutAlumno El RUT del alumno a eliminar.
     * @param listaAlumnos Lista de alumnos del sistema.
     * @param listaAsignaturas Lista de asignaturas donde puede estar inscrito el alumno.
     * @return true si el alumno fue eliminado correctamente de ambas fuentes (lista y CSV), false si no se encontró.
     * @throws IOException Si ocurre un error al leer o escribir en los archivos CSV.
     */
    public boolean eliminarAlumno(String rutAlumno, ArrayList<Alumno> listaAlumnos, ArrayList<Asignatura> listaAsignaturas) throws IOException {
        // Buscar y eliminar el alumno de la lista
        Alumno alumnoAEliminar = buscarAlumnoPorRut(rutAlumno, listaAlumnos);
        if (alumnoAEliminar == null) {
            return false;
        }
        listaAlumnos.remove(alumnoAEliminar);


        // Leer y actualizar el archivo CSV de alumnos
        ManejoCSV utilCSV = new ManejoCSV();
        ArrayList<String> lineasCSV = utilCSV.leerArchivoCSV("src/resource/alumnos.csv");
        boolean alumnoEncontrado = eliminarLineaAlumnoCSV(lineasCSV, rutAlumno);


        if (!alumnoEncontrado) {
            return false;
        }


        // Sobrescribir el archivo CSV de alumnos
        utilCSV.escribirArchivoCSV("src/resource/alumnos.csv", lineasCSV);


        // Eliminar el alumno del CSV de asignaturas
        boolean eliminadoDeAsignaturasCSV = eliminarAlumnoDeAsignaturasCSV(rutAlumno, "src/resource/asignaturas.csv");


        // Eliminar el alumno de la lista de asignaturas en memoria
        for (Asignatura asignatura : listaAsignaturas) {
            asignatura.eliminarAlumnoPorRut(rutAlumno);
        }


        return eliminadoDeAsignaturasCSV; // Retorna true si el alumno fue eliminado correctamente de ambas fuentes
    }
    
    
    
    
    /**
     * Elimina una línea correspondiente a un alumno en el archivo CSV de alumnos.
     * @param rutAlumno RUT del alumno a eliminar.
     * @param rutAlumno RUT del alumno a eliminar.
     * @return true si se encontró y eliminó la línea correspondiente al alumno, false si no se encontró.
     */
    private boolean eliminarLineaAlumnoCSV(List<String> lineasCSV, String rutAlumno) {
        for (int i = 0; i < lineasCSV.size(); i++) {
            String[] datos = lineasCSV.get(i).split(";", -1); // Preservar campos vacíos

            
            // Verificar si la línea contiene el RUT del alumno
            if (datos.length >= 4 && datos[3].equals(rutAlumno)) {
                lineasCSV.remove(i); // Eliminar la línea correspondiente al alumno
                return true; // Indicar que el alumno fue encontrado y eliminado
            }
        }
        
        
        return false; // No se encontró el alumno
    }

   

    
    /**
     * Elimina a un alumno del archivo CSV de asignaturas. Recorre las líneas del archivo CSV,
     * y elimina el RUT del alumno de las asignaturas en las que esté inscrito.
     * @param rutAlumno RUT del alumno a eliminar.
     * @param pathAsignaturasCSV pathAsignaturasCSV Ruta del archivo CSV de asignaturas.
     * @return true si el alumno fue eliminado de alguna asignatura en el CSV, false si no se encontró.
     * @throws IOException Si ocurre un error al leer o escribir en el archivo CSV.
     */
    public boolean eliminarAlumnoDeAsignaturasCSV(String rutAlumno, String pathAsignaturasCSV) throws IOException {
        ManejoCSV utilCSV = new ManejoCSV();
        ArrayList<String> lineasCSV = utilCSV.leerArchivoCSV(pathAsignaturasCSV);
        boolean alumnoEliminado = false;


        for (int i = 0; i < lineasCSV.size(); i++) {
            String[] datos = lineasCSV.get(i).split(";", -1);
            if (datos.length < 4) { // Aseguramos que hay al menos nombre, código, RUT profesor y un alumno
                continue;
            }


            StringBuilder lineaActualizada = new StringBuilder();
            for (int j = 0; j < 3; j++) { // Mantenemos los primeros tres elementos intactos
                lineaActualizada.append(datos[j]).append(";");
            }


            boolean rutEliminadoEnEstaAsignatura = false;
            for (int j = 3; j < datos.length; j++) {
                if (!datos[j].trim().equals(rutAlumno.trim())) {
                    lineaActualizada.append(datos[j]);
                    if (j < datos.length - 1) {
                        lineaActualizada.append(";");
                    }
                } else {
                    rutEliminadoEnEstaAsignatura = true;
                    alumnoEliminado = true;
                }
            }


            if (rutEliminadoEnEstaAsignatura) {
                // Eliminamos el último ';' si es necesario
                if (lineaActualizada.charAt(lineaActualizada.length() - 1) == ';') {
                    lineaActualizada.setLength(lineaActualizada.length() - 1);
                }
                lineasCSV.set(i, lineaActualizada.toString());
            }
        }


        if (alumnoEliminado) {
            utilCSV.escribirArchivoCSV(pathAsignaturasCSV, lineasCSV);
        }


        return alumnoEliminado;
    }
    
    
    
    
    /**
    * Método que valida si el alumno ha aprobado una asignatura en específico.
    * Recorre la lista de inscripciones del alumno y verifica si la asignatura 
    * (identificada por su código) ha sido aprobada.
    *
    * @param codBuscado Código de la asignatura que se desea validar.
    * @return true si el alumno ha aprobado la asignatura, false en caso contrario.
    */
    public boolean validarAsignaturaAprobada(String codBuscado){
        for (int i = 0; i < inscripciones.size(); i++)
        {
            Inscripcion auxInsc = (Inscripcion) inscripciones.get(i);
            if(auxInsc.getCodigoAsignatura().equals(codBuscado) && auxInsc.isAprobada())
                return true;
        }
        
        
        return false;
    }
    
    
    
    
    /**
     * Método que devuelve un String con todas las inscripciones del alumno.
     * @return String con la información de todas las inscripciones del alumno.
     */
    public String listarInscripciones(){
        String retornoString;
        retornoString = "";
        
        
        for(int i = 0 ; i < inscripciones.size() ; i++)
            retornoString += inscripciones.get(i).toString();
            
        
        return retornoString;
    }
    
    
    
    
    @Override
    public String toString() {
        return "Nombre alumno = " + this.getsNombre() + " " + this.getsApellido();
    }
    /******FIN ALUMNO******/
}