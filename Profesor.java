
package gestionavancecurricular;

import java.util.*;
/**
 * Clase con los datos necesarios para el manejo de profesores
 */
public class Profesor extends Persona {
    private ArrayList<Asignatura> asignaturasAsignadas;

    public Profesor(){
        super();
        this.asignaturasAsignadas = new ArrayList<>();
    }
    
    public Profesor(String sNombre, String sApellido, String sFechaNacimiento, String sRut, String sCorreoElectronico) {
        super(sNombre, sApellido, sFechaNacimiento, sRut, sCorreoElectronico);
        this.asignaturasAsignadas = new ArrayList<>();
    }
    /**
     * El método <code>obtenerAsignaturas</code> busca una asignatura según su 
     * código (único) y la devuelve. El método recorre la lista de asignaturas
     * asignadas del profesor, buscando en cada una de ellas por su código.
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
    
    public ArrayList<Asignatura> obtenerCopiaAsignaturasAsignadas() {
        return new ArrayList<>(asignaturasAsignadas);
    }
    
    /**
     * El método agrega una aignatura a la lista de asigntauras asignadas del 
     * profesor.
     * 
     * @param asignatura Objeto tipo <code>Asignatura</code> que se agregará a 
     * la colección <code>asignaturasAsignadas</code>.
     */
    public void asignarAsignatura(Asignatura asignatura) {
        this.asignaturasAsignadas.add(asignatura);
        System.out.println("Asignatura asignada al profesor: " + asignatura.getNombre());
        //HACER ESTE MÉTODO BOOLEANO PARA VALIDAR LA ENTRADA DEL DATO AL ARRAYLIST
    }

    @Override
    public String toString() {
        return "Nombre profesor = " + this.getsNombre() + " " + this.getsApellido();
    }
    
    
}
