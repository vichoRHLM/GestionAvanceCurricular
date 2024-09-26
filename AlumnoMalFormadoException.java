package gestionavancecurricular;

class AlumnoMalFormadoException extends Exception {
    
    public AlumnoMalFormadoException(String linea){
        super("Error en formato de alumno: " + linea);
    }
}