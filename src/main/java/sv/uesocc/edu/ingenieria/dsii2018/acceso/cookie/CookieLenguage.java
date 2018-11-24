package sv.uesocc.edu.ingenieria.dsii2018.acceso.cookie;

import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author daredevil
 */
public class CookieLenguage {

    private Cookie milf;
    private Cookie[] oreos;
    private boolean control = false;
    private final String user = "lenguage";

    public CookieLenguage() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        oreos = request.getCookies();
        if (oreos != null && oreos.length > 0) {
            for (Cookie cookie : oreos) {
                if (cookie.getName().equals(user)) {
                    milf = cookie;
                    control = true;
                    break;
                }
            }
        }

    }

    public void CrearCookieLenguage(String idioma) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

        oreos = request.getCookies();
        if (oreos != null && oreos.length > 0) {
            for (Cookie cookie : oreos) {
                if (cookie.getName().equals(user)) {
                    milf = cookie;
                    control = true;
                    break;
                }
            }
        }
        if (control == true) {
            milf.setValue(idioma);
        } else {
            milf = new Cookie(user, idioma);
            milf.setPath(request.getContextPath());
        }
        response.addCookie(milf);
    }

    public String getIdioma() {
        if (control == true) {
            return milf.getValue();
        } else {
            return "en_CA";
        }
    }
}
