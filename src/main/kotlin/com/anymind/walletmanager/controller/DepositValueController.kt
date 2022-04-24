package com.anymind.walletmanager.controller

import com.anymind.walletmanager.model.DepositMapper
import com.anymind.walletmanager.model.DepositValueModelDTO
import com.anymind.walletmanager.service.DepositValueServiceImpl
import org.mapstruct.factory.Mappers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/deposits")
class DepositValueController(@Autowired private val depositValueServiceImpl: DepositValueServiceImpl) {

    val depositMapper: DepositMapper = Mappers.getMapper(DepositMapper::class.java)


    @PostMapping
    fun save(@Valid @RequestBody depositValueModelDTO: DepositValueModelDTO): ResponseEntity<DepositValueModelDTO> {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(depositMapper.toDepositDTO(depositValueServiceImpl.save(depositMapper.toDeposit(depositValueModelDTO))))
    }

    @PostMapping("/getBalanceBetweenTwoDates")
    fun getBalanceForEachHourBetweenTwoDates(@RequestBody getBalanceRequestModel: GetBalanceRequestModel):
            ResponseEntity<List<DepositValueModelDTO>>{
        return ResponseEntity<List<DepositValueModelDTO>>(this.depositValueServiceImpl.getBalanceForEachHourBetweenTwoDates(getBalanceRequestModel), HttpStatus.OK)
    }
}