package co.com.mobiletest.persistent.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jaime Gamboa
 * @see Serializable
 */
@SuppressWarnings("ALL")
public class RedditData implements  Serializable {

    @SerializedName("modhash")
    @Expose
    private String modhash;

    @SerializedName("children")
    @Expose
    private List<RedditChildren> children = null;

    public RedditData(String modhash, List<RedditChildren> children) {
        this.modhash = modhash;
        this.children = children;
    }

    public RedditData() {}

    public String getModhash() {

        return modhash;
    }

    public void setModhash(String modhash) {

        this.modhash = modhash;
    }

    public List<RedditChildren> getChildren() {

        return children;
    }

    public void setChildren(List<RedditChildren> children) {

        this.children = children;
    }
}
