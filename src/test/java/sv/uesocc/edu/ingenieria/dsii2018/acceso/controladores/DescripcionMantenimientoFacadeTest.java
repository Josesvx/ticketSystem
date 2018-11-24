/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.uesocc.edu.ingenieria.dsii2018.acceso.controladores;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.ClassRule;
import org.powermock.reflect.Whitebox;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.DescripcionMantenimiento;

/**
 *
 * @author katiro
 */
public class DescripcionMantenimientoFacadeTest {

    @ClassRule
    public static EntityManagerProvider emp = EntityManagerProvider.getInstance("ticketTestPU");

    static DescripcionMantenimientoFacade dmf = new DescripcionMantenimientoFacade();

    @BeforeClass
    public static void init() {

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
        dmf.getEntityManager().getTransaction().begin();
    }

//    @Before
//    public void setUp() {
//    }
    @After
    public void tearDown() {
        dmf.getEntityManager().getTransaction().rollback();
    }

    @Test
    public void testCreateEmcargado() {
        boolean result = dmf.create(new DescripcionMantenimiento(dmf.count()+1, "test", new Date(), true));
        assertTrue(result);
    }

}
