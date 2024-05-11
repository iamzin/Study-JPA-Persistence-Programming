package jpastudy.ch04.domain

import org.springframework.data.jpa.repository.JpaRepository

interface BidRepository: JpaRepository<Bid, Long> {
}