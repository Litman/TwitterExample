package ghostl.com.twitterappexample.hashtags;


import android.util.Log;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.HashtagEntity;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ghostl.com.twitterappexample.apiretrofit.CustomTwitterApiClient;
import ghostl.com.twitterappexample.entities.Hashtag;
import ghostl.com.twitterappexample.entities.Image;
import ghostl.com.twitterappexample.hashtags.events.HashtagsEvent;
import ghostl.com.twitterappexample.images.ImagesRepository;
import ghostl.com.twitterappexample.images.event.ImagesEvent;
import ghostl.com.twitterappexample.lib.base.EventBus;


public class HashtagsRepositoryImpl implements  ImagesRepository{

    private EventBus eventBus;
    private CustomTwitterApiClient client;
    private final static int TWEET_COUNT = 100;


    public HashtagsRepositoryImpl(EventBus eventBus, CustomTwitterApiClient client) {
        this.eventBus = eventBus;
        this.client = client;
    }



    @Override
    public void getImages() {

        Callback<List<Tweet>> callback = new Callback<List<Tweet>>() {

            @Override
            public void success(Result<List<Tweet>> result) {
                List<Hashtag> items = new ArrayList<Hashtag>();
                for (Tweet tweet: result.data){
                    if(containsHashtags(tweet)){
                        Hashtag tweetModel = new Hashtag();

                        tweetModel.setId(tweet.idStr);
                        tweetModel.setFavoriteCount(tweet.favoriteCount);
                        tweetModel.setTweetText(tweet.text);

                        List<String> hashtags = new ArrayList<String>();
                        for(HashtagEntity hashtag: tweet.entities.hashtags){
                            hashtags.add(hashtag.text);
                        }




                        tweetModel.setHashtags(hashtags);

                        items.add(tweetModel);

                    }
                }

                Collections.sort(items, new Comparator<Hashtag>() {
                    @Override
                    public int compare(Hashtag o1, Hashtag o2) {
                        return o2.getFavoriteCount() - o1.getFavoriteCount();
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



    private boolean containsHashtags(Tweet tweet){
        return tweet.entities != null && tweet.entities.hashtags != null
                && !tweet.entities.hashtags.isEmpty();
    }

    private void post(String error){
        post(null, error);
    }

    private void post(List<Hashtag> items){
        post(items, null);
    }

    private void post(List<Hashtag> items, String error){
        HashtagsEvent event = new HashtagsEvent();
        event.setError(error);
        event.setHashtags(items);
        eventBus.post(event);

    }
}
