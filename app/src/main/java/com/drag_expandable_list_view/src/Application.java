package com.drag_expandable_list_view.src;


import android.content.Context;
import android.content.SyncStatusObserver;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by benholmes on 3/31/18.
 */

public class Application extends MultiDexApplication {


    @Override
    public void onCreate() {
        Realm.init(getApplicationContext());
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("default.realm")
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);

//        Print Realm File for Realm Browser
        Log.e("Realm url", Realm.getDefaultConfiguration().getRealmFileName());



//        Picasso.Builder builder = new Picasso.Builder(this);
//        builder.downloader(new OkHttpDownloader(this,Integer.MAX_VALUE));
//        Picasso built = builder.build();
//        built.setLoggingEnabled(true);
//        Picasso.setSingletonInstance(built);

        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base){
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
