package org.example.objectrelational

import jakarta.persistence.*

@Entity
@Table(
    name = "users",
    uniqueConstraints = [UniqueConstraint(name = "UK_USER_USERNAME", columnNames = ["username"])]
)
data class User(

    @Id
    var id: Long,

    @Column(length = 15, nullable = false, unique = true)
    var username: String,

    @Embedded
    var address: Address,

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, mappedBy = "user")
    var billingDetails: MutableSet<BillingDetails> = mutableSetOf()
)
