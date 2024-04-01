package org.example.week1.domain

import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.Persistence
import kotlin.test.Test
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeAll

class HibernateTest {
    companion object {
        private lateinit var entityManagerFactory: EntityManagerFactory

        @JvmStatic
        @BeforeAll
        fun setUp() {
            entityManagerFactory = Persistence.createEntityManagerFactory("week1")
        }

        @JvmStatic
        @AfterAll
        fun tearDown() {
            entityManagerFactory.close()
        }
    }

    @Test
    fun `entityManager를 활용해서 Entity를 저장 및 조회한다`() {
        val entityManager: EntityManager = entityManagerFactory.createEntityManager()
        entityManager.transaction.begin()

        val user = Users("Eddie", "sangwoo98.kang@gmail.com", "1234")
        entityManager.persist(user)

        val foundUser = entityManager.find(Users::class.java, 1L)

        entityManager.transaction.commit()
        entityManager.close()

        assertNotNull(foundUser)
        assertEquals(user, foundUser)
    }
}