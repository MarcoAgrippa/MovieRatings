package com.movieratings.igorgvozdic.movies;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.movieratings.igorgvozdic.movies.fragments.CreditsFragment;
import com.movieratings.igorgvozdic.movies.fragments.FavoriteMoviesFragment;
import com.movieratings.igorgvozdic.movies.fragments.PopularFragment;
import com.movieratings.igorgvozdic.movies.fragments.StartUpFragment;
import com.movieratings.igorgvozdic.movies.fragments.TopRatedFragment;
import com.movieratings.igorgvozdic.movies.fragments.UpcomingFragment;

public class MainActivity extends AppCompatActivity
                          implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";


    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.back_arrow);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        StartUpFragment startUpFragment = new StartUpFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_container, startUpFragment);
        transaction.commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            int fragments = getSupportFragmentManager().getBackStackEntryCount();
            if (fragments == 1) {
                finish();
            } else {
                if (getFragmentManager().getBackStackEntryCount() > 1) {
                    getFragmentManager().popBackStack();
                } else {
                    super.onBackPressed();
                }
            }
        }
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this, "Coming soon...", Toast.LENGTH_LONG).show();
            return true;
        }

        if (id == R.id.sort_by_name) {
            return true;
        }

        if (id == R.id.sort_by_average_score) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.nav_first_popular:
                PopularFragment popularFragment = new PopularFragment();
                loadFragment(popularFragment, getString(R.string.popular_movies_toolbar));
                break;

            case R.id.nav_second_top_rated:
                TopRatedFragment topRatedFragment = new TopRatedFragment();
                loadFragment(topRatedFragment, getString(R.string.top_rated_movie_toolbar));
                break;

            case R.id.nav_third_upcoming:
                UpcomingFragment upcomingFragment = new UpcomingFragment();
                loadFragment(upcomingFragment, getString(R.string.upcoming_movie_toolbar));
                break;

            case R.id.nav_fourth_favorites:
                FavoriteMoviesFragment favoriteFragment = new FavoriteMoviesFragment();
                loadFragment(favoriteFragment, getString(R.string.favorite_movies_toolbar));
                break;

            case R.id.nav_fifth_credits:
                CreditsFragment creditsFragment = new CreditsFragment();
                loadFragment(creditsFragment, getString(R.string.credits));
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadFragment(Fragment fragment, String toolbarTitle) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        toolbar.setTitle(toolbarTitle);
        transaction.commit();
    }



}
