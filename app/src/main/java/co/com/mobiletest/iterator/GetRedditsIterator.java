package co.com.mobiletest.iterator;

import java.util.List;

import co.com.mobiletest.persistent.model.RedditsModelEntity;

/**
 * @author Jaime Gamboa
 */
@SuppressWarnings("ALL")
public interface GetRedditsIterator {

    /**
     * This interface contains the methods of execution of a request
     */
    interface onRequestFinished {

        /**
         * method for notifying an error of the request
         *
         * @param error
         */
        void onFailureRequest (String error);

        /**
         * method that notifies the service response
         *
         * @param redditsModel
         */
        void onSuccessRequest (List<RedditsModelEntity> redditsModel);
    }

    /**
     * method to perform services HTTPGet
     */
    void getInfoService (String service, onRequestFinished onRequestFinished);

    /**
     * method execute bd
     */
    void getInfoOfflineMode (onRequestFinished onRequestFinished);
}
