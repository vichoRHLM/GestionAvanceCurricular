
package gestionavancecurricular;

/**
 *
 * @author Sebastian Espinoza Rivera
 * 
 * 
 */
public class Alumno {

    private String sNombre;
    private String sApellido;
    private String sFechaNacimiento;
    private String sRut;
    private String sCorreoElectronico;
    //Lista de asignaturas aprobadas
    //Listas de asignaturas 
    
    
    public Alumno(String sNombre, String sApellido, String sFechaNacimiento, String sRut, String sCorreoElectronico) {
        this.sNombre = sNombre;
        this.sApellido = sApellido;
        this.sFechaNacimiento = sFechaNacimiento;
        this.sRut = sRut;
        this.sCorreoElectronico = sCorreoElectronico;
    }
    
    //Opcional
    public Alumno(Alumno nuevoAlumno) {
        this.sNombre = nuevoAlumno.sNombre;
        this.sApellido = nuevoAlumno.sApellido;
        this.sFechaNacimiento = nuevoAlumno.sFechaNacimiento;
        this.sRut = nuevoAlumno.sRut;
        this.sCorreoElectronico = nuevoAlumno.sCorreoElectronico;
    }
    
    public Alumno() {
        this.sNombre = "";
        this.sApellido = "";
        this.sFechaNacimiento = "";
        this.sRut = "";
        this.sCorreoElectronico = "";
    }


    public String getsNombre() { return sNombre; }

    public void setsNombre(String sNombre) { this.sNombre = sNombre; }

    public String getsApellido() { return sApellido; }
     
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
