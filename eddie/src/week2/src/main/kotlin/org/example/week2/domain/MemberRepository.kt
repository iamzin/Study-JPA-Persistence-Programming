package org.example.week2.domain

import org.springframework.data.repository.CrudRepository

interface MemberRepository: CrudRepository<Member, Long>