package com.example.empresas_android.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.example.empresas_android.R

class CustomEditText : FrameLayout{
    constructor(context: Context) : super(context) {
        setupView()
    }

    private fun setupView(attrs: AttributeSet? = null) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setupView(attrs)
    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        setupView(attrs)
    }

    init {
        View.inflate(context, R.layout.custom_edit_text, this)
    }
}