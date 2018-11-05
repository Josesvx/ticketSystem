package sv.uesocc.edu.ingenieria.dsii2018.acceso.manejadores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
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
public class ManejadorEstadistica implements Serializable{

    private int numeroSolicitudes1, numeroSolicitudes2, numeroSolicitudes3, numeroSolicitudes4, numeroSolicitudes5,
            numeroSolicitudes6, numeroSolicitudes7, numeroSolicitudes8;
    private int numeroPorEstado, numeroPorEstado1, numeroPorEstado2, numeroPorEstado3, numeroPorEstado4
            ,numeroPorEstado5;
    private List<Solicitud> listaSol;
    private BarChartModel barModel;
    private BarChartModel barModelEstado;
    private LineChartModel lineModel;
    private LineChartModel lineModelEstado;
    private PieChartModel pieModel;
    private PieChartModel pieModelEstado;

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
        
        for(int i=1; i<=5; i++){
            switch(i){
                case 1:
                    numeroPorEstado1= llenarPorEstado(i);
                    break;
                case 2:
                    numeroPorEstado2= llenarPorEstado(i);
                    break;
                case 3:
                    numeroPorEstado3= llenarPorEstado(i);
                    break;
                case 4:
                    numeroPorEstado4= llenarPorEstado(i);
                    break;
                case 5:
                    numeroPorEstado5= llenarPorEstado(i);
                    break;
            }
        }
        
        createBarModel();
        initBarModel();
        createLineModel();
        createPieModel();
        initBarModelestado();
        createBarModelestado();
        createLineModelEstado();
        createPieModelEstado();

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
    
    public int llenarPorEstado(int id){
        List<Solicitud> lista = sfl.findByEstado(id);
        if(lista != null || !lista.isEmpty()){
            numeroPorEstado=lista.size();
        }else{
            numeroPorEstado=0;
        }
        return numeroPorEstado;
    }
    
    private void createBarModel() {
        barModel = initBarModel();
        barModel.setTitle("Solicitudes Por Departamento");
        barModel.setLegendPosition("ne");
         
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Departamento");
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Numero Solicitudes");
        yAxis.setMin(0);
        yAxis.setMax(15);
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
        yAxis.setMax(15);
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
        yAxis.setMax(10);
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
        yAxis.setMax(10);
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
         
        pieModelEstado.setTitle("Solicitudes por Departamento");
        pieModelEstado.setLegendPosition("w");
        pieModelEstado.setShadow(false);
    }
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
 
        ChartSeries solicitudes = new ChartSeries();
        solicitudes.setLabel("Solicitudes");
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
    
    private LineChartModel initLineModel() {
        LineChartModel model = new LineChartModel();
 
        ChartSeries series1 = new LineChartSeries();
        series1.setLabel("Solicitudes");
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
        series1.setLabel("Solicitudes");
        series1.set("Creado", numeroPorEstado1);
        series1.set("Asignado", numeroPorEstado2);
        series1.set("Pausado", numeroPorEstado3);
        series1.set("Cerrado", numeroPorEstado4);
        series1.set("Reabierto", numeroPorEstado5);
 
        model.addSeries(series1);
        return model;
    }
    
    public BarChartModel getBarModel() {
        return barModel;
    }
    
    public BarChartModel getBarModelEstado(){
        return barModelEstado;
    }
    
    public LineChartModel getLineModel() {
        return lineModel;
    }
    
    public LineChartModel getLineModelestado() {
        return lineModelEstado;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public PieChartModel getPieModelEstado() {
        return pieModelEstado;
    }
    
    
    
    
    

}
