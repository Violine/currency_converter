package com.alexander.korovin.currency.converter.ui.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alexander.korovin.currency.converter.R
import com.alexander.korovin.currency.converter.model.Currency
import com.alexander.korovin.currency.converter.utils.CustomSpinnerOnTouchListener
import com.alexander.korovin.currency.converter.utils.CustomSpinnerOnTouchListener.SelectCallback
import com.alexander.korovin.currency.converter.utils.ErrorCode
import com.alexander.korovin.currency.converter.viewmodels.*
import kotlinx.android.synthetic.main.main_fragment.*
import java.lang.NumberFormatException


class MainFragment : Fragment() {

    private lateinit var spinnerAdapter: ArrayAdapter<Currency>
    lateinit private var progressView: ContentLoadingProgressBar
    private val viewModel by lazy {ViewModelProvider(requireActivity(), MainViewModelFactory()).get(MainViewModel::class.java)}

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
        spinnerAdapter = ArrayAdapter<Currency>(requireContext(), R.layout.spinner_layout, currencyList)
        to_spinner.apply {
            adapter = spinnerAdapter
            setSelection(spinnerAdapter.getPosition(viewModel.toCurrency))
        }

        from_spinner.apply {
            adapter = spinnerAdapter
            setSelection(spinnerAdapter.getPosition(viewModel.fromCurrency))
        }

        viewModel.quantity.let {quantity.setText(viewModel.quantity.toString())}
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
    }

    private fun initView() {
        toolbar.apply {
            title = getString(R.string.toolbar_title_text)
            setTitleTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        }
        quantity.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!p0.isNullOrBlank()) {
                    try {
                        viewModel.quantity = p0.toString().toDouble()
                    } catch (e: NumberFormatException) {
                        showToast(getString(R.string.error_please_enter_numeric))
                    }
                }
            }
        })
        button_convert.setOnClickListener({ _ -> viewModel.convertCurrency() })
        val fromSpinnerListener = CustomSpinnerOnTouchListener (object : SelectCallback {
            override fun onItemSelected(position: Int) {
                viewModel.fromCurrency = spinnerAdapter.getItem(position)
            }
        })
        val toSpinnerListener = CustomSpinnerOnTouchListener (object : SelectCallback {
            override fun onItemSelected(position: Int) {
                viewModel.toCurrency = spinnerAdapter.getItem(position)
            }
        })
        from_spinner.setOnTouchListener(fromSpinnerListener)
        to_spinner.setOnTouchListener(toSpinnerListener)
        from_spinner.onItemSelectedListener = fromSpinnerListener
        to_spinner.onItemSelectedListener = toSpinnerListener
    }

    private fun initViewModel() {
        viewModel.getCurrencyList()
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
