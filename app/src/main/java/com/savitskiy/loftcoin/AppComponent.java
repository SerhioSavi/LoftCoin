package com.savitskiy.loftcoin;

import android.app.Application;


import com.savitskiy.loftcoin.data.DataModule;
import com.savitskiy.loftcoin.prefs.SettingsModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        SettingsModule.class,
        DataModule.class
})
abstract class AppComponent implements BaseComponent {

    @Component.Factory
    abstract static class Factory {
        abstract AppComponent create(@BindsInstance Application app);
    }
}
