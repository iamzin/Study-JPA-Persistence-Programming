package org.example.week1.domain

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


@Entity
class Users(
    val name: String,
    val email: String,
    val password: String
) {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other !is Users) {
            return false
        }

        return this.id != null && (this.id == other.id)
    }

    override fun hashCode(): Int {x
        return Objects.hash(this.id)
    }
}