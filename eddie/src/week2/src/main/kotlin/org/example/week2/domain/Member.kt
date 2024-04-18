package org.example.week2.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
class Member(
    var name: String,

    @Id @GeneratedValue
    var id: Long? = null
)