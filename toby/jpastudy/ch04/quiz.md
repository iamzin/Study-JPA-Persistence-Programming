# Chapter 04 - Quiz

1. Streamble을 리턴하는 repository 메소드를 사용했을 경우 stream을 try-with-resources로 감싸서 사용해야 하는가?

```Java
Streamable<User> findByEmailContaining(String text);
Streamable<User> findByLevel(int level);
```

```Java
@Test
void testStreamable() {
    try(Stream<User> result =                                       
           userRepository.findByEmailContaining("someother")        
            .and(userRepository.findByLevel(2))                     
            .stream().distinct())  {                                
      assertEquals(6, result.count());                              
   }
}
```

2. Bid, Item을 조인해서 Bid의 name, Item의 username,
price를 프로퍼티로 프로젝션한 오브젝트의 리스트를 리턴하는 리포지토리 함수를 어떻게 만들 수 있을까?

```Kotlin
@Entity
class Item(
    var name: String,

    @Id @GeneratedValue
    var id: Long? = null,
) {
    @OneToMany(mappedBy = "item", cascade = [CascadeType.PERSIST])
    val bids: Set<Bid> = mutableSetOf()

    fun addBid(bid: Bid) {
        (bids as MutableSet).add(bid)
        bid.item = this
    }
}
```

```Kotlin
@Entity
class Bid(
    var username: String,

    var price: BigDecimal,

    @Id @GeneratedValue
    var id: Long? = null,
) {
    @ManyToOne
    lateinit var item: Item
}
```
3. @Modifying 함수에는 @Transactional이 항상 필요한가?
```Kotlin
@Query("update Item i set i.name = :name")
    @Modifying
    fun updateName(name: String)
```