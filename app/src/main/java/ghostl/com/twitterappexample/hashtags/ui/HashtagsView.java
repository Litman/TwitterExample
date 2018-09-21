package ghostl.com.twitterappexample.hashtags.ui;

import java.util.List;

import ghostl.com.twitterappexample.entities.Hashtag;

public interface HashtagsView {

    void showImages();
    void hideImages();
    void showProgress();
    void hideProgress();

    void onError(String error);
    void setContent(List<Hashtag> items);
}
