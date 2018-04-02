package com.drag_expandable_list_view.src.model;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by benholmes on 3/31/18.
 */

public class Navigation  {

    public static Navigation nav;
    private Realm realmInstance;
    private RealmResults<NavigationItem> navigationItems;


    public Navigation(){
        realmInstance = Realm.getDefaultInstance();
    }

    public static Navigation getInstance(){
        return nav == null ? nav =  new Navigation() :  nav;
    }

    public void saveItems(final List<NavigationItem> items){
//        getRealm().executeTransactionAsync(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                realm.where(NavigationItem.class).findAll().deleteAllFromRealm();
//                realm.copyToRealmOrUpdate(items);
//            }
//        });
        getRealm().beginTransaction();
        getRealm().where(NavigationItem.class).findAll().deleteAllFromRealm();
        getRealm().copyToRealmOrUpdate(items);
        getRealm().commitTransaction();

    }

    public RealmResults<NavigationItem> getAllItems(){
        if(navigationItems == null || !navigationItems.isValid()){
            navigationItems =  getRealm().where(NavigationItem.class).distinct("displayName").sort("position", Sort.ASCENDING).findAll();
        }
        return navigationItems;
    }

    public Realm getRealm(){
        if(realmInstance == null || realmInstance.isClosed()){
            return Realm.getDefaultInstance();
        }
        return realmInstance;
    }


    public void rearrange(int from , int to){
        getRealm().beginTransaction();
        if(from < to){
            shiftDown(from, to);
        }else{
            shiftDown(to, from);
        }

        getRealm().commitTransaction();
        navigationItems.sort("position", Sort.ASCENDING);
    }

    public void shiftDown(int from, int to){
        for(int i = to; i >= from; i--){
            if(i == from) navigationItems.get(i).setPosition(to);
            else{
                navigationItems.get(i).setPosition(navigationItems.get(i).getPosition()-1);
            }
            getRealm().copyToRealmOrUpdate(navigationItems.get(i));
        }

    }


}
