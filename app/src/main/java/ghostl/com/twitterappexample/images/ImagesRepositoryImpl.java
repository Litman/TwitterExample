package ghostl.com.twitterappexample.images;


import android.util.Log;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.MediaEntity;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ghostl.com.twitterappexample.apiretrofit.CustomTwitterApiClient;
import ghostl.com.twitterappexample.entities.Image;
import ghostl.com.twitterappexample.images.event.ImagesEvent;
import ghostl.com.twitterappexample.lib.base.EventBus;


public class ImagesRepositoryImpl implements  ImagesRepository{

    private EventBus eventBus;
    private CustomTwitterApiClient client;
    private final static int TWEET_COUNT = 100;


    public ImagesRepositoryImpl(EventBus eventBus, CustomTwitterApiClient client) {
        this.eventBus = eventBus;
        this.client = client;
    }



    @Override
    public void getImages() {

        Callback<List<Tweet>> callback = new Callback<List<Tweet>>() {

            @Override
            public void success(Result<List<Tweet>> result) {
                List<Image> items = new ArrayList<Image>();
                for (Tweet tweet: result.data){
                    if(containsImages(tweet)){
                        Image tweetModel = new Image();

                        tweetModel.setId(tweet.idStr);
                        tweetModel.setFavouriteCount(tweet.favoriteCount);

                        String tweetText = tweet.text;
                        int index =  tweetText.indexOf("http");
                        if(index > 0){
                            tweetText = tweetText.substring(0, index);

                        }

                        tweetModel.setTweetText(tweetText);

                        MediaEntity currentPhoto = tweet.entities.media.get(0);
                        String imageUrl = currentPhoto.mediaUrl;
                        tweetModel.setImageUrl(imageUrl);

                        items.add(tweetModel);

                    }
                }

                Collections.sort(items, new Comparator<Image>() {
                    @Override
                    public int compare(Image o1, Image o2) {
                        return o2.getFavouriteCount() - o1.getFavouriteCount();
                    }
                });

                post(items);
            }

            @Override
            public void failure(TwitterException e) {
                e.printStackTrace();
                Log.d("Error", e.getMessage() );
                post(e.getLocalizedMessage());
            }
        };

        client.getTimelineService().homeTimeline(TWEET_COUNT, true, true,true,true, callback);

    }



    private boolean containsImages(Tweet tweet){
        return tweet.entities != null && tweet.entities.media != null
                && !tweet.entities.media.isEmpty();
    }

    private void post(String error){
        post(null, error);
    }

    private void post(List<Image> items){
        post(items, null);
    }

    private void post(List<Image> items, String error){
        ImagesEvent event = new ImagesEvent();
        event.setError(error);
        event.setImages(items);
        eventBus.post(event);

    }
}
