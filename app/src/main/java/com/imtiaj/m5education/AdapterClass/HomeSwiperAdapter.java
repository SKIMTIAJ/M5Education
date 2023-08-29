package com.imtiaj.m5education.AdapterClass;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.imtiaj.m5education.R;
import com.imtiaj.m5education.modelClass.uploadSchool_pojo;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

public class HomeSwiperAdapter extends PagerAdapter {

    private ArrayList<uploadSchool_pojo> Img;
    Context ctx;

    private LayoutInflater layoutInflater;
    private int CustomPosition = 0;

    public HomeSwiperAdapter(ArrayList<uploadSchool_pojo> img, Context ctx) {
        Img = img;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (LinearLayout)object);
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        if(CustomPosition>Img.size()-1)
            CustomPosition = 0;

        layoutInflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.swipe_layout,container,false);
        final ImageView imageView = (ImageView)item_view.findViewById(R.id.SwipeImage);
        Glide.with(ctx)
                .load(Img.get(CustomPosition).getUploadImage())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
        CustomPosition++;
        container.addView(item_view);
        return item_view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
