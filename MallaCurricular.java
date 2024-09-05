
package gestionavancecurricular;

import java.util.*;

/**
 *
 * @author Sebastian Espinoza Rivera
 * 
 * 
 */
public class MallaCurricular {
    private HashMap<String, ArrayList<Asignatura>> mallaCurricular;

    // Constructor que recibe un HashMap
    public MallaCurricular(HashMap<String, ArrayList<Asignatura>> mallaCurricular) {
        this.mallaCurricular = mallaCurricular; // Inicialización correcta sin sobrescribir
    }
      // Constructor vacío que inicializa un HashMap vacío
    public MallaCurricular() {
        this.mallaCurricular = new HashMap<>();
    }
    
    
    // Getter y Setter para mallaCurricular
    public HashMap<String, ArrayList<Asignatura>> getMallaCurricular() {
        return mallaCurricular;
    }
    
    public void setMallaCurricular(HashMap<String, ArrayList<Asignatura>> mallaCurricular) {
        this.mallaCurricular = mallaCurricular; // No es necesario reinicializar el HashMap aquí
    }
    
    
    

    public String buscarAsignatura(String codigoAsignatura) {
        String[] semestres = {"Sem1", "Sem2", "Sem3", "Sem4", "Sem5", "Sem6", "Sem7", "Sem8"};

        for (int i = 0; i < semestres.length; i++) {
            ArrayList<Asignatura> asignaturasSemestre = mallaCurricular.get(semestres[i]);

            if (asignaturasSemestre != null) {
                for (int j = 0; j < asignaturasSemestre.size(); j++) {
                    if (asignaturasSemestre.get(j).getsCodigo().equals(codigoAsignatura)) {
                        return semestres[i];
                    }
                }
            }
        }

        return null;
    }


    public void crearAsignaturaPorCosola(Scanner lector, ArrayList <Profesor> listaProfes ) {
        System.out.println("1.- Codigo de la asignatura: ");
        String codigoAsignatura = lector.nextLine();
        if (buscarAsignatura(codigoAsignatura) != null) {
            System.out.println("Esta asignatura ya existe");
        } else {
            System.out.println("Ingrese el semestre donde va a ingresar la asignatura (Ejemplo: Sem1, Sem2, SemN):");
            String semestre = lector.nextLine();

            if (mallaCurricular.containsKey(semestre)) {
    
                Asignatura nuevaAsignatura = new Asignatura();
                nuevaAsignatura.setsCodigo(codigoAsignatura);
                System.out.println("A continuacion, ingrese los datos de la nueva asignatura:");
                System.out.println("2.- Nombre de la asignatura: ");
                nuevaAsignatura.setNombre(lector.nextLine());

                do{
                    System.out.println("3.- Ingrese el RUT del profesor a asignar: ");
                    String rut = lector.nextLine();
                    nuevaAsignatura.asignarProfesorExistente(rut, listaProfes);
                    if(nuevaAsignatura.getProfesorAsignado() == null)
                        System.out.println("No se encontro un profesor con el rut ingresado. Vuelva a intentarlo");
                }while(nuevaAsignatura.getProfesorAsignado() == null);
                mallaCurricular.get(semestre).add(nuevaAsignatura);
                
                //Faltaria una sentencia de si se quiere agregar alumnos o no
                
                System.out.println("Asignatura creada exitosamente.");
            } else 
                System.out.println("Este semestre no existe");
            
        }
    }
}

