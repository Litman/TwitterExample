package ghostl.com.twitterappexample.hashtags;

import ghostl.com.twitterappexample.hashtags.events.HashtagsEvent;

public interface HashtagsPresenter {
    void onResume();
    void onPause();
    void onDestroy();
    void getHashtagsTweets();
    void onEventMainThread(HashtagsEvent event);
}
