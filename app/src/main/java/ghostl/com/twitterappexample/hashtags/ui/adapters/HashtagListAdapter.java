package ghostl.com.twitterappexample.hashtags.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ghostl.com.twitterappexample.R;

public class HashtagListAdapter extends RecyclerView.Adapter<HashtagListAdapter.ViewHolder>{
    private List<String> items;

    public HashtagListAdapter(List<String> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_hashtag_text, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvRowHashtag.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tvRowHashtag)
        TextView tvRowHashtag;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this. itemView);

        }
    }
}
