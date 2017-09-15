package co.leinaro.gallera;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import co.leinaro.gallera.entities.SearchDates;

public class DatesAdapter extends RecyclerView.Adapter<DatesAdapter.ViewHolder> {

    private List<SearchDates> dataSet;

    public DatesAdapter(List<SearchDates> data) {
        this.dataSet = data;
    }

    @Override
    public DatesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.date_item, parent, false);
        DatesAdapter.ViewHolder viewHolder = new DatesAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final DatesAdapter.ViewHolder holder, final int listPosition) {
        holder.searchDate = dataSet.get(listPosition);
        holder.textViewName.setText(dataSet.get(listPosition).getGroupDate());
        holder.textViewCount.setText(dataSet.get(listPosition).getCount() + "");

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, ChickListActivity.class);
                intent.putExtra(ChickListActivity.SEARCH_DATE, holder.searchDate);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewCount;
        View mView;
        SearchDates searchDate;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mView = itemView.findViewById(R.id.date_view);
            this.textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            this.textViewCount = (TextView) itemView.findViewById(R.id.textViewCount);
        }
    }
}
