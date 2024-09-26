package gestionavancecurricular;

import java.io.IOException;

public class ErrorDeLecturaArchivoException extends Exception {

    public ErrorDeLecturaArchivoException(IOException e) {
        super("Error de lectura del archivo: " + e.getMessage());
    }
}