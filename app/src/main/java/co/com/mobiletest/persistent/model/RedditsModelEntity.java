package co.com.mobiletest.persistent.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import se.emilsjolander.sprinkles.Model;
import se.emilsjolander.sprinkles.annotations.AutoIncrement;
import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.Key;
import se.emilsjolander.sprinkles.annotations.Table;

/**
 * @author Jaime Gamboa
 * @see se.emilsjolander.sprinkles.Model
 */
@SuppressWarnings("ALL")
@Table("Reddits")
public class RedditsModelEntity extends Model implements Serializable {

    @Key
    @AutoIncrement
    @Column("idReddit")
    private long idReddit;

    @SerializedName("banner_img")
    @Expose
    @Column("banner_img")
    private String banner_img;

    @SerializedName("submit_text_html")
    @Expose
    @Column("submit_text_html")
    private String submit_text_html;

    @SerializedName("submit_text")
    @Expose
    @Column("submit_text")
    private String submit_text;

    @SerializedName("display_name")
    @Expose
    @Column("display_name")
    private String display_name;

    @SerializedName("header_img")
    @Expose
    @Column("header_img")
    private String header_img;

    @SerializedName("description_html")
    @Expose
    @Column("description_html")
    private String description_html;

    @SerializedName("public_description_html")
    @Expose
    @Column("public_description_html")
    private String public_description_html;

    @SerializedName("public_description")
    @Expose
    @Column("public_description")
    private String public_description;

    public long getId() {

        return idReddit;
    }

    public RedditsModelEntity(long idReddit, String banner_img, String submit_text_html, String submit_text, String display_name, String header_img, String description_html, String public_description_html, String public_description) {
        this.idReddit = idReddit;
        this.banner_img = banner_img;
        this.submit_text_html = submit_text_html;
        this.submit_text = submit_text;
        this.display_name = display_name;
        this.header_img = header_img;
        this.description_html = description_html;
        this.public_description_html = public_description_html;
        this.public_description = public_description;
    }

    public RedditsModelEntity() {}

    public long getIdReddit() {

        return idReddit;
    }

    public void setIdReddit(long idReddit) {

        this.idReddit = idReddit;
    }

    public String getBanner_img() {

        return banner_img;
    }

    public void setBanner_img(String banner_img) {

        this.banner_img = banner_img;
    }

    public String getSubmit_text_html() {

        return submit_text_html;
    }

    public void setSubmit_text_html(String submit_text_html) {

        this.submit_text_html = submit_text_html;
    }

    public String getSubmit_text() {

        return submit_text;
    }

    public void setSubmit_text(String submit_text) {

        this.submit_text = submit_text;
    }

    public String getDisplay_name() {

        return display_name;
    }

    public void setDisplay_name(String display_name) {

        this.display_name = display_name;
    }

    public String getHeader_img() {

        return header_img;
    }

    public void setHeader_img(String header_img) {

        this.header_img = header_img;
    }

    public String getDescription_html() {

        return description_html;
    }

    public void setDescription_html(String description_html) {

        this.description_html = description_html;
    }

    public String getPublic_description_html() {

        return public_description_html;
    }

    public void setPublic_description_html(String public_description_html) {

        this.public_description_html = public_description_html;
    }

    public String getPublic_description() {

        return public_description;
    }

    public void setPublic_description(String public_description) {

        this.public_description = public_description;
    }
}
