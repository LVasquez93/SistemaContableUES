/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.edu.sv.catalogo;

/**
 *
 * @author SoporteSV
 */
// Clase Partida - Representa una línea dentro de un asiento
public class Partida {
    private Cuenta cuenta;  // Referencia a la cuenta
    private double monto;
    private boolean esDebe;
    private String descripcion;

    // Constructor
    public Partida(Cuenta cuenta, double monto, boolean esDebe, String descripcion) {
        this.cuenta = cuenta;
        this.monto = monto;
        this.esDebe = esDebe;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public Cuenta getCuenta() { return cuenta; }
    public void setCuenta(Cuenta cuenta) { this.cuenta = cuenta; }

    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }

    public boolean isEsDebe() { return esDebe; }
    public void setEsDebe(boolean esDebe) { this.esDebe = esDebe; }
    
        public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    @Override
    public String toString() {
        // Puedes personalizar esto para mostrar los detalles que necesites
        return cuenta.getCodigo() + " - " + monto + " (" + (esDebe ? "Debe" : "Haber") + ")";
    }
}

