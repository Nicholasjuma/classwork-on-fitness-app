package com.example.fitnesstracking;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private BottomNavigationView bottomNavigationView;
    private TextView welcomeTextView;
    private TextView progressStepsTextView;
    private TextView progressCaloriesTextView;
    private View cardView;
    private FloatingActionButton openBottomSheetButton;
    private View horizontalScrollView;

    private boolean isWelcomeTextVisible;
    private boolean isCardViewVisible;
    private boolean isBottomSheetButtonVisible;
    private boolean isHorizontalScrollViewVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return handleBottomNavigationSelection(item.getItemId());
            }
        });

        welcomeTextView = findViewById(R.id.welcome_text);
        cardView = findViewById(R.id.cardView);
        openBottomSheetButton = findViewById(R.id.open_bottom_sheet_button);
        horizontalScrollView = findViewById(R.id.horizontal_scroll_view);

        openBottomSheetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialogFragment bottomSheet = new BottomSheetFragment();
                bottomSheet.show(getSupportFragmentManager(), "BottomSheetFragment");
            }
        });

        progressStepsTextView = findViewById(R.id.steps_text);
        progressCaloriesTextView = findViewById(R.id.calories_text);
        progressStepsTextView.setText("Steps: 5000");
        progressCaloriesTextView.setText("Calories: 250");

        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.nav_home);
        } else {

            restoreVisibilityState(savedInstanceState);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean("isWelcomeTextVisible", isWelcomeTextVisible);
        outState.putBoolean("isCardViewVisible", isCardViewVisible);
        outState.putBoolean("isBottomSheetButtonVisible", isBottomSheetButtonVisible);
        outState.putBoolean("isHorizontalScrollViewVisible", isHorizontalScrollViewVisible);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        restoreVisibilityState(savedInstanceState);
    }

    private void restoreVisibilityState(Bundle savedInstanceState) {
        isWelcomeTextVisible = savedInstanceState.getBoolean("isWelcomeTextVisible", false);
        isCardViewVisible = savedInstanceState.getBoolean("isCardViewVisible", false);
        isBottomSheetButtonVisible = savedInstanceState.getBoolean("isBottomSheetButtonVisible", false);
        isHorizontalScrollViewVisible = savedInstanceState.getBoolean("isHorizontalScrollViewVisible", false);

        welcomeTextView.setVisibility(isWelcomeTextVisible ? View.VISIBLE : View.GONE);
        cardView.setVisibility(isCardViewVisible ? View.VISIBLE : View.GONE);
        openBottomSheetButton.setVisibility(isBottomSheetButtonVisible ? View.VISIBLE : View.GONE);
        horizontalScrollView.setVisibility(isHorizontalScrollViewVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return handleNavigationSelection(item.getItemId());
    }

    private boolean handleBottomNavigationSelection(int id) {
        Fragment selectedFragment = null;

        if (id == R.id.nav_home) {

            welcomeTextView.setVisibility(View.VISIBLE);
            cardView.setVisibility(View.VISIBLE);
            openBottomSheetButton.setVisibility(View.VISIBLE);
            horizontalScrollView.setVisibility(View.VISIBLE);

            isWelcomeTextVisible = true;
            isCardViewVisible = true;
            isBottomSheetButtonVisible = true;
            isHorizontalScrollViewVisible = true;

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment()).commit(); // Clear fragment container
        } else if (id == R.id.nav_workouts) {

            selectedFragment = new WorkoutsFragment();
            welcomeTextView.setVisibility(View.GONE);
            cardView.setVisibility(View.GONE);
            openBottomSheetButton.setVisibility(View.GONE);
            horizontalScrollView.setVisibility(View.GONE);

            isWelcomeTextVisible = false;
            isCardViewVisible = false;
            isBottomSheetButtonVisible = false;
            isHorizontalScrollViewVisible = false;
        } else if (id == R.id.nav_progress) {

            selectedFragment = new ProgressFragment();
            welcomeTextView.setVisibility(View.GONE);
            cardView.setVisibility(View.GONE);
            openBottomSheetButton.setVisibility(View.GONE);
            horizontalScrollView.setVisibility(View.GONE);

            isWelcomeTextVisible = false;
            isCardViewVisible = false;
            isBottomSheetButtonVisible = false;
            isHorizontalScrollViewVisible = false;
        }

        if (selectedFragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, selectedFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }

        return true;
    }

    private boolean handleNavigationSelection(int id) {
        if (id == R.id.nav_profile) {
            // Start ProfileActivity
            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
        } else if (id == R.id.nav_settings) {
            // Start SettingsActivity
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
