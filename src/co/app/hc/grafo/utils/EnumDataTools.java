/*
 * System Constants class
 */
package co.app.hc.grafo.utils;

/**
 *
 * @author Harold Castillo (HC)
 * @see <a href="http://www.haroldcastillo.netau.net">HC Web</a>
 */
public enum EnumDataTools {

    WINDOW_SIZE_X("800"),
    WINDOW_SIZE_Y("500"),
    WINDOW_ESTATE_T("true"),
    WINDOW_ESTATE_F("false"),
    WINDOW_TITLE_VIEWPANEL("Grafos"),
    GRAPH_RADIO("30");
    
    private final String data;
    
    private EnumDataTools(String data) {
        this.data = data;
    }
    
    public String getValue(){
        return data;
    }
}
