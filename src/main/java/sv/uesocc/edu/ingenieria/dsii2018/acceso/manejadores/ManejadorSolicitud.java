package sv.uesocc.edu.ingenieria.dsii2018.acceso.manejadores;

import java.io.FileOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
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
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Estado;
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

    private ManejadorCorreo mail;
    private List<Solicitud> listaSol, listaIT, listaGen;
    private List<Prioridad> listaP;
    private List<Estado> listaEs;
    private List<EstadoSolicitud> listaESOl;
    private Solicitud solicitud;
    protected Solicitud solicitudS;
    private EstadoSolicitud estadoSolicitud;
    private EstadoSolicitud eSol;
    private Categoria categoria;
    private Directorio directorio, Departamento;
    private CookieInstance oreo;
    private String nombre, seguimiento, nombreDep;
    private int idCategoria, numero, id, id2, idPrioridad;
    private int numeroSolicitudes1, numeroSolicitudes2, numeroSolicitudes3, numeroSolicitudes4, numeroSolicitudes5, numeroSolicitudes6, numeroSolicitudes7, numeroSolicitudes8;
    private String redirecccion = null, finale = null;
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

        listaIT = new ArrayList<>();
        listaGen = new ArrayList<>();
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

        estadoSolicitud = new EstadoSolicitud();

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
        Directorio dir = dfl.find(oreo.UsuarioId());
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

        mail = new ManejadorCorreo();

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

    public List<Solicitud> llenarPorDirectorio() {
        List<Solicitud> listaSol = new ArrayList<>();
        try {
            listaSol = sfl.findByDirectory(oreo.UsuarioId());
        } catch (Exception ex) {
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

    public String CrearNumSeguimiento() {
        numero = (int) (Math.random() * 1000000) + 1;
        seguimiento = "T" + "S" + String.valueOf(numero);

        return seguimiento;
    }

    public void subirImagen(FileUploadEvent event) {
        String path = System.getProperty("user.home");
        String finalPath = path + "/img/tmp/" + event.getFile().getFileName();
        this.solicitud.setAdjunto(finalPath);
        if (event.getFile().getContents() != null && event.getFile().getContents().length > 0) {
            try (FileOutputStream fl = new FileOutputStream(finalPath)) {
                fl.write(event.getFile().getContents());
                fl.close();
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                message.setSummary("Adjunto guardado con exito ");
            } catch (Exception e) {
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Se debe seleccionar un item  valido para adjuntar a su Solicitud");
            }
            FacesContext.getCurrentInstance().addMessage("Mensaje", message);
        }

    }

    public String CrearSolicitud() {
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
            mail.EnviarCorreo(this.solicitud, "katirox9@gmail.com");
            finale = CrearEstadoS();
        } catch (Exception e) {
        }
        return finale;
    }

    public String CrearEstadoS() {
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
            redirecccion = "principal.jsf?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al crear el registro"));
        }
        return redirecccion;
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

    //METODO PARA BUSCAR TODOS LOS ESTADOS CAMBIADOS PARA LA SOLICITUD BUSCADA
    public String DevolverEstado(Solicitud s) {
        listaEs = new ArrayList<>();
        listaEs = efl.findLastEstado(s.getIdSolicitud());
        if (listaEs.isEmpty()) {
            return "Sin Estado";
        } else {
            return listaEs.get(0).getNombre();
        }

    }

    //METODO PARA BUSCAR LAS FECHAS DE CREACION DE LAS SOLICITUDES EN LA TABLA ESTADO SOLICITUD
    public String DevolverFechaCreacion(Solicitud s) {
        listaESOl = new ArrayList<>();
        listaESOl = esfl.findByCreation(s.getIdSolicitud());
        if (listaESOl.isEmpty()) {
            return "Sin fechaCreacion";
        } else {
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yy");
            return formateador.format(listaESOl.get(0).getFecha());
        }

    }
}
