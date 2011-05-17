package se.thematrix;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.TransactionManager;

import org.hibernate.transaction.JBossTSStandaloneTransactionManagerLookup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import se.thematrix.model.Breed;
import se.thematrix.model.Dog;

public class DogBreedRunner {
	private static final Logger logger = LoggerFactory.getLogger(DogBreedRunner.class);

	public static void main(String[] args) {

		// accessing JBoss's Transaction can be done differently but this one
		// works nicely
		TransactionManager tm = new JBossTSStandaloneTransactionManagerLookup().getTransactionManager(null);

		// build the EntityManagerFactory as you would build in in Hibernate
		// Core
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("org.hibernate.ogm.tutorial.jpa");

		// Persist entities the way you are used to in plain JPA
		try {
			tm.begin();
			EntityManager em = emf.createEntityManager();
			Breed collie = new Breed();
			collie.setName("Collie");
			em.persist(collie);

			Dog dina = new Dog();
			dina.setName("Dina");
			dina.setBreed(collie);
			em.persist(dina);
			Long dinaId = dina.getId();
			em.flush();
			em.close();
			tm.commit();

			// Retrieve your entities the way you are used to in plain JPA
			tm.begin();
			em = emf.createEntityManager();
			dina = em.find(Dog.class, dinaId);
			logger.debug(dina.getName());
			logger.debug(dina.getBreed().getName());
			em.flush();
			em.close();
			tm.commit();

			emf.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
