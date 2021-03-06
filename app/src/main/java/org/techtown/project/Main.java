package org.techtown.project;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Main extends AppCompatActivity {
    // 주석
    smpFragment frag_dash;
    weatherFragment frag_home;
    communicationFragment frag_noti;
    predictFragment frag_black;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        frag_black = new predictFragment();
        frag_dash = new smpFragment();
        frag_home = new weatherFragment();
        frag_noti = new communicationFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, frag_home).commit();

        BottomNavigationView bnv = findViewById(R.id.nav_view);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()){
                    case R.id.item1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, frag_home).commit();
                        Toast.makeText(getApplicationContext(), "home", Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.item2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, frag_black).commit();

                        return true;
                    case R.id.item3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, frag_dash).commit();

                        return true;
                    case R.id.item4:
                        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, frag_noti).commit();

                        return true;
                }
                return false;
            }
        });
    }

}
