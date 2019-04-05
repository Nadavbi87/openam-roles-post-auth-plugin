package utils;

import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public enum PersistenceManagerUtil {
    INSTANCE;

    private EntityManagerFactory entityManagerFactory;

    PersistenceManagerUtil(){
        entityManagerFactory = Persistence.createEntityManagerFactory("PAP-PU");
    }

    public EntityManager getEntityManager(){
        return entityManagerFactory.createEntityManager();
    }

    @PreDestroy
    public void close(){
        if(entityManagerFactory != null){
            entityManagerFactory.close();
        }
    }
}
