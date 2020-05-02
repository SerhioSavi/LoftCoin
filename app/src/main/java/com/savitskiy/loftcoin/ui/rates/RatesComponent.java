package com.savitskiy.loftcoin.ui.rates;

import androidx.fragment.app.Fragment;

import com.savitskiy.loftcoin.BaseComponent;

import dagger.Component;

@Component(modules = {
    RatesModule.class
}, dependencies = {
    BaseComponent.class,
    Fragment.class
})
abstract class RatesComponent {

    abstract void inject(RatesFragment ratesFragment);

    abstract void inject(RatesCurrencyDialog ratesCurrencyDialog);

    abstract void inject(RatesViewModel viewModel);
}
