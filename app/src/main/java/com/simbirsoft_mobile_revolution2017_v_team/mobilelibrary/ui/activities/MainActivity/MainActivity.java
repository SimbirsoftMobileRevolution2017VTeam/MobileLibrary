package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.ui.activities.MainActivity;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.R;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.ui.fragments.FavouriteBooksFragment;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.ui.fragments.LibraryFragment;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.ui.fragments.ListBooksFragment.BookAdderFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.floatingActionButton_addBook)
    FloatingActionButton buttonAddBook;

    ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        buttonAddBook.setOnClickListener(view -> addBook(view));
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        BookAdderFragment libraryFragment = new LibraryFragment();
        adapter.addFragment(libraryFragment, getResources().getString(R.string.all_books));
        adapter.addFragment(new FavouriteBooksFragment(), getResources().getString(R.string.favourites));
        viewPager.setAdapter(adapter);
    }

    private void addBook(View view) {
        Fragment fragment =  adapter.getItem(tabLayout.getSelectedTabPosition());
        ((BookAdderFragment)fragment).addBook();
    }
}
