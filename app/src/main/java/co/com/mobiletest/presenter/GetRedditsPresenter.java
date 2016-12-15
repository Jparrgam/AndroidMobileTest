package co.com.mobiletest.presenter;

import android.content.Context;

/**
 * @author  Jaime Gamboa
 */
public interface GetRedditsPresenter {

    /**
     * Get List App
     *
     * @param service: url service execute
     */
    void getReddists (String service);

    /**
     * onDestroy interface
     */
    void onDestroyView ();

    /**
     * context application
     */
    void setContext (Context context);
}
