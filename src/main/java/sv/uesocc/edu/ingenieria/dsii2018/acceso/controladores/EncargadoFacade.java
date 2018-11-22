/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.uesocc.edu.ingenieria.dsii2018.acceso.controladores;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.DescripcionMantenimiento;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Encargado;

/**
 *
 * @author katiro
 */
@Stateless
public class EncargadoFacade extends AbstractFacade<Encargado> implements EncargadoFacadeLocal {

    @PersistenceContext(unitName = "ticketPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EncargadoFacade() {
        super(Encargado.class);
    }

    @Override
    public void change() {
    }
    
    @Override
    public Encargado FindBySolicitudE(int idSolicitud, int idDirectorio) {  
        List<Encargado> list ;
        Encargado tmp ;
        try {
            Query query = em.createNamedQuery("Encargado.findBySolicitudE");
            query.setParameter("idSolicitud", idSolicitud);
            query.setParameter("idDirectorio", idDirectorio);

            list = query.getResultList();            
            
            tmp = list.get(0);
        } catch (Exception ex) {
            throw ex;
        }
        return tmp;

    }

}
