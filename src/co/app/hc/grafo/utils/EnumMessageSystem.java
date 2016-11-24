/*
 * System Constants class
 */
package co.app.hc.grafo.utils;

/**
 *
 * @author Harold Castillo (HC)
 * @see <a href="http://www.haroldcastillo.netau.net">HC Web</a>
 */
public enum EnumMessageSystem {

    ERROR_MESSAGE("Error"),
    WARNING_MESSAGE("Alerta"),
    INFORMATION_MESSAGE("Información"),
    NUMBER_FORMAT("Ingrese solo valores numéricos "),
    EXCEPTION_MESSAGE("Excepción presentada en el método "),
    EXCEPTION_INDEX("Verifique que los datos ingresados sean correctos ");

    private final String data;

    private EnumMessageSystem(String data) {
        this.data = data;
    }

    public String getValue() {
        return data;
    }
}
