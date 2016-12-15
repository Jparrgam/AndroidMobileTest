package co.com.mobiletest.persistent.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Jaime Gamboa
 * @see Serializable
 */
@SuppressWarnings("ALL")
public class RedditChildren implements Serializable {

    @SerializedName("kind")
    @Expose
    private String kind;

    @SerializedName("data")
    @Expose
    private RedditsModelEntity data;

    public RedditChildren() {}

    public RedditChildren(String kind, RedditsModelEntity data) {
        this.kind = kind;
        this.data = data;
    }

    public String getKind() {

        return kind;
    }

    public void setKind(String kind) {

        this.kind = kind;
    }

    public RedditsModelEntity getData() {

        return data;
    }

    public void setData(RedditsModelEntity data) {

        this.data = data;
    }
}
