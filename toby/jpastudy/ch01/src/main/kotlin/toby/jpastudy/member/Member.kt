package toby.jpastudy.member

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
class Member(
    var name: String,

    @Id @GeneratedValue
    var id: Long? = null
)