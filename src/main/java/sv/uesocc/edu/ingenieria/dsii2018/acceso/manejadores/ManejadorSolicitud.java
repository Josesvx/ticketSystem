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
import sv.uesocc.edu.ingenieria.dsii2018.acceso.controladores.DescripcionMantenimientoFacadeLocal;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.controladores.PrioridadFacadeLocal;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.controladores.DirectorioFacadeLocal;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.controladores.EncargadoFacadeLocal;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.controladores.EstadoFacadeLocal;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.controladores.EstadoSolicitudFacadeLocal;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.controladores.MantenimientoEncargadoFacadeLocal;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.controladores.SolicitudFacadeLocal;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Categoria;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.DescripcionMantenimiento;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.EstadoSolicitud;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Directorio;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Encargado;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Estado;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.MantenimientoEncargado;
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
    private DescripcionMantenimiento descMant;
    private MantenimientoEncargado mantEnc;
    private Encargado encargado;
    private List<Estado> listaEs;
    private List<EstadoSolicitud> listaESOl;
    private Solicitud solicitud;
    protected Solicitud solicitudS;
    private EstadoSolicitud estadoSolicitud;
    private EstadoSolicitud eSol;
    private Estado estado;
    private Categoria categoria;
    private Directorio directorio, Departamento, dir;
    private CookieInstance oreo;
    private String imagenAdjunto;
    private byte[] adjuntoProv;
    private String nombre, seguimiento, nombreDep, redirecccion = null, finale = null;
    private int idCategoria, numero, id, id2, idPrioridad, idDirectorio, numeroSolicitudes1, numeroSolicitudes2,
            numeroSolicitudes3, numeroSolicitudes4, numeroSolicitudes5, numeroSolicitudes6,
            numeroSolicitudes7, numeroSolicitudes8, numeroESol;
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
    @EJB
    private DescripcionMantenimientoFacadeLocal dmfl;
    @EJB
    private MantenimientoEncargadoFacadeLocal mefl;
    @EJB
    private EncargadoFacadeLocal enfl;

    private ManejadorTecnico mantec;

    @PostConstruct
    public void init() {
        listaIT = new ArrayList<>();
        listaGen = new ArrayList<>();
        llenarDeps();
        llenarPrioridad();
        llenarCategoria();
        numeroESol=esfl.count()+1;

        List<Solicitud> listaS = sfl.findByEstado(1);
        if (listaS != null && !listaS.isEmpty()) {
            listaSol = listaS;
        } else {
            listaSol = new ArrayList<>();
        }

        for (Solicitud solicitud1 : listaSol) {
            if (solicitud1.getIdCategoria().getIdCategoria() == 1) {
                listaIT.add(solicitud1);
            } else {
                listaGen.add(solicitud1);
            }
        }

        estado = new Estado();

        estadoSolicitud = new EstadoSolicitud();

        solicitud = new Solicitud();

        categoria = new Categoria();

        directorio = new Directorio();

        descMant = new DescripcionMantenimiento();

        mantEnc = new MantenimientoEncargado();

        encargado = new Encargado();

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
            if (adjuntoProv != null) {
                this.solicitud.setAdjunto(adjuntoProv);
                sfl.create(this.solicitud);

            } else {
                sfl.create(this.solicitud);

            }

            sfl.create(this.solicitud);
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
        Directorio d = dfl.find(idDirectorio);
        this.directorio = dfl.find(oreo.UsuarioId());

        //crear DescripcionMtto
        try {
            this.descMant.setIdDescripcionMantenimiento(dmfl.count() + 1);
            this.descMant.setAudFechaCreacion(new Date());
            this.descMant.setAudNombreCreacion(this.directorio.getUsuario());
            this.descMant.setAudStatus(true);
            //crear MantEncargado
            this.mantEnc.setIdMantenimientoEncargado(mefl.count() + 1);
            this.mantEnc.setAudFechaCreacion(new Date());
            this.mantEnc.setAudNombreCreacion(this.directorio.getUsuario());
            this.mantEnc.setAudStatus(true);
            this.mantEnc.setIdSolicitud(solicitudS);
            this.mantEnc.setIdDescripcionMantenimiento(descMant);
            //crear Encargado
            this.encargado.setIdEncargado(enfl.count() + 1);
            this.encargado.setIdDirectorio(d);
            this.encargado.setIdMantenimientoEncargado(mantEnc);
            this.encargado.setEstado(true);
            this.encargado.setAudFechaCreacion(new Date());
            this.encargado.setAudNombreCreacion(this.directorio.getUsuario());
            this.encargado.setAudStatus(true);
            //cambiar EstadoSolicitud
            this.estadoSolicitud.setIdEstadoSolicitud(numeroESol);
            this.estadoSolicitud.setIdEstado(efl.find(2));
            this.estadoSolicitud.setJustificacion("Asignada");
            id = oreo.UsuarioId();
            this.directorio = dfl.find(id);
            this.estadoSolicitud.setAudNombreCreacion(this.directorio.getUsuario());
            this.estadoSolicitud.setAudFechaCreacion(new Date());
            this.estadoSolicitud.setAudStatus(true);
            this.estadoSolicitud.setFecha(new Date());
            this.estadoSolicitud.setIdSolicitud(solicitudS);
            
            
            solicitudS.setIdPrioridad(p);
            sfl.edit(solicitudS);
            dmfl.create(descMant);
            mefl.create(mantEnc);
            enfl.create(encargado);
            esfl.create(estadoSolicitud);

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error"));
        }

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

    public List<Solicitud> getListaIT() {
        return listaIT;
    }

    public void setListaIT(List<Solicitud> listaIT) {
        this.listaIT = listaIT;
    }

    public List<Solicitud> getListaGen() {
        return listaGen;
    }

    public void setListaGen(List<Solicitud> listaGen) {
        this.listaGen = listaGen;
    }

    public int getIdDirectorio() {
        return idDirectorio;
    }

    public void setIdDirectorio(int idDirectorio) {
        this.idDirectorio = idDirectorio;
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
