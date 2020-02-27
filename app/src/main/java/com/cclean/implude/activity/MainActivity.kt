package com.cclean.implude.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.cclean.implude.MainViewModel
import com.cclean.implude.R
import com.cclean.implude.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        val mainViewModel = MainViewModel(
            supportFragmentManager,
            layout_nav,
            viewpager_main
        )

        binding.viewModel = mainViewModel
    }

}
