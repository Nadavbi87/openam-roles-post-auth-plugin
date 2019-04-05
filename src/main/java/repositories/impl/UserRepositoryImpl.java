package repositories.impl;

import helpers.QueryHelper;
import repositories.UserRepository;
import model.User;
import utils.PersistenceManagerUtil;

import javax.persistence.EntityManager;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public User findByUsername(String username) {
        EntityManager entityManager = PersistenceManagerUtil.INSTANCE.getEntityManager();
        User user = null;
        try {
            user = entityManager.createNamedQuery(
                    QueryHelper.USER_QUERY_FIND_BY_USERNAME_QUERY_NAME,
                    User.class)
                    .setParameter(
                            QueryHelper.USER_QUERY_USERNAME_PARAMETER,
                            username)
                    .getSingleResult();

            if( user != null){
                user.getRoles().size();
            }

        }catch (Exception e){
            System.err.println("Error while trying to find user = " + username + " exception -> " + e.getMessage());
        }finally {
            if(entityManager != null){
               entityManager.close();
            }
        }


        return user;
    }
}
