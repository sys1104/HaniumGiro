package com.example.yssong.tabbedexample;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AfterLogin extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private Toolbar mToolBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.afterlogin);
//        mToolBar.setTitle("간편하GIRO");
//        mToolBar.setTitleTextColor(Color.WHITE);
        mToolBar =(Toolbar) findViewById(R.id.toolbar);
        Button toolbarTitle = (Button) mToolBar.findViewById(R.id.btn_toolbar_title);
        toolbarTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AfterLogin.class);
                getApplicationContext().startActivity(intent);
            }
        });
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "BMJUA.ttf");
        toolbarTitle.setTypeface(myTypeface);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.friends);  //탭 레이아웃에 아이콘 추가
        tabLayout.getTabAt(1).setIcon(R.drawable.history);
        tabLayout.getTabAt(2).setIcon(R.drawable.more);
    }//endOnCreate


    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //툴바 우측 메뉴버튼 설정 관련
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //툴바 우측 메뉴버튼 설정 관련
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            Toast.makeText(AfterLogin.this, "로그아웃 되었습니다", Toast.LENGTH_SHORT).show();
            SharedPreferences autoLogin = getSharedPreferences("autoLogin", Activity.MODE_PRIVATE);
            SharedPreferences.Editor autoLoginEdit = autoLogin.edit();
            autoLoginEdit.clear();
            //꼭 commit()을 해줘야 값이 저장된다.
            autoLoginEdit.apply();
            Intent intent = new Intent(AfterLogin.this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position)
            {
                case 0 :
                    Tab1Contacts tab1 = new Tab1Contacts();
                    return tab1;
                case 1 :
                    Tab2History tab2 = new Tab2History();
                    return tab2;
                case 2 :
                    Tab3More tab3 = new Tab3More();
                    return tab3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

//        @Override
//        public CharSequence getPageTitle(int position) {  //탭레이아웃에 글자추가
//            switch (position) {
//                case 0:
//                    return "친구";
//                case 1:
//                    return "납부내역";
//                case 2:
//                    return "더보기";
//            }
//            return null;
//        }
    }
}
