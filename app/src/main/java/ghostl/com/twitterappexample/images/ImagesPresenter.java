package ghostl.com.twitterappexample.images;

import ghostl.com.twitterappexample.images.event.ImagesEvent;

public interface ImagesPresenter {

    void onResume();
    void onPause();
    void onDestroy();
    void getImageTweets();
    void onEventMainThread(ImagesEvent event);

}
