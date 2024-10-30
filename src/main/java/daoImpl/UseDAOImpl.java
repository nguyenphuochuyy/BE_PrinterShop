package daoImpl;

import java.util.List;

import dao.UserDAO;
import entity.User;
import jakarta.persistence.EntityManager;

public class UseDAOImpl implements UserDAO {
	private EntityManager entityManager;

	public UseDAOImpl(EntityManager e ) {
		this.entityManager = e;
	}
	@Override
	public void insertUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> getAllUser() {
		return entityManager.createQuery("FROM User").getResultList();
	}
	
	// get role của user theo id
	@Override
	public String getRoleUser(String username) {
		try {
			return entityManager.createQuery("SELECT role FROM User WHERE username = :username").setParameter("username", username).getSingleResult().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	@Override
	public boolean checkLogin(String username, String password) {
	    // TODO Auto-generated method stub
		try {
			User user = (User) entityManager.createQuery("FROM User WHERE username = :username AND password = :password")
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
            if(user != null) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
		}
		
		return false;
	}
	@Override
	public User getUserByUsername(String username) {
		try {
            return (User) entityManager.createQuery("FROM User WHERE username = :username").setParameter("username", username).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
		return null;
	}
	
	}
}