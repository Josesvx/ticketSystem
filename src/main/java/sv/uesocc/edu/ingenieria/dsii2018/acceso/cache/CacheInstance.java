package sv.uesocc.edu.ingenieria.dsii2018.acceso.cache;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Directorio;

/**
 *
 * @author katiro
 */
public class CacheInstance {

    private CacheManager cm;
    private Cache usuarioActual;
    private static CacheInstance obj;

    public static CacheInstance constructor() {
        if (obj == null) {
            return obj = new CacheInstance();
        } else {
            return obj;
        }

    }

    public void Instance() {
        if (cm == null) {
            cm = CacheManager.create();
        }
    }

    public void CrearCache(Directorio directorio) {
        cm.addCache("UsuarioActual");
        usuarioActual = cm.getCache("UsuarioActual");
        Element usuario = new Element("usuario", directorio.getUsuario());
        usuarioActual.put(usuario);
    }

    public void cerrarSesion() {
        cm.removeAllCaches();
        //cm.shutdown();
        //CacheManager.getInstance().shutdown();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("InicioSesion.jsf");
        } catch (IOException ex) {
            Logger.getLogger(CacheInstance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void comprobarLogin() {
        if (cm.cacheExists("UsuarioActual") == true) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("principal.jsf");
            } catch (IOException ex) {
                Logger.getLogger(CacheInstance.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }
    public void comprobarLoginPrincipal() {
        if (cm.cacheExists("UsuarioActual") == false) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("InicioSesion.jsf");
            } catch (IOException ex) {
                Logger.getLogger(CacheInstance.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }

}
