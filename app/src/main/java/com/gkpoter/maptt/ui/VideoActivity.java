package com.gkpoter.maptt.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.VideoView;

import com.gkpoter.maptt.R;
import com.gkpoter.maptt.ui.fragment_video.OtherFragment;
import com.gkpoter.maptt.ui.fragment_video.PAQFragment;
import com.gkpoter.maptt.ui.fragment_video.PPTFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class VideoActivity extends AppCompatActivity{

    private int[] res={R.id.floating_a,R.id.floating_b,R.id.floating_c};
    private List<FloatingActionButton> floatingActionButtonList=new ArrayList<>();

    private boolean animFlag=true;
    private boolean animOk=true;

    @BindView(R.id.videoActivity_video)
    VideoView videoView;
    @BindView(R.id.video_tab)
    TabLayout tab;
    @BindView(R.id.video_viewPager)
    ViewPager viewPager;

    private Fragment[] mFragments;
    private FragmentPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        ButterKnife.bind(this);
        initView();

        showPagerView();

    }

    private void initView() {
        for (int i = 0; i < res.length; i++) {
            FloatingActionButton button= (FloatingActionButton) findViewById(res[i]);
            floatingActionButtonList.add(button);
        }
        mFragments = new Fragment[3];
        mFragments[0]=new OtherFragment();
        mFragments[1]=new PAQFragment();
        mFragments[2]=new PPTFragment();
    }

    /**
     * 绑定viewpager与fragment
     */
    private void showPagerView() {
        adapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mFragments[i];
            }

            @Override
            public int getCount() {
                return mFragments.length;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                //super.destroyItem(container, position, object);
            }
        };
        viewPager.setAdapter(adapter);
        tab.setupWithViewPager(viewPager);
        tab.getTabAt(0).setText("介绍");
        tab.getTabAt(1).setText("评论&问答");
        tab.getTabAt(2).setText("PPT");
    }

    /**
     * ui事件监听
     * @param view
     */
    @OnClick({R.id.floating_a,R.id.floating_b,R.id.floating_c})
    public void floatingButtonClick(View view) {
        switch (view.getId()){
            case R.id.floating_a:
                if(animOk) {
                    if (animFlag) {
                        startAnim();
                        animOk=false;
                        animFlag = false;
                    } else {
                        stopAnim();
                        animOk=false;
                        animFlag = true;
                    }
                }
                break;
            case R.id.floating_b:
                videoView.setVideoPath("http://flashmedia.eastday.com/newdate/news/2016-11/shznews1125-19.mp4");
                videoView.start();
//                videoView.setMediaController(new MediaController(this));
                break;
            case R.id.floating_c:
                videoView.pause();
                break;
            default: break;
        }
    }

    /**
     * 关闭菜单动画
     */
    private void stopAnim() {

        for (int i = 1; i < res.length; i++) {
            ObjectAnimator animator=ObjectAnimator.ofFloat(floatingActionButtonList.get(i),"translationY", -(i*250F), 0F);
            animator.setDuration(i*500);
            animator.setInterpolator(new BounceInterpolator());
            animator.setStartDelay(i*100);
            animator.start();
            final int finalI = i;
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    if(finalI ==res.length-1){
                        animOk=true;
                    }
                }
            });
        }

    }

    /**
     * 开启菜单动画
     */
    private void startAnim() {

        for (int i = 1; i < res.length; i++) {
            AnimatorSet animatorSet = new AnimatorSet();//组合动画
            ObjectAnimator animator=ObjectAnimator.ofFloat(floatingActionButtonList.get(i),"translationY", 0, -(i*250F));
            ObjectAnimator scaleX = ObjectAnimator.ofFloat(floatingActionButtonList.get(i), "scaleX", 0f, 1f);
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(floatingActionButtonList.get(i), "scaleY", 0f, 1f);
            ObjectAnimator alpha = ObjectAnimator.ofFloat(floatingActionButtonList.get(i), "alpha", 0f, 1f);
            animatorSet.playTogether(animator,scaleX,scaleY,alpha);
            animatorSet.setDuration(i*300);
            animatorSet.setInterpolator(new BounceInterpolator());
            animatorSet.setStartDelay(i*100);
            animatorSet.start();
            final int finalI = i;
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    if(finalI ==res.length-1){
                        animOk=true;
                    }
                }
            });
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
