package com.drag_expandable_list_view.src;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.drag_expandable_list_view.R;
import com.drag_expandable_list_view.src.model.Navigation;
import com.drag_expandable_list_view.src.model.NavigationItem;
import com.drag_expandable_list_view.src.views.ListItemView;
import com.example.drag_expandable_list_view.DragSortListView.DragSortController;
import com.example.drag_expandable_list_view.DragSortListView.DragSortListView;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    Context ctx;
    DragSortListView listView;
    DragSortController sortController;
    ArrayList<String> items = new ArrayList<>();
    public static RealmResults<NavigationItem> navItems;
    Navigation nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ctx = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        createNavItems();
        navItems = nav.getAllItems();


        listView = (DragSortListView) findViewById(R.id.drag_sort_list_view);
        listView.setDragEnabled(true);
        listView.setDragSortListener(getDragSortListener());
        listView.setMaxScrollSpeed(2);
        listView.setFloatViewManager(sortController = new DragSortController(listView) {

            @Override
            public View onCreateFloatView(int position) {
                // Vibration
                Vibrator v = (Vibrator) ctx.getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(50);

                View view = super.onCreateFloatView(position);
                view.setBackgroundColor(Color.TRANSPARENT);
                return view;
            }

        });
        listView.setDrawSelectorOnTop(false);
        listView.setFloatAlpha(0.5f);
        items.add("This is some Text");
        items.add("This is a drag sort Item");
        items.add("This is another drag sort item");
        items.add("Can I do anything here?");

        listView.setAdapter(new DragSortAdapter());


    }

    private void createNavItems(){
        nav = Navigation.getInstance();
        List<NavigationItem> navItems = new ArrayList<>();
        for(int i =0 ; i < 10; i++){
            NavigationItem item = new NavigationItem();
            item.setName("Item " + i);
            item.setPosition(i);
            item.setResource(R.drawable.menu_drag_handle);
            navItems.add(item);
        }
        nav.saveItems(navItems);
    }


    private DragSortListView.DragSortListener getDragSortListener(){
        return  new DragSortListView.DragSortListener() {
            @Override
            public void drag(int from, int to) {

            }

            @Override
            public void drop(int from, int to) {
                nav.rearrange(from, to);
                ((BaseAdapter)listView.getAdapter()).notifyDataSetChanged();
            }

            @Override
            public void remove(int which) {

            }
        };
    }


    class DragSortAdapter extends BaseAdapter{


        @Override
        public int getCount() {
            return navItems.size();
        }

        @Override
        public Object getItem(int i) {
            return navItems.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ListItemView itemView = new ListItemView(ctx);
            itemView.setPrimaryText(navItems.get(i).getName());
            itemView.setBackgroundColor(ContextCompat.getColor(ctx, R.color.colorPrimaryDark));
            itemView.setPrimaryTextColor(ContextCompat.getColor(ctx, R.color.colorAccent));
            return itemView;
        }
    }
}
