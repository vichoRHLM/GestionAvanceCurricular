package gestionavancecurricular;

import java.io.*;
import java.util.*;
import javax.swing.JFrame;

/**
 * Clase que inicia el fluyo del proyecto e inicia los datos base del sistema. 
 * Al poseer el método <code>main</code> la clase <code>GestionAvanceCurricular</code> 
 * se vuelve la main class del proyecto. Por otro lado tenemos que los datos 
 * base del sistema son iniciados principalmente por 
 * <code>llenadoMallaCurricular</code>
 * @author Claudio Troncoso 
 * @author Vicente Bravo
 * @author Sebastian Espinoza
 */
public class GestionAvanceCurricular {
    /******ATRIBUTOS******/
    public MallaCurricular mallaInf;
    /******FIN ATRIBUTOS******/
    
    
    /******METODO MAIN******/
    public static void main(String[] args) throws AsignaturaNoEncontradaException {
        
        MallaCurricular mallaInf = new MallaCurricular();
        ArrayList<Profesor> listaProfes = new ArrayList<>();
        ArrayList<Asignatura> listaAsignaturas = new ArrayList<>();
        ArrayList<Alumno> alumnosAInscribir = new ArrayList<>();
        

        try (BufferedReader brProfesores = new BufferedReader(new FileReader("src\\resource\\profesores.csv"));
             BufferedReader brAlumnos = new BufferedReader(new FileReader("src\\resource\\alumnos.csv"));
             BufferedReader brAsignaturas = new BufferedReader(new FileReader("src\\resource\\asignaturas.csv"));
             BufferedReader brInscripciones = new BufferedReader(new FileReader("src\\resource\\inscripciones.csv"));
             BufferedReader brAlumnAprobados = new BufferedReader(new FileReader("src\\resource\\alumnos_aprobados.csv"))){  

            llenadoMallaCurricular(brProfesores, brAlumnos, brAsignaturas, brInscripciones, mallaInf, listaProfes, listaAsignaturas, alumnosAInscribir);
            menu(mallaInf, listaProfes, alumnosAInscribir, listaAsignaturas, brProfesores, brAlumnos, brAsignaturas, brInscripciones, brAlumnAprobados);
            
        } catch (ErrorDeLecturaArchivoException e){
            System.err.println("Error al leer archivo: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
        }
    }
    /******FIN METODO MAIN******/
    
    

    /******METODOS******/
    /**
    * Este método representa el menú principal del sistema de información de avance curricular.
    * Se despliegan las opciones que el usuario puede seleccionar, tales como gestionar la malla 
    * curricular, abrir ventanas adicionales, o finalizar el programa.
    * 
    * @param mc La malla curricular que gestiona las asignaturas y los alumnos.
    * @param listaProfes Lista de profesores leída del archivo CSV.
    * @param alumnosAInscribir Lista de alumnos leída del archivo CSV.
    * @param listaAsignaturas Lista de asignaturas leída del archivo CSV.
    * @param brProfesores BufferedReader para leer el archivo CSV de profesores.
    * @param brAlumnos BufferedReader para leer el archivo CSV de alumnos.
    * @param brAsignaturas BufferedReader para leer el archivo CSV de asignaturas.
    * @param brInscripciones BufferedReader para leer el archivo CSV de inscripciones.
    * @param brAlumnAprobados BufferedReader para leer el archivo CSV de alumnos aprobados.
    * @throws ErrorDeLecturaArchivoException Si ocurre un error durante la lectura de algún archivo.
    * @throws IOException Si ocurre un error de entrada/salida.
    * @throws AsignaturaNoEncontradaException Si una asignatura no es encontrada en el sistema.
    */
    public static void menu(MallaCurricular mc, ArrayList<Profesor> listaProfes, ArrayList<Alumno> alumnosAInscribir, ArrayList<Asignatura> listaAsignaturas, BufferedReader brProfesores, BufferedReader brAlumnos, BufferedReader brAsignaturas, BufferedReader brInscripciones, BufferedReader brAlumnAprobados) throws ErrorDeLecturaArchivoException, IOException, AsignaturaNoEncontradaException{
        Scanner lector = new Scanner(System.in);
        int opcion;
        
        
        do {
            
            System.out.println("BIENVENIDO AL SISTEMA DE INFORMACION \n");
            System.out.println("OPCIONES : ");
            System.out.println("0.- FIN");
            System.out.println("1.- GESTIONAR MALLA CURRICULAR (SIA 2.4) (SIA 1.8)");
            System.out.println("2.- MENU DE VENTANAS");
            System.out.println("POR FAVOR SELECCIONE UNA OPCION : ");
            
            
            opcion = lector.nextInt(); 
            
            
            switch (opcion) {
                
                case 0 -> {
                   
                    System.out.println("FIN DEL PROGRAMA");
                    break;
                    
                }
                
                
                case 1 -> {
                    
                    //AGREGAR A LA 2DA COLECCIÓN ANIDADA
                    do {
                        
                        System.out.println("BIENVENIDO A LA GESTION DE TU AVANCE CURRICULAR, " + "POR FAVOR SELECCIONE UNA OPCION");
                        System.out.println("0.- FIN");
                        System.out.println("1.- AGREGAR UN ALUMNO");
                        System.out.println("2.- MOSTRAR LA MALLA CURRICULAR");
                        System.out.println("3.- AGREGAR UNA ASIGNATURA A LA MALLA");
                        System.out.println("4.- MODIFICAR UNA ASIGNATURA DE LA MALLA");
                        System.out.println("5.- ELIMINAR ASIGNATURA DE LA MALLA");
                        System.out.println("6.- REPORTAR Y MOSTRAR ALUMNOS APROBADOS DE LAS ASIGNATURAS");
                        System.out.println("Ingrese su opcion: ");
                        opcion = lector.nextInt();
                        lector.nextLine();
                        
                        switch (opcion) {
                            case 0 -> {
                                
                                System.out.println("FIN DE GESTION AVANCE CURRICULAR");
                                System.out.println("FIN DEL PROGRAMA");
                                break;
                                
                            }
                            
                            
                            case 1 -> {
                                
                                //AGREGAR A LA 2DA COLECCIÓN ANIDADA
                                mc.menuParaAgregarAlumnoaAsignatura(lector, alumnosAInscribir);
                                
                                break;
                            }
                            
                            
                            case 2 -> {
                                
                                //MOSTRAR LA 2DA COLECCIÓN ANIDADA
                                mc.mostrarMallaCurricular();
                                break;
                                
                            }
                            
                            
                            case 3 -> {
                                
                                //CREAR UNA ASIGNATURA NUEVA PARA LA 2DA COLECCIÓN ANIDADA
                                mc.crearAsignaturaPorConsola(lector, listaProfes);
                                break;
                                
                            }
                            
                            
                            case 4 -> {
                                
                                //MODIFICAR UNA ASIGNATURA PARA LA 2DA COLECCIÓN ANIDADA
                                mc.modificarAsignatura(lector, listaAsignaturas);
                                break;    
                                
                            }
                            
                            
                            case 5 -> {
                                
                                //ELIMINAR UNA ASIGNATURA PARA LA 2DA COLECCIÓN ANIDADA
                                mc.eliminarAsignaturaPorCodigo(lector, listaAsignaturas);
                                llenadoMallaCurricular(brProfesores, brAlumnos, brAsignaturas, brInscripciones, mc, listaProfes, listaAsignaturas, alumnosAInscribir);
                                break;    
                                
                            }
                            
                            
                            case 6 -> {
                                
                                //REPORTAR ALUMNOS APROBADOS (SIA 2.10)
                                mc.reportarAlumnosAprobadosACSV(alumnosAInscribir);
                                mc.mostrarAlumnosAprobadosPorAsignatura(brAlumnAprobados);
                                
                            }
                            

                            default -> System.out.println("OPCION INCORRECTA, POR FAVOR INTENTE DE NUEVO");
                            
                            
                        }
                        
                    } while (opcion != 0);
                    
                    break;
                }
                
                
                case 2 -> {
                    
                    java.awt.EventQueue.invokeLater(() -> {
                         VentanaPrincipal menuPrincipal = new VentanaPrincipal(mc, listaProfes, listaAsignaturas, alumnosAInscribir);
                         menuPrincipal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                         menuPrincipal.setVisible(true);
                     });
                    break;
                    
                }
                
                
                default -> System.out.println("OPCION INCORRECTA, POR FAVOR INTENTE DE NUEVO");
                
            }
            
            
        } while(opcion != 0 && opcion != 2);
    }

    
    
    
    /**
     * Método que llena la malla curricular a partir de la lectura de archivos CSV.
     * Incluye lectura de profesores, alumnos y asignaturas desde archivos CSV.
     * @param brProfesores
     * @param brAlumnos
     * @param brAsignaturas
     * @param brInscripciones
     * @param mc
     * @param listaProfes
     * @param listaAsignaturas
     * @param alumnosAInscribir
     * @throws ErrorDeLecturaArchivoException
     * @throws java.io.IOException
     */
    public static void llenadoMallaCurricular(BufferedReader brProfesores, BufferedReader brAlumnos, BufferedReader brAsignaturas, BufferedReader brInscripciones,
            MallaCurricular mc, ArrayList<Profesor> listaProfes, ArrayList<Asignatura> listaAsignaturas, ArrayList<Alumno> alumnosAInscribir) 
            throws ErrorDeLecturaArchivoException, IOException{

        try{
            // Leer Profesores desde el BufferedReader
            leerProfesoresDesdeCSV(brProfesores, listaProfes);
            
        }catch(ProfesorMalFormadoException e){
            System.err.println("Error en el formato de profesor: " + e.getMessage());
        }
        
        
        try{
            // Leer Alumnos desde el BufferedReader
            leerAlumnosDesdeCSV(brAlumnos, alumnosAInscribir);
        }catch(AlumnoMalFormadoException e){
            System.err.println("Error en el formato de alumno: " + e.getMessage());
        }
        
        
        try{// Leer Asignaturas desde el BufferedReader
            leerAsignaturasDesdeCSV(brAsignaturas, listaProfes, alumnosAInscribir, listaAsignaturas);
        }catch(FormatoAsignaturaInvalidoException e){
            System.err.println("Error en el formato de asignatura: " + e.getMessage());
        }
        
        
        try{
            //Leer Inscripciones desde el BufferedReader
            cargarInscripcionesDesdeCSV(alumnosAInscribir, listaAsignaturas,brInscripciones);
        }catch(InscripcionMalFormadaException | NotaInvalidaException e){
            System.err.println("Error en el formato de inscripción: " + e.getMessage());
        }
        
 
        // Asignación de asignaturas a semestres
        ArrayList<Asignatura> semestre_1 = new ArrayList<>();
        ArrayList<Asignatura> semestre_2 = new ArrayList<>();
        ArrayList<Asignatura> semestre_3 = new ArrayList<>();
        ArrayList<Asignatura> semestre_4 = new ArrayList<>();
        ArrayList<Asignatura> semestre_5 = new ArrayList<>();
        ArrayList<Asignatura> semestre_6 = new ArrayList<>();
        ArrayList<Asignatura> semestre_7 = new ArrayList<>();
        ArrayList<Asignatura> semestre_8 = new ArrayList<>();
        
        // Validar la cantidad de asignaturas antes de hacer sublistas
        int totalAsignaturas = listaAsignaturas.size();

        
        // Verificar si hay suficientes asignaturas para cada semestre
        if (totalAsignaturas >= 2) {
            semestre_1 = new ArrayList<>(listaAsignaturas.subList(0, 2));
        }
        else
        {
            System.out.println("SEMESTRE 1 SIN DATOS. EL NUMERO DE ASIGNATURAS ES DE 23.");
            System.out.println("        VERIFIQUE EL ARCHIVO CSV ASIGNATURAS.");
        }
        
        
        if (totalAsignaturas >= 3) {
            semestre_2 = new ArrayList<>(listaAsignaturas.subList(2, 3));
        }
        else
        {
            System.out.println("SEMESTRE 2 SIN DATOS. EL NUMERO DE ASIGNATURAS ES DE 23.");
            System.out.println("        VERIFIQUE EL ARCHIVO CSV ASIGNATURAS.");
        }
        
        
        if (totalAsignaturas >= 6) {
            semestre_3 = new ArrayList<>(listaAsignaturas.subList(3, 6));
        }
        else
        {
            System.out.println("SEMESTRE 3 SIN DATOS. EL NUMERO DE ASIGNATURAS ES DE 23.");
            System.out.println("        VERIFIQUE EL ARCHIVO CSV ASIGNATURAS.");
        }
        
        
        if (totalAsignaturas >= 10) {
            semestre_4 = new ArrayList<>(listaAsignaturas.subList(6, 10));
        }
        else
        {
            System.out.println("SEMESTRE 4 SIN DATOS. EL NUMERO DE ASIGNATURAS ES DE 23.");
            System.out.println("        VERIFIQUE EL ARCHIVO CSV ASIGNATURAS.");
        }
        
        
        if (totalAsignaturas >= 13) {
            semestre_5 = new ArrayList<>(listaAsignaturas.subList(10, 13));
        }
        else
        {
            System.out.println("SEMESTRE 5 SIN DATOS. EL NUMERO DE ASIGNATURAS ES DE 23.");
            System.out.println("        VERIFIQUE EL ARCHIVO CSV ASIGNATURAS.");
        }
        
        
        if (totalAsignaturas >= 17) {
            semestre_6 = new ArrayList<>(listaAsignaturas.subList(13, 17));
        }
        else
        {
            System.out.println("SEMESTRE 6 SIN DATOS. EL NUMERO DE ASIGNATURAS ES DE 23.");
            System.out.println("        VERIFIQUE EL ARCHIVO CSV ASIGNATURAS.");
        }
        
        
        if (totalAsignaturas >= 21) {
            semestre_7 = new ArrayList<>(listaAsignaturas.subList(17, 21));
        }
        else
        {
            System.out.println("SEMESTRE 7 SIN DATOS. EL NUMERO DE ASIGNATURAS ES DE 23.");
            System.out.println("        VERIFIQUE EL ARCHIVO CSV ASIGNATURAS.");
        }
        
        
        if (totalAsignaturas >= 23) {
            semestre_8 = new ArrayList<>(listaAsignaturas.subList(21, 23));
        }
        else
        {
            System.out.println("SEMESTRE 8 SIN DATOS. EL NUMERO DE ASIGNATURAS ES DE 23.");
            System.out.println("        VERIFIQUE EL ARCHIVO CSV ASIGNATURAS.");
        }


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
     * @param brProfesores
     * @throws ErrorDeLecturaArchivoException
     * @throws ProfesorMalFormadoException
     * @throws java.io.IOException
     */
    public static void leerProfesoresDesdeCSV(BufferedReader brProfesores, ArrayList<Profesor> listaProfes) throws ErrorDeLecturaArchivoException, ProfesorMalFormadoException, IOException {
        String linea;
        while ((linea = brProfesores.readLine()) != null) {
            String[] datos = linea.split(";");
            
            if (datos.length != 5) {
                throw new ProfesorMalFormadoException(linea);
            }
            
            Profesor profesor = new Profesor(datos[0], datos[1], datos[2], datos[3], datos[4]);
            listaProfes.add(profesor);
        }
    }


        
    
    /**
     * Método que lee los alumnos desde un archivo CSV.
     * @param listaAlumnos
     * @param brAlumnos
     * @throws ErrorDeLecturaArchivoException
     * @throws AlumnoMalFormadoException
     * @throws java.io.IOException
     */
    public static void leerAlumnosDesdeCSV(BufferedReader brAlumnos, ArrayList<Alumno> listaAlumnos) throws ErrorDeLecturaArchivoException, AlumnoMalFormadoException, IOException {
        String linea;
        while ((linea = brAlumnos.readLine()) != null) {
            String[] datos = linea.split(";");
            
            if (datos.length != 5) {
                throw new AlumnoMalFormadoException(linea);
            }
            
            Alumno alumno = new Alumno(datos[0], datos[1], datos[2], datos[3], datos[4]);
            listaAlumnos.add(alumno);
        }
    }

    
    

    /**
     * Método que lee las asignaturas desde un archivo CSV.
     * @param brAsignaturas
     * @param listaProfes Lista de profesores.
     * @param listaAlumnos Lista de alumnos.
     * @param listaAsign
     * @throws ErrorDeLecturaArchivoException
     * @throws FormatoAsignaturaInvalidoException
     * @throws java.io.IOException
     */
    public static void leerAsignaturasDesdeCSV(BufferedReader brAsignaturas, ArrayList<Profesor> listaProfes, ArrayList<Alumno> listaAlumnos, ArrayList<Asignatura> listaAsign) throws ErrorDeLecturaArchivoException, FormatoAsignaturaInvalidoException, IOException {
        String linea;

        while ((linea = brAsignaturas.readLine()) != null) {
            
            String[] datos = linea.split(";", -1);
            
            if (datos.length >= 3) {
                String nombreAsignatura = datos[0];
                String codigoAsignatura = datos[1];
                String rutProfesor = datos[2];
                String[] rutsAlumnos = datos[3].split(",");

                // Buscar el profesor por su RUT
                Profesor profesorAsignado = new Profesor();
                profesorAsignado = profesorAsignado.buscarProfesorPorRut(listaProfes, rutProfesor);
                        
                if (profesorAsignado != null) { 
                    //Lista de alumnos inscritos en la asignatura
                    ArrayList<Alumno> alumnosAsignatura = new ArrayList<>();
                    
                    
                    for (int i = 0; i< rutsAlumnos.length;i++) {
                        String rut = rutsAlumnos[i];
                        Alumno alumno = new Alumno(); 
                        alumno = alumno.buscarAlumnoPorRut(rut, listaAlumnos);
                        if (alumno != null) {
                            alumnosAsignatura.add(alumno);
                        }
                        
                        
                    }
                    
                    Asignatura asignatura = new Asignatura(nombreAsignatura, codigoAsignatura, profesorAsignado, alumnosAsignatura);
                    listaAsign.add(asignatura);
                    
                    // Asignar la asignatura al profesor
                    profesorAsignado.asignarAsignatura(asignatura); 
                    
                } else {
                    System.out.println("Profesor : " + rutProfesor + ", no encontrado");
                }
                
            } else {
                throw new FormatoAsignaturaInvalidoException(linea);
            }
        }
    }

    
    
    
    /**
    * Método que carga las inscripciones de alumnos desde un archivo CSV y las asigna a los alumnos y asignaturas.
    * El archivo CSV debe tener el formato: 'RutAlumno;CodigoAsignatura;Nota;Aprobada;Semestre'.
    * Si hay algún error en la lectura del archivo o los datos, se arrojan excepciones apropiadas.
    *
    * @param listaAlumnos Lista de alumnos.
    * @param listaAsignaturas Lista de asignaturas.
    * @param brInscripciones BufferedReader para leer el archivo CSV de inscripciones.
    * @throws IOException Si ocurre un error de entrada/salida al leer el archivo.
    * @throws InscripcionMalFormadaException Si el formato de la inscripción es incorrecto.
    * @throws NotaInvalidaException Si la nota de una inscripción es inválida (fuera del rango permitido).
    */
    public static void cargarInscripcionesDesdeCSV(ArrayList<Alumno> listaAlumnos, ArrayList<Asignatura> listaAsignaturas, BufferedReader brInscripciones) 
        throws IOException, InscripcionMalFormadaException, NotaInvalidaException {
        String linea;
        int numeroLinea = 0;
        
        while ((linea = brInscripciones.readLine()) != null) {
            numeroLinea++;
            String[] datos = linea.split(";");
            
            
            if (datos.length != 5) {
                throw new InscripcionMalFormadaException("Línea " + numeroLinea + ": " + linea);
            }
            
            
            String rutAlumno = datos[0];
            String codigoAsignatura = datos[1];
            String notaStr = datos[2];
            String semestre = datos[4];

            
            // Verificación de que la nota es numérica y válida
            double nota = Double.parseDouble(notaStr);
            if (nota < 0.0 || nota > 7.0) {
                throw new NotaInvalidaException("Línea " + numeroLinea + ": Nota fuera de rango (1.0-7.0): " + nota);
            }

            
            boolean aprobada = nota >= 4.0;
            
            Alumno alumno = new Alumno();
            alumno = alumno.buscarAlumnoPorRut(rutAlumno, listaAlumnos);

            
            if (alumno == null) {
                throw new InscripcionMalFormadaException("Línea " + numeroLinea + ": Alumno no encontrado -> " + rutAlumno);
            }


            Asignatura asignatura = new Asignatura();
            asignatura = asignatura.buscarAsignaturaPorCodigo(codigoAsignatura, listaAsignaturas);

            
            if (asignatura == null) {
                throw new InscripcionMalFormadaException("Línea " + numeroLinea + ": Asignatura no encontrada -> " + codigoAsignatura);
            }

            
            Inscripcion inscripcion = new Inscripcion(alumno, asignatura, semestre);
            inscripcion.setNota(nota);
            inscripcion.setAprobada(aprobada);
            alumno.añadirNuevaInscripcion(inscripcion);
        }
    }
    /******FIN METODOS******/
}