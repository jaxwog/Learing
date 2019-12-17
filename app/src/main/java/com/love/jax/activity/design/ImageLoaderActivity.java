package com.love.jax.activity.design;



import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.love.jax.R;
import com.love.jax.activity.BaseActivity;
import com.love.jax.imageloader.cache.DoubleCache;
import com.love.jax.imageloader.config.ImageLoaderConfig;
import com.love.jax.imageloader.core.SimpleImageLoader;
import com.love.jax.imageloader.policy.ReversePolicy;

/**
 * 需求：
 * 根据用户需求可以灵活配置（建造者模式）
 * 支持高并发，图片加载的优先级
 * 支持可以选择不同的加载策略，对加载策略进行扩展
 * 二级缓存  加载图片时内存中已经加载了，则从内存中加载，不存在去外置卡中加载，外置还不存在则从网络下载
 * 并对缓存策略可以扩展
 * 支持从加载过程中显示默认加载图片
 * 支持加载失败时 显示默认错误图片
 * 图片显示自适应。从网络加载下来的图片经最佳比例压缩后显示
 * 不能失真变形
 * 支持请求转发，下载
 *
 * 用到的模式：
 * 生产者 消费者模式
 * 建造者模式
 * 单例模式
 * 模板方法模式
 * 策略模式
 *
 * 用到的知识点
 * 内存缓存 LruCache技术
 * 硬盘缓存技术DiskLruCache技术
 * 图片下载时请求转发
 *
 * ===================================
 * 图片加载框架主要三件事：
 * 1、配置：加载策略、缓存策略、线程数量
 * 2、下载：多线程、设置请求队列
 * 3、缓存，如果加载本地就用本地加载策略，加载网络就用网络加载策略
 *    多个缓存策略：内存缓存、disk缓存，双缓存等
 */
public class ImageLoaderActivity extends BaseActivity {

    SimpleImageLoader imageLoader;

    @Override
    protected void initJestListener() {

    }

    @Override
    protected void initView() {
        GridView listview =  findViewById(R.id.listview);
        listview.setAdapter(new MyAdapter(this));
        //配置
        ImageLoaderConfig.Builder build = new ImageLoaderConfig.Builder();
        build.setThreadCount(3) //线程数量
                .setLoadPolicy(new ReversePolicy()) //加载策略
                .setCachePolicy(new DoubleCache(this)) //缓存策略
                .setLoadingImage(R.mipmap.loading)
                .setFaildImage(R.mipmap.not_found);

        ImageLoaderConfig config = build.build();
        //初始化SimpleImageLoader，包含RequestQueue队列的初始化
        imageLoader = SimpleImageLoader.getInstance(config);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_image_loader;
    }


    class MyAdapter extends BaseAdapter {

        private LayoutInflater inflater;
        private Context mContext;

        public MyAdapter(Context context) {
            mContext = context;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return imageThumbUrls.length;
        }

        @Override
        public Object getItem(int position) {
            return imageThumbUrls[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
//            View item = inflater.inflate(R.layout.item_image_load, null);
//            ImageView imageView = (ImageView) item.findViewById(R.id.iv);
            //真正的请求图片，造成大量的请求
//            imageLoader.displayImage(imageView, imageThumbUrls[position]);
//            imageLoader.displayImage(imageView, imageThumbUrls[position], null, new SimpleImageLoader.ImageListener() {
//                @Override
//                public void onComplete(ImageView imageView, Bitmap bitmap, String uri) {
//                    //图片加载成功回调
//                }
//            });

                ViewHolder holder = null;
                if (convertView == null) {
                    convertView = inflater.inflate(R.layout.item_image_load,parent, false);
                    holder = new ViewHolder();
                    holder.imageView = (ImageView) convertView.findViewById(R.id.iv);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                ImageView imageView1 = holder.imageView;
                final String tag = (String)imageView1.getTag();
                final String uri = imageThumbUrls[position];
                if (!uri.equals(tag)) {
                    imageView1.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.not_found));
                }
            imageLoader.displayImage(imageView1, imageThumbUrls[position]);



                return convertView;
        }

    }

    public final static String[] imageThumbUrls =new String[] {
            "http://h.hiphotos.baidu.com/image/pic/item/3b87e950352ac65c0f1f6e9efff2b21192138ac0.jpg",
            "http://b.zol-img.com.cn/desk/bizhi/image/3/960x600/1375841395686.jpg",
            "http://cdn.duitang.com/uploads/item/201311/03/20131103171224_rr2aL.jpeg",
            "http://h.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=429e7b1b92ef76c6d087f32fa826d1cc/7acb0a46f21fbe09cc206a2e69600c338744ad8a.jpg",
            "http://pica.nipic.com/2007-12-21/2007122115114908_2.jpg",
            "http://cdn.duitang.com/uploads/item/201405/13/20140513212305_XcKLG.jpeg",
            "http://photo.loveyd.com/uploads/allimg/080618/1110324.jpg",
            "http://cdn.duitang.com/uploads/item/201204/21/20120421155228_i52eX.thumb.600_0.jpeg",
            "http://pic.dbw.cn/0/01/33/59/1335968_847719.jpg",
            "http://a.hiphotos.baidu.com/image/pic/item/a8773912b31bb051a862339c337adab44bede0c4.jpg",
            "http://h.hiphotos.baidu.com/image/pic/item/f11f3a292df5e0feeea8a30f5e6034a85edf720f.jpg",
            "http://img0.pconline.com.cn/pconline/bizi/desktop/1412/ER2.jpg",
            "http://img02.tooopen.com/images/20140320/sy_57121781945.jpg"


           , "http://h.hiphotos.baidu.com/image/pic/item/3b87e950352ac65c0f1f6e9efff2b21192138ac0.jpg",
            "http://b.zol-img.com.cn/desk/bizhi/image/3/960x600/1375841395686.jpg",
            "http://cdn.duitang.com/uploads/item/201311/03/20131103171224_rr2aL.jpeg",
            "http://h.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=429e7b1b92ef76c6d087f32fa826d1cc/7acb0a46f21fbe09cc206a2e69600c338744ad8a.jpg",
            "http://pica.nipic.com/2007-12-21/2007122115114908_2.jpg",
            "http://cdn.duitang.com/uploads/item/201405/13/20140513212305_XcKLG.jpeg",
            "http://photo.loveyd.com/uploads/allimg/080618/1110324.jpg",
            "http://cdn.duitang.com/uploads/item/201204/21/20120421155228_i52eX.thumb.600_0.jpeg",
            "http://pic.dbw.cn/0/01/33/59/1335968_847719.jpg",
            "http://a.hiphotos.baidu.com/image/pic/item/a8773912b31bb051a862339c337adab44bede0c4.jpg",
            "http://h.hiphotos.baidu.com/image/pic/item/f11f3a292df5e0feeea8a30f5e6034a85edf720f.jpg",
            "http://img0.pconline.com.cn/pconline/bizi/desktop/1412/ER2.jpg",
            "http://img02.tooopen.com/images/20140320/sy_57121781945.jpg"

            , "http://h.hiphotos.baidu.com/image/pic/item/3b87e950352ac65c0f1f6e9efff2b21192138ac0.jpg",
            "http://b.zol-img.com.cn/desk/bizhi/image/3/960x600/1375841395686.jpg",
            "http://cdn.duitang.com/uploads/item/201311/03/20131103171224_rr2aL.jpeg",
            "http://h.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=429e7b1b92ef76c6d087f32fa826d1cc/7acb0a46f21fbe09cc206a2e69600c338744ad8a.jpg",
            "http://pica.nipic.com/2007-12-21/2007122115114908_2.jpg",
            "http://cdn.duitang.com/uploads/item/201405/13/20140513212305_XcKLG.jpeg",
            "http://photo.loveyd.com/uploads/allimg/080618/1110324.jpg",
            "http://cdn.duitang.com/uploads/item/201204/21/20120421155228_i52eX.thumb.600_0.jpeg",
            "http://pic.dbw.cn/0/01/33/59/1335968_847719.jpg",
            "http://a.hiphotos.baidu.com/image/pic/item/a8773912b31bb051a862339c337adab44bede0c4.jpg",
            "http://h.hiphotos.baidu.com/image/pic/item/f11f3a292df5e0feeea8a30f5e6034a85edf720f.jpg",
            "http://img0.pconline.com.cn/pconline/bizi/desktop/1412/ER2.jpg",
            "http://img02.tooopen.com/images/20140320/sy_57121781945.jpg"
    };

    private static class ViewHolder {
        public ImageView imageView;
    }
}

