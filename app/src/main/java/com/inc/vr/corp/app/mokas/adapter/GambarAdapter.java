package com.inc.vr.corp.app.mokas.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.inc.vr.corp.app.mokas.R;
import com.rd.PageIndicatorView;

import java.util.ArrayList;

public class GambarAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<String> gambarList= new ArrayList<>();
    public GambarAdapter(Context context, ArrayList<String> gambarList) {
        this.context = context;
        this.gambarList=gambarList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return gambarList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout, container,false);
        ImageView imageView = view.findViewById(R.id.imageView);
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.mokaspng)
                .error(R.drawable.mokaspng);
        String gambar="http://apps.mokascirebon.com/ajax/image"+gambarList.get(position);
        Log.d("DEBIT FINAL", "onBindViewHolder: "+gambar);
        Glide.with(context).load(gambar).apply(options).into(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }
}
