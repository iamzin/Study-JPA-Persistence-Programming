package org.example.objectrelational

import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestConstructor

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class BillingDetailsTest(
    private val billingDetailsRepository: BillingDetailsRepository,
    private val userRepository: UserRepository
) {

    lateinit var user: User

    @BeforeEach
    fun setUp() {
        user = User(
            username = "zin",
            address = Address(
                street = "15, Jangan-ro,",
                zipcode = "13583",
                city = "Seongnam-si"
            )
        ).apply { userRepository.save(this) }
    }
    
    @Test
    fun `create()`() {
        val billingDetails = BankAccount(
            account = "1234567890",
            bankname = "KB",
            user = user
        )

        billingDetailsRepository.save(billingDetails)

        billingDetailsRepository.flush()

        val savedBillingDetails = billingDetailsRepository.findByUser(user)!!

        savedBillingDetails shouldNotBe null
        savedBillingDetails.id shouldNotBe null
    }
}
