package ghostl.com.twitterappexample.lib.di;





import android.support.v4.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ghostl.com.twitterappexample.lib.GlideImageLoader;
import ghostl.com.twitterappexample.lib.GreenRobotEventBus;
import ghostl.com.twitterappexample.lib.base.EventBus;
import ghostl.com.twitterappexample.lib.base.ImageLoader;


@Module
public class LibsModule {
    private Fragment fragment;

    public LibsModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @Singleton
    ImageLoader providesImageLoader(RequestManager requestManager){
        return new GlideImageLoader(requestManager);
    }

    @Provides
    @Singleton
    RequestManager providesRequestManager(Fragment fragment){
        return Glide.with(fragment);
    }

    @Provides
    @Singleton
    Fragment providesFragment(){
        return this.fragment;
    }

    @Provides
    @Singleton
    EventBus provideEventBus(org.greenrobot.eventbus.EventBus eventBus){
        return new GreenRobotEventBus(eventBus);
    }

    @Provides
    @Singleton
    org.greenrobot.eventbus.EventBus provideLibraryEventBus(){
        return org.greenrobot.eventbus.EventBus.getDefault();
    }

}
