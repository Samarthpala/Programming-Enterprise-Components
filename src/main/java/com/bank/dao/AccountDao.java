package com.bank.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.bank.entity.Account;
import com.bank.utils.HibernateUtil;

@Transactional
public class AccountDao {
	private Session getCurrentSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}

	/**
	 * Save Account
	 * 
	 * @param account
	 */
	public void saveAccount(Account account) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.save(account);
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
	 * Update Account
	 * 
	 * @param account
	 */
	public void updateAccount(Account account) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.update(account);
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
	 * Delete Account
	 * 
	 * @param id
	 */
	public void deleteAccount(int id) {

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			Account account = session.get(Account.class, id);
			if (account != null) {
				session.delete(account);
				System.out.println("account is deleted");
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
	 * Get Account By ID
	 * 
	 * @param id
	 * @return
	 */
	public Account getAccount(int id) {
		Account account = null;
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			account = session.get(Account.class, id);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return account;
	}

	/**
	 * Get all Accounts
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Account> getAllAccount() {
		return getCurrentSession().createQuery("from Account").getResultList();
	}

	public Account getAccountByAccountNumber(String accountNumber) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			Query<Account> query = session.createQuery("from Account where accountNumber = ?1");
			query.setParameter(1, accountNumber);
			List<Account> result = query.getResultList();
			// commit transaction
			transaction.commit();
			if (result != null && !result.isEmpty()) {
				return result.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return null;
	}
}