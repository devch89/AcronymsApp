package com.example.acronymsmeaning.data.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.acronymsmeaning.ui.ViewModelProviderFactory
import com.example.acronymsmeaning.ui.fragments.HomeViewModel
import com.example.acronymsmeaning.data.di.ViewModelKey

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeFragmentViewModel(homeFragmentViewModel: HomeViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}