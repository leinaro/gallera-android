package co.leinaro.gallera;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GalleraListFragment extends Fragment implements GalleraListView {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static List<Chick> data;
    static View.OnClickListener myOnClickListener;
    private static ArrayList<Integer> removedItems;
    private ChickListPresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_gallera_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        myOnClickListener = new MyOnClickListener(getActivity());

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new ArrayList<Chick>();

        removedItems = new ArrayList<Integer>();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        adapter = new ChickAdapter(data);
//        recyclerView.setAdapter(adapter);

        initDependencies();
    }

    private void initDependencies() {
        mPresenter = new ChickListPresenter(this, new ApiGallera(getActivity()));
        mPresenter.getChicks();
    }

    @Override
    public void getChickSuccess(List<Chick> chickList) {
        data = chickList;
        adapter = new ChickAdapter(data);
        recyclerView.setAdapter(adapter);

//        adapter.notifyDataSetChanged();
    }

    private static class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            removeItem(v);
        }

        private void removeItem(View v) {
            int selectedItemPosition = recyclerView.getChildPosition(v);
            RecyclerView.ViewHolder viewHolder
                    = recyclerView.findViewHolderForPosition(selectedItemPosition);
            TextView textViewName
                    = (TextView) viewHolder.itemView.findViewById(R.id.textViewName);
            String selectedName = (String) textViewName.getText();
            int selectedItemId = -1;
            for (int i = 0; i < MyData.nameArray.length; i++) {
                if (selectedName.equals(MyData.nameArray[i])) {
                    selectedItemId = MyData.id_[i];
                }
            }
            removedItems.add(selectedItemId);
            data.remove(selectedItemPosition);
            adapter.notifyItemRemoved(selectedItemPosition);
        }
    }

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
