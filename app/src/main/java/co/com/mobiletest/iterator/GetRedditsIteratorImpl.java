package co.com.mobiletest.iterator;

import android.util.Log;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import co.com.mobiletest.persistent.RedditDatabase;
import co.com.mobiletest.persistent.model.RedditChildren;
import co.com.mobiletest.persistent.model.RedditJson;
import co.com.mobiletest.util.constant;
import co.com.mobiletest.persistent.model.RedditsModelEntity;
import cz.msebera.android.httpclient.Header;

/**
 * @author Jaime Gamboa
 * @see co.com.mobiletest.iterator.GetRedditsIterator
 */
@SuppressWarnings("ALL")
public class GetRedditsIteratorImpl implements GetRedditsIterator {

    private List<RedditsModelEntity> redditsModelEntity = new ArrayList<>();
    private Gson gson = new Gson();

    /**
     * method to perform services HTTPGet
     *
     * @param service
     * @param onRequestFinished
     */
    @Override
    public void getInfoService(String service, final onRequestFinished onRequestFinished) {
        AsyncHttpClient  httpClient =  new AsyncHttpClient();
        httpClient.setTimeout(20 * 1000);
        httpClient.setConnectTimeout(20*1000);
        httpClient.setResponseTimeout(20*1000);
        httpClient.get(service, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200) {
                    String data = new String(responseBody);
                    RedditJson redditJson = gson.fromJson(data, RedditJson.class);
                    if(redditJson != null && redditJson.getData() != null) {
                        for(RedditChildren redditChildren : redditJson.getData().getChildren()) {
                            redditsModelEntity.add(redditChildren.getData());
                            RedditDatabase.saveReddit(redditChildren.getData());
                        }
                        onRequestFinished.onSuccessRequest(redditsModelEntity);
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e(GetRedditsIteratorImpl.class.getSimpleName(), "onFailure");
                onRequestFinished.onFailureRequest(error.getMessage());
            }
        });
    }

    /**
     * method execute bd
     *
     * @param onRequestFinished
     */
    @Override
    public void getInfoOfflineMode(onRequestFinished onRequestFinished) {
        List<RedditsModelEntity> entities = RedditDatabase.getReddit();
        onRequestFinished.onSuccessRequest(entities);
    }
}
