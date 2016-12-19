package co.com.mobiletest;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.mobiletest.persistent.model.RedditsModelEntity;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author Jaime Gamboa
 * @see AppCompatActivity
 * {@link CircleImageView }
 */
public class DetailsReddist extends AppCompatActivity {

    /**
     * Injection views
     */
    @BindView(R.id.ImageRedditDetails)
    SimpleDraweeView ImageRedditDetails;
    @BindView(R.id.DetailRedditName)
    TextView DetailRedditName;
    @BindView(R.id.DescriptionHtmlReddit)
    TextView DescriptionHtmlReddit;
    @BindView(R.id.toolbar_details)
    Toolbar toolbar_details;

    /**
     * configuration and local variables
     */
    private RedditsModelEntity model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_reddist);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar_details);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        model = (RedditsModelEntity) getIntent().getExtras().getSerializable("RedditsModelEntity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Glide.with(this).load(model.getBanner_img()).into(ImageRedditDetails);
        if(model.getBanner_img() != null) {
            ImageRedditDetails.setImageURI(Uri.parse(model.getBanner_img()));
        }
        DetailRedditName.setText(model.getDisplay_name());
        DescriptionHtmlReddit.setText(Html.fromHtml(model.getPublic_description()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
