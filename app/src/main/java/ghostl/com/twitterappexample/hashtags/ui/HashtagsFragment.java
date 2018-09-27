package ghostl.com.twitterappexample.hashtags.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.BindInt;
import butterknife.ButterKnife;
import ghostl.com.twitterappexample.R;
import ghostl.com.twitterappexample.hashtags.HashtagsPresenter;
import ghostl.com.twitterappexample.hashtags.di.HashtagsComponent;
import ghostl.com.twitterappexample.hashtags.ui.adapters.HashtagsAdapter;
import ghostl.com.twitterappexample.twitter.TwitterClientApp;

/**
 * A simple {@link Fragment} subclass.
 */
public class HashtagsFragment extends Fragment {

    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.containerRL)
    FrameLayout container;

    @Inject
    HashtagsAdapter adapter;
    @Inject
    HashtagsPresenter presenter;


    public HashtagsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);

        ButterKnife.bind(this, view);
        setupInjection();
        setupRecyclerView();
        presenter.getHashtagsTweets();

        return view;


    }

    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void setupInjection() {
        TwitterClientApp app = (TwitterClientApp)getActivity().getApplication();
        HashtagsComponent hashtagsComponent = null;
        //hashtagsComponent.inject();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }
}
