package com.example.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewpager;
    private List<String> mLists = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        mViewpager.setAdapter(new pagerAdapter(getSupportFragmentManager()));
    }

    private void initView() {
        mViewpager = (ViewPager) findViewById(R.id.viewpager);
        for (int i = 0; i < 1000; i++) {
            mLists.add("测试" + i);
        }
    }

    private class pagerAdapter extends FragmentPagerAdapter {
        FragmentManager mFragmentManager;
        FragmentTransaction mCurTransaction;
         BlankFragment mCurrentPrimaryItem;
        public pagerAdapter(FragmentManager fm) {
            super(fm);
            mFragmentManager = fm;
        }

        @Override
        public Fragment getItem(int position) {
            mCurrentPrimaryItem = BlankFragment.newInstance(mLists.get(position), "");
            return mCurrentPrimaryItem;
        }

        @Override
        public int getCount() {
            return mLists.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }
        /*   @Override
        public Object instantiateItem(ViewGroup container, int position) {
           if (mCurTransaction == null) {
                 mCurTransaction = mFragmentManager.beginTransaction();
            }
            final long itemId = getItemId(position);
            String name = makeFragmentName(container.getId(), itemId);
            Fragment fragment = mFragmentManager.findFragmentByTag(name);
            if (fragment != null) {
                //用show而不用attach，防止频繁调用oncreatview
                mCurTransaction.show(fragment);
            }else {
                fragment = getItem(position);
                mCurTransaction.add(container.getId(), fragment,
                        makeFragmentName(container.getId(), itemId));
            }

            if (fragment != mCurrentPrimaryItem) {
                fragment.setMenuVisibility(false);
                fragment.setUserVisibleHint(false);
            }
            return fragment;
        }*/



        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            if (mCurTransaction == null) {
                mCurTransaction = mFragmentManager.beginTransaction();
            }
            //用hide而不用detach，防止频繁调用oncreatview
            mCurTransaction.hide((Fragment) object);
            super.destroyItem(container, position, object);
        }
    }



    private static String makeFragmentName(int viewId, long index) {
        return "android:switcher:" + viewId + ":" + index;
    }
}
