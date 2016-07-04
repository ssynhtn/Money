package com.ssynhtn.money.ui;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ssynhtn.money.R;
import com.ssynhtn.money.custom.OnMultipleClickListener;
import com.ssynhtn.money.ui.base.BaseActivity;
import com.ssynhtn.money.ui.test.TestFragment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int NUMBER_OF_CLICKS_TO_TRIGGER_POPUP = 10;

    private ViewPager mViewPager;
    private MainAdapter mPagerAdapter;
    private LinearLayout mTabsLinearLayout;
    private List<View> mTabs;

    private View mPopupView;
    private View mPopupBackgroundView;
    private View mPopupContainer;

    private Set<Animator> mAnimators = new HashSet<>();

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

        mPopupView = findViewById(R.id.frame_layout_popup);
        mPopupBackgroundView = findViewById(R.id.popup_background);
        mPopupContainer = findViewById(R.id.popup_container);
        mPopupContainer.setVisibility(View.GONE);
        mPopupBackgroundView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hidePopupView();
            }
        });

        LinearLayout imageContainer = (LinearLayout) mPopupView.findViewById(R.id.image_container);
        for (int i = 0; i < imageContainer.getChildCount(); i++) {
            View child = imageContainer.getChildAt(i);
            final int finalI = i;
            child.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    makeToast("click on view position; " + finalI);
                }
            });
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (isAnimatingAndShouldBlockTouch()) {
            return true;
        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onBackPressed() {
        if (isAnimatingAndShouldBlockTouch()) {
            return;
        }

        if (mPopupContainer.getVisibility() != View.GONE) {
            hidePopupView();
            return;
        }

        super.onBackPressed();
    }

    private boolean isAnimatingAndShouldBlockTouch() {
        return !mAnimators.isEmpty();
    }

    private void createTabs() {
        mTabs = new ArrayList<>();

        for (int i = 0; i < mPagerAdapter.getCount(); i++) {
            final int position = i;
            MainAdapter.TabItem tabItem = mPagerAdapter.getTabItem(i);

            View tab = getLayoutInflater().inflate(R.layout.tab_item_view, mTabsLinearLayout, false);
            mTabsLinearLayout.addView(tab);

            configureTab(tab, tabItem);
            if (position != mPagerAdapter.getCount() - 1) {
                tab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(position);
                    }
                });
            } else {
                tab.setOnClickListener(new OnMultipleClickListener(tab, NUMBER_OF_CLICKS_TO_TRIGGER_POPUP, new OnMultipleClickListener.OnConsecutiveClickCallback() {
                    @Override
                    public void onConsecutiveClick(View view, OnMultipleClickListener listener, int numberOfClicks, int numberOfClicksRequired) {
                        mViewPager.setCurrentItem(position);
                        if (numberOfClicks == numberOfClicksRequired) {
                            showPopupView();
                        }
                    }
                }));
            }

            mTabs.add(tab);


        }
    }

    private void showPopupView() {
        mPopupContainer.setVisibility(View.VISIBLE);

        Animator pushIn = AnimatorInflater.loadAnimator(this, R.animator.push_up_in);
        pushIn.setTarget(mPopupView);
        pushIn.setInterpolator(new OvershootInterpolator());

        Animator fadeIn = AnimatorInflater.loadAnimator(this, R.animator.fade_in);
        fadeIn.setTarget(mPopupBackgroundView);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(pushIn, fadeIn);

        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);

                mAnimators.add(animation);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                mAnimators.remove(animation);
            }
        });

        mAnimators.add(set);
        set.start();

    }

    private void hidePopupView() {
        Animator pushOut = AnimatorInflater.loadAnimator(this, R.animator.push_down_out);
        pushOut.setTarget(mPopupView);
        pushOut.setInterpolator(new AccelerateInterpolator());
        pushOut.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mPopupContainer.setVisibility(View.GONE);
            }
        });

        Animator fadeOut = AnimatorInflater.loadAnimator(this, R.animator.fade_out);
        fadeOut.setTarget(mPopupBackgroundView);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(pushOut, fadeOut);

        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);

                mAnimators.add(animation);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                mAnimators.remove(animation);
            }
        });

        mAnimators.add(set);
        set.start();
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
