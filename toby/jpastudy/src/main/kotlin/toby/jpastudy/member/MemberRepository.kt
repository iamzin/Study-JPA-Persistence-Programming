package toby.jpastudy.member

import org.springframework.data.repository.CrudRepository

interface MemberRepository: CrudRepository<Member, Long> {
}