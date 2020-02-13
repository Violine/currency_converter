package com.alexander.korovin.currency.converter.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alexander.korovin.currency.converter.App
import com.alexander.korovin.currency.converter.repository.Repository
import java.lang.IllegalArgumentException
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory : ViewModelProvider.Factory {
    @Inject
    lateinit var repository: Repository

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        App.instance.restApiComponent.inject(this)
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Illegal ViewModel class")
    }
}