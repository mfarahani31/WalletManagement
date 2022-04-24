package com.anymind.walletmanager.controller

import java.util.*

data class GetBalanceRequestModel(
    val startDatetime: Date,
    val endDatetime: Date
)