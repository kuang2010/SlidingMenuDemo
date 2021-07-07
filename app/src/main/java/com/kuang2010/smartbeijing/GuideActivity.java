package com.kuang2010.smartbeijing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.kuang2010.smartbeijing.utils.DensityUtil;
import com.kuang2010.smartbeijing.utils.MyConstaints;
import com.kuang2010.smartbeijing.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {

    private ViewPager mVp_guide;

    private List<ImageView> mDatas = new ArrayList<>();
    private int[] imags = new int[]{R.mipmap.guide_1,R.mipmap.guide_2,R.mipmap.guide_3};
    private LinearLayout mLl_points_contiant;
    private View mView_red_point;
    private Button mBtn_go_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        initData();
        initevent();
    }

    private void initevent() {
        mVp_guide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // 页面滑动的调用
                //position 页面的当前位置
                //positionOffset 滑动距离占整个页面宽度的比例值
                //positionOffsetPixels 偏移的像素

                //红点跟着移动
                View point1 = mLl_points_contiant.getChildAt(0);
                View point2 = mLl_points_contiant.getChildAt(1);
                int dx = point2.getLeft() - point1.getLeft();
                Log.d("tagtag","dx>>>>>>>>>>>>>"+dx);
                if (dx>0){
                    int marginLeft = Math.round(dx*(positionOffset+position));
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mView_red_point.getLayoutParams();
                    layoutParams.leftMargin = marginLeft;
                    mView_red_point.setLayoutParams(layoutParams);
                }
            }

            @Override
            public void onPageSelected(int position) {
                if (position==mDatas.size()-1){
                    mBtn_go_home.setVisibility(View.VISIBLE);
                }else {
                    mBtn_go_home.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mBtn_go_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuideActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                SPUtils.putBoolean(GuideActivity.this, MyConstaints.GUIDE_FINISH,true);
            }
        });
    }

    private void initData() {
        for (int i=0;i<imags.length;i++){
            ImageView imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(imags[i]);
            mDatas.add(imageView);

            //动态添加点 到容器
            View view = new View(this);
            view.setBackgroundResource(R.drawable.shape_gray_point);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtil.dip2px(this,10),DensityUtil.dip2px(this,10));
            if (i!=0){
                params.leftMargin = DensityUtil.dip2px(this,20);
            }
            view.setLayoutParams(params);
            mLl_points_contiant.addView(view);

        }
        GuidePageAdapter pageAdapter = new GuidePageAdapter();
        mVp_guide.setAdapter(pageAdapter);
    }

    private void initView() {

        mVp_guide = findViewById(R.id.vp_guide);
        mLl_points_contiant = findViewById(R.id.ll_points_contiant);
        mView_red_point = findViewById(R.id.view_red_point);
        mBtn_go_home = findViewById(R.id.btn_go_home);

    }

    class GuidePageAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView imageView = mDatas.get(position);
            container.addView(imageView);

            return imageView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }
}
