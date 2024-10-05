package gestionavancecurricular;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * La clase MallaCurricular gestiona las asignaturas distribuidas por semestres 
 * en un instituto. Su estructura principal es un HashMap que organiza las 
 * asignaturas según el semestre. Además la clase maneja todos las 
 * funcionalidades.
 */
public class MallaCurricular {
    /******ATRIBUTOS******/
    private HashMap<String, ArrayList<Asignatura>> mallaCurricular;
    /******FIN ATRIBUTOS******/
    
    
    /******CONSTRUCTORES******/
    public MallaCurricular(HashMap<String, ArrayList<Asignatura>> mallaCurricular) { this.mallaCurricular = mallaCurricular; }
    
    
    public MallaCurricular() { this.mallaCurricular = new HashMap<>(); }
    /******FIN CONSTRUCTORES******/
    
    
    /******METODOS******/
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
     * @throws java.io.IOException 
     * @throws gestionavancecurricular.AsignaturaNoEncontradaException 
     */
    public void menuParaAgregarAlumnoaAsignatura(Scanner lector, ArrayList<Alumno> alumnosAInscribir) throws ErrorDeLecturaArchivoException, IOException, AsignaturaNoEncontradaException{
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
                     
                System.out.println("Asignatura creada exitosamente.");
                
            } else 
                System.out.println("Este semestre no existe");
        }
    }
    
    
    
    
    /**
     * Actualiza el archivo CSV con los datos de una nueva asignatura, 
     * incluyendo su nombre, código y RUT de los alumnos inscritos.
     *
     * @param nuevaAsignatura La nueva asignatura que se desea añadir al archivo CSV.
     * @param bw El BufferedWriter utilizado para escribir en el archivo CSV.
     * @throws ErrorDeLecturaArchivoException Si ocurre un error relacionado con la lectura del archivo.
     * @throws IOException Si ocurre un error de entrada/salida al escribir en el archivo.
     */
    public void actualizarCSVConNuevaAsignatura(Asignatura nuevaAsignatura, BufferedWriter bw) throws ErrorDeLecturaArchivoException, IOException {
        // Formato del CSV: nombreAsignatura;codigoAsignatura;RUTalumnos; (RUTs separados por comas si hay más de uno)
        StringBuilder lineaAsignatura = new StringBuilder();
        lineaAsignatura.append(nuevaAsignatura.getNombre()).append(";").append(nuevaAsignatura.getsCodigo()).append(";");

        
        // Si tiene alumnos asignados, los añadimos al CSV
        if (nuevaAsignatura.getAlumnosInscritos() != null && !nuevaAsignatura.getAlumnosInscritos().isEmpty()) {
            for (Alumno alumno : nuevaAsignatura.getAlumnosInscritos()) {
                lineaAsignatura.append(alumno.getsRut()).append(",");
            }
            
            // Eliminar la última coma si se añadió algún alumno
            lineaAsignatura.deleteCharAt(lineaAsignatura.length() - 1);
            
        }
        
        
        // Escribir la línea al CSV
        bw.write(lineaAsignatura.toString());
        bw.newLine();  // Asegura que la siguiente asignatura se escriba en una nueva línea
    }
    
    
    
    
    /**
    * El método exporta todos los alumnos aprobados de todas las asignaturas
    * a un archivo CSV. Recorre todas las asignaturas de la malla curricular
    * y busca los alumnos aprobados, los cuales serán almacenados en un archivo.
    * Además, incluye el código de la asignatura en la que aprobaron.
    *
    * @param arrAlmn Lista de alumnos a considerar en la exportación.
    * @throws IOException Si ocurre un error durante la escritura del archivo.
    */
    public void reportarAlumnosAprobadosACSV(ArrayList<Alumno> arrAlmn) throws IOException {
        ArrayList<Alumno> alumnosAprobados = new ArrayList<>();
        ArrayList<Asignatura> listaAsignaturas = obtenerListaAsignaturas();
        ArrayList<String> codAsignaturasAprobadas = new ArrayList<>();
        
        
        if (!listaAsignaturas.isEmpty()) 
        {
            
            for (int i = 0; i < listaAsignaturas.size(); i++)
            {
                Asignatura auxAsig = (Asignatura) listaAsignaturas.get(i);
                alumnosAprobados.addAll(auxAsig.obtenerAlumnosAprobados(arrAlmn, codAsignaturasAprobadas));
            }
            
        }

        // Escribir los datos de los alumnos aprobados en un archivo CSV
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src\\resource\\alumnos_aprobados.csv"))) {
            
            for (int i = 0; i < alumnosAprobados.size() ; i++) {
                Alumno auxAlumn = (Alumno) alumnosAprobados.get(i);
                String codAsigAprob = codAsignaturasAprobadas.get(i);
                bw.write(auxAlumn.getsRut() + ";" + auxAlumn.getsNombre() + " " + auxAlumn.getsApellido() + ";" + codAsigAprob + "\n");
            }
            
        }
        System.out.println("Alumnos aprobados exportados correctamente a " + "src\\resource\\alumnos_aprobados.csv");
    }
    
    
    
    
    /**
     * Método que lee el archivo CSV de alumnos que aprobaron asignaturas y
     * muestra los datos en la consola.
     * El archivo tiene el siguiente formato:
     * Rut Alumno; Nombre Alumno; Codigo Asignatura Aprobada
     *
     * @param brAlumnAprobados 
     * @throws IOException Si hay un error al leer el archivo CSV.
     */
    public void mostrarAlumnosAprobadosPorAsignatura(BufferedReader brAlumnAprobados) throws IOException {
        
        String linea;
        System.out.println("\nALUMNOS QUE APROBARON SUS ASIGNATURAS :\n");
        System.out.println("RUT\t\tNOMBRE\t\t\tASIGNATURA");

        // Leer y mostrar cada línea del archivo CSV
        while ((linea = brAlumnAprobados.readLine()) != null) {
            String[] datos = linea.split(";", -1); // Dividir la línea en partes usando el ";" como delimitador
            
            if (datos.length == 3) {
                String rutAlumno = datos[0];
                String nombreAlumno = datos[1];
                String codigoAsignatura = datos[2];

                // Mostrar los datos de cada alumno aprobado
                System.out.println(rutAlumno + "\t" + nombreAlumno + "\t\t" + codigoAsignatura);
                
            } else {
                System.out.println("Error en el formato de la línea: " + linea);
            }
        }
        System.out.println(" 1");
    }
    
    
    
    
    /**
    * El método obtiene todas las asignaturas de la malla curricular. 
    * Recorre los semestres definidos en la estructura de datos 
    * mallaCurricular (HashMap) y agrega todas las asignaturas encontradas
    * en una lista.
    * 
    * @return Lista de todas las asignaturas registradas en la malla curricular.
    */
    public ArrayList<Asignatura> obtenerListaAsignaturas() {
        ArrayList<Asignatura> listaAsignaturas = new ArrayList <>();
        // Obtiene la lista de claves (semestres) del HashMap
        String[] semestres = {"Sem1", "Sem2", "Sem3", "Sem4", "Sem5", "Sem6", "Sem7", "Sem8"};
        
        // Recorrer cada semestre utilizando un ciclo for 
        for (int i = 0; i < semestres.length; i++) {
            String semestre = semestres[i];
            ArrayList<Asignatura> asignaturas = mallaCurricular.get(semestre);
            listaAsignaturas.addAll(asignaturas);
        }
        
        return listaAsignaturas;
    }
    
    
    
    
    /**
     * Elimina una asignatura de la malla curricular según su código, 
     * recorriendo los semestres para encontrar la asignatura correspondiente.
     *
     * @param lector El objeto Scanner utilizado para leer el código de la asignatura a eliminar desde la entrada estándar.
     * @param listaAsignaturas La lista de asignaturas en la que se desea eliminar la asignatura.
     * @throws IOException Si ocurre un error de entrada/salida durante el proceso de eliminación.
     */
    public void eliminarAsignaturaPorCodigo(Scanner lector, ArrayList<Asignatura> listaAsignaturas) throws IOException {
        boolean asignaturaEliminada = false;
        String[] semestres = {"Sem1", "Sem2", "Sem3", "Sem4", "Sem5", "Sem6", "Sem7", "Sem8"};
        
        System.out.println("INGRESA CODIGO DE LA ASIGNATURA QUE DESAS ELIMINAR : (EN MAYUSCULA)");
        
        String codigoAEliminar = lector.nextLine();
        ArrayList<Asignatura> asignaturas;
        
        // Recorrer cada semestre utilizando un ciclo for   
        for (int i = 0; i < semestres.length; i++) {
            String semestre = semestres[i];
            asignaturas = mallaCurricular.get(semestre);
            
            for(int k = 0; k < asignaturas.size() ; k++)
            {
                
                Asignatura asigEliminar = (Asignatura) asignaturas.get(k);   
               
                
                if (asigEliminar.getsCodigo().equals(codigoAEliminar))
                {
                    
                    // Eliminar la asignatura si se encuentra
                    asignaturas.remove(k);
                    
                    
                    if (asigEliminar.eliminarAsignatura(codigoAEliminar, listaAsignaturas)){
                        asignaturaEliminada = true;
                        System.out.println("Asignatura con código " + codigoAEliminar + ", eliminada de " + semestre + " con exito");
                        
                    }
                    
                    
                    break;  // Salir del bucle de asignaturas ya que se ha eliminado
                }
            }
        }

        // Verificar si la asignatura fue encontrada y eliminada
        if (!asignaturaEliminada) {
            System.out.println("Asignatura con código " + codigoAEliminar + " no encontrada.");
        }
    }
    
    
    
    /**
     * Modifica el nombre de una asignatura existente en la malla curricular, 
     * buscando primero la asignatura por su código.
     *
     * @param lector El objeto Scanner utilizado para leer los datos de la asignatura a modificar desde la entrada estándar.
     * @param listaAsignaturas La lista de asignaturas en la que se encuentra la asignatura a modificar.
     * @throws IOException Si ocurre un error de entrada/salida durante el proceso de modificación.
     */
    public void modificarAsignatura(Scanner lector,
            ArrayList<Asignatura> listaAsignaturas) throws IOException
    {
        Asignatura asignaturaAModificar;
        String nuevoNombre;
        
        
        System.out.println("BIENVENIDO A LA OPCION DE MODIFICAR ASIGNATURA");
        System.out.println("INGRESA CODIGO DE LA ASIGNATURA QUE DESAS MODIFICAR : (EN MAYUSCULA)");
        String codigoBuscado = lector.nextLine();
        
        
        //Buscar la asginatura
        asignaturaAModificar = buscarAsignaturaParaRetornarla(codigoBuscado);
       
        
        if(asignaturaAModificar != null)
        {
            System.out.println("INGRESE EL NUEVO NOMBRE DE LA ASIGNATURA " + asignaturaAModificar.getNombre() + " : ");
            nuevoNombre = lector.nextLine();
            
            
            if(asignaturaAModificar.modificarNombreAsignatura(codigoBuscado, nuevoNombre, listaAsignaturas)){
                System.out.println("ASIGNATURA CON CODIGO " + asignaturaAModificar.getsCodigo()
                        + " AHORA SE LLAMA : " + nuevoNombre);
            }
            
        }
        else
        {
            System.out.println("ASIGNATURA NO ENCONTRADA EN MALLA");
        }
        System.out.println("");
    }
    /******FIN METODOS******/
}