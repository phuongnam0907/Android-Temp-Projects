package com.example.phuon.androidtvapptutorial;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v17.leanback.app.BrowseFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.OnItemViewSelectedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.net.URI;
import java.util.ArrayList;

/**
 * Created by corochann on 2015/06/28.
 */
public class MainFragment extends BrowseFragment {
    private static final String TAG = MainFragment.class.getSimpleName();

    private ArrayObjectAdapter mRowsAdapter;

    /* Grid row item settings */
    private static final int GRID_ITEM_WIDTH = 300;
    private static final int GRID_ITEM_HEIGHT = 200;
    private static final String GRID_STRING_ERROR_FRAGMENT = "ErrorFragment";
    private static final String GRID_STRING_GUIDED_STEP_FRAGMENT = "GuidedStepFragment";
    private static final String GRID_STRING_RECOMMENDATION = "Recommendation";

    private static PicassoBackgroundManager picassoBackgroundManager = null;

    ArrayList<Movie> mItems = (ArrayList<Movie>) MovieList.setupMovies();
    private static int recommendationCounter = 0;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);

        setupUIElements();

        loadRows();

        setupEventListeners();

        picassoBackgroundManager = new PicassoBackgroundManager(getActivity());
        picassoBackgroundManager.updateBackgroundWithDelay("https://images.wallpaperscraft.com/image/starry_sky_night_mountains_radiance_glitter_118791_1920x1200.jpg");
    }

    private void setupEventListeners() {
        setOnItemViewSelectedListener(new ItemViewSelectedListener());
        setOnItemViewClickedListener(new ItemViewClickedListener());

        // Existence of this method make In-app search icon visible
        setOnSearchClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    private final class ItemViewClickedListener implements OnItemViewClickedListener {
        @Override
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item,
                                  RowPresenter.ViewHolder rowViewHolder, Row row) {
            // each time the item is clicked, code inside here will be executed.
            Log.d(TAG, "onItemClicked: item = " + item.toString());
            if (item instanceof Movie) {
                Movie movie = (Movie) item;

                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra(DetailsActivity.MOVIE, movie);

                getActivity().startActivity(intent);
            } else if (item instanceof String){
                if (item == GRID_STRING_ERROR_FRAGMENT) {
                    Intent intent = new Intent(getActivity(), ErrorActivity.class);
                    startActivity(intent);
                } else if (item == GRID_STRING_GUIDED_STEP_FRAGMENT) {
                    Intent intent = new Intent(getActivity(), GuidedStepActivity.class);
                    startActivity(intent);
                }
            }
        }
    }

    private final class ItemViewSelectedListener implements OnItemViewSelectedListener {
        @Override
        public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item,
                                   RowPresenter.ViewHolder rowViewHolder, Row row) {
            // each time the item is selected, code inside here will be executed.
            if (item instanceof String) {                    // GridItemPresenter
                picassoBackgroundManager.updateBackgroundWithDelay("https://images.wallpaperscraft.com/image/starry_sky_night_mountains_radiance_glitter_118791_1920x1200.jpg");
            } else if (item instanceof Movie) {              // CardPresenter
                picassoBackgroundManager.updateBackgroundWithDelay(((Movie) item).getCardImageUrl());
            }
        }
    }

    private void setupUIElements() {
        // setBadgeDrawable(getActivity().getResources().getDrawable(R.drawable.videos_by_google_banner));
        setTitle("Nam Phương's TV!"); // Badge, when set, takes precedent
        // over title
        setHeadersState(HEADERS_ENABLED);
        setHeadersTransitionOnBackEnabled(true);

        // set fastLane (or headers) background color
        setBrandColor(getResources().getColor(R.color.youtube_color));
        // set search icon color
        setSearchAffordanceColor(getResources().getColor(R.color.youtube_search));
    }

    private void loadRows() {
        mRowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());


        /* GridItemPresenter */
        HeaderItem gridItemPresenterHeader = new HeaderItem(0, "GridItemPresenter");

        GridItemPresenter mGridPresenter = new GridItemPresenter();
        ArrayObjectAdapter gridRowAdapter = new ArrayObjectAdapter(mGridPresenter);
        gridRowAdapter.add(GRID_STRING_ERROR_FRAGMENT);
        gridRowAdapter.add(GRID_STRING_GUIDED_STEP_FRAGMENT);
        gridRowAdapter.add(GRID_STRING_RECOMMENDATION);
        mRowsAdapter.add(new ListRow(gridItemPresenterHeader, gridRowAdapter));
//
//        /* CardPresenter */
//        HeaderItem cardPresenterHeader = new HeaderItem(1, "CardPresenter");
//        CardPresenter cardPresenter = new CardPresenter();
//        ArrayObjectAdapter cardRowAdapter = new ArrayObjectAdapter(cardPresenter);
//
//        for (Movie movie : mItems) {
//            cardRowAdapter.add(movie);
//        }
//
//        mRowsAdapter.add(new ListRow(cardPresenterHeader, cardRowAdapter));

        /* Set */

        for (int j = 1; j < 5; j++){
            HeaderItem PresentHeader = new HeaderItem(j, "St." + MovieList.MOVIE_CATEGORY[j-1]);
            CardPresenter cardPresenter = new CardPresenter();
            ArrayObjectAdapter cardRowAdapter = new ArrayObjectAdapter(cardPresenter);

            for (Movie movie : mItems) {
                cardRowAdapter.add(movie);
            }
            mRowsAdapter.add(new ListRow(PresentHeader, cardRowAdapter));
        }

        setAdapter(mRowsAdapter);
    }

    /**
     * from AOSP sample source code
     * GridItemPresenter class. Show TextView with item type String.
     */
    private class GridItemPresenter extends Presenter {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent) {
            TextView view = new TextView(parent.getContext());
            view.setLayoutParams(new ViewGroup.LayoutParams(GRID_ITEM_WIDTH, GRID_ITEM_HEIGHT));
            view.setFocusable(true);
            view.setFocusableInTouchMode(true);
            view.setBackgroundColor(getResources().getColor(R.color.default_background));
            view.setTextColor(Color.WHITE);
            view.setGravity(Gravity.CENTER);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, Object item) {
            ((TextView) viewHolder.view).setText((String) item);
        }

        @Override
        public void onUnbindViewHolder(ViewHolder viewHolder) {

        }
    }


}
