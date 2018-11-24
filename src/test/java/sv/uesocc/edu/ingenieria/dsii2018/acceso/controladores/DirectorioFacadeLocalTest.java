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
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Departamento;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Directorio;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Rol;

/**
 *
 * @author katiro
 */
public class DirectorioFacadeLocalTest {

    @ClassRule
    public static EntityManagerProvider emp = EntityManagerProvider.getInstance("ticketTestPU");

    static DepartamentoFacade df = new DepartamentoFacade();
    static RolFacade rf = new RolFacade();
    static DirectorioFacade dif = new DirectorioFacade();

    @BeforeClass
    public static void init() {
        Whitebox.setInternalState(df, "em", emp.em());
        Whitebox.setInternalState(rf, "em", emp.em());
        Whitebox.setInternalState(dif, "em", emp.em());
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void start() {
        dif.getEntityManager().getTransaction().begin();
    }

    @After
    public void tearDown() {
        dif.getEntityManager().getTransaction().rollback();
    }

    @Test
    public void testCreateDirectorio() {
        Departamento departamento = new Departamento(df.count() + 1, "TEST", "Test", new Date(), true);
        Rol rol = new Rol(rf.count() + 1, "test", "test", new Date(), true);
        df.create(departamento);
        rf.create(rol);
        Directorio directorio = new Directorio(dif.count() + 1, "test", "test", "test", "test", "test", "test", "test", "test", new Date(), true);
        directorio.setIdDepartamento(df.find(1));
        directorio.setIdRol(rf.find(1));
        boolean result = dif.create(directorio);
        assertTrue(result);
    }

}
