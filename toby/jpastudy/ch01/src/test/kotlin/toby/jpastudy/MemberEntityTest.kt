package toby.jpastudy

import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.transaction.TestTransaction
import toby.jpastudy.member.Member
import toby.jpastudy.member.MemberRepository

@SpringBootTest
class MemberEntityTest {
    @Autowired
    lateinit var emf: EntityManagerFactory

    @Autowired
    lateinit var memberRepository: MemberRepository

    @Test
    fun persistWithEMF() {
        emf.createEntityManager().use { em: EntityManager ->
            val tx = em.transaction
            try {
                tx.begin()

                em.createQuery("delete from Member").executeUpdate()

                val member = Member("name")
                em.persist(member)

                assertThat(member.id).isNotNull

                tx.commit()

                /** --------------------------------------------------------------------------- */

                tx.begin()

                val members = em.createQuery("select m from Member m", Member::class.java).resultList

                assertThat(members).hasSize(1)

                tx.commit()
            } catch (e: RuntimeException) {
                if (tx.isActive) tx.rollback()
                throw e
            }
        }
    }

    @Test
//    @Transactional(propagation = Propagation.NEVER)
    fun persistWithDataJpa() {
        val member = Member("name")

        assertThat(TestTransaction.isActive()).isFalse()

        memberRepository.save(member)
    }
}