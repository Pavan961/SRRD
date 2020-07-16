package com.example.srrd.Home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.srrd.R;
import com.example.srrd.Settings.SettingsActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.Objects;

import com.example.srrd.Adapters.HomeAutoImageSliderAdapter;
import com.example.srrd.Donation.DonationActivity;
import com.example.srrd.Gallery.GalleryActivity;
import com.example.srrd.HistoryTimeline.HistoryTimelineActivity;
import com.example.srrd.Map.MapsActivity;
import com.example.srrd.Prayers.PrayersActivity;
import com.example.srrd.TemplePlan.TemplePlanActivity;
import com.example.srrd.Testimonials.TestimonialsActivity;
import com.example.srrd.TicketBooking.TicketBookingActivity;
import com.example.srrd.Utils.GetImages;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout mDrawer;
    NavigationView navigationView;
    Toolbar toolbar;
    Menu menu;
    TextView textView;
    LinearLayout ticketBookingLinearLayout, locationLinerLayout, templePlanLinearLayout, donationsLinearLayout, galleryLinearLayout, prayersLinearLayout, testimonialsLinearLayout, templeHistoryLinearLayout;
    SliderView sliderViewLeft;
    int leftSideSlideCount = 0;
    ArrayList<String> imageArrayList = new ArrayList<>();
    private DatabaseReference mStorageLeftRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        locationLinerLayout = findViewById(R.id.locationLLayout);
        templePlanLinearLayout = findViewById(R.id.templeLLayout);
        donationsLinearLayout = findViewById(R.id.donationsLLayout);
        galleryLinearLayout = findViewById(R.id.galleryLLayout);
        prayersLinearLayout = findViewById(R.id.prayersLLayout);
        templeHistoryLinearLayout = findViewById(R.id.templeHistoryLLayout);
        testimonialsLinearLayout = findViewById(R.id.testimonialsLLayout);
        ticketBookingLinearLayout = findViewById(R.id.ticketBookingLLayout);
        sliderViewLeft = findViewById(R.id.imageSlider);

        locationLinerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));

            }
        });
        templePlanLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), TemplePlanActivity.class));
            }
        });
        donationsLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DonationActivity.class));
            }
        });
        galleryLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), GalleryActivity.class));
            }
        });
        prayersLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PrayersActivity.class));
            }
        });
        testimonialsLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), TestimonialsActivity.class));
            }
        });
        templeHistoryLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), HistoryTimelineActivity.class));
            }
        });
        ticketBookingLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), TicketBookingActivity.class));
            }
        });
        toolbar = findViewById(R.id.toolbar_main);
        toolbar.setTitle("Home");
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }
        mStorageLeftRef = FirebaseDatabase.getInstance().getReference("homeimages");
        getHomeAutoSliderImages();
    }

    private void getHomeAutoSliderImages() {
        final GetImages getImages = new GetImages();
        mStorageLeftRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int lsc = (int) dataSnapshot.getChildrenCount();
                Log.d("pavan", "left image count: " + leftSideSlideCount);
                for (DataSnapshot snapshot : Objects.requireNonNull(dataSnapshot).getChildren()) {
                    Log.d("pavan", "left image url: " + snapshot.getValue());
                    getImages.setLeft(Objects.requireNonNull(snapshot.getValue()).toString());
                    String stringImages = snapshot.getValue().toString();
                    Log.d("now", "left image urls sting: " + stringImages);
                    imageArrayList.add(stringImages);
                }
                leftSideSlideCount = lsc;
                leftSlides();
            }

            private void leftSlides() {
                HomeAutoImageSliderAdapter adapter = new HomeAutoImageSliderAdapter(getApplicationContext(), imageArrayList);
                Log.d("kpp", imageArrayList + "");
                adapter.setCount(leftSideSlideCount);
                sliderViewLeft.setSliderAdapter(adapter);
                sliderViewLeft.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
                sliderViewLeft.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
                sliderViewLeft.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
                sliderViewLeft.setIndicatorSelectedColor(Color.WHITE);
                sliderViewLeft.setIndicatorUnselectedColor(Color.GRAY);
                sliderViewLeft.setScrollTimeInSec(5);
                sliderViewLeft.startAutoCycle();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("pavan", databaseError.getMessage());
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
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
        // automatically handle clicks on the com.example.srrd.Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home_menu) {
            // Handle the camera action
        } else if (id == R.id.divine_menu) {

        } else if (id == R.id.quiz_menu) {

        } else if (id == R.id.settings_menu) {
            startActivity(new Intent(HomeActivity.this, SettingsActivity.class));
        } else if (id == R.id.share_menu) {

        } else if (id == R.id.logout_menu) {

        }
        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }
}