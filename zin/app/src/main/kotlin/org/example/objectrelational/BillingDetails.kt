package org.example.objectrelational

import jakarta.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", discriminatorType = DiscriminatorType.STRING)
abstract class BillingDetails(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var account: String,

    @Column(nullable = false)
    var bankname: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = ForeignKey(name = "FK_BILLING_DETAILS_USER_ID"), nullable = false)
    var user: User
)

@Entity
@DiscriminatorValue("CREDIT_CARD")
class CreditCard(

    account: String,

    bankname: String,

    user: User

): BillingDetails(
    account = account,
    bankname = bankname,
    user = user
)

@Entity
@DiscriminatorValue("BANK_ACCOUNT")
class BankAccount(

    account: String,

    bankname: String,

    user: User

): BillingDetails(
    account = account,
    bankname = bankname,
    user = user
)
