/* UNIVERSIDAD DE EL SALVADOR
    FACULTAD DE INGENIERIA Y ARQUITECTURA
        SISTEMAS CONTABLES SIC115
            VM15037
*/
package ues.edu.sv.catalogo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ManagedBean
@SessionScoped
public class CuentaController implements Serializable {

    private final List<Cuenta> cuentas = new ArrayList<>();
    private String clase;
    private String subclase;
    private String nombre;
    private String rubro;
    private String codigo;

    // Lista de clases y subclases
    private final List<String> clases = Arrays.asList("Activo", "Pasivo", "Patrimonio", "Ingresos", "Costos", "Gastos");
    private final List<String> subclases = new ArrayList<>();

    // Mapa para la secuencia de cada combinación de clase y subclase
    private final Map<String, Integer> secuenciaPorClaseYSubclase = new HashMap<>();

    // Este método actualiza la lista de subclases en función de la clase seleccionada
    public void actualizarSubclases() {
        subclases.clear();
        switch (clase) {
            case "Activo":
                subclases.add("Corriente");
                subclases.add("No Corriente");
                break;
            case "Pasivo":
                subclases.add("Corriente");
                subclases.add("No Corriente");
                break;
            case "Patrimonio":
                subclases.add("Capital");
                subclases.add("Reservas");
                break;
            case "Ingresos":
                subclases.add("Ordinarios");
                subclases.add("Extraordinarios");
                break;
            case "Costos":
                subclases.add("Costo de Ventas");
                break;
            case "Gastos":
                subclases.add("Gasto de Ventas");
                subclases.add("Financieros");
                subclases.add("Generales y Administrativos");
                subclases.add("Otros");
                break;
        }
    }

    // Método que genera un código único basado en clase y subclase
    private void generarCodigo() {
        String claseCodigo = "";
        String subclaseCodigo = "";

        // Determinamos el código de la clase
        switch (clase) {
            case "Activo":
                claseCodigo = "10";
                break;
            case "Pasivo":
                claseCodigo = "20";
                break;
            case "Patrimonio":
                claseCodigo = "30";
                break;
            case "Ingresos":
                claseCodigo = "40";
                break;
            case "Costos":
                claseCodigo = "50";
                break;
            case "Gastos":
                claseCodigo = "60";
                break;
        }

        // Determinamos el código de la subclase
        switch (subclase) {
            case "Corriente":
                subclaseCodigo = "01";
                break;
            case "No Corriente":
                subclaseCodigo = "02";
                break;
            case "Capital":
                subclaseCodigo = "01";
                break;
            case "Reservas":
                subclaseCodigo = "02";
                break;
            case "Ordinarios":
                subclaseCodigo = "01";
                break;
            case "Extraordinarios":
                subclaseCodigo = "02";
                break;
            case "Costo de Ventas":
                subclaseCodigo = "01";
                break;
            case "Gasto de Ventas":
                subclaseCodigo = "01";
                break;
            case "Financieros":
                subclaseCodigo = "02";
                break;
            case "Generales y Administrativos":
                subclaseCodigo = "03";
                break;
            case "Otros":
                subclaseCodigo = "04";
                break;
        }

        // Creamos un código único para clase y subclase
        String claveClaseSubclase = claseCodigo + subclaseCodigo;
        int secuencia = secuenciaPorClaseYSubclase.getOrDefault(claveClaseSubclase, 0) + 1;
        secuenciaPorClaseYSubclase.put(claveClaseSubclase, secuencia);

        // Generamos el código completo de la cuenta
        codigo = claseCodigo + subclaseCodigo + String.format("%03d", secuencia);
    }

    // Agregar una cuenta a la lista de cuentas y actualizar el código generado
    public void agregarCuenta() {
        generarCodigo();
        Cuenta cuenta = new Cuenta(codigo, clase, subclase, rubro, nombre);
        cuentas.add(cuenta);
        ordenarCuentas();
        limpiarFormulario();

        // Guardamos la lista en la sesión para que esté accesible entre vistas
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.getSessionMap().put("cuentas", cuentas);
    }

    // Ordenamos las cuentas para mantenerlas organizadas por clase y subclase
    private void ordenarCuentas() {
        cuentas.sort(Comparator.comparing(Cuenta::getClase)
                .thenComparing(Cuenta::getSubclase)
                .thenComparing(Cuenta::getCodigo));
    }

    // Limpiamos el formulario después de agregar una cuenta para recibir nuevos datos
    private void limpiarFormulario() {
        clase = "";
        subclase = "";
        nombre = "";
        rubro = "";
        codigo = "";
    }

    // Getters y Setters
    public List<Cuenta> getCuentas() {
        return cuentas;
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

    public List<String> getClases() {
        return clases;
    }

    public List<String> getSubclases() {
        return subclases;
    }

    public String getCodigo() {
        return codigo;
    }
}
