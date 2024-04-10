package org.example.objectrelational

import org.springframework.data.jpa.repository.JpaRepository

interface BillingDetailsRepository: JpaRepository<BillingDetails, Long> {

    fun findByUser(user: User): BillingDetails?
}
