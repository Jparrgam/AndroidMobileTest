package co.com.mobiletest;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.pnikosis.materialishprogress.ProgressWheel;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import co.com.mobiletest.adapter.RecyclerViewRedditAdapter;
import co.com.mobiletest.presenter.GetRedditsPresenter;
import co.com.mobiletest.presenter.GetRedditsPresenterImpl;
import co.com.mobiletest.receiver.WifiBroadcastReceiver;
import co.com.mobiletest.util.constant;
import co.com.mobiletest.view.ViewReddits;
import co.com.mobiletest.persistent.model.RedditsModelEntity;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import top.wefor.circularanim.CircularAnim;


/**
 * @author Jaime Gamboa
 * @see AppCompatActivity
 * @see ViewReddits
 */
@SuppressWarnings("ALL")
public class ListReddits extends AppCompatActivity implements ViewReddits, Toolbar.OnMenuItemClickListener {

    /**
     * Injection views
     */
    @BindView(R.id.recyclerViewReddists)
    RecyclerView recyclerViewReddists;
    @BindView(R.id._progress_bar_reddists)
    ProgressWheel _progress_bar_reddists;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    /**
     * configuration and local variables
     */
    private GetRedditsPresenter getReddistsPresenter;
    private RecyclerViewRedditAdapter recyclerViewRedditAdapter;
    private int positionDelete;

    /**
     * onCreate view
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_reddists);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        getReddistsPresenter = new GetRedditsPresenterImpl(this);
        getReddistsPresenter.setContext(this);

        if(recyclerViewReddists != null) {
            recyclerViewReddists.setHasFixedSize(true);
        }

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
            recyclerViewReddists.setLayoutManager(gridLayoutManager);
        } else {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerViewReddists.setLayoutManager(linearLayoutManager);
        }
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(1000);
        defaultItemAnimator.setRemoveDuration(1000);
        recyclerViewReddists.setItemAnimator(defaultItemAnimator);
        WifiBroadcastReceiver.setCallbackManager(this);
        getReddistsPresenter.getReddists(constant._SERVICE_URL);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getReddistsPresenter.onDestroyView();
    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    /**
     * show progress bar
     */
    @Override
    public void showProgress() {

        _progress_bar_reddists.setVisibility(View.VISIBLE);
    }

    /**
     * method execute http request failed
     *
     * @param failure message failed
     */
    @Override
    public void onFailureRequest(String failure) {
        _progress_bar_reddists.setVisibility(View.INVISIBLE);
        Log.e(this.getLocalClassName().getClass().getSimpleName(),"onFailureRequest response " +failure);
        Crouton.makeText(this, getResources().getString(R.string._unexpected_error), Style.ALERT).show();
    }

    /**
     * method execute http request success code #200
     *
     * @param redditsModel response api
     */
    @Override
    public void onSuccessRequest(List<RedditsModelEntity> redditsModel) {
        _progress_bar_reddists.setVisibility(View.INVISIBLE);
        recyclerViewRedditAdapter = new RecyclerViewRedditAdapter(redditsModel, this, this);
        recyclerViewReddists.setAdapter(recyclerViewRedditAdapter);
        Crouton.makeText(this, getResources().getString(R.string._details_item), Style.CONFIRM).show();
    }

    /**
     * call back method cardView selected item
     *
     * @param redditsModelEntity item selected
     */
    @Override
    public void onItemSelected(final RedditsModelEntity redditsModelEntity, View view) {
        CircularAnim.fullActivity(ListReddits.this, view).go(new CircularAnim.OnAnimationEndListener() {
            @Override
            public void onAnimationEnd() {
                startActivity(new Intent(ListReddits.this, DetailsReddist.class).putExtra("RedditsModelEntity",(Serializable)redditsModelEntity));
            }
        });
    }

    /**
     * method callback verified status network
     *
     * @param status
     */
    @Override
    public void onNetworkStatusChanged(boolean status) {
        if(!status) {
            Crouton.makeText(this, getResources().getString(R.string._message_not_network_disconnect), Style.ALERT).show();
        } else {
            Crouton.cancelAllCroutons();
        }
    }

    /**
     * method callback long listener adapter recyclerView
     *
     * @param redditsModelEntity
     */
    @Override
    public void onLongUserListener(int redditsModelEntity) {
        positionDelete = redditsModelEntity;
        if(!toolbar.getTitle().equals(getResources().getString(R.string._menu_options_title))) {
            toolbar.setTitle(getResources().getString(R.string._menu_options_title));
            toolbar.inflateMenu(R.menu.menu_options);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setOnMenuItemClickListener(this);
        }
    }

    /**
     * This method will be invoked when a menu item is clicked if the item itself did
     * not already handle the event.
     *
     * @param item {@link MenuItem} that was clicked
     * @return <code>true</code> if the event was handled, <code>false</code> otherwise.
     */
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                recyclerViewRedditAdapter.removeAt(positionDelete);
                break;
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                setDefaultToolbar ();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * default toolbar
     */
    private void setDefaultToolbar() {
        toolbar.setTitle(getResources().getString(R.string.app_name));
        toolbar.getMenu().clear();
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }
}
