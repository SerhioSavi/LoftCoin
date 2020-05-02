package com.savitskiy.loftcoin;

//Dagger.module is responsible to provide dependency
import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
abstract class AppModule {

    @Provides
    @Singleton
    static Context context(Application app) {
        return app.getApplicationContext();
    }

}
