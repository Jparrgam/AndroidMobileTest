package co.com.mobiletest.persistent;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.List;

import co.com.mobiletest.persistent.model.RedditsModelEntity;
import se.emilsjolander.sprinkles.ManyQuery;
import se.emilsjolander.sprinkles.Migration;
import se.emilsjolander.sprinkles.Query;
import se.emilsjolander.sprinkles.Sprinkles;

/**
 * @author Jaime Gamboa
 * @see android.app.Application
 */
@SuppressWarnings("ALL")
public class RedditDatabase extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);
        Sprinkles sprinkles = Sprinkles.init(getApplicationContext());

        sprinkles.addMigration(new Migration() {
            @Override
            protected void doMigration(SQLiteDatabase db) {
                db.execSQL(
                    "CREATE TABLE Reddits (" +
                        "idReddit INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        "banner_img TEXT,"+
                        "submit_text_html TEXT,"+
                        "submit_text TEXT," +
                        "display_name TEXT," +
                        "header_img TEXT," +
                        "description_html TEXT," +
                        "public_description_html TEXT ," +
                        "public_description TEXT"+
                        ");"
                );
            }
        });
    }

    /**
     * method to the save new reddit
     *
     * @param redditsModel data to save
     * @return boolean result save
     */
    public static boolean saveReddit(RedditsModelEntity redditsModel) {
        boolean result;
        if(redditsModel.exists()) {
            redditsModel.delete();
            result = redditsModel.save();
        } else  {
            result = redditsModel.save();
        }
        return result;
    }

    /**
     * get result data to database
     *
     * @return RedditsModelEntity
     */
    public static List<RedditsModelEntity> getReddit() {
        ManyQuery<RedditsModelEntity> data = Query.all(RedditsModelEntity.class);
        return data.get().asList();
    }
}
