package gestionavancecurricular;


public class FormatoAsignaturaInvalidoException extends Exception {
    public FormatoAsignaturaInvalidoException(String linea) {
        super("Error en formato de asignatura: " + linea);
    }
}