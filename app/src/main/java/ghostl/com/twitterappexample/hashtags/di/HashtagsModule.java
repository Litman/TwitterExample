package ghostl.com.twitterappexample.hashtags.di;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Provides;
import ghostl.com.twitterappexample.entities.Hashtag;
import ghostl.com.twitterappexample.hashtags.HashtagsPresenter;
import ghostl.com.twitterappexample.hashtags.HashtagsPresenterImpl;
import ghostl.com.twitterappexample.hashtags.ui.HashtagsView;
import ghostl.com.twitterappexample.hashtags.ui.adapters.HashtagsAdapter;
import ghostl.com.twitterappexample.hashtags.ui.adapters.OnItemClickListener;


public class HashtagsModule {
    private HashtagsView view;
    private OnItemClickListener clickListener;

    public HashtagsModule(HashtagsView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    @Singleton
    @Provides
    HashtagsAdapter providerAdapter(List<Hashtag> dataset,OnItemClickListener clickListener){
        return new HashtagsAdapter(dataset, clickListener);
    }


    @Singleton
    @Provides
    OnItemClickListener providesOnItemClicklistener(){
        return this.clickListener;
    }

    @Singleton
    @Provides
    List<Hashtag> providesItemsList(){
        return new ArrayList<Hashtag>();
    }

    @Singleton
    @Provides
    HashtagsPresenter providesHashtagsPresenter(){
        return new HashtagsPresenterImpl();
    }


}
