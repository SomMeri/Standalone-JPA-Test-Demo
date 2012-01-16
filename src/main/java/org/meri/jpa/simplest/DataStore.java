package org.meri.jpa.simplest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.meri.jpa.simplest.entity.Person;

//FIXME: what is best practice about entity managers and factories?
public class DataStore {
	
	public List<Person> getAllUsers() {
		//OpenJPAEntityManagerFactory factory = OpenJPAPersistence.createEntityManagerFactory("Simplest", "META-INF/persistenceCustom.xml");
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Simplest");
		EntityManager em = factory.createEntityManager();
		@SuppressWarnings("unchecked")
		List<Person> resultList = em.createQuery("SELECT x FROM Person x").getResultList();
		em.close();
		factory.close();
		return resultList;
	}

	/**
	 * 
	 * @param userId
	 * 
	 * @return person with id userId or <code>null</null> if such person does not exist
	 */
	public Person getUser(long userId) {
		Person result = getThing(Person.class, userId);
		return result;
	}

	/**
	 * 
	 * @param rClass entity class to be returned
	 * @param thingId id of the returned instance
	 * 
	 * @return entity with id thing or <code>null</null> if such entity does not exist
	 */
	public <T> T getThing(Class<T> rClass, long thingId) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ApacheLuceneDemo");
		EntityManager em = factory.createEntityManager();
		
		T result = em.find(rClass, thingId);
		
		em.close();
		factory.close();
		return result;
	}

}
