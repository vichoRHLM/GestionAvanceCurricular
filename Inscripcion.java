
package gestionavancecurricular;

/**
 * El fin de la clase <code>Inscripcion</code> es almacenar los datos que 
 * vinculan a un Alumno con una Asignatura. Esta clase podría incluir 
 * información como la nota del alumno, si aprobó o no la asignatura, 
 * y otros datos relacionados.
 * @author Sebastian Espinoza Rivera
 */
public class Inscripcion {
    private Alumno alumno;
    private Asignatura asignatura;
    private double nota; 
    private boolean aprobada;
    private String semestre;

    public Inscripcion(Alumno alumno, Asignatura asignatura) {
        this.alumno = alumno;
        this.asignatura = asignatura;
        this.nota = 0.0;
        this.aprobada = false;
    }
    
    public Inscripcion(Alumno alumno, Asignatura asignatura, String semestre) {
        this.alumno = alumno;
        this.asignatura = asignatura;
        this.semestre = semestre;
        this.nota = 0.0;
        this.aprobada = false;
    }
    
    // Métodos getter y setter
    public String getSemestre() { return semestre; }
    public void setSemestre(String semestre) { this.semestre = semestre; }

    public double getNota() { return nota; }
    public void setNota(double nota) {
        this.nota = nota;
        this.aprobada = nota >= 4.0;
    }
    
    public void setAprobada(boolean aprobada){ this.aprobada = aprobada; }
    public boolean isAprobada() { return aprobada; }

    //IMPLEMENTAR METODO TOSTRING(), YA QUE EN mostrarInscripciones DE ALUMNO LO
    //PIDE :D

    @Override
    public String toString() {
        return "Inscripcion : " + "Alumno = " + alumno + ", asignatura = " + asignatura + ", nota = " + nota + ", situacion academica = " + aprobada + ", semestre cursado = " + semestre;
    }
    
}

    

