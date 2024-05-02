# Chapter 02 - Quiz

1. 투명한 영속성(transparent persistence)이란 무엇인가? JPA는 완전히 투명한 영속성을 지원하는가? 

2. Kotlin은 클래스는 디폴트로 final이다. 그렇다면 JPA 스펙의 요구조건(엔티티 클래스와 메소드는 final이면 안 된다)을 충족하지 못하는데 그러면 어떻게 되는가, 어떻게 해야하나?

3. OneToMany에서 양방향 관계를 가지는 경우 add 함수로 양방향 관계를 한번에 설정할 수 있게 만든다. 
   이 경우 OneToMany 콜렉션을을 직접 조작하지 못하도록 만들려면 어떻게 해야할까. 
   다음 테스트 코드를 충족하도록 Item-Bid (OneToMany) 클래스 코드를 만들어보자.

```kotlibn
@Test
fun bidItem() {
    val item = Item()
    entityManager.persist(item)

    val bid = Bid()
    item.addBid(bid)

//        item.bids.add(bid) - 이 코드를 넣으면 컴파일 에러가 나야 한다

    entityManager.flush()
    entityManager.clear()

    val item2 = entityManager.find(Item::class.java, item.id)

    Assertions.assertThat(item2.bids.size).isEqualTo(1)
}
```

