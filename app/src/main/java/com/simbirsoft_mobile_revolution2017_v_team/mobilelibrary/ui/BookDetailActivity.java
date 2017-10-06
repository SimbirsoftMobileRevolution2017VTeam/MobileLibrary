package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.ui;

import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

public class BookDetailActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }

        if (savedInstanceState == null) {
            BookDetailFragment fragment = new BookDetailFragment();
            Bundle arguments = getIntent().getExtras();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, fragment).commit();
        }
    }

}
