package sv.uesocc.edu.ingenieria.dsii2018.acceso.controladores;

import javassist.compiler.ast.Stmnt;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 *
 * @author katiro
 */
public class EntityManagerProvider implements TestRule {

    private static EntityManagerProvider object;
    private final EntityManager em;
    private final EntityTransaction tx;

    private EntityManagerProvider(String unitName) {
        this.em = Persistence.createEntityManagerFactory(unitName).createEntityManager();
        this.tx = this.em.getTransaction();
    }

    public final static EntityManagerProvider getInstance(String unitName) {
            object = new EntityManagerProvider(unitName);
        return object;
    }

    public EntityManager em() {
        return this.em;
    }

    @Override
    public Statement apply(final Statement stmnt, Description d) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                stmnt.evaluate();
                em.clear();
                em.close();
            }
        };
    }

}
