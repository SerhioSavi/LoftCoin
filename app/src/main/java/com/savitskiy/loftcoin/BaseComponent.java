package com.savitskiy.loftcoin;

import android.content.Context;

import androidx.annotation.NonNull;

import com.savitskiy.loftcoin.data.CoinsRepo;
import com.savitskiy.loftcoin.data.CurrencyRepo;
import com.savitskiy.loftcoin.prefs.Settings;
import com.savitskiy.loftcoin.util.ChangeFormatter;
import com.savitskiy.loftcoin.util.PriceFormatter;

public interface BaseComponent {

    static BaseComponent get(@NonNull Context context) {
        if (context.getApplicationContext() instanceof LoftApp) {
            return ((LoftApp)context.getApplicationContext()).getComponent();
        }
        throw new IllegalArgumentException("No such component in" + context);
    }

    Context context();

    Settings settings();

    CurrencyRepo currencyRepo();

    CoinsRepo coinsRepo();

    PriceFormatter priceFormatter();

    ChangeFormatter changeFormatter();
}
