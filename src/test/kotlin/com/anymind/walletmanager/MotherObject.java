package com.anymind.walletmanager;

import com.anymind.walletmanager.model.DepositValueModel;

import java.math.BigDecimal;
import java.util.Date;

public class MotherObject {
    public static DepositValueModel getAnyDepositValueModel() {
        return new DepositValueModel(1L, BigDecimal.TEN, new Date(), BigDecimal.ZERO);
    }
}
