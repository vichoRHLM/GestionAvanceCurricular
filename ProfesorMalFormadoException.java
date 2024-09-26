package gestionavancecurricular;

public class ProfesorMalFormadoException extends Exception {

    public ProfesorMalFormadoException(String linea) {
        super("Error: la li­nea no tiene el formato correcto. " + linea);
    }
}