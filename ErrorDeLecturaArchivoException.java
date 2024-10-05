package gestionavancecurricular;

import java.io.IOException;

public class ErrorDeLecturaArchivoException extends Exception {

    public ErrorDeLecturaArchivoException() {
        super("Error de lectura del archivo...");
    }
}