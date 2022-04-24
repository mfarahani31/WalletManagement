package com.anymind.walletmanager.model

import org.mapstruct.Mapper

@Mapper
interface DepositMapper {

    fun toDepositDTO(
        depositValueModel: DepositValueModel
    ): DepositValueModelDTO

    fun toDeposit(
        depositValueModelDTO: DepositValueModelDTO
    ): DepositValueModel
}