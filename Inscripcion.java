
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
    
    public String getCodigoAsignatura () {return asignatura.getsCodigo(); }
    
    public Asignatura getCopiaAsignatura() { return new Asignatura(this.asignatura); }
    
    public void setAprobada(boolean aprobada){ this.aprobada = aprobada; }
    public boolean isAprobada() { return aprobada; }

    //IMPLEMENTAR METODO TOSTRING(), YA QUE EN mostrarInscripciones DE ALUMNO LO
    //PIDE :D

    @Override
    public String toString() {
        String situacion;
        
        if(aprobada) situacion = "APROBADA";
        else situacion = "REPROBADA";
        
        return asignatura.getsCodigo()+", "+asignatura.getNombre()+", "+ Double.toString(nota)+", "+(String)situacion+", "+(String)semestre+"\n";
    }
    
}

    

