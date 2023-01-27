package com.example.acronymsmeaning.data.di

import android.app.Application
import com.example.acronymsmeaning.AcronymsApp
import com.example.acronymsmeaning.data.di.modules.ActivityBindingModule
import com.example.acronymsmeaning.data.di.modules.AppModule
import com.example.acronymsmeaning.data.di.modules.FragmentBindingModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class),
    (AppModule::class),
    (ActivityBindingModule::class), (FragmentBindingModule::class)])
interface AppComponent : AndroidInjector<AcronymsApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: Application)
}