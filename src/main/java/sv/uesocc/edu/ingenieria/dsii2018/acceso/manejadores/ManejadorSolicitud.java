package sv.uesocc.edu.ingenieria.dsii2018.acceso.manejadores;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
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

    private ManejadorCorreo mail;
    private List<Solicitud> listaSol, listaIT, listaGen, listaSoli, listaSlc;
    private List<Solicitud>  listaITP, listaGenP, listaSoliP, listaSolP;
    private List<Prioridad> listaP;
    private List<DescripcionMantenimiento> listaDM;
    private DescripcionMantenimiento descMant;
    private MantenimientoEncargado mantEnc;
    private Encargado encargado, tmpEnc;
    private List<Estado> listaEs;
    private List<EstadoSolicitud> listaESOl;
    private List<Directorio> creadas, creadasIT, creadasIF;
    private Solicitud solicitud;
    private Solicitud solicitudSeleccionada;
    private DescripcionMantenimiento descripcionM, tmp, DMSeleccionada;
    private List<Solicitud> selectedSolicitud;
    protected Solicitud solicitudS;
    private EstadoSolicitud estadoSolicitud;
    private EstadoSolicitud eSol;
    private Estado estado;
    private Categoria categoria;
    private Directorio directorio, Departamento, dir;
    private CookieInstance oreo;
    private String imagenAdjunto;
    private byte[] adjuntoProv;
    private String nombre, seguimiento, nombreDep, redirecccion = null, finale = null, nombreC, retorno, justificacion;
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
        
        listaITP = new ArrayList<>();
        listaGenP = new ArrayList<>();
        
        llenarDeps();
        llenarPrioridad();
        llenarCategoria();
        creadas = new ArrayList<>();
        numeroESol = esfl.count() + 1;
        listaSol = new ArrayList<>();
        listaSoli = new ArrayList<>();
        listaDM = new ArrayList<>();
        creadasIT = new ArrayList<>();
        creadasIF = new ArrayList<>();

        //listaSlc = new ArrayList<>();
        List<Solicitud> LS2 = new ArrayList<>();
        for (Solicitud solicitud1 : sfl.findAll()) {
            LS2 = sfl.findByEstado(solicitud1.getIdSolicitud());
            if (LS2 != null && !LS2.isEmpty()) {
                listaSoli.add(LS2.get(0));
            } else {
                LS2 = new ArrayList<>();
            }
        }
      
        if (listaSoli != null && !listaSoli.isEmpty()) {
            listaSol = listaSoli;
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
        for (Directorio directorio1 : findxAud()) {
            if (directorio1.getIdDepartamento().getIdDepartamento()==7) {
                creadasIT.add(directorio1);
            } else {
                creadasIF.add(directorio1);
            }
        }
        
        
        
        
        
        
        
        
        listaSoliP = sfl.findByPausadas();
      
        if (listaSoliP != null && !listaSoliP.isEmpty()) {
            listaSolP = listaSoliP;
        } else {
            listaSolP = new ArrayList<>();
        }

        for (Solicitud solicitud1 : listaSolP) {
            if (solicitud1.getIdCategoria().getIdCategoria() == 1 ) {
                
                    
                       listaITP.add(solicitud1); 
                    
                
                
            } else {
                listaGenP.add(solicitud1);
            }
        }

        
        
        
        
        
        
        
        
        
        

        estado = new Estado();

        estadoSolicitud = new EstadoSolicitud();

        solicitud = new Solicitud();

        categoria = new Categoria();

        directorio = new Directorio();

        descMant = new DescripcionMantenimiento();

        descripcionM = new DescripcionMantenimiento();

        mantEnc = new MantenimientoEncargado();

        encargado = new Encargado();

        tmpEnc = new Encargado();

        estadoSolicitud = new EstadoSolicitud();

        oreo = new CookieInstance();

        mail = new ManejadorCorreo();

        id2 = oreo.UsuarioId();
        Departamento = dfl.find(id2);
        nombreDep = Departamento.getIdDepartamento().getNombre();
        if (nombreDep != null && !nombreDep.isEmpty()) {
            nombre = nombreDep;
        } else {
            nombre = "No Funciona";
        }

        llenarFiltro();
        llenarFiltroITGerente();
        llenarFiltroManGerente();
        ObtenerSolicitudesXTec();
        findxAud();
        llenarAudIT();

    }

    public List<Solicitud> llenarFiltro() {
        Directorio dir = dfl.find(oreo.UsuarioId());
        if (dir.getIdDepartamento().getIdDepartamento() == 7 && dir.getIdRol().getIdRol() == 3) {
            if (listaGen != null && !listaGen.isEmpty()) {
                return listaGen;
            } else {
                return new ArrayList<>();
            }
        } else if (listaIT != null && !listaIT.isEmpty()) {
            return listaIT;
        } else {
            return new ArrayList<>();
        }
    }

    public List<Solicitud> llenarFiltroManGerente() {
        Directorio dir = dfl.find(oreo.UsuarioId());

        if (listaGen != null && !listaGen.isEmpty()) {
            return listaGen;
        } else {
            return new ArrayList<>();
        }

    }
     public List<Directorio> llenarAudIT() {
        Directorio dir = dfl.find(oreo.UsuarioId());

        if (creadasIT != null && !creadasIT.isEmpty()) {
            return creadasIT;
        } else {
            return new ArrayList<>();
        }

    }

    public List<Solicitud> llenarFiltroITGerente() {
        Directorio dir = dfl.find(oreo.UsuarioId());

        if (listaIT != null && !listaIT.isEmpty()) {
            return listaIT;
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

    public List<DescripcionMantenimiento> llenarPorCorrelativo() {

        try {
            listaSol = sfl.findByTecnic(oreo.UsuarioId());
            listaDM = dmfl.FindByCorrelativo(listaSol.get(0).getCorrelativo());
        } catch (Exception ex) {
            throw ex;
        }
        return listaDM;
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
        this.solicitud.setAdjunto(event.getFile().getFileName());
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

    public Solicitud ObtenerSolicitud(int codigo) {
        Solicitud s = sfl.find(codigo);
        return s;
    }

    public List<Directorio> findxAud() {
        try {
            creadas = dfl.findByAuditor(dfl.find(oreo.UsuarioId()).getUsuario());

        } catch (Exception e) {
        }

        return creadas;
    }

    //METODO PARA OBTENER LAS SOLITUDES QUE SE HAN ASIGNADO A UN TECNICO
    public Solicitud ObtenerSolicitudesXTec() {
        listaSol = sfl.findByTecnic(oreo.UsuarioId());
        if (listaSol == null || listaSol.isEmpty()) {
            return null;
        } else {
            listaEs = new ArrayList<>();
            listaEs = efl.findLastEstado(listaSol.get(0).getIdSolicitud());
            if (listaEs.get(0).getIdEstado().equals(2)) {
                return listaSol.get(0);
            } else {
                return null;
            }
        }
    }

    public String nombreC() {
        listaSol = sfl.findByTecnic(oreo.UsuarioId());
        if (listaSol == null || listaSol.isEmpty()) {
            return null;
        } else {
            nombreC = listaSol.get(0).getIdDirectorio().getNombre1() + " "
                    + listaSol.get(0).getIdDirectorio().getNombre2() + " "
                    + listaSol.get(0).getIdDirectorio().getApellido1() + " "
                    + listaSol.get(0).getIdDirectorio().getApellido2();
            return nombreC;
        }
    }

    public String comprobar() {
        listaSol = sfl.findByTecnic(oreo.UsuarioId());
        if (listaSol == null || listaSol.isEmpty()) {
            return "none";
        } else {
            return "block";
        }
    }

    public String comprobar2() {
        listaSol = sfl.findByTecnic(oreo.UsuarioId());
        if (listaSol == null || listaSol.isEmpty()) {
            return "block";
        } else {
            return "none";
        }
    }

    public List<Solicitud> SolicitudePorCorrelativo(String correlativo) {
        return null;
    }

    public void BuscarHistorial(int idSolicitud) {

    }

    public void ActualizarDatos() {
        try {
            //OBTENIENDO LA SOLICITUD ASIGNADA AL TECNICO LOGEADO
            listaSol = sfl.findByTecnic(oreo.UsuarioId());
            tmp = dmfl.FindBySolicitudEncargado(listaSol.get(0).getIdSolicitud(), oreo.UsuarioId());

            //AGREGANDO LAS DESCRIPCIONES AL MANTENIMIENTO
            tmp.setDescripcionProblema(this.descripcionM.getDescripcionProblema());
            tmp.setDescripcionSolucion(this.descripcionM.getDescripcionSolucion());
            tmp.setAudFechaCreacion(new Date());
            id = oreo.UsuarioId();
            this.directorio = dfl.find(id);
            tmp.setAudNombreCreacion(this.directorio.getUsuario());
            dmfl.edit(tmp);

            //CAMBIANDO EL ESTADO DEL TICKET A TERMINADO            
            this.estadoSolicitud.setIdEstadoSolicitud(esfl.count() + 1);
            this.estadoSolicitud.setFecha(new Date());
            this.estadoSolicitud.setIdEstado(efl.find(4));
            this.estadoSolicitud.setIdSolicitud(listaSol.get(0));
            this.estadoSolicitud.setJustificacion("Terminada");
            this.estadoSolicitud.setAudNombreCreacion(this.directorio.getUsuario());
            this.estadoSolicitud.setAudFechaCreacion(new Date());
            this.estadoSolicitud.setAudStatus(true);
            esfl.create(this.estadoSolicitud);

            //CAMBIANDO EL ESTADO DEL TECNICO A LIBRE PARA PODER SER ASIGNADO A OTRAS SOLICITUDES
            tmpEnc = enfl.FindBySolicitudE(listaSol.get(0).getIdSolicitud(), this.directorio.getIdDirectorio());
            tmpEnc.setEstado(false);
            tmpEnc.setAudFechaModificacion(new Date());
            enfl.edit(tmpEnc);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Ticket Terminado"));
            FacesContext.getCurrentInstance().getExternalContext().redirect("principal.jsf");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al terminar el ticket"));
        }

    }

    public void Actualizar(Solicitud solicitud) {
        Prioridad p = pfl.find(idPrioridad);
        Directorio d = dfl.find(idDirectorio);
        this.directorio = dfl.find(oreo.UsuarioId());
        
        List<EstadoSolicitud> esEdit = esfl.findByLastEStado(solicitudS.getIdSolicitud());
        
        EstadoSolicitud edit = esEdit.get(0);
        
        edit.setAudStatus(false);
        
        esfl.edit(edit);
        
        

                
        
        //cambiando valor a false de un audStatus
        
        
        //llenarFiltro();

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
            //mail.EnviarCorreo(solicitudS, d);
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("AsignarPrioridad.jsf");
            } catch (IOException ex) {
                Logger.getLogger(ManejadorSolicitud.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        if (s == null) {
            return "No envio Solicitud";
        } else {
            listaEs = efl.findLastEstado(s.getIdSolicitud());
            if (listaEs.isEmpty()) {
                return "Sin Estado";
            } else {
                return listaEs.get(0).getNombre();
            }
        }

    }
    
    public int DevolverEstadoID(Solicitud s) {
        listaEs = new ArrayList<>();
        if (s == null) {
            return 0;
        } else {
            listaEs = efl.findLastEstado(s.getIdSolicitud());
            if (listaEs.isEmpty()) {
                return 0;
            } else {
                return listaEs.get(0).getIdEstado();
            }
        }

    }

    //METODO PARA BUSCAR LAS FECHAS DE CREACION DE LAS SOLICITUDES EN LA TABLA ESTADO SOLICITUD
    public String DevolverFechaCreacion(Solicitud s) {
        listaESOl = new ArrayList<>();
        if (s == null) {
            return "No envio Solicitud";
        } else {
            listaESOl = esfl.findByCreation(s.getIdSolicitud());
            if (listaESOl.isEmpty()) {
                return "Sin fechaCreacion";
            } else {
                SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yy");
                return formateador.format(listaESOl.get(0).getFecha());
            }
        }
    }

    public String Dialogo() {
        return retorno;
    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Solicitud Seleccionada", ((Solicitud) event.getObject()).getIdSolicitud().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Solicitud deseleccionada", ((Solicitud) event.getObject()).getIdSolicitud().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public List<Solicitud> getSelectedSolicitud() {
        return selectedSolicitud;
    }

    public void setSelectedSolicitud(List<Solicitud> selectedSolicitud) {
        this.selectedSolicitud = selectedSolicitud;
    }

    public Solicitud getSolicitudSeleccionada() {
        return solicitudSeleccionada;
    }

    public void setSolicitudSeleccionada(Solicitud solicitudSeleccionada) {
        this.solicitudSeleccionada = solicitudSeleccionada;
    }

    public DescripcionMantenimiento getDescripcionM() {
        return descripcionM;
    }

    public void setDescripcionM(DescripcionMantenimiento descripcionM) {
        this.descripcionM = descripcionM;
    }

    public DescripcionMantenimiento getDMSeleccionada() {
        return DMSeleccionada;
    }

    public void setDMSeleccionada(DescripcionMantenimiento DMSeleccionada) {
        this.DMSeleccionada = DMSeleccionada;
    }

    public String imagen(String ruta) {
        String path = System.getProperty("user.home") + "/img/tmp/" + ruta;
        String contenType = FacesContext.getCurrentInstance().getExternalContext().getMimeType(path);
        int en = ruta.indexOf(".") + 1;
        int fi = ruta.length();
        String re = ruta.substring(en, fi);
        if (re.equals("png") || re.equals("jpg") || re.equals("jpeg")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "mess" + contenType));
            return contenType;
        } else {
            return "";

        }
    }

    public StreamedContent archivo(String ruta) throws FileNotFoundException, IOException {
        StreamedContent files = null;
        String path = System.getProperty("user.home") + "/img/tmp/" + ruta;
        String contenType = FacesContext.getCurrentInstance().getExternalContext().getMimeType(path);
        try {
            StreamedContent sc = new DefaultStreamedContent(new FileInputStream(path), contenType, ruta);
            files = sc;
            //FacesContext.getCurrentInstance().getExternalContext().redirect("principal.jsf");
        } catch (FileNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "No contiene adjunto"));
        }
        return files;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public void pausar() {
        listaSol = sfl.findByTecnic(oreo.UsuarioId());
        id = oreo.UsuarioId();

        this.directorio = dfl.find(id);
        this.estadoSolicitud.setIdEstadoSolicitud(esfl.count() + 1);
        this.estadoSolicitud.setFecha(new Date());
        this.estadoSolicitud.setIdEstado(efl.find(3));
        this.estadoSolicitud.setIdSolicitud(listaSol.get(0));
        this.estadoSolicitud.setJustificacion(justificacion);
        this.estadoSolicitud.setAudNombreCreacion(this.directorio.getUsuario());
        this.estadoSolicitud.setAudFechaCreacion(new Date());
        this.estadoSolicitud.setAudStatus(true);
        esfl.create(this.estadoSolicitud);

        tmpEnc = enfl.FindBySolicitudE(listaSol.get(0).getIdSolicitud(), this.directorio.getIdDirectorio());
        tmpEnc.setEstado(false);
        tmpEnc.setAudFechaModificacion(new Date());
        enfl.edit(tmpEnc);

    }

    public List<Directorio> getCreadas() {
        return creadas;
    }

    public void setCreadas(List<Directorio> creadas) {
        this.creadas = creadas;
    }

    public List<Directorio> getCreadasIT() {
        return creadasIT;
    }

    public void setCreadasIT(List<Directorio> creadasIT) {
        this.creadasIT = creadasIT;
    }

    public List<Directorio> getCreadasIF() {
        return creadasIF;
    }

    public void setCreadasIF(List<Directorio> creadasIF) {
        this.creadasIF = creadasIF;
    }
    
    
        public List<Solicitud> llenarFiltroPAUSADOS() {
        Directorio dir = dfl.find(oreo.UsuarioId());
        if (dir.getIdDepartamento().getIdDepartamento() == 7 && dir.getIdRol().getIdRol() == 3) {
            if (listaGenP != null && !listaGenP.isEmpty() ) {
                
                return listaGenP;
            } else {
                return new ArrayList<>();
            }
        } else if (listaITP != null && !listaITP.isEmpty()) {
            return listaITP;
        } else {
            return new ArrayList<>();
        }
    }

    public List<Solicitud> getListaITP() {
        return listaITP;
    }

    public void setListaITP(List<Solicitud> listaITP) {
        this.listaITP = listaITP;
    }

    public List<Solicitud> getListaGenP() {
        return listaGenP;
    }

    public void setListaGenP(List<Solicitud> listaGenP) {
        this.listaGenP = listaGenP;
    }
        
        

}
