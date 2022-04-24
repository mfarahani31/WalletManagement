package com.anymind.walletmanager.service

import com.anymind.walletmanager.controller.GetBalanceRequestModel
import com.anymind.walletmanager.date.DateHandler
import com.anymind.walletmanager.model.BalanceReport
import com.anymind.walletmanager.model.DepositValueModel
import com.anymind.walletmanager.model.DepositValueModelDTO
import com.anymind.walletmanager.repository.DepositValueRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


@Service
class DepositValueServiceImpl : DepositValueService {
    @Autowired
    private lateinit var depositValueRepository: DepositValueRepository

    private var dateHandler:DateHandler = DateHandler()

    override fun save(depositValueModel: DepositValueModel): DepositValueModel {
        var balance: BigDecimal? = this.getLastBalance()
        balance = balance?.plus(depositValueModel.amount)
        depositValueModel.balance = balance
        return depositValueRepository.save(depositValueModel)
    }


    private fun getLastBalance(): BigDecimal? {
        return if (depositValueRepository.findAll().isNotEmpty())
            depositValueRepository.findFirstByOrderByDepositIdDesc().balance
        else
            BigDecimal.valueOf(0)
    }

    override fun getBalanceForEachHourBetweenTwoDates(balanceRequestModel: GetBalanceRequestModel): List<DepositValueModelDTO> {
        val transactions: List<BalanceReport> =
            this.depositValueRepository.findAllByDateTimeBetweenStartDateAndEndDate(
                balanceRequestModel.startDatetime,
                balanceRequestModel.endDatetime
            )
        val format: DateFormat = SimpleDateFormat("yyyy-M-dd'T'HH:mm:ss", Locale.ENGLISH)
        format.timeZone = TimeZone.getTimeZone("UTC");

        val endHourBalances = hashMapOf<Date, BigDecimal?>()
        if (transactions.size != 1) {
            for (currentIndex in 0 until transactions!!.size - 1) {
                val transaction = transactions[currentIndex]

                val transactionDate = format.parse(transactions[currentIndex].getTransaction_key())

                val nextTransactionDate = format.parse(transactions[currentIndex + 1].getTransaction_key())
                endHourBalances[transactionDate] = transaction.getMax_balance()
                val diffInHour = this.dateHandler.diffInHour(nextTransactionDate, transactionDate)
                if (diffInHour >= 1) {
                    var hours = 1
                    while (hours <= diffInHour) {
                        val tempKey = this.dateHandler.addHourToTimestamp(transactionDate, hours)
                        endHourBalances[tempKey] = transaction.getMax_balance()
                        hours++
                    }
                }
            }
        } else {
            val transactionDate = format.parse(transactions[0].getTransaction_key())
            endHourBalances[transactionDate] = transactions[0].getMax_balance()
        }
        return getListOfDepositValueModelDTOs(endHourBalances)
    }



    private fun getListOfDepositValueModelDTOs(endHourBalances: HashMap<Date, BigDecimal?>): List<DepositValueModelDTO> {
        val depositValueModelDTOs = mutableListOf<DepositValueModelDTO>()
        for (mutableEntry in endHourBalances) {
            val date:Date = this.dateHandler.addHourToTimestamp(mutableEntry.key, 1)

            val depositValueModelDTO = DepositValueModelDTO(mutableEntry.value, date)
            depositValueModelDTOs.add(depositValueModelDTO)
        }
        return depositValueModelDTOs.sortedBy { it.dateTime }
    }

}