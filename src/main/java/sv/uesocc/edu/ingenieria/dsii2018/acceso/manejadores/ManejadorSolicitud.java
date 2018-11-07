package sv.uesocc.edu.ingenieria.dsii2018.acceso.manejadores;

import com.sun.javafx.scene.control.skin.VirtualFlow;
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
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.cookie.CookieInstance;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.controladores.CategoriaFacadeLocal;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.controladores.PrioridadFacadeLocal;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.controladores.DirectorioFacadeLocal;
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
    private List<Solicitud> listaSol, listaIT, listaGen;
    private List<Prioridad> listaP;
    private Solicitud solicitud;
    protected Solicitud solicitudS;
    private EstadoSolicitud eSol;
    private Categoria categoria;
    private Directorio directorio, Departamento;
    private CookieInstance oreo;
    private String nombre, seguimiento, nombreDep;
    private int idCategoria, numero, id, id2, idPrioridad;
    private String imagenAdjunto;
    private byte[] adjuntoProv;
    private int numeroSolicitudes1, numeroSolicitudes2, numeroSolicitudes3, numeroSolicitudes4, numeroSolicitudes5,
            numeroSolicitudes6, numeroSolicitudes7, numeroSolicitudes8;
    private BarChartModel barModel;

    @EJB
    private SolicitudFacadeLocal sfl;
    @EJB
    private CategoriaFacadeLocal cfl;
    @EJB
    private PrioridadFacadeLocal pfl;
    @EJB
    private DirectorioFacadeLocal dfl;
    @EJB
    private EstadoSolicitudFacadeLocal esfd;

    @PostConstruct
    public void init() {

        listaIT=new ArrayList<>();
        listaGen=new ArrayList<>();
        llenarDeps();
        llenarPrioridad();
        llenarCategoria();

        List<Solicitud> listaS = sfl.findByEstado(1);
        if (listaS != null && !listaS.isEmpty()) {
            listaSol = listaS;
        } else {
            listaSol = new ArrayList<>();
        }

        for (Solicitud solicitud1 : listaSol) {
            if (solicitud1.getIdCategoria().getIdCategoria() == 1 && solicitud1.getIdDirectorio().getIdRol().getIdRol() == 3) {
                listaIT.add(solicitud1);
            } else {
                listaGen.add(solicitud1);
            }
        }


        solicitud = new Solicitud();

        categoria = new Categoria();

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
        
        llenarFiltro();
    }

    public List<Solicitud> llenarFiltro() {
        Directorio dir= dfl.find(oreo.UsuarioId());
        if (dir.getIdDepartamento().getIdDepartamento() == 7 && dir.getIdRol().getIdRol() == 3) {
            if (listaIT != null && !listaIT.isEmpty()) {
                return listaIT;
            } else {
                return new ArrayList<>();
            }
        } else if (listaGen != null && !listaGen.isEmpty()) {
            return listaGen;
        } else {
            return new ArrayList<>();
        }
    }

    public void llenarCategoria() {
        List<Categoria> listaC = cfl.findAll();
        if (listaC != null && !listaC.isEmpty()) {
            listaCat = listaC;
        } else {
            listaCat = new ArrayList<>();
        }
    }

    public void llenarPrioridad() {
        List<Prioridad> listaPri = pfl.findAll();
        if (listaPri != null && !listaPri.isEmpty()) {
            listaP = listaPri;
        } else {
            listaP = new ArrayList<>();
        }
    }

    public void llenarDeps() {
        for (int i = 1; i <= 8; i++) {
            switch (i) {
                case 1:
                    numeroSolicitudes1 = sfl.findByDepartamento(i);
                    break;
                case 2:
                    numeroSolicitudes2 = sfl.findByDepartamento(i);
                    break;
                case 3:
                    numeroSolicitudes3 = sfl.findByDepartamento(i);
                    break;
                case 4:
                    numeroSolicitudes4 = sfl.findByDepartamento(i);
                    break;
                case 5:
                    numeroSolicitudes5 = sfl.findByDepartamento(i);
                    break;
                case 6:
                    numeroSolicitudes6 = sfl.findByDepartamento(i);
                    break;
                case 7:
                    numeroSolicitudes7 = sfl.findByDepartamento(i);
                    break;
                case 8:
                    numeroSolicitudes8 = sfl.findByDepartamento(i);
                    break;
            }
        }
    }

    public EstadoSolicitud geteSol() {
        return eSol;
    }

    public void seteSol(EstadoSolicitud eSol) {
        this.eSol = eSol;
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
        FacesMessage message = new FacesMessage();
        try {
            adjuntoProv = event.getFile().getContents();
            message.setSeverity(FacesMessage.SEVERITY_INFO);
            message.setSummary("Adjunto guardado con exito");
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
            if (adjuntoProv != null) {
                this.solicitud.setAdjunto(adjuntoProv);
                sfl.create(this.solicitud);

            } else {
                sfl.create(this.solicitud);

            }

        } catch (Exception e) {
        }

    }

    public String getImagenAdjunto() {
        return imagenAdjunto;
    }

    public void setImagenAdjunto(String imagenAdjunto) {
        this.imagenAdjunto = imagenAdjunto;
    }

    public void CrearEstadoS(Solicitud solicitud) {

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
