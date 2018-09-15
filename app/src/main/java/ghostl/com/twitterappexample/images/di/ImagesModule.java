package ghostl.com.twitterappexample.images.di;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Session;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ghostl.com.twitterappexample.apiretrofit.CustomTwitterApiClient;
import ghostl.com.twitterappexample.entities.Image;
import ghostl.com.twitterappexample.images.ImagesInteractor;
import ghostl.com.twitterappexample.images.ImagesInteractorImpl;
import ghostl.com.twitterappexample.images.ImagesPresenter;
import ghostl.com.twitterappexample.images.ImagesPresenterImpl;
import ghostl.com.twitterappexample.images.ImagesRepository;
import ghostl.com.twitterappexample.images.ImagesRepositoryImpl;
import ghostl.com.twitterappexample.images.ui.ImagesView;
import ghostl.com.twitterappexample.images.ui.adapters.ImagesAdapter;
import ghostl.com.twitterappexample.images.ui.adapters.OnItemClickListener;
import ghostl.com.twitterappexample.lib.base.EventBus;
import ghostl.com.twitterappexample.lib.base.ImageLoader;

@Module
public class ImagesModule{
    private ImagesView view;
    private OnItemClickListener clickListener;

    public ImagesModule(ImagesView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }


    @Provides
    @Singleton
    ImagesAdapter provideAdapter(List<Image> dataset, ImageLoader imageLoader, OnItemClickListener clickListener){
        return new ImagesAdapter(dataset, imageLoader, clickListener);
    }

    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener(){
        return this.clickListener;
    }

    @Provides
    @Singleton
    List<Image> providesItemsList(){
        return new ArrayList<Image>();
    }

    @Provides
    @Singleton
    ImagesPresenter providesImagesPresenter(ImagesView imagesView, EventBus eventBus, ImagesInteractor imagesInteractor){

        return new ImagesPresenterImpl(view, eventBus, imagesInteractor);
    }

    @Provides
    @Singleton
    ImagesView provideImagesView(){
        return this.view;
    }

    @Provides
    @Singleton
    ImagesInteractor provideImagesInteractor(ImagesRepository repository){
        return new ImagesInteractorImpl(repository);
    }

    @Provides
    @Singleton
    ImagesRepository provideImagesRepository(EventBus eventBus, CustomTwitterApiClient client){
        return new ImagesRepositoryImpl(eventBus, client);
    }

    @Provides
    @Singleton
    CustomTwitterApiClient providesCustomTwittesApiClient(Session session){
        return new CustomTwitterApiClient(session);
    }

    @Provides
    @Singleton
    Session provideTwitter(){
        return Twitter.getSessionManager().getActiveSession();
    }
}
