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
import static sv.uesocc.edu.ingenieria.dsii2018.acceso.controladores.SolicitudFacadeTest.df;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Categoria;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Departamento;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Directorio;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Estado;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.EstadoSolicitud;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Rol;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Solicitud;

/**
 *
 * @author katiro
 */
public class EstadoSolicitudFacadeTest {
    
    @ClassRule
    public static EntityManagerProvider emp = EntityManagerProvider.getInstance("ticketTestPU");
    
    static DepartamentoFacade df = new DepartamentoFacade();
    static RolFacade rf = new RolFacade();
    static DirectorioFacade dif = new DirectorioFacade();
    static CategoriaFacade cf = new CategoriaFacade();
    static SolicitudFacade sf = new SolicitudFacade();
    static EstadoSolicitudFacade esf = new EstadoSolicitudFacade();
    static EstadoFacade ef = new EstadoFacade();
    
    @BeforeClass
    public static void init() {
        Whitebox.setInternalState(df, "em", emp.em());
        Whitebox.setInternalState(rf, "em", emp.em());
        Whitebox.setInternalState(dif, "em", emp.em());
        Whitebox.setInternalState(cf, "em", emp.em());
        Whitebox.setInternalState(sf, "em", emp.em());
        Whitebox.setInternalState(esf, "em", emp.em());
        Whitebox.setInternalState(ef, "em", emp.em());
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void start() {
        esf.getEntityManager().getTransaction().begin();
    }
    
    @After
    public void tearDown() {
        esf.getEntityManager().getTransaction().rollback();
    }

    @Test
    public void TestCreateEstadoSolicitud() {
        df.create(new Departamento(df.count() + 1, "test", "test", new Date(), true));
        rf.create(new Rol(rf.count() + 1, "test", "test", new Date(), true));
        Directorio directorio = new Directorio(dif.count() + 1, "test", "test", "test", "test", "test", "test", "test", "test", new Date(), true);
        directorio.setIdDepartamento(df.find(df.count()));
        directorio.setIdRol(rf.find(rf.count()));
        dif.create(directorio);
        cf.create(new Categoria(cf.count() + 1, "test", "test", new Date(), true));
        Solicitud solicitud = new Solicitud(sf.count() + 1, "test", "test", "test", "test", new Date(), true);
        solicitud.setIdDirectorio(dif.find(dif.count()));
        solicitud.setIdCategoria(cf.find(cf.count()));
        sf.create(solicitud);
        ef.create(new Estado(ef.count() + 1, "test", "test", new Date(), true));
        EstadoSolicitud estadoSolicitud = new EstadoSolicitud(esf.count() + 1, new Date(), "test", new Date(), true);
        estadoSolicitud.setIdEstado(ef.find(ef.count()));
        estadoSolicitud.setIdSolicitud(sf.find(sf.count()));
        boolean result = esf.create(estadoSolicitud);
        assertTrue(result);
    }
    
}
