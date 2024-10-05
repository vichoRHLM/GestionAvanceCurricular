package gestionavancecurricular;

import java.io.*;
import java.util.*;

/**
 * La clase Asignatura en el sistema de gestión de avance curricular es 
 * responsable de manejar toda la información relacionada con las asignaturas 
 * y sus alumnos inscritos. Esta clase facilita la asignación de alumnos 
 * a asignaturas, el manejo de profesores asignados, y la administración de 
 * información relacionada con las asignaturas, como la exportación y 
 * modificación de datos en archivos CSV.
 */
public class Asignatura {
    /******ATRIBUTOS******/
    private String sNombre;
    private String sCodigo;
    private Profesor profesorAsignado;
    private ArrayList<Alumno> alAlumnosInscritos;
    /******FIN ATRIBUTOS******/
    
    
    /******CONSTRUCTORES******/
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
    
    
    public Asignatura(Asignatura a) {
        this.sNombre = a.getNombre();
        this.sCodigo = a.getsCodigo();
        this.profesorAsignado = a.getProfesorAsignado(); 
        this.alAlumnosInscritos = new ArrayList <>(a.getAlumnosInscritos()); 
    }
    
    
    public Asignatura() {
        this.sNombre = "";
        this.sCodigo = "";
        this.profesorAsignado = new Profesor();
        this.alAlumnosInscritos = new ArrayList <> ();
    }
    /******FIN CONSTRUCTORES******/

    
    /******SETTERS Y GETTERS******/
    public String getNombre() { return sNombre; }
    public void setNombre(String sNombre) { this.sNombre = sNombre; }

    public String getsCodigo() { return sCodigo; }
    public void setsCodigo(String sCodigo) { this.sCodigo = sCodigo; }

    public Profesor getProfesorAsignado() { return profesorAsignado; }
    public void setProfesorAsignado(Profesor profesorAsignado) { this.profesorAsignado = profesorAsignado; }

    /**
     * Devuelve un alumno inscrito en la asignatura según su RUT.
     * @param alumnoInscribir Objeto de tipo Alumno que se desea buscar en la lista de inscritos.
     * @return El alumno si está inscrito, null en caso contrario.
     */
    public Alumno getAlumnosInscritos(Alumno alumnoInscribir) { return buscarAlumnosInscritosPorRut(alumnoInscribir.getsRut()); }
    public ArrayList<Alumno> getAlumnosInscritos() { return new ArrayList<>(alAlumnosInscritos); }
    /******FIN SETTERS Y GETTERS******/
    
    
    /******METODOS******/
    /**
     * Setter que agrega un alumno al arreglo de <code>alAlumnosInscritos</code>.
     * Además se valida que el alumno no se encuentre repetido dentro de 
     * <code>alAlumnosInscritos</code>. (Sobrecargado).
     * @param alumnoInscribir  Objeto de tipo alumno que se desea agregar a <code>alAlumnosInscritos</code>.
     */
    public void setAlumnosInscritos(Alumno alumnoInscribir) {
        if (buscarAlumnosInscritosPorRut(alumnoInscribir.getsRut()) == null){
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
        Alumno alumno = buscarAlumnosInscritosPorRut(rutAlumno, listaAlumnos);
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
        Profesor profesorAsignar = new Profesor();
        profesorAsignar = profesorAsignar.buscarProfesorPorRut(listaProfes, rut);
        if(profesorAsignar != null){
            this.profesorAsignado = profesorAsignar;
            System.out.println("El profesor "+profesorAsignar.getsNombre()+" "+profesorAsignar.getsApellido()+" se ha asignado correctamente a su nueva asinatura");
        }
        else{
            this.profesorAsignado = null;
        }
    }
    
    
    
    
    /**
     * Método sobrecargado, que busca un Alumno según su rut.
     * @param rutAlumno Identificador unico del alumno.
     * @return Retorna un objeto de tipo Alumno si es que se encontro dentro de <code>alAlumnosInscritos</code>, si no retorna null.
     */
    private Alumno buscarAlumnosInscritosPorRut(String rutAlumno) {
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
    private Alumno buscarAlumnosInscritosPorRut(String rutAlumno, ArrayList<Alumno> listaAlumnos) {
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
     */
    public void agregarAlumnoPorConsola(Scanner lector, ArrayList<Alumno> listaAlumnos) {
        String rutNuevo;
        Alumno alumnoPorAgregar;

        try {
            do {
                System.out.println("Ingrese rut del alumno que desea agregar a la asignatura : ");
                rutNuevo = lector.nextLine();
                alumnoPorAgregar = buscarAlumnosInscritosPorRut(rutNuevo, listaAlumnos);

                if (alumnoPorAgregar == null) {
                    System.out.println("Alumno no encontrado, por favor vuelva a ingresar.");
                }

            } while (alumnoPorAgregar == null);

            // Intenta agregar el alumno y actualizar el archivo CSV
            setAlumnosInscritos(alumnoPorAgregar);
            actualizarAsignaturaCSVConAlumno(alumnoPorAgregar.getsRut(), "src\\resource\\asignaturas.csv");

        } catch (IOException e) {
            System.out.println("Error al acceder al archivo: " + e.getMessage());

        } catch (AsignaturaNoEncontradaException e) {
            System.out.println("No se encontró la asignatura con el código: " + sCodigo);
        }
    }


    
    
    /**
     * Actualiza el archivo CSV de asignaturas agregando el RUT de un nuevo alumno.
     * Si la asignatura es encontrada, se agrega el RUT al archivo.
     * @param rutAlumnoNuevo RUT del alumno a agregar en el archivo CSV.
     * @param rutaArchivoCSV Ruta del archivo CSV que se va a actualizar.
     * @throws IOException Si ocurre un error al escribir en el archivo.
     * @throws AsignaturaNoEncontradaException Si no se encuentra la asignatura en el archivo CSV.
     */
    public void actualizarAsignaturaCSVConAlumno(String rutAlumnoNuevo, String rutaArchivoCSV) throws IOException, AsignaturaNoEncontradaException { 
        ManejoCSV utilCSV = new ManejoCSV();
        ArrayList<String> lineasCSV = utilCSV.leerArchivoCSV(rutaArchivoCSV);
        boolean asignaturaEncontrada = actualizarLineasAsignaturaCSV(lineasCSV, rutAlumnoNuevo);
        
        if (!asignaturaEncontrada) {
            throw new AsignaturaNoEncontradaException(this.sCodigo);
        }
        
        utilCSV.escribirArchivoCSV(rutaArchivoCSV, lineasCSV);
    }


    
    
    /**
     * Actualiza las líneas del archivo CSV para agregar un nuevo alumno inscrito en la asignatura.
     * @param lineasCSV Lista de líneas del archivo CSV.
     * @param rutAlumnoNuevo RUT del alumno a agregar.
     * @return true si la asignatura fue encontrada y actualizada, false en caso contrario.
     */
    private boolean actualizarLineasAsignaturaCSV(List<String> lineasCSV, String rutAlumnoNuevo) {
        for (int i = 0; i < lineasCSV.size(); i++) {
            String[] datos = lineasCSV.get(i).split(";", -1); // Preservar campos vacíos

            if (datos.length < 3) {
                continue; // Saltar líneas mal formadas
            }

            String nombreAsignatura = datos[0];
            String codigoAsignatura = datos[1];
            String rutsAlumnos = datos[2];

            if (codigoAsignatura.equals(this.sCodigo)) {
                
                // Verificar si el campo de RUTs está vacío
                if (rutsAlumnos.isEmpty()) {
                    rutsAlumnos = rutAlumnoNuevo; // Si está vacío, agregamos el nuevo RUT
                } else {
                    // Convertir la cadena de RUTs a una lista para facilitar la manipulación
                    List<String> listaRuts = new ArrayList<>(Arrays.asList(rutsAlumnos.split(",")));

                    
                    // Verificar si el RUT ya existe en la lista
                    if (!listaRuts.contains(rutAlumnoNuevo)) {
                        listaRuts.add(rutAlumnoNuevo); // Agregar nuevo RUT si no está en la lista
                        rutsAlumnos = String.join(",", listaRuts); // Convertir la lista de vuelta a una cadena
                    }
                }

                // Actualizar la línea en el CSV
                lineasCSV.set(i, String.join(";", nombreAsignatura, codigoAsignatura, rutsAlumnos));
                return true; // Se encontró y actualizó la asignatura
            }
        }
        return false; // La asignatura no se encontró
    }

    
    
    
    /**
     * Elimina un alumno de la lista de inscritos de la asignatura según su RUT.
     * @param rutAlumno RUT del alumno a eliminar de la lista de inscritos.
     */
    public void eliminarAlumnoPorRut(String rutAlumno) {
        for (int i = 0; i < alAlumnosInscritos.size(); i++) {
            if (alAlumnosInscritos.get(i).getsRut().equals(rutAlumno)) {
                alAlumnosInscritos.remove(i);
                break; // Terminar el ciclo una vez que se elimine el alumno
            }
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
    
    
    
    
    /**
     * Busca una asignatura en la lista de asignaturas por su código.
     * @param codigoAsignatura Código de la asignatura a buscar.
     * @param listaAsignaturas Lista de asignaturas donde se realizará la búsqueda.
     * @return La asignatura encontrada o null si no se encuentra.
     */
    public Asignatura buscarAsignaturaPorCodigo(String codigoAsignatura, ArrayList<Asignatura> listaAsignaturas) {
        
        for (int i = 0; i<listaAsignaturas.size();i++) {
            Asignatura asignatura = listaAsignaturas.get(i);
            if (asignatura.getsCodigo().equals(codigoAsignatura)) {
                return asignatura;
            }
        }
        return null;
    }
    
    
    
    
    /**
     * Crea una nueva asignatura y la agrega a la lista de asignaturas y al archivo CSV.
     * Si la asignatura ya existe o si el profesor no se encuentra, no se realiza la operación.
     * @param nombreAsignatura Nombre de la asignatura.
     * @param codigoAsignatura Código único de la asignatura.
     * @param rutProfesor RUT del profesor que se asignará a la asignatura.
     * @param listaProfesores Lista de profesores disponibles.
     * @param listaAsignaturas Lista de asignaturas.
     * @return La nueva asignatura creada o null si hubo algún error.
     * @throws IOException Si ocurre un error al escribir en el archivo CSV.
     */
    public Asignatura agregarAsignatura(String nombreAsignatura, String codigoAsignatura, String rutProfesor, ArrayList<Profesor> listaProfesores, ArrayList<Asignatura> listaAsignaturas) throws IOException {

        // Verificar que los campos no estén vacíos o nulos
        if (nombreAsignatura == null || nombreAsignatura.trim().isEmpty() ||
            codigoAsignatura == null || codigoAsignatura.trim().isEmpty() ||
            rutProfesor == null || rutProfesor.trim().isEmpty()) {
            return null;  // Retornar null si algún campo es inválido
        }
        
        
        // Verificar si la asignatura ya existe
        Asignatura asignaturaExistente = buscarAsignaturaPorCodigo(codigoAsignatura, listaAsignaturas);
        if (asignaturaExistente != null) {
            return null; // La asignatura ya existe
        }

        
        // Buscar el profesor por RUT
        Profesor profesorAsignado = this.profesorAsignado.buscarProfesorPorRut(listaProfesores, rutProfesor);
        if (profesorAsignado == null) {
            return null; // No se encontró al profesor
        }

        
        // Crear nueva asignatura y asignar el profesor
        Asignatura nuevaAsignatura = new Asignatura(nombreAsignatura, codigoAsignatura, profesorAsignado);
        listaAsignaturas.add(nuevaAsignatura); // Agregar la asignatura a la lista

        
        // Escribir la nueva asignatura en el archivo CSV
        agregarProfesoraArchivoCSV("src\\resource\\asignaturas.csv", nuevaAsignatura);

        
        return nuevaAsignatura;  // Retornar la asignatura creada
    }

    
    
    
    /**
     * Método para actualizar el archivo CSV con la información de una nueva asignatura
     * @param pathArchivo Ruta del archivo CSV
     * @param asignatura Asignatura que se añadirá al archivo
     * @throws IOException En caso de error al escribir en el archivo
     */
    private void agregarProfesoraArchivoCSV(String pathArchivo, Asignatura asignatura) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathArchivo, true))) {
            bw.newLine();
            
            String linea = String.join(";",
                    asignatura.getNombre(),
                    asignatura.getsCodigo(),
                    asignatura.getProfesorAsignado().getsRut());
            
            bw.write(linea);
        }
    }

    
    
    /**
     * Modifica el nombre de una asignatura específica.
     * El método busca la asignatura correspondiente al código proporcionado, actualiza su nombre 
     * en la lista de asignaturas y también en el archivo CSV.
     *
     * @param codigoAsignatura Código de la asignatura que se desea modificar.
     * @param nuevoNombre Nuevo nombre que se asignará a la asignatura.
     * @param listaAsignaturas Lista de asignaturas donde se realizará la búsqueda de la asignatura.
     * @return true si la asignatura fue encontrada y modificada correctamente, false si no se encontró.
     * @throws IOException Si ocurre un error durante la lectura o escritura del archivo CSV.
     */
    public boolean modificarNombreAsignatura(String codigoAsignatura, String nuevoNombre, ArrayList<Asignatura> listaAsignaturas) throws IOException {
        // Buscar la asignatura a modificar
        Asignatura asignaturaModificar = buscarAsignaturaPorCodigo(codigoAsignatura, listaAsignaturas);
        if (asignaturaModificar == null) {
            return false; // No se encontro la asignatura
        }

        
        // Modificar solo el nombre de la asignatura
        asignaturaModificar.setNombre(nuevoNombre);

        
        // Actualizar el archivo CSV
        ManejoCSV utilCSV = new ManejoCSV();
        ArrayList<String> lineasCSV = utilCSV.leerArchivoCSV("src\\resource\\asignaturas.csv");
        
        
        for (int i = 0; i < lineasCSV.size(); i++) {
            
            String[] datos = lineasCSV.get(i).split(";", -1);
            
            if (datos[1].equals(codigoAsignatura)) {
                // Actualizar solo el nombre en el CSV, manteniendo el RUT del profesor
                lineasCSV.set(i, nuevoNombre + ";" + codigoAsignatura + ";" + datos[2] + ";" + datos[3] + ";");
                break;
            }
        }

        utilCSV.escribirArchivoCSV("src\\resource\\asignaturas.csv", lineasCSV);
        
        return true; // Nombre de asignatura modificado correctamente
    }
        
    
    
    
    /**
    * Método que obtiene una lista de alumnos aprobados en la asignatura actual.
    * Recorre la lista de alumnos proporcionada y verifica si han aprobado la 
    * asignatura actual (identificada por el código de la asignatura). 
    * Además, agrega el código de la asignatura a la lista de códigos aprobados.
    *
    * @param arrAlumn Lista de alumnos que se desea verificar.
    * @param codigosAsigAprobadas Lista que almacenará los códigos de las asignaturas que los alumnos han aprobado.
    * @return Lista de alumnos que han aprobado la asignatura actual.
    */
    public ArrayList<Alumno> obtenerAlumnosAprobados(ArrayList <Alumno> arrAlumn, ArrayList <String> codigosAsigAprobadas){
        ArrayList<Alumno> alumnosAprobados = new ArrayList<>();
        
        for(int i = 0; i < arrAlumn.size(); i++)
        {
            Alumno auxAlm = (Alumno) arrAlumn.get(i);
            if (auxAlm.validarAsignaturaAprobada(sCodigo)){
                alumnosAprobados.add(auxAlm);
                codigosAsigAprobadas.add(sCodigo);
            }
                
        }
        return alumnosAprobados;
    }
    
    
    
    /**
     * Elimina una asignatura tanto de la lista de asignaturas en memoria como del archivo CSV.
     * @param codigoAsignatura Código de la asignatura a eliminar.
     * @param listaAsignaturas Lista de asignaturas.
     * @return true si la asignatura fue eliminada correctamente, false si no se encontró.
     * @throws IOException Si ocurre un error al escribir en el archivo CSV.
     */
    public boolean eliminarAsignatura(String codigoAsignatura, ArrayList<Asignatura> listaAsignaturas) throws IOException {
        // Buscar y eliminar la asignatura de la lista
        Asignatura asignaturaAEliminar = buscarAsignaturaPorCodigo(codigoAsignatura, listaAsignaturas);
        if (asignaturaAEliminar == null) {
            return false; // No se encontró la asignatura
        }
        
        //Eliminar la asignatura de la lista de asignaturas
        listaAsignaturas.remove(asignaturaAEliminar);

        
        // Leer y actualizar el archivo CSV de asignaturas
        ManejoCSV utilCSV = new ManejoCSV();
        ArrayList<String> lineasCSV = utilCSV.leerArchivoCSV("src/resource/asignaturas.csv");

        
        // Eliminar la línea correspondiente a la asignatura del CSV
        boolean asignaturaEncontrada = eliminarLineaAsignaturaCSV(lineasCSV, codigoAsignatura);
        if (!asignaturaEncontrada) {
            return false; // Si no se encontró la asignatura en el CSV
        }
        

        // Sobrescribir el archivo CSV de asignaturas
        utilCSV.escribirArchivoCSV("src/resource/asignaturas.csv", lineasCSV);

        
        return true; // Asignatura eliminada correctamente
    }

    
    
    /**
     * Elimina una línea de una asignatura en el archivo CSV.
     * Recorre el archivo CSV de asignaturas buscando el código de la asignatura a eliminar.
     * Si se encuentra, se elimina la línea correspondiente a dicha asignatura.
     * @param lineasCSV Lista que contiene todas las líneas del archivo CSV.
     * @param codigoAsignatura Código de la asignatura que se desea eliminar.
     * @return true si la asignatura fue encontrada y eliminada, false si no se encuentra.
     */
    private boolean eliminarLineaAsignaturaCSV(ArrayList<String> lineasCSV, String codigoAsignatura) {
        for (int i = 0; i < lineasCSV.size(); i++) {
            String[] datos = lineasCSV.get(i).split(";", -1); // Preservar campos vacíos

            // Verificar si la línea contiene el código de la asignatura
            if (datos.length > 0 && datos[1].equals(codigoAsignatura)) {
                lineasCSV.remove(i); // Eliminar la línea correspondiente a la asignatura
                return true; // Indicar que la asignatura fue encontrada y eliminada
            }
        }
        return false; // No se encontró la asignatura
    }
    
    
    
    
    @Override
    public String toString() {
        return sCodigo + ", " + sNombre + "\n";
    }
    
    /******FIN METODOS******/
}