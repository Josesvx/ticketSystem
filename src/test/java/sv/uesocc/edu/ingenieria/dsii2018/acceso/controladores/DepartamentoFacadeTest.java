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

/**
 *
 * @author katiro
 */
public class DepartamentoFacadeTest {

    @ClassRule
    public static EntityManagerProvider emp = EntityManagerProvider.getInstance("ticketTestPU");

    static DepartamentoFacade df = new DepartamentoFacade();

    @BeforeClass
    public static void init() {
        Whitebox.setInternalState(df, "em", emp.em());
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void start() {
        df.getEntityManager().getTransaction().begin();
    }

    @After
    public void tearDown() {
        df.getEntityManager().getTransaction().rollback();
    }

    /**
     * Test of create method, of class DepartamentoFacade.
     */
    @Test
    public void testCreateDepartemento() throws Exception {
        Departamento departamento = new Departamento(df.count() + 1, "TEST", "Test", new Date(), true);
        boolean result = df.create(departamento);
        assertTrue(result);
    }
}
