/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.edu.sv.catalogo;

import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class LibroDiarioController {

    private Asiento asientoActual;
    private List<Asiento> libroDiario;

    @ManagedProperty("#{cuentaController}")
    private CuentaController cuentaController;

    // Constructor
    public LibroDiarioController() {
        asientoActual = new Asiento();
        // Cargar el libro diario de la sesión si existe
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        libroDiario = (List<Asiento>) ec.getSessionMap().get("libroDiario");
        
        if (libroDiario == null) {
            libroDiario = new ArrayList<>();
        }
    }

    // Método para agregar un asiento al libro diario
    public void registrarAsiento() {
        if (asientoActual.estaCuadrado()) {
            asientoActual.setFecha(new Date());
            libroDiario.add(asientoActual);
            asientoActual = new Asiento();  // Reiniciar el asiento actual para el próximo registro

            // Guardar el libro diario en la sesión
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.getSessionMap().put("libroDiario", libroDiario);

            // Agrega un mensaje de éxito sin cambiar de página
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Asiento registrado exitosamente"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El asiento no está cuadrado. Debe y Haber deben ser iguales."));
        }
    }

    // Método para agregar una partida al asiento actual
    public void agregarPartida(Cuenta cuenta, double monto, boolean esDebe, String descripcion) {
        Partida partida = new Partida(cuenta, monto, esDebe, descripcion);
        asientoActual.agregarPartida(partida);
    }

    // Obtener lista de cuentas desde el CuentaController
    public List<Cuenta> getCuentasDisponibles() {
        return cuentaController.getCuentas();
    }

    // Getters y Setters
    public Asiento getAsientoActual() {
        return asientoActual;
    }

    public List<Asiento> getLibroDiario() {
        return libroDiario;
    }

    public void setCuentaController(CuentaController cuentaController) {
        this.cuentaController = cuentaController;
    }
}
