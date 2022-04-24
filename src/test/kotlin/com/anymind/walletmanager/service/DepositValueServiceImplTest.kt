package com.anymind.walletmanager.service

import com.anymind.walletmanager.MotherObject
import com.anymind.walletmanager.model.DepositValueModel
import com.anymind.walletmanager.repository.DepositValueRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

internal class DepositValueServiceImplTest {

    @InjectMocks
    var depositService: DepositValueServiceImpl? = null

    @Mock
    var depositValueRepository: DepositValueRepository? = null

    private lateinit var depositValueModel: DepositValueModel

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        depositValueModel = MotherObject.getAnyDepositValueModel()
    }

    @Test
    fun testContext() {
        assertNotNull(depositService)
        assertNotNull(depositValueRepository)
    }
}