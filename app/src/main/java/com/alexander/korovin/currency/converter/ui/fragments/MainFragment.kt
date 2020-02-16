package com.alexander.korovin.currency.converter.ui.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alexander.korovin.currency.converter.R
import com.alexander.korovin.currency.converter.model.Currency
import com.alexander.korovin.currency.converter.utils.ErrorCode
import com.alexander.korovin.currency.converter.viewmodels.*
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.main_fragment.view.*


class MainFragment : Fragment() {

    lateinit private var progressView: ContentLoadingProgressBar
    private lateinit var viewModel: MainViewModel
    private var stateObserver = Observer<CurrencyListState> { state ->
        state?.let {
            when (state) {
                is LoadingState -> {
                    progress.show()
                }
                is ReadyState -> {
                    progress.hide()
                    initSpinners(it.currencyList)
                }
                is ErrorState -> {
                    progress.hide()
                    handleError((it as ErrorState).errorCode)
                }
                is ConvertResultState -> {
                    hideKeyboard ()
                    result.text = (it as ConvertResultState).result.toString()
                }
            }
        }
    }

    private fun initSpinners(currencyList: List<Currency>) {
        val adapter: ArrayAdapter<Currency> = ArrayAdapter<Currency>(requireContext(), R.layout.spinner_layout, currencyList)
        from_spinner.adapter = adapter
        to_spinner.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
        savedInstanceState?.let { viewModel.restoreCurrencyList() } ?: viewModel.getCurrencyList()
    }

    private fun initView() {
        toolbar.apply {
            title = getString(R.string.toolbar_title_text)
            setTitleTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        }
        button_convert.setOnClickListener({ _ ->
            run {
                var toCurrency: Currency = to_spinner.selectedItem as Currency
                var fromCurrency: Currency = from_spinner.selectedItem as Currency
                var currencyQuantity: String = quantity_input_layout.quantity.text.toString().trim()
                if (currencyQuantity.isNotEmpty()) {
                    viewModel.convertCurrency(fromCurrency, toCurrency, currencyQuantity.toDouble())
                } else {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.error_empty_field_message),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, MainViewModelFactory()).get(MainViewModel::class.java)
        viewModel.state.observe(viewLifecycleOwner, stateObserver)
    }

    private fun handleError(errorCode: Int) {
        var errorMessage: String = getString(R.string.error_unknown_message)
        when (errorCode) {
            ErrorCode.EMPTY_FIELD_ERROR -> {
                errorMessage = getString(R.string.error_empty_field_message)
            }
            ErrorCode.NO_CURRENCY_DATA_ERROR -> {
                errorMessage = getString(R.string.error_no_currency_data_message)
            }
            ErrorCode.NETWORK_ERROR -> {
                errorMessage = getString(R.string.error_network_message)
            }
        }
        showToast(errorMessage)
    }

    private fun showToast (message : String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
