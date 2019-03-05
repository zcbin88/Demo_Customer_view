package com.bingo.customer.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bingo.customer.R;
import com.bingo.customer.view.GlideCircleTransform;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;

/**
 * <pre>
 *     @author : Jimmy.tsang
 *     e-mail : jimmytsangfly@gmail.com
 *     time   : 2017/03/30
 *     desc   : 图片加载工具类
 *     version: 1.0
 * </pre>
 * <p>
 * Glide特点
 * 1.使用简单
 * 2.可配置度高，自适应程度高
 * 3.支持常见图片格式 Jpg png gif webp
 * 4.支持多种数据源  网络、本地、资源、Assets 等
 * 5.高效缓存策略    支持Memory和Disk图片缓存 默认Bitmap格式采用RGB_565内存使用至少减少一半
 * 6.生命周期集成   根据Activity/Fragment生命周期自动管理请求
 * 7.高效处理Bitmap  使用Bitmap Pool使Bitmap复用，主动调用recycle回收需要回收的Bitmap，减小系统回收压力
 * 8.这里默认支持Context，Glide支持Context,Activity,Fragment，FragmentActivity</p>
 */
public class GlideUtils {
    // 參考文章链接 {@link http://blog.csdn.net/shangmingchao/article/details/51125554/}
    // Glide之GlideModule {@line http://blog.csdn.net/shangmingchao/article/details/51026742}

    public static final String FILE_URL_PREFIX = "file://";

    public static RequestOptions options = new RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.ic_default)
            .error(R.drawable.ic_load_failure)
            .priority(Priority.HIGH);

    private static RequestOptions circleOptions = new RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.ic_default)
            .error(R.drawable.ic_load_failure)
            .priority(Priority.HIGH)
            .transform(new GlideCircleTransform());

    /**
     * 默认加载图片
     *
     * @param context   context
     * @param path      图片地址
     * @param imageView 需要显示的ImageView
     */
    public static void loadImageView(Context context, String path, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .apply(options)
                .into(imageView);
    }

    /**
     * 默认加载图片 页面销毁则不加载图片
     *
     * @param context   context
     * @param path      图片地址
     * @param imageView 需要显示的ImageView
     */
    public static void loadImageView(Activity context, String path, ImageView imageView) {
        if (!context.isFinishing()) {
            Glide.with(context).load(path).apply(options).into(imageView);
        }
    }

    /**
     * 默认加载图片
     *
     * @param context   context
     * @param path      图片地址
     * @param imageView 需要显示的ImageView
     */
    public static void loadImageView(Context context, Uri path, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .apply(options)
                .into(imageView);
    }

    /**
     * 设置头像加载中以及加载失败图片
     *
     * @param context   context
     * @param path      图片地址
     * @param imageView 需要显示的ImageView
     */
    public static void loadAvatarLoading(Context context, String path, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .apply(circleOptions)
                .into(imageView);
    }

    /**
     * 设置头像加载中以及加载失败图片
     *
     * @param context   context
     * @param path      图片地址
     * @param imageView 需要显示的ImageView
     */
    public static void loadAvatarLoading(Context context, int path, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .apply(circleOptions)
                .into(imageView);
    }

    public static void loadImageViewLoding(Context context, String path, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .thumbnail(0.2f) //缩略图为原来的十分之二，点开图片后会加载高清原图
                .apply(options)
                .into(imageView);
    }

    /**
     * 加载本地文件
     */
    public static void loadImageViewForFile(Context context, File file, ImageView imageView) {
        Glide.with(context)
                .load(FILE_URL_PREFIX + file.getAbsolutePath())
                .apply(options)
                .into(imageView);
    }




    /**
     * 设置加载中以及加载失败图片 ： 鉴美大师
     *
     * @param context   context
     * @param path      图片地址
     * @param imageView 需要显示的ImageView
     */
    public static void loadImageViewForIdentifyBeauty(Context context, String path, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .apply(new RequestOptions().centerCrop().placeholder(R.drawable.ic_default).error(R.drawable.ic_default))
                .into(imageView);
    }








}
