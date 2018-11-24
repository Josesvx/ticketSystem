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
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Directorio;

/**
 *
 * @author katiro
 */
@Stateless
public class DescripcionMantenimientoFacade extends AbstractFacade<DescripcionMantenimiento> implements DescripcionMantenimientoFacadeLocal {

    @PersistenceContext(unitName = "ticketPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DescripcionMantenimientoFacade() {
        super(DescripcionMantenimiento.class);
    }

    
    @Override
    public DescripcionMantenimiento FindBySolicitudEncargado(int idSolicitud, int idDirectorio) {  
        List<DescripcionMantenimiento> lista2 ;
        DescripcionMantenimiento tmp ;
        try {
            Query query = em.createNamedQuery("DescripcionMantenimiento.finBySolicitudEncargado");
            query.setParameter("idSolicitud", idSolicitud);
            query.setParameter("idDirectorio", idDirectorio);

            lista2 = query.getResultList();            
            
            tmp = lista2.get(0);
        } catch (Exception ex) {
            throw ex;
        }
        return tmp;

    }
    
    @Override
    public List<DescripcionMantenimiento> FindByCorrelativo(String correlativo) {  
        List<DescripcionMantenimiento> Bycorrelativo ;
        
        try {
            Query query = em.createNamedQuery("DescripcionMantenimiento.findByCorrelativo");
            query.setParameter("correlativo", correlativo);
            

            Bycorrelativo = query.getResultList();            
            
            
        } catch (Exception ex) {
            throw ex;
        }
        return Bycorrelativo;

    }

}
