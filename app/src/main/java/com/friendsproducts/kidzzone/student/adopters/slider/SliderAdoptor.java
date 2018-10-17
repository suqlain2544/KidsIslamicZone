package com.friendsproducts.kidzzone.student.adopters.slider;

/**
 * Created by Ali Hassan on 5/8/2018.
 */

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.friendsproducts.kidzzone.R;
import com.friendsproducts.kidzzone.student.activities.gkactivities.SliderActivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import uk.co.senab.photoview.PhotoViewAttacher;

//import com.bogdwellers.pinchtozoom.ImageMatrixTouchHandler;

public class SliderAdoptor extends PagerAdapter {

    private ArrayList<String> images;
    private LayoutInflater inflater;
    private Context context;
    PhotoViewAttacher photoViewAttacher ;

    public SliderAdoptor(Context context, ArrayList<String> images) {
        this.context = context;
        this.images = images;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View myImageLayout = inflater.inflate(R.layout.image_slide, view, false);
        ImageView myImage = (ImageView) myImageLayout
                .findViewById(R.id.image);
       // myImage.setImageResource();
        Bitmap bitmap = getBitmapFromAsset(images.get(position));
        myImage.setImageBitmap(bitmap);
        photoViewAttacher = new PhotoViewAttacher(myImage);

        photoViewAttacher.update();
       // myImage.setOnTouchListener(new ImageMatrixTouchHandler(view.getContext()));
        view.addView(myImageLayout, 0);
        return myImageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    private Bitmap getBitmapFromAsset(String id) {
        AssetManager assetManager = context.getAssets();
        InputStream stream = null;
        try {
            stream = assetManager.open(id + ".png");
            return BitmapFactory.decodeStream(stream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}