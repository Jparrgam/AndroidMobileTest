package co.com.mobiletest.presenter;

import android.content.Context;

import java.util.List;

import co.com.mobiletest.iterator.GetRedditsIteratorImpl;
import co.com.mobiletest.iterator.GetRedditsIterator;
import co.com.mobiletest.receiver.WifiBroadcastReceiver;
import co.com.mobiletest.view.ViewReddits;
import co.com.mobiletest.persistent.model.RedditsModelEntity;

/**
 * @author Jaime Gamboa
 * @see GetRedditsPresenter
 * @see co.com.mobiletest.iterator.GetRedditsIterator.onRequestFinished
 */
@SuppressWarnings("ALL")
public class GetRedditsPresenterImpl implements GetRedditsPresenter, GetRedditsIterator.onRequestFinished {

    private ViewReddits viewReddists;
    private GetRedditsIteratorImpl getReddistsIterator;
    private Context context;

    /**
     * get list Reddists
     *
     * @param viewReddists
     */
    public GetRedditsPresenterImpl(ViewReddits viewReddists) {
        this.viewReddists = viewReddists;
        this.getReddistsIterator = new GetRedditsIteratorImpl();
    }

    /**
     * Get List App
     *
     * @param service : url service execute
     */
    @Override
    public void getReddists(String service) {
        if(viewReddists != null) {
            viewReddists.showProgress();
            boolean connect = WifiBroadcastReceiver.isNetworkAvailable(context);
            if(connect) {
                getReddistsIterator.getInfoService (service, this);
            } else {
                getReddistsIterator.getInfoOfflineMode(this);
            }
        }
    }

    /**
     * onDestroy interface
     */
    @Override
    public void onDestroyView() {

        viewReddists = null;
    }

    /**
     * context application
     *
     * @param context
     */
    @Override
    public void setContext(Context context) {

        this.context = context;
    }

    /**
     * method for notifying an error of the request
     *
     * @param error
     */
    @Override
    public void onFailureRequest(String error) {
        if(viewReddists !=  null) {
            viewReddists.onFailureRequest(error);
        }
    }

    /**
     * method that notifies the service response
     *
     * @param redditsModel
     */
    @Override
    public void onSuccessRequest(List<RedditsModelEntity> redditsModel) {
        if(viewReddists != null) {
            viewReddists.onSuccessRequest(redditsModel);
        }
    }


}
