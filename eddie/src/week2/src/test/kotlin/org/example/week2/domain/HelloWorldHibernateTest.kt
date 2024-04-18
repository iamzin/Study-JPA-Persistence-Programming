package org.example.week2.domain

import jakarta.persistence.Persistence
import org.hibernate.SessionFactory
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import org.hibernate.cfg.Configuration
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class HelloWorldHibernateTest {
    private fun createSessionFactory(): SessionFactory {
        val configuration = Configuration()
        configuration.configure().addAnnotatedClass(Message::class.java)
        val serviceRegistry = StandardServiceRegistryBuilder().applySettings(configuration.properties).build()
        return configuration.buildSessionFactory(serviceRegistry)
    }

    @Test
    fun storeLoadMessage() {
        createSessionFactory().use { sessionFactory ->
            sessionFactory.openSession().use { session ->
                val tx = session.beginTransaction()

                // INSERT
                val message = Message("Hello World!")
                session.persist(message)
                tx.commit()

                // SELECT
                tx.begin()
                val criteriaQuery = session.criteriaBuilder.createQuery(Message::class.java)
                criteriaQuery.from(Message::class.java)
                val messages = session.createQuery(criteriaQuery).resultList

                // UPDATE
                messages[messages.size - 1].message = "Hello Hibernate!"
                tx.commit()

                assertAll(
                    { assert(messages.size == 1) },
                    { assert(messages[0].message == "Hello Hibernate!") }
                )
            }
        }
    }

    @Test
    fun `EntityManagerFactory에서 SessionFactory 가져오기`() {
        Persistence.createEntityManagerFactory("ch02").use { entityManagerFactory ->
            val sessionFactory = entityManagerFactory.unwrap(SessionFactory::class.java)
            assert(sessionFactory != null)
        }
    }

    @Test
    fun `하이버네이트 구성으로부터 EntityManagerFactory 가져오기`() {
        val configuration = Configuration()
        configuration.configure().addAnnotatedClass(Message::class.java)

        val properties = HashMap<String, String>()
        val propertyNames = configuration.properties.propertyNames()

        while (propertyNames.hasMoreElements()) {
            val key = propertyNames.nextElement() as String
            val value = configuration.properties.getProperty(key)
            properties[key] = value
        }

        val entityManagerFactory = Persistence.createEntityManagerFactory("ch02", properties)

        assert(entityManagerFactory != null)
    }
}