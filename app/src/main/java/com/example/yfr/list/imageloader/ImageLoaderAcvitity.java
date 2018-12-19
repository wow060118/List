package com.example.yfr.list.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.yfr.list.R;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * @Author: fengrui.yang
 * @Description:
 * @Date: created in 上午10:26 2018/12/19
 * @Modified_By:
 */
public class ImageLoaderAcvitity extends AppCompatActivity {

    DisplayImageOptions options;
    ImageLoaderConfiguration config;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle starInstanceState) {
        super.onCreate(starInstanceState);
        setContentView(R.layout.imageloader);
        init(getApplicationContext());
        initOptions();
        //这句话用写在application里面 然后在不同的地方调用getinstance就可以用了 斜眼笑
        ImageLoader.getInstance().init(config);
        imageView=findViewById(R.id.image_loader);
        // imageUrl代表图片的URL地址，imageView代表承载图片的IMAGEVIEW控件 ， options   代表DisplayImageOptions配置文件
        // ImageLoadingListener 监听图片加载的进度， ImageLoadingProgressListener 检查加载进度的
        ImageLoader.getInstance().displayImage("http://img5.imgtn.bdimg.com/it/u=3218480149,1328367548&fm=21&gp=0.jpg", imageView, options, new ImageLoadingListener() {

            @Override
            public void onLoadingStarted(String imageUri, View view) {
                //开始
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                //加载失败
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                //加载完成
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                //取消加载
            }
        }, new ImageLoadingProgressListener() {
            @Override
            public void onProgressUpdate(String imageUri, View view, int current, int total) {

            }
        });

    }

    public void initOptions() {
        options = new DisplayImageOptions.Builder()
                // 设置图片在下载期间显示的图片
                .showImageOnLoading(R.mipmap.img)
                // 设置图片Uri为空或是错误的时候显示的图片
                .showImageForEmptyUri(R.mipmap.img)
                // 设置图片加载/解码过程中错误时候显示的图片
                .showImageOnFail(R.mipmap.img)
                // 设置下载的图片是否缓存在内存中
                .cacheInMemory(true)
                // 设置下载的图片是否缓存在SD卡中
                .cacheOnDisc(true)
                // 是否考虑JPEG图像EXIF参数（旋转，翻转）
                .considerExifParams(true)
                // 设置图片以如何的编码方式显示
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                // 设置图片的解码类型//
                .bitmapConfig(Bitmap.Config.RGB_565)
                // 设置图片的解码配置
                // .decodingOptions(options)
                // .delayBeforeLoading(int delayInMillis)//int
                // delayInMillis为你设置的下载前的延迟时间
                // 设置图片加入缓存前，对bitmap进行设置
                // .preProcessor(BitmapProcessor preProcessor)
                // 设置图片在下载前是否重置，复位
                .resetViewBeforeLoading(true)
                // 是否设置为圆角，弧度为多少
                .displayer(new RoundedBitmapDisplayer(20))
                // 是否图片加载好后渐入的动画时间
                .displayer(new FadeInBitmapDisplayer(100))
                // 构建完成
                .build();
    }

    public void init(Context context) {
        File cacheDir = StorageUtils.getCacheDirectory(context);
        Log.i("cacheDir",cacheDir.getPath());
        config = new ImageLoaderConfiguration.Builder(
                context)
                // 设置内存图片的宽高
                .memoryCacheExtraOptions(480, 800)
                // default = device screen dimensions
                // 缓存到磁盘中的图片宽高
                .diskCacheExtraOptions(480, 800, null)
                // .taskExecutor(null)
                // .taskExecutorForCachedImages()
                .threadPoolSize(3)
                // default 线程优先级
                .threadPriority(Thread.NORM_PRIORITY - 2)
                // default
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                // // default设置在内存中缓存图像的多种尺寸
                //加载同一URL图片时,imageView从小变大时,从内存缓存中加载
                .denyCacheImageMultipleSizesInMemory()
                // 超过设定的缓存大小时,内存缓存的清除机制
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                // 内存的一个大小
                .memoryCacheSize(2 * 1024 * 1024)
                .memoryCacheSizePercentage(13)
                // default 将图片信息缓存到该路径下
                .diskCache(new UnlimitedDiskCache(cacheDir))
                // default 磁盘缓存的大小
                .diskCacheSize(50 * 1024 * 1024)
                // 磁盘缓存文件的个数
                .diskCacheFileCount(100)
                //磁盘缓存的文件名的命名方式//一般使用默认值 (获取文件名称的hashcode然后转换成字符串)或MD5 new Md5FileNameGenerator()源文件的名称同过md5加密后保存
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
                // 设置默认的图片加载
                .imageDownloader(
                        new BaseImageDownloader(context)) // default
                // 使用默认的图片解析器
                .imageDecoder(new BaseImageDecoder(true)) // default
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                .writeDebugLogs().build();
    }
}
