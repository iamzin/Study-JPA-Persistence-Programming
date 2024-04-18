package jpastudy.ch02

import jakarta.persistence.EntityManagerFactory
import jpastudy.ch02.member.Member
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Ch02Application {
    @Bean
    fun init(emf: EntityManagerFactory) = ApplicationRunner {
        emf.createEntityManager().run {
            transaction.begin()
            val member = Member("name1")
            persist(member)
            transaction.commit()

            println(find(Member::class.java, member.id!!))
        }
    }
}

fun main(args: Array<String>) {
    runApplication<Ch02Application>(*args)
}
