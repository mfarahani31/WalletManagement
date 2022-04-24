package com.anymind.walletmanager.model

import java.math.BigDecimal

interface BalanceReport {
    fun getMax_balance():BigDecimal?

    fun getTransaction_key():String


}