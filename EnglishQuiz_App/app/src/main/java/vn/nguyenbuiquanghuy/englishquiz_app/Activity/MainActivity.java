package vn.nguyenbuiquanghuy.englishquiz_app.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import vn.nguyenbuiquanghuy.englishquiz_app.Fragment.HomeFragment;
import vn.nguyenbuiquanghuy.englishquiz_app.Fragment.SettingFragment;
import vn.nguyenbuiquanghuy.englishquiz_app.Fragment.ProfileFragment;
import vn.nguyenbuiquanghuy.englishquiz_app.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar); //Ignore red line errors
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new HomeFragment()).commit();
        } else if (item.getItemId() == R.id.nav_setting) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new SettingFragment()).commit();
        } else if (item.getItemId() == R.id.nav_profile) {

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new ProfileFragment()).commit();
        } else if (item.getItemId() == R.id.nav_logout) {
            new AlertDialog.Builder(this)
                    .setTitle("Thoát")
                    .setMessage("Bạn có chắc chắn muốn thoát ứng dụng?")
                    .setPositiveButton("Có", (dialog, which) -> {
                        finishAffinity();
                        System.exit(0);
                    })
                    .setNegativeButton("Không", null)
                    .show();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_nav, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView=(SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search Topics");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                performSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                performSearch(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void performSearch(String query) {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_layout);

        if (currentFragment instanceof HomeFragment) {
            ((HomeFragment) currentFragment).filterTopics(query);
        }
    }

}