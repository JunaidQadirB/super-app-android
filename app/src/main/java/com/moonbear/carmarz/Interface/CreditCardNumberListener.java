package com.moonbear.carmarz.Interface;

import androidx.annotation.NonNull;

import com.moonbear.carmarz.codeclasses.CreditCardBrand;


public interface CreditCardNumberListener {

    void onChanged(@NonNull String number, @NonNull CreditCardBrand brand);
}
