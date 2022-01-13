package com.example.helper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

//Cansu Yeliz Uluğ
public class Home extends AppCompatActivity {
    private FirebaseAuth mAuth;
    BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        mAuth= FirebaseAuth.getInstance();
        navView= findViewById(R.id.bottomNav);
        navView.setOnNavigationItemSelectedListener(navViewMethod);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new Fragment()).commit();
        Toast toast;
        Thread thread;

        toast = Toast.makeText(this, "Thanks for joining us!", Toast.LENGTH_SHORT);
        thread = new Thread(new Runnable() {
            @Override
            public void run() {//thread
                for (int i=0; i<5; i++){
                    try {
                        Thread.sleep(1000);
                        toast.show();
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navViewMethod= new BottomNavigationView.OnNavigationItemSelectedListener(){
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.profile:
                    fragment = new Profile();
                    break;
                case R.id.offers:
                    fragment = new OffersFragment();
                    break;
                case R.id.proposal:
                    fragment = new ProposalsFragment();
                    break;
                case R.id.users:
                    fragment= new UsersFragment();
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
            return true;
        }

    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.logout){
            mAuth.signOut();//For Logout
            Intent intent = new Intent(Home.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}