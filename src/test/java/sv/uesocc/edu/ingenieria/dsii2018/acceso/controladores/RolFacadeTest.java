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
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Rol;

/**
 *
 * @author katiro
 */
public class RolFacadeTest {
    
    @ClassRule
    public static EntityManagerProvider emp = EntityManagerProvider.getInstance("ticketTestPU");

    static RolFacade rf = new RolFacade();

    @BeforeClass
    public static void init() {
        Whitebox.setInternalState(rf, "em", emp.em());
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void start() {
        rf.getEntityManager().getTransaction().begin();
    }

    @After
    public void tearDown() {
        rf.getEntityManager().getTransaction().rollback();
    }
    
    @Test
    public void TestCreateRol(){
        boolean result = rf.create(new Rol(rf.count()+1, "test", "test", new Date(), true));
        assertTrue(result);
    }
    
}
