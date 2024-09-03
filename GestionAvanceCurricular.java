package gestionavancecurricular;


import java.util.*;

public class GestionAvanceCurricular {
    // Declarar listas de listas de asignaturas
    static MallaCurricular mc;
    
    
    public static void main(String[] args) {
        
        mc = new MallaCurricular();
        
        llenadoMallaCurricular();
        menu();
        
        
        
        
    }
    
    
    public static void llenadoMallaCurricular() {                     
        //Profesores
        Profesor profe_1 = new Profesor("Ricardo", "Guzman", "21/03/1980", "14203819-5", "RicardGuz.man@gmail.com");
        Profesor profe_2 = new Profesor("Daniel", "Prieto", "03/07/1973", "12361710-9", "D.prieto27@gmail.com");
        Profesor profe_3 = new Profesor("Pepe", "Poblete", "16/12/1985", "13670114-2", "pobletePepeStyle@gmail.com");
        Profesor profe_4 = new Profesor("Franco", "Maturana", "10/09/1976", "14702535-0", "FrancoMatLPTF@gmail.com");
        Profesor profe_5 = new Profesor("Richard", "Rios", "30/08/1968", "10824100-0", "richardHsgRios7@gmail.com");
        Profesor profe_6 = new Profesor("Sergio", "Opazo", "25/||/1991", "18409443-8", "opazo.sergio.l@gmail.com");
        Profesor profe_7 = new Profesor("Pablo", "Saldivia", "02/01/1988", "17132687-4", "PbloSaldi2029@gmail.com");
        Profesor profe_8 = new Profesor("Armando", "Solari", "07/02/1977", "13103988-6", "solariArmandoRHLM@gmail.com");
        Profesor profe_9 = new Profesor("Ernesto", "Morales", "19/05/1995", "19014209-2", "ErnestoMoralesMora177@gmail.com");
        Profesor profe_10 = new Profesor("Moises", "Gonzales", "14/04/1979", "14678202-3", "MoiGonzaHttp@gmail.com");
        
        //Alumnos
        Alumno alumno_0 = new Alumno("Sebastian", "Espinoza", "04/10/2004", "21672303-1", "sebax7p0@gmail.com");
        Alumno alumno_1 = new Alumno("Juan", "Pérez", "12/01/2000", "12.345.678-9", "juan.perez@example.com");
        Alumno alumno_2 = new Alumno("María", "Gómez", "23/02/2001", "22.345.678-1", "maria.gomez@example.com");
        Alumno alumno_3 = new Alumno("Carlos", "Rodríguez", "15/03/1999", "13.234.567-8", "carlos.rodriguez@example.com");
        Alumno alumno_4 = new Alumno("Ana", "Martínez", "04/04/2002", "14.345.678-2", "ana.martinez@example.com");
        Alumno alumno_5 = new Alumno("Luis", "Hernández", "30/05/1998", "15.345.678-3", "luis.hernandez@example.com");
        Alumno alumno_6 = new Alumno("Sofía", "López", "18/06/2000", "16.345.678-4", "sofia.lopez@example.com");
        Alumno alumno_7 = new Alumno("David", "González", "22/07/1997", "17.345.678-5", "david.gonzalez@example.com");
        Alumno alumno_8 = new Alumno("Isabella", "Sánchez", "09/08/2001", "18.345.678-6", "isabella.sanchez@example.com");
        Alumno alumno_9 = new Alumno("Jorge", "Díaz", "14/09/1996", "19.345.678-7", "jorge.diaz@example.com");
        Alumno alumno_10 = new Alumno("Camila", "Fernández", "21/10/1998", "20.345.678-8", "camila.fernandez@example.com");
        Alumno alumno_11 = new Alumno("Miguel", "Torres", "11/11/1995", "21.345.678-9", "miguel.torres@example.com");
        Alumno alumno_12 = new Alumno("Valentina", "Ramírez", "01/12/1997", "22.345.678-1", "valentina.ramirez@example.com");
        Alumno alumno_13 = new Alumno("Pedro", "Flores", "25/01/2000", "23.345.678-2", "pedro.flores@example.com");
        Alumno alumno_14 = new Alumno("Marta", "Morales", "07/02/1999", "24.345.678-3", "marta.morales@example.com");
        Alumno alumno_15 = new Alumno("Andrés", "Ortiz", "29/03/2002", "25.345.678-4", "andres.ortiz@example.com");
        Alumno alumno_16 = new Alumno("Laura", "Castillo", "16/04/1998", "26.345.678-5", "laura.castillo@example.com");
        Alumno alumno_17 = new Alumno("Francisco", "Vega", "08/05/2001", "27.345.678-6", "francisco.vega@example.com");
        Alumno alumno_18 = new Alumno("Paula", "Mendoza", "13/06/1997", "28.345.678-7", "paula.mendoza@example.com");
        Alumno alumno_19 = new Alumno("Ricardo", "Ríos", "20/07/2000", "29.345.678-8", "ricardo.rios@example.com");
        Alumno alumno_20 = new Alumno("Daniela", "Carrillo", "05/08/1999", "30.345.678-9", "daniela.carrillo@example.com");

        //Lista alumnos
        ArrayList <Alumno> alumnosAInscribir = new ArrayList<> ();
        
        //Llenado de alumnos
        alumnosAInscribir.add(alumno_0);
        alumnosAInscribir.add(alumno_1);
        alumnosAInscribir.add(alumno_2);
        alumnosAInscribir.add(alumno_3);
        alumnosAInscribir.add(alumno_4);
        alumnosAInscribir.add(alumno_5);
        alumnosAInscribir.add(alumno_6);
        alumnosAInscribir.add(alumno_7);
        alumnosAInscribir.add(alumno_8);
        alumnosAInscribir.add(alumno_9);
        alumnosAInscribir.add(alumno_10);
        alumnosAInscribir.add(alumno_11);
        alumnosAInscribir.add(alumno_12);
        alumnosAInscribir.add(alumno_13);
        alumnosAInscribir.add(alumno_14);
        alumnosAInscribir.add(alumno_15);
        alumnosAInscribir.add(alumno_16);
        alumnosAInscribir.add(alumno_17);
        alumnosAInscribir.add(alumno_18);
        alumnosAInscribir.add(alumno_19);
        alumnosAInscribir.add(alumno_20);
        
        
        
        
        //Semestres
        //sem 1 
        //CADA ASIGNATURA DEBE TENER SU PROPIA ASIGNATURA CON SUS PROPIOS ALUMNOS
        Asignatura asignatura_1 = new Asignatura ("FUNDAMENTOS DE ALGORITMOS", "INF1211", profe_1, alumnosAInscribir);
        Asignatura asignatura_2 = new Asignatura ("INTRODUCCION A LA INGENIERIA INFORMATICA", "INF1212", profe_2, alumnosAInscribir);
        
        //sem2
        Asignatura asignatura_3 = new Asignatura ("FUNDAMENTOS DE PROGRAMACION", "INF1214", profe_3, alumnosAInscribir);
        
        //sem3
        Asignatura asignatura_4 = new Asignatura ("ESTADISTICA COMPUTACIONAL", "INF2121", profe_4, alumnosAInscribir);
        Asignatura asignatura_5 = new Asignatura ("ESTRUCTURA DE DATOS", "INF2223", profe_5, alumnosAInscribir);
        Asignatura asignatura_23 = new Asignatura ("HARDWARE Y SISTEMAS OPERATIVOS", "INF2322", profe_5, alumnosAInscribir);
        
        //sem4
        Asignatura asignatura_6 = new Asignatura ("BASE DE DATOS", "INF2235", profe_6, alumnosAInscribir);
        Asignatura asignatura_7 = new Asignatura ("PROGRAMACION AVANZADA", "INF2236", profe_7, alumnosAInscribir);
        Asignatura asignatura_8 = new Asignatura ("INGENIERIA DE SOFTWARE", "INF2237", profe_8, alumnosAInscribir);  
        Asignatura asignatura_9 = new Asignatura ("REDES DE COMPUTADORES", "INF2324", profe_9, alumnosAInscribir);
       
        //sem5
        Asignatura asignatura_10 = new Asignatura ("INTELIGENCIA ARTIFICIAL", "INF3233", profe_8, alumnosAInscribir);
        Asignatura asignatura_11 = new Asignatura ("MODELAMIENTO DE SOFTWARE", "INF3234", profe_5, alumnosAInscribir);
        Asignatura asignatura_12 = new Asignatura ("INGENIERIA DE REQUERIMIENTOS", "INF3235", profe_2, alumnosAInscribir);
        
        //sem6
        Asignatura asignatura_13 = new Asignatura ("OPTIMIZACION", "INF3136", profe_1, alumnosAInscribir);
        Asignatura asignatura_14 = new Asignatura ("INGENIERIA WEB Y MOVIL", "INF3245", profe_7, alumnosAInscribir);
        Asignatura asignatura_15 = new Asignatura ("EXPERIENCIA DEL USUARIO", "INF3246", profe_6, alumnosAInscribir);
        Asignatura asignatura_16 = new Asignatura ("TALLER DE BASE DE DATOS", "INF3541", profe_9, alumnosAInscribir);
        
        //sem7
        Asignatura asignatura_17 = new Asignatura ("CIBERSEGURIDAD", "INF4353", profe_10, alumnosAInscribir);
        Asignatura asignatura_18 = new Asignatura ("TECNOLOGIAS EMERGENTES", "INF4457", profe_9, alumnosAInscribir);
        Asignatura asignatura_19 = new Asignatura ("TALLER DE INGENIERIA DE SOFTWARE", "INF4552", profe_8, alumnosAInscribir);
        Asignatura asignatura_20 = new Asignatura ("SEMINARIO DE TITULO", "INF4556", profe_7, alumnosAInscribir);
        
        //saem8
        Asignatura asignatura_21 = new Asignatura ("LEGISLACION, ETICA Y TECNOLOGIA", "INF4459", profe_6, alumnosAInscribir);
        Asignatura asignatura_22 = new Asignatura ("PROYECTO DE TITULO", "INF4560", profe_5, alumnosAInscribir);

        
        ArrayList<Asignatura> asignaturas;
        asignaturas = new ArrayList<>();
        //Llenado de semestres
        asignaturas.add(asignatura_1);
        asignaturas.add(asignatura_2);
        asignaturas.add(asignatura_3);
        asignaturas.add(asignatura_4);
        asignaturas.add(asignatura_5);
        asignaturas.add(asignatura_6);
        asignaturas.add(asignatura_7);
        asignaturas.add(asignatura_8);
        asignaturas.add(asignatura_9);
        asignaturas.add(asignatura_10);
        asignaturas.add(asignatura_11);
        asignaturas.add(asignatura_12);
        asignaturas.add(asignatura_13);
        asignaturas.add(asignatura_14);
        asignaturas.add(asignatura_15);
        asignaturas.add(asignatura_16);
        asignaturas.add(asignatura_17);
        asignaturas.add(asignatura_18);
        asignaturas.add(asignatura_19);
        asignaturas.add(asignatura_20);
        asignaturas.add(asignatura_21);
        asignaturas.add(asignatura_22);
        asignaturas.add(asignatura_23);
        
        //Lista de asignaturas correspondiente al semestre 1
        ArrayList<Asignatura> semestre_1 = new ArrayList<>();
        semestre_1.add(asignatura_1);
        semestre_1.add(asignatura_2);
        
        
        //Lista de asignaturas correspondiente al semestre 2
        ArrayList<Asignatura> semestre_2 = new ArrayList<>();
        semestre_2.add(asignatura_3);

        
        //Lista de asignaturas correspondiente al semestre 3
        ArrayList<Asignatura> semestre_3 = new ArrayList<>();    
        semestre_3.add(asignatura_4);
        semestre_3.add(asignatura_5);
        semestre_3.add(asignatura_23);
        
        
        //Lista de asignaturas correspondiente al semestre 4
        ArrayList<Asignatura> semestre_4 = new ArrayList<>();
        semestre_4.add(asignatura_6);
        semestre_4.add(asignatura_7);
        semestre_4.add(asignatura_8);
        semestre_4.add(asignatura_9);
        
        
        //Lista de asignaturas correspondiente al semestre 5
        ArrayList<Asignatura> semestre_5 = new ArrayList<>();
        semestre_5.add(asignatura_10);
        semestre_5.add(asignatura_11);
        semestre_5.add(asignatura_12);
        
        
        //Lista de asignaturas correspondiente al semestre 6
        ArrayList<Asignatura> semestre_6 = new ArrayList<>();
        semestre_6.add(asignatura_13);
        semestre_6.add(asignatura_14);
        semestre_6.add(asignatura_15);
        semestre_6.add(asignatura_16); 
    
        
        //Lista de asignaturas correspondiente al semestre 7
        ArrayList<Asignatura> semestre_7 = new ArrayList<>();
        semestre_7.add(asignatura_17);
        semestre_7.add(asignatura_18);
        semestre_7.add(asignatura_19);
        semestre_7.add(asignatura_20);
        
        
        //Lista de asignaturas correspondiente al semestre 8
        ArrayList<Asignatura> semestre_8 = new ArrayList<>();
        semestre_8.add(asignatura_21);
        semestre_8.add(asignatura_22);
        
        
        //Llenado del hashMapp correspondiente a la malla curricular :D
        
       
                
        
        
        HashMap<String, ArrayList<Asignatura>> mallaCurricular;
        mallaCurricular = new HashMap<>();
        
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
    
    public static void menuAlumno(){
        // Con contraseña alumno
        
    }
    
    public static void menuAdministrador(){
        // Con contraseña
        
    }
    
    public static void menuProfesor(){
        // Con contraseña de profesor
        
    }
    
    public static void menu()
    {
        Scanner lector = new Scanner(System.in);
        int opcion;
        do{
            System.out.println("Opciones");
            System.out.println("1.- Agregar ALumno");
            System.out.println("2.- Mostrar Alumno Inscritos");
            System.out.println("3.- FIN");
            System.out.println("Ingreses u opcion a elegir puñeta");
            opcion = lector.nextInt();
            lector.nextLine();
            switch (opcion) {
                case 1 -> {
                    mc.getMallaCurricular().get("Sem1").get(0).crearAlumnoPorConsola(lector);
                    break;
                }
                case 2 -> {
                    System.out.println(); mc.getMallaCurricular().get("Sem1").get(0).mostrarAlumnosAsignatura();
                    break;
                }
                case 3 -> {
                    System.out.println("fin");
            }
                default -> System.out.println("Numero incorreto");
            }
            
        }while(opcion != 3);
    }
}
