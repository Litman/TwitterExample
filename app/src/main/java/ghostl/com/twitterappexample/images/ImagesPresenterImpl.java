package ghostl.com.twitterappexample.images;

import org.greenrobot.eventbus.Subscribe;

import ghostl.com.twitterappexample.images.event.ImagesEvent;
import ghostl.com.twitterappexample.images.ui.ImagesView;
import ghostl.com.twitterappexample.lib.base.EventBus;


public class ImagesPresenterImpl implements ImagesPresenter {

    private ImagesView imagesView;
    private EventBus eventBus;
    private ImagesInteractor imagesInteractor;

    public ImagesPresenterImpl(ImagesView imagesView, EventBus eventBus, ImagesInteractor imagesInteractor) {
        this.imagesView = imagesView;
        this.eventBus = eventBus;
        this.imagesInteractor = imagesInteractor;
    }

    @Override
    public void onResume() {
        eventBus.register(this);
    }

    @Override
    public void onPause() {
        eventBus.unregister(this);
    }

    @Override
    public void onDestroy() {
        imagesView = null;

    }

    @Override
    public void getImageTweets() {
        if(imagesView != null){
            imagesView.hideImages();
            imagesView.showProgress();
        }
        imagesInteractor.execute();
    }

    @Override
    @Subscribe
    public void onEventMainThread(ImagesEvent event) {
        String errorMsg = event.getError();
        if(imagesView != null){
            imagesView.showImages();
            imagesView.hideProgress();

            if(errorMsg != null){
                imagesView.onError(errorMsg);
            }else{
                imagesView.setContent(event.getImages());
            }
        }


    }
}
