package ues.edu.sv.catalogo;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.context.ExternalContext;
import java.util.List;

@FacesConverter(value = "CuentaConverter")
public class CuentaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        // Verifica si el valor es nulo o vacío
        if (value == null || value.isEmpty()) {
            return null;
        }

        // Obtener la lista de cuentas desde la sesión
        ExternalContext externalContext = context.getExternalContext();
        List<Cuenta> cuentas = (List<Cuenta>) externalContext.getSessionMap().get("cuentas");

        // Si la lista de cuentas es nula, no hay cuentas almacenadas
        if (cuentas == null) {
            return null;
        }

        // Buscar la cuenta con el código especificado
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getCodigo().equals(value)) {
                return cuenta;  // Retorna la cuenta encontrada
            }
        }

        // Si no se encuentra la cuenta, retornar null
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        // Verifica si el valor es nulo
        if (value == null) {
            return "";
        }

        // Asegúrate de que el valor sea una instancia de Cuenta
        if (value instanceof Cuenta) {
            Cuenta cuenta = (Cuenta) value;
            return cuenta.getCodigo();  // Retorna el código de la cuenta
        }

        return "";
    }
}
