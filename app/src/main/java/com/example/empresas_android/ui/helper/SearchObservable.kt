package com.example.empresas_android.ui.helper

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

class SearchObservable: BaseObservable() {
    var twoWayDataBindingSearch: String? = null
    @Bindable
    get

    var twoWayDataBindingText: String? = null
    @Bindable
    set(value) {

        notifyPropertyChanged(BR.twoWayDataBindingSearch)
    }
}