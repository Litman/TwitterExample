package ghostl.com.twitterappexample.lib;


import android.widget.ImageView;

import com.bumptech.glide.RequestManager;

import ghostl.com.twitterappexample.lib.base.ImageLoader;

public class GlideImageLoader implements ImageLoader{

    private RequestManager glideRequestManager;

    public GlideImageLoader(RequestManager glideRequestManager) {
        this.glideRequestManager = glideRequestManager;
    }

    //    public GlideImageLoader(Fragment fragment) {
//        this.glideRequestManager = Glide.with(fragment);
//    }

    @Override
    public void load(ImageView imageView, String URL) {
        glideRequestManager.load(URL)
                //.diskCacheStrategy(DiskCacheStrategy.ALL)
               // .centerCrop()
                //.override(600,400)
                .into(imageView);



    }
}
