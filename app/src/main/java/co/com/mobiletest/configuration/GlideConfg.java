package co.com.mobiletest.configuration;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.GlideModule;

/**
 * @author Jaime Gamboa
 * @see com.bumptech.glide.module.GlideModule
 */
public class GlideConfg implements GlideModule {
    /**
     * Lazily apply options to a {@link GlideBuilder} immediately before the Glide singleton is
     * created.
     * <p>
     * <p>
     * This method will be called once and only once per implementation.
     * </p>
     *
     * @param context An Application {@link Context}.
     * @param builder The {@link GlideBuilder} that will be used to create Glide.
     */
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDecodeFormat(DecodeFormat.DEFAULT);
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, 250 * 1024 * 1024));
        builder.setMemoryCache(new LruResourceCache(250 * 1024 * 1024));
    }

    /**
     * Lazily register components immediately after the Glide singleton is created but before any requests can be
     * started.
     * <p>
     * <p>
     * This method will be called once and only once per implementation.
     * </p>
     *
     * @param context An Application {@link Context}.
     * @param glide   The newly created Glide singleton.
     */
    @Override
    public void registerComponents(Context context, Glide glide) {
    }

}
