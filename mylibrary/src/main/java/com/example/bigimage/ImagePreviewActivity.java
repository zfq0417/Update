package com.example.bigimage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
    private Button imgBtn;
    public static final int CODE_RESULT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_preview);
        initView();
    }

    private void initView() {
        vp = findViewById(R.id.image_vp);
        imgBtn = findViewById(R.id.img_btn);


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
        if (pos > 0) vp.setCurrentItem(pos);


        //返回点击监听
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                intent.putExtra("path", "zzz");
                setResult(CODE_RESULT,intent);
                finish();
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