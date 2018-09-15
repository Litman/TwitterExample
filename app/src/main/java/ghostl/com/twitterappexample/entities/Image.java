package ghostl.com.twitterappexample.entities;

public class Image {
    private String id;
    private String imageUrl;
    private String tweetText;
    private int favouriteCount;
    private final static String BASE_TWEET_URL = "https://twitter.com/null/status";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTweetText() {
        return tweetText;
    }

    public void setTweetText(String tweetText) {
        this.tweetText = tweetText;
    }

    public int getFavouriteCount() {
        return favouriteCount;
    }

    public void setFavouriteCount(int favouriteCount) {
        this.favouriteCount = favouriteCount;
    }

    public String getTweetUrl(){
        return BASE_TWEET_URL + this.id;
    }
}
