package com.alexander.korovin.currency.converter.utils

import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView

class CustomSpinnerOnTouchListener (val callback : SelectCallback)  : AdapterView.OnItemSelectedListener, View.OnTouchListener {
    interface SelectCallback {
        fun onItemSelected(position: Int)
    }

    var isUserTouch = false

    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
        isUserTouch = true
        return false
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {}

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        if (isUserTouch) {
            isUserTouch = false
            callback.onItemSelected(p2)
        }

    }
}