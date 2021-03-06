package task.application.com.moviefinder.ui.search;

import com.androidtmdbwrapper.enums.MediaType;
import com.androidtmdbwrapper.model.mediadetails.MediaBasic;

import java.util.ArrayList;

import task.application.com.moviefinder.ui.base.BasePresenter;
import task.application.com.moviefinder.ui.base.BaseView;

/**
 * Created by sHIVAM on 1/30/2017.
 */

public interface SearchContract {

    interface View extends BaseView<Presenter> {
        void showSearchListUi(ArrayList<? extends MediaBasic> movieDbs);
        void showLoadingResultsError();
        void showLoadingIndicator(boolean flag);
        void showNoResults();
    }

    interface Presenter extends BasePresenter {
        void searchByKeyword(String keyword);

        void setFilteringType(MediaType filteringType);

        MediaType getFilteringType();
    }
}
