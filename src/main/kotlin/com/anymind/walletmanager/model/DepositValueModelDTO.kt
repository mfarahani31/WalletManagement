package com.anymind.walletmanager.model

import com.anymind.walletmanager.constants.ErrorMessages
import com.fasterxml.jackson.annotation.JsonIgnore
import java.math.BigDecimal
import java.util.*
import javax.validation.constraints.Digits
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class DepositValueModelDTO(
    @field:NotNull
    @field:Positive(message = ErrorMessages.DEPOSIT_MUST_BE_POSITIVE)
    @field:Digits(integer = 8, fraction = 8, message = ErrorMessages.AMOUNT_OF_BTC_NOT_VALID)
    var amount: BigDecimal?,
    var dateTime: Date,
)
