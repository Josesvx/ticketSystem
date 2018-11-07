package sv.uesocc.edu.ingenieria.dsii2018.acceso.manejadores;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletContext;
import org.primefaces.event.FileUploadEvent;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.cookie.CookieInstance;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.controladores.CategoriaFacadeLocal;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.controladores.PrioridadFacadeLocal;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.controladores.DirectorioFacadeLocal;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.controladores.EstadoFacadeLocal;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.controladores.EstadoSolicitudFacadeLocal;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.controladores.SolicitudFacadeLocal;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Categoria;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.DescripcionMantenimiento;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.EstadoSolicitud;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Directorio;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Prioridad;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Solicitud;

/**
 *
 * @author katiro
 */
@ManagedBean
@Named
@ViewScoped
public class ManejadorSolicitud implements Serializable {

    private List<Categoria> listaCat;
    private List<Solicitud> listaSol;
    private List<Prioridad> listaP;
    private Solicitud solicitud;
    private Solicitud solicitudS;
    private EstadoSolicitud estadoSolicitud;
    private EstadoSolicitud eSol;
    private Categoria categoria;
    private Directorio directorio, Departamento;
    private CookieInstance oreo;
    private String nombre, seguimiento, nombreDep;
    private int idCategoria, numero, id, id2, idPrioridad;
    private String imagenAdjunto;
    //   private byte[] adjuntoProv = null;
    FacesMessage message = new FacesMessage();

    @EJB
    private SolicitudFacadeLocal sfl;
    @EJB
    private CategoriaFacadeLocal cfl;
    @EJB
    private PrioridadFacadeLocal pfl;
    @EJB
    private DirectorioFacadeLocal dfl;
     @EJB
    private EstadoSolicitudFacadeLocal esfl;
     @EJB
    private EstadoFacadeLocal efl;

    @PostConstruct
    public void init() {

        List<Categoria> listaC = cfl.findAll();
        if (listaC != null && !listaC.isEmpty()) {
            listaCat = listaC;
        } else {
            listaCat = new ArrayList<>();
        }

        List<Prioridad> listaPri = pfl.findAll();
        if (listaPri != null && !listaPri.isEmpty()) {
            listaP = listaPri;
        } else {
            listaP = new ArrayList<>();
        }

        List<Solicitud> listaS = sfl.findByEstado(1);
        if (listaS != null && !listaS.isEmpty()) {
            listaSol = listaS;
        } else {
            listaSol = new ArrayList<>();
        }

        solicitud = new Solicitud();

        categoria = new Categoria();

        estadoSolicitud = new EstadoSolicitud();
        
        directorio = new Directorio();

        oreo = new CookieInstance();

        id2 = oreo.UsuarioId();
        Departamento = dfl.find(id2);
        nombreDep = Departamento.getIdDepartamento().getNombre();
        if (nombreDep != null && !nombreDep.isEmpty()) {
            nombre = nombreDep;
        } else {
            nombre = "No Funciona";
        }
        
        llenarPorDirectorio();

    }
    
    public List<Solicitud> llenarPorDirectorio(){
        List<Solicitud> listaSol= new ArrayList<>();
        try{
            listaSol=sfl.findByDirectory(oreo.UsuarioId());
        }catch(Exception ex){
            throw ex;
        }
        return listaSol;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Prioridad> getListaP() {
        return listaP;
    }

    public void setListaP(List<Prioridad> listaP) {
        this.listaP = listaP;
    }

    public List<Categoria> getListaCat() {
        return listaCat;
    }

    public void setListaCat(List<Categoria> listaCat) {
        this.listaCat = listaCat;
    }

    public List<Solicitud> getListaSol() {
        return listaSol;
    }

    public void setListaSol(List<Solicitud> listaSol) {
        this.listaSol = listaSol;
    }

    public Solicitud getSolicitudS() {
        return solicitudS;
    }

    public void setSolicitudS(Solicitud solicitudS) {
        this.solicitudS = solicitudS;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

//    public byte[] getAdjuntoProv() {
//        return adjuntoProv;
//    }
//
//    public void setAdjuntoProv(byte[] adjuntoProv) {
//        this.adjuntoProv = adjuntoProv;
//    }
    public String CrearNumSeguimiento() {
        //nombre = cache.ObtenerNombreDepartamento().toUpperCase();
        nombre = "RECUERSOS HUMANOS";

        numero = (int) (Math.random() * 1000000) + 1;
        seguimiento = nombre.charAt(1) + nombre.charAt(2) + String.valueOf(numero);

        return seguimiento;
    }

    public static String guardarBlobEnFicheroTemporal(byte[] bytes, String nombreArchivo) {
        String ubicacionArchivo = null;
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String path = servletContext.getRealPath("")
                + File.separatorChar + "src"
                + File.separatorChar + "main"
                + File.separatorChar + "resources"
                + File.separatorChar + "img"
                + File.separatorChar + "tmp"
                + File.separatorChar + nombreArchivo;
        File f = null;
        InputStream in = null;

        try {
            f = new File(path);
            in = new ByteArrayInputStream(bytes);
            FileOutputStream out = new FileOutputStream(f.getAbsolutePath());

            int c = 0;
            while ((c = in.read()) >= 0) {
                out.write(c);
            }
            out.flush();
            out.close();
            ubicacionArchivo = "src/main/resources/img/tmp" + nombreArchivo;

        } catch (Exception e) {
            System.err.println("No se pudo cargar la imagen");
        }
        return ubicacionArchivo;
    }

    public void subirImagen(FileUploadEvent event) {

        try {
            this.solicitud.setAdjunto(event.getFile().getContents());
            message.setSeverity(FacesMessage.SEVERITY_INFO);
            message.setSummary("Adjunto guardado con exito ");
        } catch (Exception e) {
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Se debe seleccionar un item  valido para adjuntar a su Solicitud");
        }
        FacesContext.getCurrentInstance().addMessage("Mensaje", message);
    }

    public void CrearSolicitud() {
        try {
            this.solicitud.setIdSolicitud(sfl.count() + 1);
            this.solicitud.setAudFechaCreacion(new Date());
            id = oreo.UsuarioId();
            this.directorio = dfl.find(id);
            this.solicitud.setIdCategoria(cfl.find(1));
            this.solicitud.setAudNombreCreacion(directorio.getUsuario());
            this.solicitud.setAudStatus(true);
            this.solicitud.setNSeguimiento(CrearNumSeguimiento());
            this.solicitud.setIdCategoria(cfl.find(idCategoria));
            this.solicitud.setIdDirectorio(directorio);
            sfl.create(this.solicitud);
//            message.setSeverity(FacesMessage.SEVERITY_INFO);
//            message.setSummary("Tciker creado con exito");
            CrearEstadoS();
        } catch (Exception e) {
        }

    }

    public String getImagenAdjunto() {
        return imagenAdjunto;
    }

    public void setImagenAdjunto(String imagenAdjunto) {
        this.imagenAdjunto = imagenAdjunto;
    }
    public void CrearEstadoS() {
        try {
            this.estadoSolicitud.setIdEstadoSolicitud(esfl.count() + 1);
            this.estadoSolicitud.setFecha(new Date());
            this.estadoSolicitud.setIdEstado(efl.find(1));
            this.estadoSolicitud.setIdSolicitud(this.solicitud);
            this.estadoSolicitud.setJustificacion("Creada");
            id = oreo.UsuarioId();
            this.directorio = dfl.find(id);
            this.estadoSolicitud.setAudNombreCreacion(this.directorio.getUsuario());
            this.estadoSolicitud.setAudFechaCreacion(new Date());
            this.estadoSolicitud.setAudStatus(true);
            esfl.create(this.estadoSolicitud);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Registro con exito"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al crear el registro"));
        }
    }

    public Solicitud ObtenerSolicitud(String codigo) {
        return null;
    }

    public List<Solicitud> ObtenerCreadas() {
        return null;
    }

    public List<Solicitud> ObtenerSolicitudesXTec(int tec) {
        return null;
    }

    public List<Solicitud> SolicitudePorCorrelativo(String correlativo) {
        return null;
    }

    public void BuscarHistorial(int idSolicitud) {

    }

    public void ActualizarDatos(Solicitud solicitud, DescripcionMantenimiento mantenimientoDescripcion) {

    }

    public void Actualizar(Solicitud solicitud) {
        Prioridad p = pfl.find(idPrioridad);
        solicitudS.setIdPrioridad(p);
        sfl.edit(solicitudS);
    }

    public List<Solicitud> ObtenerPorUsuario(int idDirectorio) {
        return null;
    }

    public void Actualizar(Solicitud solicitud, String comentario) {

    }

    public int getIdPrioridad() {
        return idPrioridad;
    }

    public void setIdPrioridad(int idPrioridad) {
        this.idPrioridad = idPrioridad;
    }
}
