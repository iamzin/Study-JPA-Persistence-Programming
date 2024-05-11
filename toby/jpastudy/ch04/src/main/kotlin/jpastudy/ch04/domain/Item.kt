package jpastudy.ch04.domain

import jakarta.persistence.*

@Entity
class Item(
    var name: String,

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