/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.uesocc.edu.ingenieria.dsii2018.acceso.controladores;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

}
