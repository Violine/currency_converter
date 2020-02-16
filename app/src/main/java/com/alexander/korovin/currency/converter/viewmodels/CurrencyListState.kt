package com.alexander.korovin.currency.converter.viewmodels

import com.alexander.korovin.currency.converter.model.Currency


sealed class CurrencyListState {
    abstract val currencyList: List<Currency>
}
data class LoadingState (override val currencyList: List<Currency>) : CurrencyListState()
data class ReadyState (override val currencyList: List<Currency>) : CurrencyListState()
data class ErrorState (val errorCode: Int, override val currencyList: List<Currency>) : CurrencyListState()
data class ConvertResultState (val result: Double, override val currencyList: List<Currency>) : CurrencyListState()