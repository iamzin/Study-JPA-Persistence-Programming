package jpastudy.ch02.member

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

class Member(
    var name: String,
    var id: Long? = null
) {
    override fun toString(): String {
        return "Member(name='$name', id=$id)"
    }
}