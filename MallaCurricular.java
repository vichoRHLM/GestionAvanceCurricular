
package gestionavancecurricular;

import java.util.*;

/**
 * Clase que gestiona la malla curricular
 */
public class MallaCurricular {
    private HashMap<String, ArrayList<Asignatura>> mallaCurricular;
    
    
    public MallaCurricular(HashMap<String, ArrayList<Asignatura>> 
            mallaCurricular) { this.mallaCurricular = mallaCurricular; }
    
    public MallaCurricular() { this.mallaCurricular = new HashMap<>(); }
    
    /**
     * Método que devuelve una copia del HashMap <code>mallaCurricular</code>. 
     * Primero se inicializa <code>copiaMallaCurricular</code>, un nuevo HashMap 
     * para copiar los datos. Luego se obtienen  las claves de 
     * <code>mallaCurricular</code> en la lista <code>claves</code> para ser 
     * recorridas por un ciclo for. Por ultimo se obtienen las asignaturas del 
     * asociadas al semestre, en la lista <code>asignaturas</code> y se agregan 
     * a <code>copiaMallaCurricular</code>.
     * @return Retorna una copia del  HashMap <code>mallaCurricular</code> propio
     * de la clase <code>MallaCurricular</code>.
     */
    public HashMap<String, ArrayList<Asignatura>> getMallaCurricular() {

        HashMap<String, ArrayList<Asignatura>> copiaMallaCurricular = new HashMap<>();
        
        ArrayList<String> claves = new ArrayList<>(this.mallaCurricular.keySet());

        for (int i = 0; i < claves.size(); i++) {
            String semestre = claves.get(i);

            ArrayList<Asignatura> asignaturas = this.mallaCurricular.get(semestre);

            copiaMallaCurricular.put(semestre, new ArrayList<>(asignaturas));
        }
        return copiaMallaCurricular;
    }

    /**
     * El método se asigna los datos de la variable <code>datosMallaCurricular</code> 
     * en el HashMap propio de la clase <code>MallaCurricular</code>. Primero se 
     * inicializa un nuevo HashMap para copiar los datos. Luego se obtienen 
     * las claves de <code>datosMallaCurricular</code> en una lista para ser 
     * recorridas por un ciclo for. Por ultimo se obtienen las asignaturas
     * asociadas al semestre en la lista <code>asignaturas</code> y se agregan
     * al nuevo HashMap.
     * @param datosMallaCurricular Datos de la malla curricular que se quieren 
     * setear en <code>mallaCurricular</code>
     */
    public void setMallaCurricular(HashMap<String, ArrayList<Asignatura>> datosMallaCurricular) {
        
        this.mallaCurricular = new HashMap<>();

        ArrayList<String> claves = new ArrayList<>(datosMallaCurricular.keySet());

        for (int i = 0; i < claves.size(); i++) {
            String semestre = claves.get(i);

            ArrayList<Asignatura> asignaturas = datosMallaCurricular.get(semestre);

            this.mallaCurricular.put(semestre, new ArrayList<>(asignaturas));
        }
    }
    
    /**
     * Método usado para verificar que un semestre estre dentro de las keys 
     * de <code>mallaCurricular</code>.
     * @param semestre Llave para acceder a datos de un hashMap. 
     * (Formato: "Sem1", "Sem2", "SemN").
     * @return Retorna true si esta en las keys de <code>mallaCurricular</code>,
     * si no retoruna false.
     */
    private boolean verificarSemestre(String semestre){
        return mallaCurricular.containsKey(semestre);
    }
    
    /**
     * Agregar una asignatura a la mallaCurricular según el 
     * <code>semestre</code> indicado. Además verifica si existe el semestre 
     * en el que se quiere agregar. (Sobrecargado).
     * @param semestre Llave para acceder a datos de un hashMap. 
     * (Formato: "Sem1", "Sem2", "SemN"). 
     * @param asignatura Objeto tipo <code>Asignatura</code> que se desea 
     * agregar.
     */
    public void agregarAsignatura(String semestre, Asignatura asignatura) {
        if (verificarSemestre(semestre)) {
            mallaCurricular.get(semestre).add(asignatura); 
            System.out.println("Asignatura agregada exitosamente al semestre " + semestre);
        } else {
            System.out.println("El semestre especificado no existe");
        }
    }
    
    /**
     * Agregar una asignatura con datos a la mallaCurricular según el 
     * <code>semestre</code> indicado. Además verifica si existe el 
     * semestre en el que se quiere agregar. (Sobrecargado).
     * @param semestre Llave para acceder a datos de un hashMap. (Formato: "Sem1", "Sem2", "SemN").
     * @param nombreAsignatura Nombre de la asignatura.
     * @param codigoAsignatura Código único usado para identificar la asignatura.
     * @param profesor Profesor que sera asignado a la asignatura. Objeto tipo <code>Profesor</code>.
     */
    public void agregarAsignatura(String semestre, String nombreAsignatura, String codigoAsignatura, Profesor profesor) {
        if (verificarSemestre(semestre)) {
            //Instanciar un objeto con datos
            Asignatura nuevaAsignatura = new Asignatura(nombreAsignatura, codigoAsignatura, profesor);
            mallaCurricular.get(semestre).add(nuevaAsignatura); //Se agrega al arrayList de asignaturas
            System.out.println("Asignatura creada y agregada exitosamente al semestre " + semestre);
        } else {
            System.out.println("El semestre especificado no existe");
        }
    }
    
    /**
     * Mostrar por pantalla los datos del HashMap mallaCurricular.
     */ 
    public void mostrarMallaCurricular() {
        // Obtiene la lista de claves (semestres) del HashMap
        String[] semestres = {"Sem1", "Sem2", "Sem3", "Sem4", "Sem5", "Sem6", "Sem7", "Sem8"};
        // Recorrer cada semestre utilizando un ciclo for   
        for (int i = 0; i < semestres.length; i++) {
            String semestre = semestres[i];
            System.out.println("Semestre "
                    + "" + (i+1) + " : ");

        
            ArrayList<Asignatura> asignaturas = mallaCurricular.get(semestre);
            // Recorre la lista de asignaturas del semestre actual
            if (asignaturas != null) {
                for (int j = 0; j < asignaturas.size(); j++) {
                    Asignatura asignatura = asignaturas.get(j);
                    System.out.println(" -> " + asignatura.getNombre() + ": " + asignatura.getsCodigo());
                }
                System.out.println("");
            } else {
                System.out.println(" No hay asignaturas para este semestre.\n");
            }
        }
        System.out.println("");
    }
    
    /**
     * Menú usado para agregar un alumno a una asignatura
     * @param lector Objeto de tipo <code>Scanner</code> usado para leer 
     * datos ingresados por consola. 
     * @param alumnosAInscribir 
     * @throws ErrorDeLecturaArchivoException 
     */
    public void menuParaAgregarAlumnoaAsignatura(Scanner lector, ArrayList<Alumno> alumnosAInscribir) throws ErrorDeLecturaArchivoException{
        String sCodigoAsignaturaBuscado;
        Asignatura asignaturaBuscada;
        
        do{
            System.out.println("Ingrese codigo de la asignatura a la que agregara un alumno : ");
            sCodigoAsignaturaBuscado = lector.nextLine();
            asignaturaBuscada= buscarAsignaturaParaRetornarla(sCodigoAsignaturaBuscado);
   
            if(asignaturaBuscada == null){
                System.out.println("No se encontro una asignatura con ese codigo, porfavor vuelva a intentar.");
            }else
            {
                asignaturaBuscada.agregarAlumnoPorConsola(lector, alumnosAInscribir);
            }
        }while(asignaturaBuscada == null);
    }
    
    /**
     * Método que busca una asignatura por su código.
     * @param codigoAsignatura Código único usado para identificar la asignatura.
     * @return Retorna el semestre en donde se encuentra la asignatura, 
     * si no retorna null.
     */
    private String buscarAsignatura(String codigoAsignatura) {
        ArrayList<String> semestres = new ArrayList<>(mallaCurricular.keySet());

        for (int i = 0; i < semestres.size(); i++) {
            ArrayList<Asignatura> asignaturasSemestre = mallaCurricular.get(semestres.get(i));

            if (asignaturasSemestre != null) {
                for (int j = 0; j < asignaturasSemestre.size(); j++) {
                    if (asignaturasSemestre.get(j).getsCodigo().equals(codigoAsignatura)) {
                        return semestres.get(i); 
                    }
                }
            }
        }
        return null; 
    }

    /**
     * Método que busca una asignatura por su código
     * @param codigoAsignatura Código único usado para identificar la asignatura.
     * @return Retorna la asignatura si es que se encuentra dentro de los 
     * semestre, si no retorna null.
     */
    public Asignatura buscarAsignaturaParaRetornarla(String codigoAsignatura) {
        ArrayList<String> semestres = new ArrayList<>(mallaCurricular.keySet());

        for (int i = 0; i < semestres.size(); i++) {
            ArrayList<Asignatura> asignaturasSemestre = mallaCurricular.get(semestres.get(i));

            if (asignaturasSemestre != null) {
                for (int j = 0; j < asignaturasSemestre.size(); j++) {
                    if (asignaturasSemestre.get(j).getsCodigo().equals(codigoAsignatura)) {
                        return asignaturasSemestre.get(j); 
                    }
                }
            }
        }
        return null;
    }

    /**
     * Médtodo usado para la creación y asignación de datos por consola de un 
     * objeto tipo <code>Asignatura</code>. El método verifica si la asignatura 
     * ya existe. Además verifica si existe el semestre en donde se quiere 
     * agregar la asignatura. También se verifica que exista el profesor en 
     * <code>listaProfes</code> mediante <code>rut</code>. Por ultimo se agrega 
     * la asignatura creada en el semestre indicado.
     * @param lector Objeto de tipo <code>Scanner</code> usado para leer datos 
     * ingresados por consola. 
     * @param listaProfes Lista de profesores con datos predefinidos.
     */
    public void crearAsignaturaPorConsola(Scanner lector, ArrayList <Profesor> listaProfes ) {
        System.out.println("1.- Codigo de la asignatura: ");
        String codigoAsignatura = lector.nextLine();
        if (buscarAsignatura(codigoAsignatura) != null) {
            System.out.println("Esta asignatura ya existe");
        } else {
            System.out.println("Ingrese el semestre donde va a ingresar la asignatura (Ejemplo: Sem1, Sem2, SemN):");
            String semestre = lector.nextLine();

            if (verificarSemestre(semestre)) {
    
                Asignatura nuevaAsignatura = new Asignatura();
                nuevaAsignatura.setsCodigo(codigoAsignatura);
                System.out.println("A continuacion, ingrese los datos de la nueva asignatura:");
                System.out.println("2.- Nombre de la asignatura: ");
                nuevaAsignatura.setNombre(lector.nextLine());

                do{
                    System.out.println("3.- Ingrese el RUT del profesor a asignar:(Formto : 12345678-9)");
                    String rut = lector.nextLine();
                    nuevaAsignatura.asignarProfesorExistente(rut, listaProfes);
                    if(nuevaAsignatura.getProfesorAsignado() == null)
                        System.out.println("El profesor no se encuentra en los datos o el rut esta mal escrito");
                }while(nuevaAsignatura.getProfesorAsignado() == null);
                mallaCurricular.get(semestre).add(nuevaAsignatura);
                
                //Faltaria una sentencia de si se quiere agregar alumnos o no
                
                System.out.println("Asignatura creada exitosamente.");
            } else 
                System.out.println("Este semestre no existe");
        }
    }
}