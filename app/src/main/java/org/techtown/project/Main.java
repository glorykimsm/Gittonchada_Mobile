package org.techtown.project;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.techtown.project.ui.dashboard.DashboardFragment;
import org.techtown.project.ui.home.HomeFragment;
import org.techtown.project.ui.notifications.NotificationsFragment;

public class Main extends AppCompatActivity {

    DashboardFragment frag_dash;
    HomeFragment frag_home;
    NotificationsFragment frag_noti;
    BlankFragment frag_black;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        frag_black = new BlankFragment();
        frag_dash = new DashboardFragment();
        frag_home = new HomeFragment();
        frag_noti = new NotificationsFragment();

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
