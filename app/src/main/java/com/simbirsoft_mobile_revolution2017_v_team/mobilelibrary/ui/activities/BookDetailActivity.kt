package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.ui.activities

import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.ui.fragments.BookDetailFragmentK

/**
 * Created by user on 15.11.2017.
 */

class BookDetailActivityK : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish()
            return
        }

        if (savedInstanceState == null) {
            val fragment = BookDetailFragmentK()
            val arguments = intent.extras
            fragment.arguments = arguments
            supportFragmentManager.beginTransaction().add(android.R.id.content, fragment).commit()
        }
    }
}