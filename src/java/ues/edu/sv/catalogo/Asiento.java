/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.edu.sv.catalogo;

// Clase Asiento - Representa el asiento contable completo
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Asiento {
    private Date fecha;
    private String descripcion;
    private List<Partida> partidas;

    public Asiento() {
        partidas = new ArrayList<>();
    }

    // Métodos para agregar partidas
    public void agregarPartida(Partida partida) {
        partidas.add(partida);
    }

    // Métodos para obtener totales del debe y haber
    public double getTotalDebe() {
        return partidas.stream().filter(Partida::isEsDebe).mapToDouble(Partida::getMonto).sum();
    }

    public double getTotalHaber() {
        return partidas.stream().filter(p -> !p.isEsDebe()).mapToDouble(Partida::getMonto).sum();
    }

    // Método de validación de cuadratura
    public boolean estaCuadrado() {
        return getTotalDebe() == getTotalHaber();
    }

    // Getters y Setters
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public List<Partida> getPartidas() { return partidas; }
    
    public String getsPartidas() {
        return partidas.stream()
                        .map(Partida::toString) // Llama al método toString() de cada partida
                        .collect(Collectors.joining(", "));
    }

    public double getTotal() {
        return partidas.stream()
                       .mapToDouble(Partida::getMonto)
                       .sum();
    }
}
