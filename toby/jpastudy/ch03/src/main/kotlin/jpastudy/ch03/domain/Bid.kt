package jpastudy.ch03.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne

@Entity
class Bid(
    @Id @GeneratedValue
    var id: Long? = null,
) {
    @ManyToOne
    lateinit var item: Item
}