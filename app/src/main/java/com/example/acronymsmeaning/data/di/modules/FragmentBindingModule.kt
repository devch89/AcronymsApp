package com.example.acronymsmeaning.data.di.modules

import com.example.acronymsmeaning.ui.fragments.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    abstract fun provideHomeFragment(): HomeFragment
}