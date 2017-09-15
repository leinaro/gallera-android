package co.leinaro.gallera;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import co.leinaro.gallera.api.client.ApiGallera;
import co.leinaro.gallera.entities.SearchDates;

public class DatesListFragment extends Fragment implements GalleraListView {

    private static DatesAdapter datesAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static List<SearchDates> dates;
    private ChickListPresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_gallera_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        dates = new ArrayList<SearchDates>();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDependencies();
    }

    private void initDependencies() {
        mPresenter = new ChickListPresenter(this, new ApiGallera(getActivity()));
        mPresenter.getChicks();
    }

    @Override
    public void getChickSuccess(ApiGallera.Responses response) {
        dates = response.dates;
        // Sorting
        Collections.sort(dates, new Comparator<SearchDates>() {
            @Override
            public int compare(SearchDates chick2, SearchDates chick1) {
                DateTime dateTime2 = ISODateTimeFormat.dateTimeParser().parseDateTime(chick2.getDateCreated());
                DateTime dateTime1 = ISODateTimeFormat.dateTimeParser().parseDateTime(chick1.getDateCreated());
                if (dateTime1.isBefore(dateTime2)) {
                    return -1;
                } else if (dateTime2.isBefore(dateTime1)) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        Storage.setChickens(response.chickens);

        datesAdapter = new DatesAdapter(dates);
        recyclerView.setAdapter(datesAdapter);
    }
}
