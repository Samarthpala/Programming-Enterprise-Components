package com.bank.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.bank.entity.User;
import com.bank.utils.HibernateUtil;

/**
 * CRUD database operations
 * 
 * @author Ramesh Fadatare
 *
 */
@Transactional
public class UserDao {

	private Session getCurrentSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}

	/**
	 * Save User
	 * 
	 * @param user
	 */
	public void saveUser(User user) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.save(user);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	/**
	 * Update User
	 * 
	 * @param user
	 */
	public void updateUser(User user) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.update(user);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	/**
	 * Delete User
	 * 
	 * @param id
	 */
	public void deleteUser(int id) {

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			User user = session.get(User.class, id);
			if (user != null) {
				session.delete(user);
				System.out.println("user is deleted");
			}
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	/**
	 * Get User By ID
	 * 
	 * @param id
	 * @return
	 */
	public User getUser(int id) {
		User user = null;
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			user = session.get(User.class, id);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return user;
	}

	/**
	 * Get all Users
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<User> getAllUser() {
		return getCurrentSession().createQuery("from User").getResultList();
	}

	public User getUserByEmailAndPassword(String email, String password) {
		Query<User> query = getCurrentSession().createQuery("from User where username = ?1 and password = ?2");
		query.setParameter(1, email);
		query.setParameter(2, password);
		List<User> result = query.getResultList();
		if (result != null && !result.isEmpty()) {
			return result.get(0);
		} else {
			return null;
		}
	}
}