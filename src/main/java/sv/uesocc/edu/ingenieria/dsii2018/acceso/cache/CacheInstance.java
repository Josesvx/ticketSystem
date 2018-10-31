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
    private Directorio directorio;

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
        Element usuario = new Element("directorio", directorio);
        usuarioActual.put(usuario);
    }

    public String ObtenerNombreDepartamento() {
        String nombre;
        directorio = (Directorio) cm.getCache("UsuarioActual").get("directorio").getObjectValue();
        nombre = directorio.getIdDepartamento().getNombre();
        return nombre;
    }

    public Directorio ObtenerDirectorio() {
        directorio = (Directorio) cm.getCache("UsuarioActual").get("directorio").getObjectValue();
        return directorio;
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

    public void comprobarLoginRolGerente() {
        if (cm.cacheExists("UsuarioActual") == true) {
            directorio = (Directorio) cm.getCache("UsuarioActual").get("directorio").getObjectValue();
            if (!(directorio.getIdRol().getIdRol().toString().equals("4"))) {
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("principal.jsf");
                } catch (IOException ex) {
                    Logger.getLogger(CacheInstance.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("InicioSesion.jsf");
            } catch (IOException ex) {
                Logger.getLogger(CacheInstance.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void comprobarLoginRolTecnico() {
        if (cm.cacheExists("UsuarioActual") == true) {
            directorio = (Directorio) cm.getCache("UsuarioActual").get("directorio").getObjectValue();
            if (!(directorio.getIdRol().getIdRol().toString().equals("2"))) {
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("principal.jsf");
                } catch (IOException ex) {
                    Logger.getLogger(CacheInstance.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("InicioSesion.jsf");
            } catch (IOException ex) {
                Logger.getLogger(CacheInstance.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void comprobarLoginRolJefe() {
        if (cm.cacheExists("UsuarioActual") == true) {
            directorio = (Directorio) cm.getCache("UsuarioActual").get("directorio").getObjectValue();
            if (!(directorio.getIdRol().getIdRol().toString().equals("3"))) {
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("principal.jsf");
                } catch (IOException ex) {
                    Logger.getLogger(CacheInstance.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("InicioSesion.jsf");
            } catch (IOException ex) {
                Logger.getLogger(CacheInstance.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
