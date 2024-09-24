package gestionavancecurricular;

import java.io.*;
import java.util.*;

public class GestionAvanceCurricular {
    public MallaCurricular mallaInf;

    public static void main(String[] args) {
        MallaCurricular mallaInf = new MallaCurricular();
        ArrayList<Profesor> listaProfes = new ArrayList<>();
        ArrayList<Asignatura> listaAsignaturas = new ArrayList<>();
        
        try {
            llenadoMallaCurricular(mallaInf, listaProfes, listaAsignaturas);
        } catch (IOException e) {
            System.out.println("Error al leer los archivos: " + e.getMessage());
        }

        menu(mallaInf, listaProfes);
    }

    public static void menu(MallaCurricular mc, ArrayList<Profesor> listaProfes) {
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
                    mc.menuParaAgregarAlumnoaAsignatura(lector);
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
     */
    public static void llenadoMallaCurricular(MallaCurricular mc, ArrayList<Profesor> listaProfes, ArrayList<Asignatura> listaAsignaturas) throws IOException {
        // Leer Profesores desde archivo CSV
        leerProfesoresDesdeCSV(listaProfes);

        // Leer Alumnos desde archivo CSV
        ArrayList<Alumno> alumnosAInscribir = leerAlumnosDesdeCSV("src\\resource\\alumnos.csv");
        
         for (int i = 0; i < alumnosAInscribir.size(); i++)
        {
            System.out.println(alumnosAInscribir.get(i).toString());
        }

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
     * @throws IOException
     */
    public static void leerProfesoresDesdeCSV(ArrayList<Profesor> listaProfes) throws IOException {
        try (BufferedReader brProfesores = new BufferedReader(new FileReader("src\\resource\\profesores.csv"))) {
            String linea;
            while ((linea = brProfesores.readLine()) != null) {
                String[] datos = linea.split(";");
                Profesor profesor = new Profesor(datos[0], datos[1], datos[2], datos[3], datos[4]);
                listaProfes.add(profesor);
            }
        }
    }

        
    
    /**
     * Método que lee los alumnos desde un archivo CSV.
     * @param rutaArchivo Ruta del archivo CSV.
     * @return Lista de alumnos.
     * @throws IOException
     */
    public static ArrayList<Alumno> leerAlumnosDesdeCSV(String rutaArchivo) throws IOException {
        ArrayList<Alumno> listaAlumnos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length == 5) {
                    Alumno alumno = new Alumno(datos[0], datos[1], datos[2], datos[3], datos[4]);
                    listaAlumnos.add(alumno);
                } else {
                    System.out.println("Error en formato de alumno: " + linea);
                }
            }
        }
        return listaAlumnos;
    }

    /**
     * Método que lee las asignaturas desde un archivo CSV.
     * @param rutaArchivo Ruta del archivo CSV.
     * @param listaProfes Lista de profesores.
     * @param listaAlumnos Lista de alumnos.
     * @return Lista de asignaturas.
     * @throws IOException
     */
    public static ArrayList<Asignatura> leerAsignaturasDesdeCSV(String rutaArchivo, ArrayList<Profesor> listaProfes, ArrayList<Alumno> listaAlumnos) throws IOException {
        ArrayList<Asignatura> listaAsignaturas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            int contadorProfesores = 0; // Para asignar profesores secuencialmente
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length == 2) {
                    String nombreAsignatura = datos[0];
                    String codigoAsignatura = datos[1];

                    // Asignar un profesor de la lista de profesores de manera secuencial
                    Profesor profesorAsignado = listaProfes.get(contadorProfesores % listaProfes.size());
                    contadorProfesores++;

                    Asignatura asignatura = new Asignatura(nombreAsignatura, codigoAsignatura, profesorAsignado, listaAlumnos);
                    listaAsignaturas.add(asignatura);
                } else {
                    System.out.println("Error en formato de asignatura: " + linea);
                }
            }
        }
        return listaAsignaturas;
    }
}
