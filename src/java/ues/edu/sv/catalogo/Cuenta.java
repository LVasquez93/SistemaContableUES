/* UNIVERSIDAD DE EL SALVADOR
    FACULTAD DE INGENIERIA Y ARQUITECTURA
        SISTEMAS CONTABLES SIC115
            VM15037
*/
package ues.edu.sv.catalogo;

public class Cuenta {
    private String codigo;
    private String clase;
    private String subclase;

   
    private String rubro;
    private String nombre;
    private int rowspan;

    public int getRowspan() {
        return rowspan;
    }

    public void setRowspan(int rowspan) {
        this.rowspan = rowspan;
    }


    // Constructor
    public Cuenta(String codigo, String clase, String subclase, String rubro, String nombre) {
        this.codigo = codigo;
        this.clase = clase;
        this.subclase = subclase;
        this.nombre = nombre;
        this.rubro = rubro;
    }

    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getSubclase() {
        return subclase;
    }

    public void setSubclase(String subclase) {
        this.subclase = subclase;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
     public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }
    
    
}
