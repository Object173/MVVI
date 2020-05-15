package com.object173.mvvi.sample.feature

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.object173.mvvi.sample.feature.user.presentation.ui.UserFragment
import com.object173.mvvi.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, UserFragment())
                .commitNow()
        }
    }
}
