package org.example.objectrelational

import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestConstructor

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class UserTest(
    private val userRepository: UserRepository
) {
    
    @Test
    fun `create()`() {
        val user = User(
            username = "zin",
            address = Address(
                street = "15, Jangan-ro,",
                zipcode = "13583",
                city = "Seongnam-si"
            )
        )

        userRepository.save(user)

        userRepository.flush()

        val savedUser = userRepository.findByUsername("zin")!!

        savedUser shouldNotBe null
        savedUser.id shouldNotBe null
    }
}
