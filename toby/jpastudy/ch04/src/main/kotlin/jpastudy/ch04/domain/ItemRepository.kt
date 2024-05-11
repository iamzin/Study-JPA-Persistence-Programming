package jpastudy.ch04.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import java.math.BigDecimal

interface ItemRepository: JpaRepository<Item, Long> {
    @Query("select i.name as name, b.username as username, b.price as price from Item i join i.bids b")
    fun findAllWithBid(): List<ItemBidDto>

    @Query("update Item i set i.name = :name")
    @Modifying
    fun updateName(name: String)
}

interface ItemBidDto {
    val name: String
    val username: String
    val price: BigDecimal
}

fun ItemBidDto.names() = "$name/$username"
