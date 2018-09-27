package ghostl.com.twitterappexample.twitter;

import android.app.Application;
import android.support.v4.app.Fragment;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;


import ghostl.com.twitterappexample.BuildConfig;


import ghostl.com.twitterappexample.hashtags.di.HashtagsComponent;
import ghostl.com.twitterappexample.hashtags.ui.HashtagsView;
import ghostl.com.twitterappexample.images.di.DaggerImagesComponent;
import ghostl.com.twitterappexample.images.di.ImagesComponent;
import ghostl.com.twitterappexample.images.di.ImagesModule;
import ghostl.com.twitterappexample.images.ui.ImagesView;
import ghostl.com.twitterappexample.images.ui.adapters.OnItemClickListener;
import ghostl.com.twitterappexample.lib.di.LibsModule;
import io.fabric.sdk.android.Fabric;

public class TwitterClientApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        initFabric();
    }

    private void initFabric() {
        TwitterAuthConfig authConfig = new TwitterAuthConfig(BuildConfig.TWITTER_KEY, BuildConfig.TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
    }

    public ImagesComponent getImagesComponent(Fragment fragment, ImagesView view, OnItemClickListener clickListener){
        return DaggerImagesComponent
                .builder()
                .libsModule(new LibsModule(fragment))
                .imagesModule(new ImagesModule(view, clickListener ))
                .build();

    }

//    public HashtagsComponent getHashtagsComponent(HashtagsView view, ghostl.com.twitterappexample.hashtags.ui.adapters.OnItemClickListener clickListener){
//        return DaggerHashtagsComponent
//                .builder()
//                .lib
//    }


}
