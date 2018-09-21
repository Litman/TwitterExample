package ghostl.com.twitterappexample.hashtags.di;

import ghostl.com.twitterappexample.hashtags.ui.HashtagsView;
import ghostl.com.twitterappexample.images.ui.adapters.OnItemClickListener;

public class HashtagsModule {
    private HashtagsView view;
    private OnItemClickListener clickListener;

    public HashtagsModule(HashtagsView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }


}
