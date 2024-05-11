package jpastudy.ch04.domain

import jakarta.persistence.EntityManager
import jakarta.persistence.TransactionRequiredException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.dao.InvalidDataAccessApiUsageException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@DataJpaTest
class RepositoryTest @Autowired constructor(
    val itemRepository: ItemRepository,
    val entityManager: EntityManager
) {
    @Test
    fun dataJpaProjection() {
        val item = Item("item1")

        item.addBid(Bid("user1", 100.toBigDecimal()))
        item.addBid(Bid("user2", 50.toBigDecimal()))
        item.addBid(Bid("user3", 20.toBigDecimal()))

        itemRepository.save(item)
        itemRepository.flush()
        entityManager.clear()

        assertThat(itemRepository.findByIdOrNull(item.id!!)!!.bids).hasSize(3)

        assertThat(itemRepository.findAllWithBid()).hasSize(3)

        itemRepository.findAllWithBid().forEach {
            println("name: ${it.name}, username: ${it.username}, price: ${it.price} - ${it.names()}")
        }
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    fun modifyingQuery() {
        val item = Item("item1")
        itemRepository.save(item)
        itemRepository.flush()
        entityManager.clear()

        /**
         * 중첩 예외로 jakarta.persistence.TransactionRequiredException이 발생한다
         */
        Assertions.assertThrows(InvalidDataAccessApiUsageException::class.java) {
            itemRepository.updateName("item2")
        }
    }
}