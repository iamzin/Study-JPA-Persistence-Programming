package jpastudy.ch04.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.math.BigDecimal

@Entity
class Bid(
    var username: String,

    var price: BigDecimal,

    @Id @GeneratedValue
    var id: Long? = null,
) {
    @ManyToOne
    lateinit var item: Item
}