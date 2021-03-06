package task.application.com.moviefinder.ui.favorites;

import com.androidtmdbwrapper.enums.MediaType;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.OrderedRealmCollectionSnapshot;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import task.application.com.moviefinder.ApplicationClass;
import task.application.com.moviefinder.model.local.realm.datamodels.MediaItem;
import task.application.com.moviefinder.util.TmdbApi;
import task.application.com.moviefinder.util.Util;

/**
 * Created by sHIVAM on 6/4/2017.
 */

public class FavoritesPresenter implements FavoritesMediaContract.Presenter {

    private FavoritesMediaContract.View view;
    private Realm realm;
    private MediaType filter = MediaType.MOVIES;

    FavoritesPresenter(FavoritesMediaContract.View view) {
        this.view = Util.checkNotNull(view, "FavoriteMediaFragment view is null!");
        view.setPresenter(this);
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void start() {

    }

    @Override
    public void destroy() {
        realm.removeAllChangeListeners();
        realm.close();
        realm = null;
    }

    @Override
    public void fetchDataFromRealm(String FILTER) {
        RealmResults<MediaItem> res = realm.where(MediaItem.class)
                .equalTo("mediaType", FILTER)
                .findAllSortedAsync("createdAt", Sort.DESCENDING);
        res.addChangeListener(mediaItems -> {
            if (mediaItems.isValid()) {
                view.updateListAdapter(mediaItems);
            }
        });
    }

    @Override
    public void showMediaDetails(MediaItem item) {
        view.showItemDetailsUI(item);
    }

    @Override
    public void deleteDataFromRealm(final List<Integer> items,
                                    final OrderedRealmCollectionSnapshot<MediaItem> snapshot) {
        realm.executeTransaction((r) -> {
            for (int pos : items) {
                snapshot.deleteFromRealm(pos);
            }
        });
    }

    @Override
    public void searchByKeyword(String keyword) {
        view.showLoadingIndicator(true);
        TmdbApi tmdb = TmdbApi.getApiClient(ApplicationClass.API_KEY);
        if (getFilter().equals(MediaType.MOVIES)) {
            tmdb.searchService().searchMovies(keyword)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(searchRes -> {
                        view.showSearchListUi(new ArrayList<>(searchRes.getResults()));
                        view.showLoadingIndicator(false);
                    });

        } else {

            tmdb.searchService().searchTv(keyword)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(searchRes -> {
                        view.showSearchListUi(new ArrayList<>(searchRes.getResults()));
                        view.showLoadingIndicator(false);
                    });
        }
    }

    public MediaType getFilter() {
        return filter;
    }

    public void setFilter(MediaType filter) {
        this.filter = filter;
    }
}
