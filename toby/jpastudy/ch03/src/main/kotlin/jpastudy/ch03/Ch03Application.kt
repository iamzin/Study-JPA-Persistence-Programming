package jpastudy.ch03

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
class Ch03Application

fun main(args: Array<String>) {
    runApplication<Ch03Application>(*args)
}
