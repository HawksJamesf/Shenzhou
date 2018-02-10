package com.hawksjamesf.simpleweather;

import com.hawksjamesf.simpleweather.ui.HomeFragment;
import com.hawksjamesf.simpleweather.ui.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Copyright ® $ 2017
 * All right reserved.
 * Code Link : https://github.com/HawksJamesf/SimpleWeather
 *  @author: hawks jamesf
 *  @since: 2017/7/4
 */

@Singleton
@Component(modules = AppModule.class/*more modules*/)
public interface AppComponent {
    void inject(SplashActivity splashActivity);

    void inject(HomeFragment homeFragment);
}
