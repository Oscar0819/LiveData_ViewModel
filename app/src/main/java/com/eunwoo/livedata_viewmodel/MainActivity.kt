package com.eunwoo.livedata_viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.eunwoo.livedata_viewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    companion object {
        const val TAG: String = "로그"
    }

    lateinit var myNumberViewModel: MyNumberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        myNumberViewModel = ViewModelProvider(this).get(MyNumberViewModel::class.java)

        myNumberViewModel.currentValue.observe(this, Observer {
            Log.d(TAG, "MainActivity - myNumberViewModel - currentValue 라이브 데이터 값 변경 : $it")
            binding.numberTextview.text = it.toString()
        })

        binding.plusBtn.setOnClickListener(this)
        binding.minusBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        var userInput: Int = binding.userinputEdittext.text.toString().toInt()
        Log.d(TAG, userInput.toString())
        when(v) {
            binding.plusBtn ->
                myNumberViewModel.updateValue(actionType = ActionType.PLUS, input = userInput)
            binding.minusBtn ->
                myNumberViewModel.updateValue(actionType = ActionType.MINUS, input = userInput)
        }
    }
}