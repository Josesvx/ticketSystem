package sv.uesocc.edu.ingenieria.dsii2018.acceso.manejadores;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.controladores.SolicitudFacadeLocal;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Solicitud;

/**
 *
 * @author katiro
 */
@ManagedBean
@Named
@ViewScoped
public class ManejadorEstadistica implements Serializable {

    private int numeroSolicitudes1, numeroSolicitudes2, numeroSolicitudes3, numeroSolicitudes4, numeroSolicitudes5,
            numeroSolicitudes6, numeroSolicitudes7, numeroSolicitudes8;
    private int numeroPorEstado, numeroPorEstado1, numeroPorEstado2, numeroPorEstado3, numeroPorEstado4, numeroPorEstado5;
    private int numeroPorPrioridad, numeroPorPrioridad1, numeroPorPrioridad2, numeroPorPrioridad3;
    private int numeroPorCat, numeroPorCat1, numeroPorCat2, numeroPorCat3, numeroPorFecha;
    private List<Solicitud> listaSol;
    private BarChartModel barModel;
    private BarChartModel barModelEstado;
    private BarChartModel barModelPrioridad;
    private BarChartModel barModelCategoria;
    private LineChartModel lineModel;
    private LineChartModel lineModelEstado;
    private LineChartModel lineModelPrioridad;
    private LineChartModel lineModelCategoria;
    private PieChartModel pieModel;
    private PieChartModel pieModelEstado;
    private PieChartModel pieModelPrioridad;
    private PieChartModel pieModelCategoria;
    private Date date1, date2;
    private Date fecha1;
    private Date fecha2, d1, d2;
    String fechaSeleccionada;
    private List<Solicitud> solicitudFecha, listaTabla;

    @EJB
    private SolicitudFacadeLocal sfl;

    @PostConstruct
    public void init() {

        llenarLista();

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

        for (int i = 1; i <= 5; i++) {
            switch (i) {
                case 1:
                    numeroPorEstado1 = llenarPorEstado(i);
                    break;
                case 2:
                    numeroPorEstado2 = llenarPorEstado(i);
                    break;
                case 3:
                    numeroPorEstado3 = llenarPorEstado(i);
                    break;
                case 4:
                    numeroPorEstado4 = llenarPorEstado(i);
                    break;
                case 5:
                    numeroPorEstado5 = llenarPorEstado(i);
                    break;
            }
        }

        for (int i = 1; i <= 3; i++) {
            switch (i) {
                case 1:
                    numeroPorPrioridad1 = llenarPorPrioridad(i);
                    break;
                case 2:
                    numeroPorPrioridad2 = llenarPorPrioridad(i);
                    break;
                case 3:
                    numeroPorPrioridad3 = llenarPorPrioridad(i);
                    break;
            }
        }

        for (int i = 1; i <= 3; i++) {
            switch (i) {
                case 1:
                    numeroPorCat1 = llenarPorCategoria(i);
                    break;
                case 2:
                    numeroPorCat2 = llenarPorCategoria(i);
                    break;
                case 3:
                    numeroPorCat3 = llenarPorCategoria(i);
                    break;
            }
        }

        llenarDepartamento();
        llenarEstado();
        llenarPrioridad();
        llenarCategoria();
        llenarPorFecha();

    }

    public Date getFecha1() {
        return fecha1;
    }

    public void setFecha1(Date fecha1) {
        this.fecha1 = fecha1;
    }

    public Date getFecha2() {
        return fecha2;
    }

    public void setFecha2(Date fecha2) {
        this.fecha2 = fecha2;
    }
    

    public void llenarDepartamento() {
        createBarModel();
        initBarModel();
        createLineModel();
        createPieModel();
    }

    public void llenarEstado() {
        createBarModelestado();
        initBarModelestado();
        createLineModelEstado();
        createPieModelEstado();
    }

    public void llenarPrioridad() {
        createBarModelPrioridad();
        initBarModelPrioridad();
        createLineModelPrioridad();
        createPieModelPrioridad();
    }

    public void llenarCategoria() {
        createBarModelCategoria();
        initBarModelCategoria();
        createLineModelCategoria();
        createPieModelCategoria();
    }

    public List<Solicitud> MostrarEstadistica(int tipo, Object[] arrayParam) {
        return null;
    }

    public List<Solicitud> llenarLista() {
        List<Solicitud> listaS = sfl.findByEstado(1);
        if (listaS != null && !listaS.isEmpty()) {
            listaSol = listaS;
        } else {
            listaSol = new ArrayList<>();
        }
        return listaSol;
    }

    public int llenarPorEstado(int id) {
        numeroPorEstado = sfl.findByStatus(id);
        return numeroPorEstado;
    }

    public List<Solicitud> llenarPorFecha() {
        solicitudFecha = sfl.findByDates(fecha1, fecha2);
        return solicitudFecha;
    }
    
    public List<Solicitud> llenarDesdeFecha(){
        solicitudFecha=sfl.findByDates(fecha1, new Date());
        return solicitudFecha;
    }

    public int llenarPorPrioridad(int id) {
        List<Solicitud> lista = sfl.findByPrioridad(id);
        if (lista != null || !lista.isEmpty()) {
            numeroPorPrioridad = lista.size();
        } else {
            numeroPorPrioridad = 0;
        }
        return numeroPorPrioridad;
    }

    public int llenarPorCategoria(int id) {
        List<Solicitud> lista = sfl.findByCategoria(id);
        if (lista != null || !lista.isEmpty()) {
            numeroPorCat = lista.size();
        } else {
            numeroPorCat = 0;
        }
        return numeroPorCat;
    }

    public void onDateSelect(SelectEvent event) throws ParseException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", dateFormat.format(event.getObject())));
    }

    private void createBarModel() {
        barModel = initBarModel();
        barModel.setTitle("Solicitudes por Departamento");
        barModel.setLegendPosition("ne");

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Departamento");

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Numero Solicitudes");
        yAxis.setMin(0);
        yAxis.setMax(sfl.count() + 10);
    }

    private void createBarModelestado() {
        barModelEstado = initBarModelestado();
        barModelEstado.setTitle("Solicitudes Por Estado");
        barModelEstado.setLegendPosition("ne");

        Axis xAxis = barModelEstado.getAxis(AxisType.X);
        xAxis.setLabel("Estado");

        Axis yAxis = barModelEstado.getAxis(AxisType.Y);
        yAxis.setLabel("Numero Solicitudes");
        yAxis.setMin(0);
        yAxis.setMax(sfl.count() + 10);
    }

    private void createBarModelPrioridad() {
        barModelPrioridad = initBarModelPrioridad();
        barModelPrioridad.setTitle("Solicitudes Por Prioridad");
        barModelPrioridad.setLegendPosition("ne");

        Axis xAxis = barModelPrioridad.getAxis(AxisType.X);
        xAxis.setLabel("Prioridad");

        Axis yAxis = barModelPrioridad.getAxis(AxisType.Y);
        yAxis.setLabel("Numero Solicitudes");
        yAxis.setMin(0);
        yAxis.setMax(sfl.count() + 10);
    }

    private void createBarModelFecha() {
        barModelPrioridad = initBarModelPrioridad();
        barModelPrioridad.setTitle("Solicitudes Por Prioridad");
        barModelPrioridad.setLegendPosition("ne");

        Axis xAxis = barModelPrioridad.getAxis(AxisType.X);
        xAxis.setLabel("Prioridad");

        Axis yAxis = barModelPrioridad.getAxis(AxisType.Y);
        yAxis.setLabel("Numero Solicitudes");
        yAxis.setMin(0);
        yAxis.setMax(sfl.count() + 10);
    }

    private void createBarModelCategoria() {
        barModelCategoria = initBarModelCategoria();
        barModelCategoria.setTitle("Solicitudes Por Categoria");
        barModelCategoria.setLegendPosition("ne");

        Axis xAxis = barModelCategoria.getAxis(AxisType.X);
        xAxis.setLabel("Categoria");

        Axis yAxis = barModelCategoria.getAxis(AxisType.Y);
        yAxis.setLabel("Numero Solicitudes");
        yAxis.setMin(0);
        yAxis.setMax(sfl.count() + 10);
    }

    private void createLineModel() {
        lineModel = initLineModel();
        lineModel.setTitle("Solicitudes por Departamento");
        lineModel.setLegendPosition("e");
        lineModel.setShowPointLabels(true);
        lineModel.getAxes().put(AxisType.X, new CategoryAxis("Departamentos"));
        Axis yAxis = lineModel.getAxis(AxisType.Y);
        yAxis.setLabel("Numero de Solicitudes");
        yAxis.setMin(0);
        yAxis.setMax(sfl.count() + 10);
    }

    private void createLineModelEstado() {
        lineModelEstado = initLineModelEstado();
        lineModelEstado.setTitle("Solicitudes por Estado");
        lineModelEstado.setLegendPosition("e");
        lineModelEstado.setShowPointLabels(true);
        lineModelEstado.getAxes().put(AxisType.X, new CategoryAxis("Estados"));
        Axis yAxis = lineModelEstado.getAxis(AxisType.Y);
        yAxis.setLabel("Numero de Solicitudes");
        yAxis.setMin(0);
        yAxis.setMax(sfl.count() + 10);
    }

    private void createLineModelPrioridad() {
        lineModelPrioridad = initLineModelPrioridad();
        lineModelPrioridad.setTitle("Solicitudes por Prioridad");
        lineModelPrioridad.setLegendPosition("e");
        lineModelPrioridad.setShowPointLabels(true);
        lineModelPrioridad.getAxes().put(AxisType.X, new CategoryAxis("Prioridades"));
        Axis yAxis = lineModelPrioridad.getAxis(AxisType.Y);
        yAxis.setLabel("Numero de Solicitudes");
        yAxis.setMin(0);
        yAxis.setMax(sfl.count() + 10);
    }

    private void createLineModelCategoria() {
        lineModelCategoria = initLineModelCategoria();
        lineModelCategoria.setTitle("Solicitudes por Categoria");
        lineModelCategoria.setLegendPosition("e");
        lineModelCategoria.setShowPointLabels(true);
        lineModelCategoria.getAxes().put(AxisType.X, new CategoryAxis("Categorias"));
        Axis yAxis = lineModelCategoria.getAxis(AxisType.Y);
        yAxis.setLabel("Numero de Solicitudes");
        yAxis.setMin(0);
        yAxis.setMax(sfl.count() + 10);
    }

    private void createPieModel() {
        pieModel = new PieChartModel();

        pieModel.set("Reclutamiento", numeroSolicitudes1);
        pieModel.set("Contabilidad", numeroSolicitudes2);
        pieModel.set("HR", numeroSolicitudes3);
        pieModel.set("IT", numeroSolicitudes4);
        pieModel.set("Mantenimiento", numeroSolicitudes5);
        pieModel.set("Seguridad", numeroSolicitudes6);
        pieModel.set("Teleoperadores", numeroSolicitudes7);
        pieModel.set("Gerencia", numeroSolicitudes8);

        pieModel.setTitle("Solicitudes por Departamento");
        pieModel.setLegendPosition("w");
        pieModel.setShadow(false);
    }

    private void createPieModelEstado() {
        pieModelEstado = new PieChartModel();

        pieModelEstado.set("Creado", numeroPorEstado1);
        pieModelEstado.set("Asignado", numeroPorEstado2);
        pieModelEstado.set("Pausado", numeroPorEstado3);
        pieModelEstado.set("Cerrado", numeroPorEstado4);
        pieModelEstado.set("Reabierto", numeroPorEstado5);

        pieModelEstado.setTitle("Solicitudes por Estado");
        pieModelEstado.setLegendPosition("w");
        pieModelEstado.setShadow(false);
    }

    private void createPieModelPrioridad() {
        pieModelPrioridad = new PieChartModel();

        pieModelPrioridad.set("Baja", numeroPorPrioridad1);
        pieModelPrioridad.set("Media", numeroPorPrioridad2);
        pieModelPrioridad.set("Alta", numeroPorPrioridad3);

        pieModelPrioridad.setTitle("Solicitudes por Prioridad");
        pieModelPrioridad.setLegendPosition("w");
        pieModelPrioridad.setShadow(false);
    }

    private void createPieModelCategoria() {
        pieModelCategoria = new PieChartModel();

        pieModelCategoria.set("Infraestructura", numeroPorCat1);
        pieModelCategoria.set("Hardware", numeroPorCat2);
        pieModelCategoria.set("Software", numeroPorCat3);

        pieModelCategoria.setTitle("Solicitudes por Categoria");
        pieModelCategoria.setLegendPosition("w");
        pieModelCategoria.setShadow(false);
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();

        ChartSeries solicitudes = new ChartSeries();
        solicitudes.setLabel("Departamento");
        solicitudes.set("Reclutamiento", numeroSolicitudes1);
        solicitudes.set("Contabilidad", numeroSolicitudes2);
        solicitudes.set("HR", numeroSolicitudes3);
        solicitudes.set("IT", numeroSolicitudes4);
        solicitudes.set("Mantenimiento", numeroSolicitudes5);
        solicitudes.set("Seguridad", numeroSolicitudes6);
        solicitudes.set("Teleoperadores", numeroSolicitudes7);
        solicitudes.set("Gerencia", numeroSolicitudes8);

        model.addSeries(solicitudes);
        return model;
    }

    private BarChartModel initBarModelestado() {
        BarChartModel model = new BarChartModel();

        ChartSeries solicitudes = new ChartSeries();
        solicitudes.setLabel("Estado");
        solicitudes.set("Creado", numeroPorEstado1);
        solicitudes.set("Asignado", numeroPorEstado2);
        solicitudes.set("Pausado", numeroPorEstado3);
        solicitudes.set("Cerrado", numeroPorEstado4);
        solicitudes.set("Reabierto", numeroPorEstado5);

        model.addSeries(solicitudes);
        return model;
    }

    private BarChartModel initBarModelfecha() {
        BarChartModel model = new BarChartModel();

        ChartSeries solicitudes = new ChartSeries();
        solicitudes.setLabel("Estado");
        solicitudes.set("Creado", numeroPorEstado1);
        solicitudes.set("Asignado", numeroPorEstado2);
        solicitudes.set("Pausado", numeroPorEstado3);
        solicitudes.set("Cerrado", numeroPorEstado4);
        solicitudes.set("Reabierto", numeroPorEstado5);

        model.addSeries(solicitudes);
        return model;
    }

    private BarChartModel initBarModelPrioridad() {
        BarChartModel model = new BarChartModel();

        ChartSeries solicitudes = new ChartSeries();
        solicitudes.setLabel("Prioridad");
        solicitudes.set("Baja", numeroPorPrioridad1);
        solicitudes.set("Media", numeroPorPrioridad2);
        solicitudes.set("Alta", numeroPorPrioridad3);

        model.addSeries(solicitudes);
        return model;
    }

    private BarChartModel initBarModelCategoria() {
        BarChartModel model = new BarChartModel();

        ChartSeries solicitudes = new ChartSeries();
        solicitudes.setLabel("Categoria");
        solicitudes.set("Infraestructura", numeroPorCat1);
        solicitudes.set("Hardware", numeroPorCat2);
        solicitudes.set("Software", numeroPorCat3);

        model.addSeries(solicitudes);
        return model;
    }

    private LineChartModel initLineModel() {
        LineChartModel model = new LineChartModel();

        ChartSeries series1 = new LineChartSeries();
        series1.setLabel("Departamentos");
        series1.set("Reclutamiento", numeroSolicitudes1);
        series1.set("Contabilidad", numeroSolicitudes2);
        series1.set("HR", numeroSolicitudes3);
        series1.set("IT", numeroSolicitudes4);
        series1.set("Mantenimiento", numeroSolicitudes5);
        series1.set("Seguridad", numeroSolicitudes6);
        series1.set("Teleoperadores", numeroSolicitudes7);
        series1.set("Gerencia", numeroSolicitudes8);

        model.addSeries(series1);
        return model;
    }

    private LineChartModel initLineModelEstado() {
        LineChartModel model = new LineChartModel();

        ChartSeries series1 = new LineChartSeries();
        series1.setLabel("Estados");
        series1.set("Creado", numeroPorEstado1);
        series1.set("Asignado", numeroPorEstado2);
        series1.set("Pausado", numeroPorEstado3);
        series1.set("Cerrado", numeroPorEstado4);
        series1.set("Reabierto", numeroPorEstado5);

        model.addSeries(series1);
        return model;
    }

    private LineChartModel initLineModelPrioridad() {
        LineChartModel model = new LineChartModel();

        ChartSeries series1 = new LineChartSeries();
        series1.setLabel("Prioridades");
        series1.set("Baja", numeroPorPrioridad1);
        series1.set("Media", numeroPorPrioridad2);
        series1.set("Alta", numeroPorPrioridad3);

        model.addSeries(series1);
        return model;
    }

    private LineChartModel initLineModelCategoria() {
        LineChartModel model = new LineChartModel();

        ChartSeries series1 = new LineChartSeries();
        series1.setLabel("Categorias");
        series1.set("Infraestructura", numeroPorCat1);
        series1.set("Hardware", numeroPorCat2);
        series1.set("Software", numeroPorCat3);

        model.addSeries(series1);
        return model;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public BarChartModel getBarModelEstado() {
        return barModelEstado;
    }

    public BarChartModel getBarModelPrioridad() {
        return barModelPrioridad;
    }

    public BarChartModel getBarModelCategoria() {
        return barModelCategoria;
    }

    public LineChartModel getLineModel() {
        return lineModel;
    }

    public LineChartModel getLineModelestado() {
        return lineModelEstado;
    }

    public LineChartModel getLineModelPrioridad() {
        return lineModelPrioridad;
    }

    public LineChartModel getLineModelCategoria() {
        return lineModelCategoria;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public PieChartModel getPieModelEstado() {
        return pieModelEstado;
    }

    public PieChartModel getPieModelPrioridad() {
        return pieModelPrioridad;
    }

    public PieChartModel getPieModelCategoria() {
        return pieModelCategoria;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public List<Solicitud> getSolicitudFecha() {
        return solicitudFecha;
    }

    public void setSolicitudFecha(List<Solicitud> solicitudFecha) {
        this.solicitudFecha = solicitudFecha;
    }

}
