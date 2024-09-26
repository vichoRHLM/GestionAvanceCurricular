
package gestionavancecurricular;

/**
 *
 * @author Sebastian Espinoza Rivera
 * 
 * 
 */
public class Persona {
    protected String sNombre;
    protected String sApellido;
    protected String sFechaNacimiento;
    protected String sRut;
    protected String sCorreoElectronico;

     public Persona(){
         this.sNombre = "";
        this.sApellido = "";
        this.sFechaNacimiento = "";
        this.sRut = "";
        this.sCorreoElectronico = "";
     }
    
    public Persona(String sNombre, String sApellido, String sFechaNacimiento, String sRut, String sCorreoElectronico) {
        this.sNombre = sNombre;
        this.sApellido = sApellido;
        this.sFechaNacimiento = sFechaNacimiento;
        this.sRut = sRut;
        this.sCorreoElectronico = sCorreoElectronico;
    }

    public String getsNombre() { return sNombre; }
    public void setsNombre(String sNombre) { this.sNombre = sNombre; }

    public String getsApellido() { return sApellido; }
    public void setsApellido(String sApellido) { this.sApellido = sApellido; }

    public String getsFechaNacimiento() { return sFechaNacimiento; }
    public void setsFechaNacimiento(String sFechaNacimiento) { this.sFechaNacimiento = sFechaNacimiento; }

    public String getsRut() { return sRut; }
     public void setsRut(String sRut) { 
        if(validarRut(sRut))
            this.sRut = sRut; 
    }

    public String getsCorreoElectronico() { return sCorreoElectronico; }
    public void setsCorreoElectronico(String sCorreoElectronico) { this.sCorreoElectronico = sCorreoElectronico; }
    
    /**
     * Método que valida el formato de <code>rut</code>. En primer lugar se 
     * verifica la longitud correcta (se asume 10 como longitud esperada, ya que
     * el formato es 12345678-9). Luego se verifica que hasta el guión, todos 
     * los datos sean números. En tercer lugar, se verifica si falta el guión. 
     * Por último, se verifica que el último carácter sea un dígito o 'k'.
     * @param rut Identificador unico de una persona. En este caso del alumno.
     * @return Retorna true en caso de que se cumpla con las validaciones de formato, si no retorna false.
     */
    public boolean validarRut(String rut) {
        int largo = rut.length();

        if (largo != 10) {
            return false;
        }
       
        for (int i = 0; i < 8; i++) {
            if (!Character.isDigit(rut.charAt(i))) {
                return false;
            }
        }

        if (rut.charAt(8) != '-') {
            return false;
        }

        char ultimoCaracter = rut.charAt(9);
        if (!Character.isDigit(ultimoCaracter) && ultimoCaracter != 'k' && ultimoCaracter != 'K') {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persona{" + "sNombre=" + sNombre + ", sApellido=" + sApellido + ", sFechaNacimiento=" + sFechaNacimiento + ", sRut=" + sRut + ", sCorreoElectronico=" + sCorreoElectronico + '}';
    }
}