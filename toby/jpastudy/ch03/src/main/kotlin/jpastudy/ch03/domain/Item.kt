package jpastudy.ch03.domain

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class Item(
    @Id @GeneratedValue
    var id: Long? = null,
) {
    @OneToMany(mappedBy = "item", cascade = [CascadeType.PERSIST])
    val bids: Set<Bid> = mutableSetOf()

    fun addBid(bid: Bid) {
        (bids as MutableSet).add(bid)
        bid.item = this
    }
}