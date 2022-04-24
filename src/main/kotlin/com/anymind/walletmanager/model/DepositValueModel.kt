package com.anymind.walletmanager.model

import com.anymind.walletmanager.constants.ErrorMessages
import com.sun.istack.Nullable
import org.hibernate.annotations.CreationTimestamp
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Digits

@Entity
@Table(name = "DEPOSIT")
data class DepositValueModel(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val depositId: Long,
    @Column(name = "AMOUNT")
    @field:Digits(integer = 8, fraction = 8, message = ErrorMessages.AMOUNT_OF_BTC_NOT_VALID)
    val amount: BigDecimal,
    @Column(name = "DATE")
    @field:CreationTimestamp
    val dateTime: Date,
    @Column(name = "BALANCE")
    @field:Digits(integer = 8, fraction = 8, message = ErrorMessages.AMOUNT_OF_BTC_NOT_VALID)
    var balance: BigDecimal?
)