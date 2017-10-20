package com.giroux.kevin.dofustuff.network;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.widget.ImageView;

import com.giroux.kevin.androidhttprequestlibrairy.AndroidHttpRequest;

import java.util.Map;

import pl.droidsonroids.gif.GifImageView;

/**
 * Created by kevin on 26/11/2016.
 */

public class    ImageLoaderTask extends AndroidHttpRequest {

    public ImageLoaderTask(String url, String method, Map<String, String> paramStr) {
        super(url, method, paramStr);
    }
    private Activity activity;
    public Context getContext() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPostExecute(Object o) {

        if(getListObject().get("gifVImageView") instanceof ImageView){
            ImageView vh =  ((ImageView) getListObject().get("gifVImageView"));
            if(o instanceof byte[]){
                byte []bytes = (byte[])o;
                BitmapDrawable bitmapDrawable = new BitmapDrawable(activity.getApplicationContext().getResources(), BitmapFactory.decodeByteArray(bytes,0,bytes.length));
                Bitmap image = bitmapDrawable.getBitmap();
                if(image != null){
                    Log.i("ImgTask","Image found and download");
                    vh.setImageBitmap(image);
                    vh.setScaleType(ImageView.ScaleType.FIT_XY);
                    vh.setAdjustViewBounds(true);
                }
            }
        }

    }
}
