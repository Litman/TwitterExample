package ghostl.com.twitterappexample.images.ui;

import java.util.List;

import ghostl.com.twitterappexample.entities.Image;

public interface ImagesView {
    void showImages();
    void hideImages();
    void showProgress();
    void hideProgress();

    void onError(String error);
    void setContent(List<Image> items);
}
