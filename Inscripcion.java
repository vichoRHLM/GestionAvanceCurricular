
package gestionavancecurricular;

/**
 * El fin de la clase <code>Inscripcion</code> es almacenar los datos que 
 * vinculan a un Alumno con una Asignatura. Esta clase podría incluir 
 * información como la nota del alumno, si aprobó o no la asignatura, 
 * y otros datos relacionados.
 * @author Sebastian Espinoza Rivera
 * 
 * 
 */
public class Inscripcion {
    private Alumno alumno;
    private Asignatura asignatura;
    private double nota; 
    private boolean aprobada;

    public Inscripcion(Alumno alumno, Asignatura asignatura) {
        this.alumno = alumno;
        this.asignatura = asignatura;
        this.nota = 0.0;
        this.aprobada = false;
    }
    
    public Inscripcion(Alumno alumno, Asignatura asignatura, double nota) {
        this.alumno = alumno;
        this.asignatura = asignatura;
        this.nota = nota;
        this.aprobada = false;
    }

    // Métodos para gestionar la inscripción
    public Alumno getAlumno() { return alumno; }
    public Asignatura getAsignatura() { return asignatura; }

    public double getNota() { return nota; }
    public void setNota(double nota) {
        this.nota = nota;
        this.aprobada = nota >= 4.0; // De momento supongamos que la nota mínima para aprobar es 4.0
    }

    public boolean isAprobada() { return aprobada; }
}

