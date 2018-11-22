/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.uesocc.edu.ingenieria.dsii2018.acceso.controladores;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.ClassRule;
import org.powermock.reflect.Whitebox;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Categoria;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Departamento;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.DescripcionMantenimiento;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Directorio;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Encargado;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Estado;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.EstadoSolicitud;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.MantenimientoEncargado;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Rol;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Solicitud;

/**
 *
 * @author katiro
 */
public class EncargadoFacadeLocalTest {

    @ClassRule
    public static EntityManagerProvider emp = EntityManagerProvider.getInstance("ticketTestPU");

    static EncargadoFacade ef = new EncargadoFacade();
    static RolFacade rf = new RolFacade();
    static DepartamentoFacade dpf = new DepartamentoFacade();
    static CategoriaFacade cf = new CategoriaFacade();
    static PrioridadFacade pf = new PrioridadFacade();
    static DirectorioFacade df = new DirectorioFacade();
    static EstadoFacade esf = new EstadoFacade();
    static SolicitudFacade sf = new SolicitudFacade();
    static EstadoSolicitudFacade estf = new EstadoSolicitudFacade();
    static MantenimientoEncargadoFacade mef = new MantenimientoEncargadoFacade();
    static DescripcionMantenimientoFacade dmf = new DescripcionMantenimientoFacade();

    @BeforeClass
    public static void init() {
        Whitebox.setInternalState(ef, "em", emp.em());
        Whitebox.setInternalState(rf, "em", emp.em());
        Whitebox.setInternalState(dpf, "em", emp.em());
        Whitebox.setInternalState(cf, "em", emp.em());
        Whitebox.setInternalState(pf, "em", emp.em());
        Whitebox.setInternalState(df, "em", emp.em());
        Whitebox.setInternalState(esf, "em", emp.em());
        Whitebox.setInternalState(sf, "em", emp.em());
        Whitebox.setInternalState(estf, "em", emp.em());
        Whitebox.setInternalState(mef, "em", emp.em());
        Whitebox.setInternalState(dmf, "em", emp.em());
    }

//    @BeforeClass
//    public static void setUpClass() {
//    }
    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void start() {
        ef.getEntityManager().getTransaction().begin();
    }

//    @Before
//    public void setUp() {
//    }

    @After
    public void tearDown() {
        ef.getEntityManager().getTransaction().rollback();
    }

    @Test
    public void testCreateEmcargado() {
        // Creacion de Rol y Departamento
        rf.create(new Rol(rf.count() + 1, "test", "test", new Date(), true));
        dpf.create(new Departamento(dpf.count() + 1, "TEST", "Test", new Date(), true));
        // CReacion de Directorio
        Directorio directorio = new Directorio(df.count() + 1, "test", "test", "test", "test", "test", "test", "test", "test", new Date(), true);
        directorio.setIdDepartamento(dpf.find(dpf.count()));
        directorio.setIdRol(rf.find(rf.count()));
        df.create(directorio);
        cf.create(new Categoria(cf.count() + 1, "test", "test", new Date(), true));
        // Creacion de Solicitud
        Solicitud solicitud = new Solicitud(sf.count() + 1, "test", "test", "test", "test", new Date(), true);
        solicitud.setIdDirectorio(df.find(df.count()));
        solicitud.setIdCategoria(cf.find(cf.count()));
        sf.create(solicitud);
        // CReacion de Estado
        esf.create(new Estado(esf.count() + 1, "test", "test", new Date(), true));
        // Creacion de Estado Solicitud
        EstadoSolicitud estadoSolicitud = new EstadoSolicitud(estf.count() + 1, new Date(), "test", new Date(), true);
        estadoSolicitud.setIdEstado(esf.find(esf.count()));
        estadoSolicitud.setIdSolicitud(sf.find(sf.count()));
        estf.create(estadoSolicitud);
        // Creacion de Descripcion Mantenimiento
        dmf.create(new DescripcionMantenimiento(dmf.count() + 1, "test", new Date(), true));
        // Creacion de Mantenimiento Encargado
        MantenimientoEncargado mantenimientoEncargado = new MantenimientoEncargado(mef.count() + 1, "test", new Date(), true);
        mantenimientoEncargado.setIdSolicitud(sf.find(sf.count()));
        mantenimientoEncargado.setIdDescripcionMantenimiento(dmf.find(dmf.count()));
        mef.create(mantenimientoEncargado);
        // Creacion de Encargado
        Encargado encargado = new Encargado(ef.count() + 1, true, "test", new Date(), true);
        encargado.setIdDirectorio(df.find(df.count()));
        encargado.setIdMantenimientoEncargado(mef.find(mef.count()));
        boolean result = ef.create(encargado);
        assertTrue(result);
    }

}
