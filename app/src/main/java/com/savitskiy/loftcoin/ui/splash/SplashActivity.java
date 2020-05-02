package com.savitskiy.loftcoin.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.savitskiy.loftcoin.BaseComponent;
import com.savitskiy.loftcoin.R;
import com.savitskiy.loftcoin.prefs.Settings;
import com.savitskiy.loftcoin.ui.main.MainActivity;
import com.savitskiy.loftcoin.ui.welcome.WelcomeActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //there is example of Service Locator pattern (usage of Settings through AppComponent) implementation:
        final Settings settings = BaseComponent.get(this).settings();

        new Handler().postDelayed(()-> {
            if (settings.shouldShowWelcomeScreen()) {
                startActivity(new Intent(this, WelcomeActivity.class));
            } else {
                startActivity(new Intent(this, MainActivity.class));
            }
        }, 1000);

    }
}
