/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.uesocc.edu.ingenieria.dsii2018.acceso.Idiomas.ws;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

@Named(value = "sesionBean")
@SessionScoped
public class SesionDeUsuarioBean implements Serializable {

    /**
     * Creates a new instance of idiomaBean
     */
    public SesionDeUsuarioBean() {
    }

    private String codigoIdioma = "en_CA";
    private static Map<String, Object> paises;

    static {
        paises = new LinkedHashMap<>();

        paises.put("Español", Locale.CANADA);
        paises.put("English", Locale.US);
    }

    public Map<String, Object> getPaises() {
        return paises;

    }

    public String getCodigoIdioma() {
        return codigoIdioma;
    }

    public void setCodigoIdioma(String codigoIdioma) {
        this.codigoIdioma = codigoIdioma;
    }

    public void cambioIdioma(ValueChangeEvent cambio) {
        if (cambio.getNewValue() != null) {
            try {
                String nuevoCodigo = cambio.getNewValue().toString();
                for (Map.Entry<String, Object> entryset : paises.entrySet()) {
                    Locale value = (Locale) entryset.getValue();
                    if (value.toString().compareTo(nuevoCodigo) == 0) {
                        FacesContext.getCurrentInstance().getViewRoot().setLocale(value);
                        return;
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }

}
