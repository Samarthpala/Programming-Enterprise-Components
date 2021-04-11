package com.bank.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.bank.utils.HibernateUtil;

/**
 * CRUD database operations
 * 
 * @author Ramesh Fadatare
 *
 */
@Transactional
public class TransactionDao {

	private Session getCurrentSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}

	/**
	 * Save com.bank.entity.Transaction
	 * 
	 * @param transaction
	 */
	public void saveTransaction(com.bank.entity.Transaction transactionEntity) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.save(transactionEntity);
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
	 * Update com.bank.entity.Transaction
	 * 
	 * @param transaction
	 */
	public void updateTransaction(com.bank.entity.Transaction transactionEntity) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.update(transactionEntity);
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
	 * Delete com.bank.entity.Transaction
	 * 
	 * @param id
	 */
	public void deleteTransaction(int id) {

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			com.bank.entity.Transaction transactionEntity = session.get(com.bank.entity.Transaction.class, id);
			if (transactionEntity != null) {
				session.delete(transactionEntity);
				System.out.println("transaction is deleted");
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
	 * Get com.bank.entity.Transaction By ID
	 * 
	 * @param id
	 * @return
	 */
	public com.bank.entity.Transaction getTransaction(int id) {
		com.bank.entity.Transaction transactionEntity = null;
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			transactionEntity = session.get(com.bank.entity.Transaction.class, id);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return transactionEntity;
	}

	/**
	 * Get all Transactions
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<com.bank.entity.Transaction> getAllTransaction() {
		return getCurrentSession().createQuery("from Transaction").getResultList();
	}

	public List<com.bank.entity.Transaction> getTransactionsByDate(Date date, int id) {
		Query<com.bank.entity.Transaction> query = getCurrentSession()
				.createQuery("from Transaction where date = ?1 and account.id=?2");
		query.setParameter(1, date);
		query.setParameter(2, id);
		return query.getResultList();
	}
}