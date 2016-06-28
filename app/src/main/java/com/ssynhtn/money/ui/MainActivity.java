package com.ssynhtn.money.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ssynhtn.money.R;
import com.ssynhtn.money.ui.base.BaseActivity;
import com.ssynhtn.money.ui.test.TestFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ViewPager mViewPager;
    private MainAdapter mPagerAdapter;
    private LinearLayout mTabsLinearLayout;
    private List<View> mTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mPagerAdapter = new MainAdapter(getSupportFragmentManager(), getTabItems());

        mViewPager.setAdapter(mPagerAdapter);

        mTabsLinearLayout = (LinearLayout) findViewById(R.id.linear_layout_tabs);
        createTabs();

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < mTabs.size(); i++) {
                    mTabs.get(i).setSelected(i == position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mTabs.get(mViewPager.getCurrentItem()).setSelected(true);

    }

    private void createTabs() {
        mTabs = new ArrayList<>();

        for (int i = 0; i < mPagerAdapter.getCount(); i++) {
            final int position = i;
            MainAdapter.TabItem tabItem = mPagerAdapter.getTabItem(i);

            View tab = getLayoutInflater().inflate(R.layout.tab_item_view, mTabsLinearLayout, false);
            mTabsLinearLayout.addView(tab);

            configureTab(tab, tabItem);
            tab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.setCurrentItem(position);
                }
            });

            mTabs.add(tab);


        }
    }

    private void configureTab(View tab, MainAdapter.TabItem tabItem) {
        TextView tabNameTextView = (TextView) tab.findViewById(R.id.text_view_tab_name);
        tabNameTextView.setText(tabItem.getTabName());

        ImageView tabIconImageView = (ImageView) tab.findViewById(R.id.image_view_tab_icon);
        tabIconImageView.setImageResource(tabItem.getIconDrawable());
    }

    private List<MainAdapter.TabItem> getTabItems() {
        List<MainAdapter.TabItem> tabItems = new ArrayList<>();

        tabItems.add(new MainAdapter.TabItem() {
            @Override
            public String getTabName() {
                return getString(R.string.tab_account_book);
            }

            @Override
            public int getIconDrawable() {
                return R.drawable.tab_money_book;
            }

            @Override
            public Fragment getFragment() {
                return new MoneyBookListFragment();
            }
        });

        tabItems.add(new MainAdapter.TabItem() {
            @Override
            public String getTabName() {
                return getString(R.string.tab_users);
            }

            @Override
            public int getIconDrawable() {
                return R.drawable.tab_users;
            }

            @Override
            public Fragment getFragment() {
//                return new UserListFragment();
                return SimpleTextFragment.newInstance("User List");
            }
        });

        tabItems.add(new MainAdapter.TabItem() {
            @Override
            public String getTabName() {
                return getString(R.string.test);
            }

            @Override
            public int getIconDrawable() {
                return R.drawable.tab_test;
            }

            @Override
            public Fragment getFragment() {
                return new TestFragment();
            }
        });

        return tabItems;
    }


    private static class MainAdapter extends FragmentPagerAdapter {

        private final List<TabItem> mTabItems;

        public MainAdapter(FragmentManager fm, List<TabItem> tabItems) {
            super(fm);
            mTabItems = tabItems;
        }

        @Override
        public Fragment getItem(int position) {
            return mTabItems.get(position).getFragment();
        }

        @Override
        public int getCount() {
            return mTabItems.size();
        }

        public TabItem getTabItem(int position) {
            return mTabItems.get(position);
        }

        public static interface TabItem {
            public String getTabName();
            public int getIconDrawable();
            public Fragment getFragment();
        }

    }

    @Override
    protected boolean isShowHomeAsUp() {
        return false;
    }
}
