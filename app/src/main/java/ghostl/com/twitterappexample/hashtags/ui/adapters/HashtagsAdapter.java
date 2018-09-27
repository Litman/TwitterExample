package ghostl.com.twitterappexample.hashtags.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ghostl.com.twitterappexample.R;
import ghostl.com.twitterappexample.entities.Hashtag;

public class HashtagsAdapter extends RecyclerView.Adapter<HashtagsAdapter.ViewHolder>{

    private List<Hashtag> dataset;
    private OnItemClickListener clickListener;

    public HashtagsAdapter(List<Hashtag> dataset, OnItemClickListener clickListener) {
        this.dataset = dataset;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.conten_hashtags, parent, false);
        return new ViewHolder(view, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hashtag tweet = dataset.get(position);
        holder.setOnclickListener(tweet, clickListener);
        holder.tvTweet.setText(tweet.getTweetText());
        holder.setItems(tweet.getHashtags());
    }

    public void setItems(List<Hashtag> newItems){
        dataset.addAll(newItems);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.tvTweet)
        TextView tvTweet;

        @Bind(R.id.rvHashtags)
        RecyclerView rvHashtags;

        private View view;
        private HashtagListAdapter adapter;
        private ArrayList<String> items;

        public ViewHolder(View itemView, Context context) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.view = itemView;

            items = new ArrayList<String>();
            adapter = new HashtagListAdapter(items);

            CustomGridLayoutManager layoutManager = new CustomGridLayoutManager(context, 3);
            rvHashtags.setLayoutManager(layoutManager);
            rvHashtags.setAdapter(adapter);
        }

        public void setItems(List<String> newItems){
            items.clear();
            items.addAll(newItems);
            adapter.notifyDataSetChanged();
        }

        public void setOnclickListener(final Hashtag hashtag, final OnItemClickListener listener){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(hashtag);
                }
            });
        }
    }
}
