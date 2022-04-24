package com.anymind.walletmanager.repository

import com.anymind.walletmanager.model.BalanceReport
import com.anymind.walletmanager.model.DepositValueModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface DepositValueRepository : JpaRepository<DepositValueModel, Long> {

    fun findFirstByOrderByDepositIdDesc(): DepositValueModel

    @Query(
        nativeQuery = true, value =
        "SELECT max(d.balance) as max_balance ,concat(year(d.date), '-',month(d.date ),'-',day_of_month(d.date ) , 'T', hour(d.date),':',minute (d.date),':00') as transaction_key FROM DEPOSIT d WHERE d.date > :startDate and d.date < :endDate group by transaction_key"
    )
    fun findAllByDateTimeBetweenStartDateAndEndDate(
        @Param("startDate") startDate: Date,
        @Param("endDate") endDate: Date
    ): List<BalanceReport>


}