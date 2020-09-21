package com.example.bigimage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class ImagePreviewActivity extends AppCompatActivity {

    private ViewPager vp;
    private List<String> list;
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_preview);
        initView();
    }

    private void initView() {
        vp = findViewById(R.id.image_vp);
        if (getIntent() != null) {
            //获取图片的路径集合
            list = getIntent().getStringArrayListExtra("imgs");
            //当前显示的图片位置
            pos = getIntent().getIntExtra("pos", 0);
        } else {
            list = new ArrayList<>();
        }

        //创建适配器
        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        //绑定适配器
        vp.setAdapter(myViewPagerAdapter);
        if(pos > 0) vp.setCurrentItem(pos);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


    class MyViewPagerAdapter extends FragmentPagerAdapter {

        public MyViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            // return ImageFragment.newInstance(list.get(position));
            return ImageFragment.newInstance(list.get(position));
        }
    }

}