package py.una.pol.is2.entities;

public class Hijo {
    
    // hijo_id
    // nombre
    // apellido
    // fecha_nacimiento
    // padre_id

    private Integer hijoId;

    private String nombre;

    private String apellido;

    private String fechaNacimiento;

    private Integer padreId;
    
    public Hijo() {
        
    }
    
    public Hijo(String nombre, String apellido, String fechaNacimiento, Integer padreId) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.padreId = padreId;
    }
    
    public Integer getHijoId() {
        return hijoId;
    }

    public void setHijoId(Integer hijoId) {
        this.hijoId = hijoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getPadreId() {
        return padreId;
    }


    public void setPadreId(Integer padreId) {
        this.padreId = padreId;
    }

}
