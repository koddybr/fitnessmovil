package com.example.fitness;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import com.example.fitness.Connection.Server;
import com.example.fitness.Database.DataBase;
import com.example.fitness.Fragments.MiEstdoFragment;
import com.example.fitness.Models.Alimento;
import com.example.fitness.Models.EstadoFisico;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.LayoutInflater;
import android.view.View;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DataBase dataBase;
    Server server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        server = new Server();
        dataBase = new DataBase(this);
        //updating database

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        ArrayList<Alimento> storeAlimentos = server.getAlimentos("","");
        Alimento.actualizarDB(dataBase, storeAlimentos);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
            //return true;
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            MiEstdoFragment miEstdoFragment = new MiEstdoFragment();
            miEstdoFragment.setContext(this);
            ft.replace(R.id.contenedor, miEstdoFragment, "NewFragmentTag");
            ft.commit();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
//            Intent intent = new Intent(this, IngresandoActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
//            startActivity(intent);
//            LinearLayout mainLayout = (LinearLayout) findViewById(R.id.contenedor);
//            LayoutInflater inflater = (LayoutInflater)getSystemService(this.LAYOUT_INFLATER_SERVICE);
//            View layout = inflater.inflate(R.layout.menu, null);
//            mainLayout.removeAllViews();
//            mainLayout.addView(layout);


            Toast.makeText(MainActivity.this, "Preparando camara", Toast.LENGTH_LONG).show();

            Intent unityIntent = new Intent(MainActivity.this, UnityPlayerActivity.class);
            MainActivity.this.startActivity(unityIntent);
            //this.finish();
        } else if (id == R.id.nav_gallery) {
            LinearLayout mainLayout = (LinearLayout) findViewById(R.id.contenedor);
            LayoutInflater inflater = (LayoutInflater)getSystemService(this.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.retos, null);
            mainLayout.removeAllViews();
            mainLayout.addView(layout);

        } else if (id == R.id.nav_slideshow) {
            LinearLayout mainLayout = (LinearLayout) findViewById(R.id.contenedor);
            LayoutInflater inflater = (LayoutInflater)getSystemService(this.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.ultimo, null);
            mainLayout.removeAllViews();
            mainLayout.addView(layout);

        } else if (id == R.id.nav_tools) {
            LinearLayout mainLayout = (LinearLayout) findViewById(R.id.contenedor);
            LayoutInflater inflater = (LayoutInflater)getSystemService(this.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.recomendaciones, null);
            mainLayout.removeAllViews();
            mainLayout.addView(layout);
        } else if (id == R.id.nav_share) {
            LinearLayout mainLayout = (LinearLayout) findViewById(R.id.contenedor);
            LayoutInflater inflater = (LayoutInflater)getSystemService(this.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.quepuedocomer, null);
            mainLayout.removeAllViews();
            mainLayout.addView(layout);

        } else if (id == R.id.nav_send) {
            this.finish();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
