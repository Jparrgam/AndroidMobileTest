package co.com.mobiletest.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.getkeepsafe.taptargetview.TapTargetView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.mobiletest.R;
import co.com.mobiletest.view.ViewReddits;
import co.com.mobiletest.persistent.model.RedditsModelEntity;

/**
 * @author Jaime Gamboa
 * @see android.support.v7.widget.RecyclerView.Adapter
 * {@link "https://developer.android.com/reference/android/support/v7/widget/RecyclerView.Adapter.html"}
 */
@SuppressWarnings("ALL")
public class RecyclerViewRedditAdapter extends RecyclerView.Adapter<RecyclerViewRedditAdapter.cardViewHolderReddit> {

    /**
     * configuration and local variables
     */
    private List<RedditsModelEntity> items;
    private Context context;
    private ViewReddits viewReddits;


    public RecyclerViewRedditAdapter(List<RedditsModelEntity> items, Context context, ViewReddits viewReddits) {
        this.items = items;
        this.context = context;
        this.viewReddits = viewReddits;
    }

    /**
     * Called when RecyclerView needs a new {@link RecyclerView.ViewHolder} of the given type to represent
     * an item.
     * <p/>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p/>
     * The new ViewHolder will be used to display items of the adapter using
     * . Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     */
    @Override
    public cardViewHolderReddit onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.card_widget_reddit, parent, false);
        return new cardViewHolderReddit(v);
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link RecyclerView.ViewHolder#itemView} to reflect the item at the given
     * position.
     * <p/>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link RecyclerView.ViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p/>
     * Override  instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(cardViewHolderReddit holder, int position) {
        final RedditsModelEntity modelEntity = items.get(position);
        holder.RedditDescription.setMovementMethod(new ScrollingMovementMethod());
        Glide.with(this.context)
                .load(modelEntity.getBanner_img())
                .placeholder(R.mipmap.splash_screen)
                .into(holder.imageReddit);
        holder.nameReddit.setText(modelEntity.getDisplay_name());
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            if(modelEntity.getPublic_description() != null) {
                holder.RedditDescription.setText(Html.fromHtml(modelEntity.getPublic_description(),Html.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH));
            }
        } else  {
            if(modelEntity.getPublic_description() != null) {
                holder.RedditDescription.setText(Html.fromHtml(modelEntity.getPublic_description()));
            }
        }
        holder.imageReddit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewReddits.onItemSelected(modelEntity, view);
            }
        });
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {

        return items.size();
    }


    /**
     * @author Jaime Gamboa
     * @see android.support.v7.widget.RecyclerView.ViewHolder
     */
    static class cardViewHolderReddit extends RecyclerView.ViewHolder {

        @BindView(R.id.imageReddit) ImageView imageReddit;
        @BindView(R.id.nameReddit) TextView nameReddit;
        @BindView(R.id.RedditDescription) TextView RedditDescription;

        public cardViewHolderReddit(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
