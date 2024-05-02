package jpastudy.ch03

import jakarta.persistence.EntityManager
import jpastudy.ch03.domain.Bid
import jpastudy.ch03.domain.Item
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class Ch03ApplicationTests {
    @Autowired lateinit var entityManager: EntityManager

    @Test
    fun bidItem() {
        val item = Item()
        entityManager.persist(item)

        val bid = Bid()
        item.addBid(bid)

//        item.bids.add(bid) - 이 코드를 넣으면 컴파일 에러가 나야 한다

        entityManager.flush()
        entityManager.clear()

        val item2 = entityManager.find(Item::class.java, item.id)

        Assertions.assertThat(item2.bids.size).isEqualTo(1)
    }
}
