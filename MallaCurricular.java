
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

    public MallaCurricular(HashMap<String, ArrayList<Asignatura>> mallaCurricular) {
        this.mallaCurricular = new HashMap();
        this.mallaCurricular = mallaCurricular;
    }
    public MallaCurricular() {
        this.mallaCurricular = new HashMap();
    }
    
    
    public HashMap<String, ArrayList<Asignatura>> getMallaCurricular() {
        return mallaCurricular;
    }

    public void setMallaCurricular(HashMap<String, ArrayList<Asignatura>> mallaCurricular) {
        this.mallaCurricular = new HashMap();
        this.mallaCurricular = mallaCurricular;
    }
    
   public Boolean buscarAsignatura(String buscado) {
    String[] semestres = {"Sem1", "Sem2", "Sem3", "Sem4", "Sem5", "Sem6", "Sem7", "Sem8"};
    for (int i = 0; i < semestres.length; i++) {
        ArrayList<Asignatura> asignaturasSemestre = mallaCurricular.get(semestres[i]);
        if (asignaturasSemestre != null) { //DUDAS SOBRE ESTE IF ;D
            for (int j = 0; j < asignaturasSemestre.size(); j++) {
                if (asignaturasSemestre.get(j).getsCodigo().equals(buscado)) {
                    return true;
                }
            }
        }
    }
    return false;
}
    public Boolean buscarAsignatura(Asignatura buscado)
    {
        String[] semestres = {"Sem1", "Sem2", "Sem3", "Sem4", "Sem5", "Sem6", "Sem7", "Sem8"};
    for (int i = 0; i < semestres.length; i++) {
        ArrayList<Asignatura> asignaturasSemestre = mallaCurricular.get(semestres[i]);
        if (asignaturasSemestre != null) { //DUDAS SOBRE ESTE IF ;D
            for (int j = 0; j < asignaturasSemestre.size(); j++) {
                if (asignaturasSemestre.get(j).equals(buscado)) {
                    return true;
                }
            }
        }
    }
    return false;
    }

    public void crearAsignaturaPorCosola(Scanner lector){
        System.out.println("Ingrese clave de la asignatura :");
        if(buscarAsignatura(lector.nextLine()))
            System.out.println("Esta asignatura ya existe");
        else{
            System.out.println("Ingrese el semestre donde va a ingresar la asignatura :(Ejemplo: Sem1,Sem2,SemN)");
            if(mallaCurricular.containsKey(lector.nextLine())){
                Asignatura nuevaAsignatura = new Asignatura();
                System.out.println("Acontinuacion ingrese los datos de la nueva asignatura :");
                System.out.println("1.- Nombre");
                nuevaAsignatura.setNombre(lector.nextLine());
                System.out.println("2.- ");

                mallaCurricular.get(lector).add();
            } else
                System.out.println("Este semestre no existe");
        }
    }
    

}

