package www.cput.za.exampreparation.conf;

import android.app.Application;
import android.content.Context;


/**
 * Created by Game330 on 2016-05-12.
 */
public class App extends Application {
    private static Context context;
    private static App singleton;

    public void onCreate() {
        super.onCreate();
        App.context = getApplicationContext();
        singleton = this;
    }

    public static Context getAppContext() {
        return App.context;
    }

    public static final String TAG = App.class
            .getSimpleName();


    public static synchronized App getInstance() {
        return singleton;
    }

}
