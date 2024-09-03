
package gestionavancecurricular;
    
import java.util.ArrayList;

/**
 *
 * @author Sebastian Espinoza Rivera
 * 
 * 
 */
public class Profesor {
    private String sNombre;
    private String sApellido;
    private String sFechaNacimiento;
    private String sRut;
    private String sCorreoElectronico;
    //private ArrayList <String> alAsignaturasAsignadas;
    
    
    
    //Analizar constructores
    public Profesor(String sNombre, String sApellido, String sFechaNacimiento, String sRut, String sCorreoElectronico /*ArrayList <String> alAsignaturasAsignadas*/) {
        this.sNombre = sNombre;
        this.sApellido = sApellido;
        this.sFechaNacimiento = sFechaNacimiento;
        this.sRut = sRut;
        this.sCorreoElectronico = sCorreoElectronico;
        //this.alAsignaturasAsignadas = new ArrayList<> ();
        //this.alAsignaturasAsignadas.addAll(alAsignaturasAsignadas);
    }

    public Profesor() {
        this.sNombre = "";
        this.sApellido = "";
        this.sFechaNacimiento = "";
        this.sRut = "";
        this.sCorreoElectronico = "";
        //this.alAsignaturasAsignadas = new ArrayList<>();
    }

    public String getsNombre() {
        return sNombre;
    }

    public void setsNombre(String sNombre) {
        this.sNombre = sNombre;
    }

    public String getsApellido() {
        return sApellido;
    }

    public void setsApellido(String sApellido) {
        this.sApellido = sApellido;
    }

    public String getsFechaNacimiento() {
        return sFechaNacimiento;
    }

    public void setsFechaNacimiento(String sFechaNacimiento) {
        this.sFechaNacimiento = sFechaNacimiento;
    }

    public String getsRut() {
        return sRut;
    }

    public void setsRut(String sRut) {
        this.sRut = sRut;
    }

    public String getsCorreoElectronico() {
        return sCorreoElectronico;
    }

    public void setsCorreoElectronico(String sCorreoElectronico) {
        this.sCorreoElectronico = sCorreoElectronico;
    }
}
