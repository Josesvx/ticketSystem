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
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Prioridad;

/**
 *
 * @author katiro
 */
public class PrioridadFacadeTest {
    
    @ClassRule
    public static EntityManagerProvider emp = EntityManagerProvider.getInstance("ticketTestPU");

    static PrioridadFacade pf = new PrioridadFacade();

    @BeforeClass
    public static void init() {
        Whitebox.setInternalState(pf, "em", emp.em());
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void start() {
        pf.getEntityManager().getTransaction().begin();
    }

    @After
    public void tearDown() {
        pf.getEntityManager().getTransaction().rollback();
    }
    
    @Test
    public void TestCreatePrioridad(){
        boolean result = pf.create(new Prioridad(pf.count()+1, "test", "test", new Date(), true));
        assertTrue(result);
    }
    
}
