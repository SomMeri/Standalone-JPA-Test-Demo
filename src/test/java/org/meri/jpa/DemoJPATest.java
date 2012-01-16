package org.meri.jpa;

import static org.junit.Assert.assertFalse;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.meri.jpa.simplest.entity.Person;

public class DemoJPATest extends AbstractTestCase {

  private static final String CHANGELOG_LOCATION = "src/test/java/org/meri/jpa/simplest/db.changelog.xml";
  private static EntityManagerFactory factory;

  public DemoJPATest() {
  }

  @Override
  protected String getInitialChangeLog() {
    return CHANGELOG_LOCATION;
  }

  @Test
  @SuppressWarnings("unchecked")
  public void testJPA() {
    EntityManager em = factory.createEntityManager();

    Query query = em.createQuery("SELECT x FROM Person x");
    List<Person> allUsers = query.getResultList();
    em.close();

    assertFalse(allUsers.isEmpty());
  }

  @BeforeClass
  public static void createFactory() {
    factory = Persistence.createEntityManagerFactory("Simplest");
  }

  @AfterClass
  public static void closeFactory() {
    factory.close();
  }

}
