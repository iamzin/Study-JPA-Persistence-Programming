package org.example.week2.domain

import jakarta.persistence.Persistence
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class MessageEntityTest {

    @Test
    fun storeLoadMessage() {
        Persistence.createEntityManagerFactory("ch02").use {
            it.createEntityManager().use { em ->
                val tx = em.transaction

                // INSERT
                tx.begin()
                val message = Message("Hello World!")
                em.persist(message)
                tx.commit()

                // SELECT
                tx.begin()
                val messages = em.createQuery("select m from Message m", Message::class.java).resultList

                // UPDATE
                messages[messages.size - 1].message = "Hello JPA!"
                tx.commit()

                assertAll(
                    { assert(messages.size == 1) },
                    { assert(messages[0].message == "Hello JPA!") }
                )
            }
        }
    }
}