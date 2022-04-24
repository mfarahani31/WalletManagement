package com.anymind.walletmanager.service

import com.anymind.walletmanager.controller.GetBalanceRequestModel
import com.anymind.walletmanager.model.DepositValueModel
import com.anymind.walletmanager.model.DepositValueModelDTO

interface DepositValueService {
    fun save(depositValueModel: DepositValueModel): DepositValueModel

    fun getBalanceForEachHourBetweenTwoDates(balanceRequestModel: GetBalanceRequestModel): List<DepositValueModelDTO>
}