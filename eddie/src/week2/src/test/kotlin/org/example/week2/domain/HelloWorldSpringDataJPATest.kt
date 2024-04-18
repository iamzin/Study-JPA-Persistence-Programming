package org.example.week2.domain

import org.example.week2.DataConfig
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [DataConfig::class])
class HelloWorldSpringDataJPATest {
    @Autowired
    lateinit var messageRepository: MessageRepository

    @Test
    fun storeLoadMessage() {
        val message = Message("Hello World!")
        messageRepository.save(message)

        val messages = messageRepository.findAll() as List<Message>

        assert(messages.size == 1)
        assert(messages[0].message == "Hello World!")
    }
}