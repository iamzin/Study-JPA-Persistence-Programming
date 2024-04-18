package org.example.objectrelational

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
data class Address(

    @Column(length = 255, nullable = false)
    val street: String,

    @Column(length = 5, nullable = false)
    val zipcode: String,

    @Column(length = 255, nullable = false)
    val city: String
)
