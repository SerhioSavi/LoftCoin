package com.savitskiy.loftcoin.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public interface CoinsRepo {
    void listings(@NonNull MutableLiveData<List<Coin>> coins,
                  @NonNull MutableLiveData<Boolean> loading,
                  @NonNull Currency currency);
}
