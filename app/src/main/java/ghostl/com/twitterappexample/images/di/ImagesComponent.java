package ghostl.com.twitterappexample.images.di;

import javax.inject.Singleton;

import dagger.Component;
import ghostl.com.twitterappexample.images.ImagesPresenter;
import ghostl.com.twitterappexample.images.ui.ImagesFragment;
import ghostl.com.twitterappexample.lib.di.LibsModule;

@Singleton @Component(modules = {LibsModule.class, ImagesModule.class})
public interface ImagesComponent {

    void inject(ImagesFragment imagesFragment);
    ImagesPresenter getPresenter();

}
