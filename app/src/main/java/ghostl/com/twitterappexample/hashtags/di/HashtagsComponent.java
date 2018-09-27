package ghostl.com.twitterappexample.hashtags.di;

import javax.inject.Singleton;

import dagger.Component;
import ghostl.com.twitterappexample.entities.Hashtag;
import ghostl.com.twitterappexample.hashtags.HashtagsPresenter;
import ghostl.com.twitterappexample.hashtags.ui.HashtagsFragment;
import ghostl.com.twitterappexample.lib.di.LibsModule;

@Singleton @Component(modules = {LibsModule.class, HashtagsModule.class})
public interface HashtagsComponent {

    void inject(HashtagsFragment hashtagsFragment);
    HashtagsPresenter getPresenter();


}
