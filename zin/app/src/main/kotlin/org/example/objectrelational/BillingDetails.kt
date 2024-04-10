package org.example.objectrelational

import jakarta.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", discriminatorType = DiscriminatorType.STRING)
abstract class BillingDetails(

    @Id
    var id: Long,

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

    id: Long,

    account: String,

    bankname: String,

    user: User

): BillingDetails(id, account, bankname, user)

@Entity
@DiscriminatorValue("BANK_ACCOUNT")
class BankAccount(

    id: Long,

    account: String,

    bankname: String,

    user: User

): BillingDetails(id, account, bankname, user)
