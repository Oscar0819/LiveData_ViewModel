package com.eunwoo.livedata_viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class ActionType {
    PLUS, MINUS
}

class MyNumberViewModel : ViewModel() {

    var value = 0

    companion object {
        const val TAG: String = "로그"
    }

    // 뮤터블 라이브 데이터 - 수정가능
    // 라이브 데이터 - 변경안됨

    // 내부에서 설정하는 자료형은 뮤터블로
    // 변경 가능하도록 설정
    private val _currentValue = MutableLiveData<Int>()

    val currentValue: LiveData<Int>
        get() = _currentValue

    init {
        Log.d(TAG, "MyNumberViewModel - 생성자 호출")
        _currentValue.value = 0
    }

    fun updateValue(actionType: ActionType, input: Int) {
        when(actionType) {
            ActionType.PLUS ->
                _currentValue.value = _currentValue.value?.plus(input)
            ActionType.MINUS ->
                _currentValue.value = _currentValue.value?.minus(input)
        }
    }

}