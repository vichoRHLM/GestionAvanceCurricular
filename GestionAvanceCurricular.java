package gestionavancecurricular;

import java.io.*;
import java.util.*;

public class GestionAvanceCurricular {
    public MallaCurricular mallaInf;

    public static void main(String[] args) throws ErrorDeLecturaArchivoException, ProfesorMalFormadoException, AlumnoMalFormadoException, FormatoAsignaturaInvalidoException{
        MallaCurricular mallaInf = new MallaCurricular();
        ArrayList<Profesor> listaProfes = new ArrayList<>();
        ArrayList<Asignatura> listaAsignaturas = new ArrayList<>();
        ArrayList<Alumno> alumnosAInscribir = new ArrayList<>();
        
        llenadoMallaCurricular(mallaInf, listaProfes, listaAsignaturas, alumnosAInscribir);
       
        menu(mallaInf, listaProfes, alumnosAInscribir);
    }

    public static void menu(MallaCurricular mc, ArrayList<Profesor> listaProfes, ArrayList<Alumno> alumnosAInscribir) throws ErrorDeLecturaArchivoException {
        Scanner lector = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("BIENVENIDO A LA GESTION DE TU AVANCE CURRICULAR, "
                    + "POR FAVOR SELECCIONE UNA OPCION");
            System.out.println("0.- FIN");
            System.out.println("1.- AGREGAR UN ALUMNO");
            System.out.println("2.- MOSTRAR LA MALLA CURRICULAR");
            System.out.println("3.- AGREGAR UNA ASIGNATURA A LA MALLA");
            System.out.println("Ingrese su opcion:");
            opcion = lector.nextInt();
            lector.nextLine();
            switch (opcion) {
                case 0 -> {
                    System.out.println("FIN DEL PROGRAMA");
                    break;
                }
                case 1 -> {
                    mc.menuParaAgregarAlumnoaAsignatura(lector, alumnosAInscribir);
                    break;
                }
                case 2 -> {
                    mc.mostrarMallaCurricular();
                    break;
                }
                case 3 -> {
                    mc.crearAsignaturaPorConsola(lector, listaProfes);
                    break;
                }
                default -> System.out.println("OPCION INCORRECTA, POR FAVOR INTENTE DE NUEVO");
            }
        } while (opcion != 0);
    }

    /**
     * Método que llena la malla curricular a partir de la lectura de archivos CSV.
     * Incluye lectura de profesores, alumnos y asignaturas desde archivos CSV.
     * @param mc
     * @param listaProfes
     * @param listaAsignaturas
     * @param alumnosAInscribir
     * @throws ErrorDeLecturaArchivoException
     * @throws ProfesorMalFormadoException
     * @throws FormatoAsignaturaInvalidoException
     * @throws AlumnoMalFormadoException
     * @throws java.io.IOException
     */
    public static void llenadoMallaCurricular(MallaCurricular mc, ArrayList<Profesor> listaProfes, ArrayList<Asignatura> listaAsignaturas, ArrayList<Alumno> alumnosAInscribir) throws ErrorDeLecturaArchivoException, ProfesorMalFormadoException, AlumnoMalFormadoException, FormatoAsignaturaInvalidoException {
        // Leer Profesores desde archivo CSV
        leerProfesoresDesdeCSV(listaProfes);
        
        // Leer Alumnos desde archivo CSV
        leerAlumnosDesdeCSV(alumnosAInscribir);
        
        
        // Leer Asignaturas desde archivo CSV
        listaAsignaturas = leerAsignaturasDesdeCSV("src\\resource\\asignaturas.csv", listaProfes, alumnosAInscribir);

        // Asignación de asignaturas a semestres
        ArrayList<Asignatura> semestre_1 = new ArrayList<>(listaAsignaturas.subList(0, 2));
        ArrayList<Asignatura> semestre_2 = new ArrayList<>(listaAsignaturas.subList(2, 3));
        ArrayList<Asignatura> semestre_3 = new ArrayList<>(listaAsignaturas.subList(3, 6));
        ArrayList<Asignatura> semestre_4 = new ArrayList<>(listaAsignaturas.subList(6, 10));
        ArrayList<Asignatura> semestre_5 = new ArrayList<>(listaAsignaturas.subList(10, 13));
        ArrayList<Asignatura> semestre_6 = new ArrayList<>(listaAsignaturas.subList(13, 17));
        ArrayList<Asignatura> semestre_7 = new ArrayList<>(listaAsignaturas.subList(17, 21));
        ArrayList<Asignatura> semestre_8 = new ArrayList<>(listaAsignaturas.subList(21, 23));

        // Leer Inscripciones de los Alumnos desde archivo CSV
        cargarInscripcionesDesdeCSV(alumnosAInscribir, listaAsignaturas);
        
        // Llenar malla curricular
        HashMap<String, ArrayList<Asignatura>> mallaCurricular = new HashMap<>();
        mallaCurricular.put("Sem1", semestre_1);
        mallaCurricular.put("Sem2", semestre_2);
        mallaCurricular.put("Sem3", semestre_3);
        mallaCurricular.put("Sem4", semestre_4);
        mallaCurricular.put("Sem5", semestre_5);
        mallaCurricular.put("Sem6", semestre_6);
        mallaCurricular.put("Sem7", semestre_7);
        mallaCurricular.put("Sem8", semestre_8);

        mc.setMallaCurricular(mallaCurricular);
    }

    /**
     * Método que lee los profesores desde un archivo CSV.
     * @param listaProfes
     * @throws ErrorDeLecturaArchivoException
     * @throws ProfesorMalFormadoException
     */
    public static void leerProfesoresDesdeCSV(ArrayList<Profesor> listaProfes) throws ErrorDeLecturaArchivoException,ProfesorMalFormadoException {
        try (BufferedReader brProfesores = new BufferedReader(new FileReader("src\\resource\\profesores.csv")))
        {
            String linea;
            while ((linea = brProfesores.readLine()) != null) {
                String[] datos = linea.split(";");
                
                if(datos.length != 5){
                    throw new ProfesorMalFormadoException(linea);
                }
                Profesor profesor = new Profesor(datos[0], datos[1], datos[2], datos[3], datos[4]);
                listaProfes.add(profesor);
            }
        }
        catch(IOException e){
            throw new ErrorDeLecturaArchivoException(e);
        }
    }

        
    
    /**
     * Método que lee los alumnos desde un archivo CSV.
     * @param listaAlumnos
     * @return Lista de alumnos.
     * @throws ErrorDeLecturaArchivoException
     * @throws AlumnoMalFormadoException
     */
    public static ArrayList<Alumno> leerAlumnosDesdeCSV(ArrayList<Alumno> listaAlumnos) throws ErrorDeLecturaArchivoException, AlumnoMalFormadoException {
        try (BufferedReader br = new BufferedReader(new FileReader("src\\resource\\alumnos.csv"))) 
        {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length != 5) {
                    throw new AlumnoMalFormadoException(linea);
                }
                Alumno alumno = new Alumno(datos[0], datos[1], datos[2], datos[3], datos[4]);
                listaAlumnos.add(alumno);
 
            }
        }
        catch(IOException e){
            throw new ErrorDeLecturaArchivoException(e);
        }
        return listaAlumnos;
    }

    /**
     * Método que lee las asignaturas desde un archivo CSV.
     * @param rutaArchivo Ruta del archivo CSV.
     * @param listaProfes Lista de profesores.
     * @param listaAlumnos Lista de alumnos.
     * @return Lista de asignaturas.
     * @throws ErrorDeLecturaArchivoException
     * @throws FormatoAsignaturaInvalidoException
     */
    public static ArrayList<Asignatura> leerAsignaturasDesdeCSV(String rutaArchivo, ArrayList<Profesor> listaProfes, ArrayList<Alumno> listaAlumnos) throws ErrorDeLecturaArchivoException, FormatoAsignaturaInvalidoException {
        ArrayList<Asignatura> listaAsignaturas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            int contadorProfesores = 0; // Para asignar profesores secuencialmente

            
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";", -1);
                if (datos.length >= 3) {
                    String nombreAsignatura = datos[0];
                    String codigoAsignatura = datos[1];
                    String[] rutsAlumnos = datos[2].split(","); // Los RUT de los alumnos inscritos

                    ArrayList<Alumno> alumnosAsignatura = new ArrayList<>();
                    
                    // Buscar y agregar los alumnos por su RUT usando ciclos for convencionales
                    for (int i = 0; i < rutsAlumnos.length; i++) {
                        String rut = rutsAlumnos[i];

                        for (int j = 0; j < listaAlumnos.size(); j++) {
                            Alumno alumno = (Alumno) listaAlumnos.get(j);

                            if (alumno.getsRut().equals(rut)) {
                                alumnosAsignatura.add(alumno);
                                break; // Dejar de buscar cuando se encuentra el alumno
                            }
                        }
                    }
                    
                    // Asignar un profesor de la lista de profesores de manera secuencial
                    Profesor profesorAsignado = listaProfes.get(contadorProfesores % listaProfes.size());
                    contadorProfesores++;

                    Asignatura asignatura = new Asignatura(nombreAsignatura, codigoAsignatura, profesorAsignado, alumnosAsignatura);
                    listaAsignaturas.add(asignatura);
                    
                } else {
                    throw new FormatoAsignaturaInvalidoException(linea);
                }
            } 
        } catch(IOException e){
            throw new ErrorDeLecturaArchivoException(e);
        }
        return listaAsignaturas;
    }
    
    /** ESTA FUNCIÓN DEVE SER ARREGLADA POR CLAUDIO TRONCOSO RUT: NOSE
     * Método que lee las asignaturas desde un archivo CSV.
     * @param listaAlumnos
     * @param listaAsignaturas
     * @throws  
     */
    public static void cargarInscripcionesDesdeCSV(ArrayList<Alumno> listaAlumnos, ArrayList<Asignatura> listaAsignaturas) throws ErrorDeLecturaArchivoException {
        try (BufferedReader br = new BufferedReader(new FileReader("src\\resource\\inscripciones.csv"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");

                if (datos.length == 5) {
                    String rutAlumno = datos[0];
                    String codigoAsignatura = datos[1];
                    double nota = Double.parseDouble(datos[2]); // Convertir la nota de String a double
                    boolean aprobada = nota >= 4.0; // Determinar si aprobó la asignatura basado en la nota
                    String semestre = datos[4];

                    // Buscar alumno por su RUT
                    Alumno alumno = buscarAlumnoPorRut(rutAlumno, listaAlumnos);
                    // Buscar asignatura por su código
                    Asignatura asignatura = buscarAsignaturaPorCodigo(codigoAsignatura, listaAsignaturas);

                    // Verificar si el alumno y la asignatura existen
                    if (alumno != null && asignatura != null) {
                        // Crear inscripción con nota y aprobación
                        Inscripcion inscripcion = new Inscripcion(alumno, asignatura, semestre);
                        inscripcion.setNota(nota);
                        inscripcion.setAprobada(aprobada);

                        // Añadir inscripción al alumno y asignatura
                        alumno.añadirNuevaInscripcion(inscripcion);
                    } else {
                        if (alumno == null) {
                            System.out.println("Alumno no encontrado con RUT: " + rutAlumno);
                        }
                        if (asignatura == null) {
                            System.out.println("Asignatura no encontrada con código: " + codigoAsignatura);
                        }
                    }
                } else {
                    System.out.println("Error en el formato de la inscripción: " + linea);
                }
            }
        } catch (IOException e) {
            throw new ErrorDeLecturaArchivoException(e);
        }
    }
    
    /**
     * 
     * @param rut
     * @param listaAlumnos
     * @return 
     */
    private static Alumno buscarAlumnoPorRut(String rut, ArrayList<Alumno> listaAlumnos) {
        for (int i = 0; i < listaAlumnos.size();i++) {
            Alumno alumn = (Alumno)listaAlumnos.get(i);
            if (alumn.getsRut().equals(rut)) {
                return alumn;
            }
        }
        return null;
    }

    /**
     * 
     * @param codigo
     * @param listaAsignaturas
     * @return 
     */
    private static Asignatura buscarAsignaturaPorCodigo(String codigo, ArrayList<Asignatura> listaAsignaturas) {
        for (int i = 0; i < listaAsignaturas.size();i++) {
            Asignatura asig = (Asignatura) listaAsignaturas.get(i);
            if (asig.getsCodigo().equals(codigo)) {
                return asig;
            }
        }
        return null;
    }
}