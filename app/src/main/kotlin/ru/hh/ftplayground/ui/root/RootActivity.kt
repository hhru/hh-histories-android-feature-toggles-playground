package ru.hh.ftplayground.ui.root

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ru.hh.ftplayground.R


@AndroidEntryPoint
class RootActivity : AppCompatActivity(R.layout.activity_root) {

    private val viewModel by viewModels<RootViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onInited()
    }

}