package co.leinaro.gallera;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import co.leinaro.gallera.entities.Chick;
import co.leinaro.gallera.entities.SearchDates;

public class ChickListActivity extends AppCompatActivity {

    private static ChickAdapter adapter;
    public static String SEARCH_DATE = "search_date";
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;

    private static List<Chick> data;
//    private static ArrayList<Integer> removedItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallera_list);

        SearchDates selectedSarchDate = (SearchDates) getIntent().getExtras().getSerializable(SEARCH_DATE);
        data = Storage.getChickensByDate(selectedSarchDate);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

//        data = Storage.getChickens();
//        removedItems = new ArrayList<Integer>();
        adapter = new ChickAdapter(data);
        recyclerView.setAdapter(adapter);
    }


//    private static class MyOnClickListener implements View.OnClickListener {
//
//        private final Context context;
//
//        private MyOnClickListener(Context context) {
//            this.context = context;
//        }
//
//        @Override
//        public void onClick(View v) {
////            removeItem(v);
//        }
//
////        private void removeItem(View v) {
////            int selectedItemPosition = recyclerView.getChildPosition(v);
////            RecyclerView.ViewHolder viewHolder
////                    = recyclerView.findViewHolderForPosition(selectedItemPosition);
////            TextView textViewName
////                    = (TextView) viewHolder.itemView.findViewById(R.id.textViewName);
////            String selectedName = (String) textViewName.getText();
////            int selectedItemId = -1;
//////            for (int i = 0; i < MyData.nameArray.length; i++) {
//////                if (selectedName.equals(MyData.nameArray[i])) {
//////                    selectedItemId = MyData.id_[i];
//////                }
//////            }
////            removedItems.add(selectedItemId);
////            data.remove(selectedItemPosition);
////            adapter.notifyItemRemoved(selectedItemPosition);
////        }
//    }

    //    private void addRemovedItemToList() {
//        int addItemAtListPosition = 3;
//        data.add(addItemAtListPosition, new DataModel(
//                MyData.nameArray[removedItems.get(0)],
//                MyData.versionArray[removedItems.get(0)],
//                MyData.id_[removedItems.get(0)],
//                MyData.drawableArray[removedItems.get(0)]
//        ));
//        adapter.notifyItemInserted(addItemAtListPosition);
//        removedItems.remove(0);
//    }
}
