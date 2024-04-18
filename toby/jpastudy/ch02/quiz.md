# Chapter 02 - Quiz

1. EntityManagerFactory는 [   ]에 의해서 [   ]을 위해 사용되는가?

2. 다음 코드를 실행하면 결과는? (에러, 성공). 그 이유는?

```kotlin
class Member(
    var name: String,
    var id: Long? = null
) {
    override fun toString(): String {
        return "Member(name='$name', id=$id)"
    }
}
```

```kotlin
@SpringBootApplication
class Ch02Application {
    @Bean
    fun init(emf: EntityManagerFactory) = ApplicationRunner {
        emf.createEntityManager().run {
            transaction.begin()
            val member = Member("name1")
            persist(member)
            transaction.commit()

            println(find(Member::class.java, member.id!!))
        }
    }
}
```

3. SpringBoot 없이 Spring Data JPA를 사용하려면 필요한 스프링 빈과 애노테에션은?

