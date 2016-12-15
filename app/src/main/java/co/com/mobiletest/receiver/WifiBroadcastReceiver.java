package co.com.mobiletest.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.widget.Toast;

import co.com.mobiletest.R;
import co.com.mobiletest.view.ViewReddits;

/**
 * receiver System events wifi state and mobile data
 *
 * @author Jaime Gamboa
 * @see android.content.BroadcastReceiver
 */
@SuppressWarnings("ALL")
public class WifiBroadcastReceiver extends BroadcastReceiver {
    /**
     * Action settings WIFI_STATE_CHANGED and STATE_CHANGE, change status wifi network connect
     */
    public String WIFI_STATE_CHANGED = "android.net.wifi.WIFI_STATE_CHANGED";
    public String STATE_CHANGE = "android.net.wifi.STATE_CHANGE";
    public String CONNECTIVITY_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE";

    private static ViewReddits viewReddits;

    /**
     * boolean validate connect network
     */
    public static Boolean isConnect = false;

    /**
     * This method is called when the BroadcastReceiver is receiving an Intent
     * broadcast.  During this time you can use the other methods on
     * BroadcastReceiver to view/modify the current result values.  This method
     * is always called within the main thread of its process, unless you
     * explicitly asked for it to be scheduled on a different thread using
     * {@link Context#registerReceiver(BroadcastReceiver,
     * IntentFilter, String, Handler)}. When it runs on the main
     * thread you should
     * never perform long-running operations in it (there is a timeout of
     * 10 seconds that the system allows before considering the receiver to
     * be blocked and a candidate to be killed). You cannot launch a popup dialog
     * in your implementation of onReceive().
     * <p/>
     * <p><b>If this BroadcastReceiver was launched through a &lt;receiver&gt; tag,
     * then the object is no longer alive after returning from this
     * function.</b>  This means you should not perform any operations that
     * return a result to you asynchronously -- in particular, for interacting
     * with services, you should use
     * {@link Context#startService(Intent)} instead of
     * {@link Context#bindService(Intent, ServiceConnection, int)}.  If you wish
     * to interact with a service that is already running, you can use
     * {@link #peekService}.
     * <p/>
     * <p>The Intent filters used in {@link Context#registerReceiver}
     * and in application manifests are <em>not</em> guaranteed to be exclusive. They
     * are hints to the operating system about how to find suitable recipients. It is
     * possible for senders to force delivery to specific recipients, bypassing filter
     * resolution.  For this reason, {@link #onReceive(Context, Intent) onReceive()}
     * implementations should respond only to known actions, ignoring any unexpected
     * Intents that they may receive.
     *
     * @param context The Context in which the receiver is running.
     * @param intent  The Intent being received.
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            if (intent.getAction().equals(WIFI_STATE_CHANGED) ||
                intent.getAction().equals(STATE_CHANGE) ||
                intent.getAction().equals(CONNECTIVITY_CHANGE)) {
                isConnect = isNetworkAvailable(context);
                if(!isConnect) {
                    viewReddits.onNetworkStatusChanged(isConnect);
                }
            }
        }
    }

    /**
     * verified network available
     *
     * @param context The Context in which the receiver is running
     * @return
     */
    public static boolean isNetworkAvailable (Context context) {
        ConnectivityManager connectivityManager
            = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /**
     * set call back manager
     *
     * @param callbackManager
     */
    public static void setCallbackManager (ViewReddits callbackManager) {
        viewReddits = callbackManager;
    }
}
