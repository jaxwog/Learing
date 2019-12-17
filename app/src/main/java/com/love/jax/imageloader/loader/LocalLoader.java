package com.love.jax.imageloader.loader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;


import com.love.jax.imageloader.request.BitmapRequest;
import com.love.jax.imageloader.utils.BitmapDecoder;
import com.love.jax.imageloader.utils.ImageViewHelper;

import java.io.File;

/**
 * 本地图片加载器
 */
public class LocalLoader extends AbsLoader {

    @Override
    protected Bitmap onLoad(BitmapRequest request) {
        //得到本地图片的路径
        final String path = Uri.parse(request.getImageUri()).getPath();
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }
        BitmapDecoder decoder = new BitmapDecoder() {
            @Override
            public Bitmap decodeBitmapWithOption(Options options) {
                return BitmapFactory.decodeFile(path, options);
            }
        };

        return decoder.decodeBitmap(ImageViewHelper.getImageViewWidth(request.getImageView()), ImageViewHelper.getImageViewHeight(request.getImageView()));
    }

}
