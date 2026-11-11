package com.example.cst2335lab8;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    protected DrawerLayout drawerLayout;
    protected NavigationView navigationView;

    @LayoutRes
    protected int getContentLayout() {
        return 0;
    }

    protected String getToolbarTitle() {
        return "Dog World";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        Toolbar toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getToolbarTitle());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        FrameLayout content = findViewById(R.id.activity_content);
        getLayoutInflater().inflate(getContentLayout(), content, true);

        if (this instanceof MainActivity) {
            navigationView.setCheckedItem(R.id.nav_home);
        } else if (this instanceof DadJoke) {
            navigationView.setCheckedItem(R.id.nav_joke);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.choice_one) {
            Toast.makeText(this, "You clicked on item 1", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.choice_two) {
            Toast.makeText(this, "You clicked on item 2", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.more_info) {
            Toast.makeText(this, "Welcome to Dog World", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            if (!(this instanceof MainActivity)) {
                startActivity(new Intent(this, MainActivity.class));
            }
        } else if (id == R.id.nav_joke) {
            if (!(this instanceof DadJoke)) {
                startActivity(new Intent(this, DadJoke.class));
            }
        } else if (id == R.id.nav_exit) {
            finishAffinity();
        }

        drawerLayout.closeDrawers();
        return true;
    }
}
