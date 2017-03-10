package app.rocketship.metabolicb.utils;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import app.rocketship.natrapharmutil.DataHandler;
import app.rocketship.natrapharmutil.sqlite.SQLiteSingleton;
import app.rocketship.metabolicb.MainActivity;
import app.rocketship.metabolicb.R;

public class SliderActivity extends AppCompatActivity {

    private PagerAdapter pagerAdapter;
    private ViewPager sliderViewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);

        sliderViewPager = (ViewPager) findViewById(R.id.sliderViewPager);
        tabLayout = (TabLayout) findViewById(R.id.sliderdotlayout);

        DataHandler.setNetworkConnection();

        pagerAdapter = new SliderFragmentPagerAdapter(getSupportFragmentManager(), MainActivity.selectedPage);
        sliderViewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(sliderViewPager, true);
    }

    private void switchSliders(PageSlidesHandler.Page page){

        DataHandler.setCurrentContext(this);
        DataHandler.savePageClick(page.getKey());

        MainActivity.selectedPage = page;

        Intent i = new Intent(this, SliderActivity.class);

        startActivity(i);
        finish();
        overridePendingTransition(0, R.anim.fade_in);

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(SliderActivity.this, MainActivity.class);
        startActivity(i);
        finish();
        overridePendingTransition( 0, R.anim.fade_in );
    }

    @Override
    protected void onDestroy(){

        SQLiteSingleton.getInstance(this).close();

        super.onDestroy();
    }

}
