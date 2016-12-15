package co.com.mobiletest.persistent.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Jaime Gamboa
 * @see Serializable
 */
@SuppressWarnings("ALL")
public class RedditJson implements Serializable {

    @SerializedName("kind")
    @Expose
    private String kind;

    @SerializedName("data")
    @Expose
    private RedditData data;

    public RedditJson(String kind, RedditData data) {
        this.kind = kind;
        this.data = data;
    }

    public RedditJson() {}

    public String getKind() {

        return kind;
    }

    public void setKind(String kind) {

        this.kind = kind;
    }

    public RedditData getData() {

        return data;
    }

    public void setData(RedditData data) {

        this.data = data;
    }
}
