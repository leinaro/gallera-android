package co.leinaro.gallera;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import co.leinaro.gallera.entities.Chick;

public class ChickAdapter extends RecyclerView.Adapter<ChickAdapter.MyViewHolder> {

    public static final int HEADER = 0;
    public static final int CHILD = 1;


    private List<Chick> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        View mView;
        Chick chick;
        TextView textViewName;
        TextView textViewVersion;
        TextView textViewVersion2;
        ImageView imageViewIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.mView = itemView.findViewById(R.id.chick_view);
            this.textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            this.textViewVersion = (TextView) itemView.findViewById(R.id.textViewVersion);
            this.textViewVersion2 = (TextView) itemView.findViewById(R.id.textViewVersion2);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    public ChickAdapter(List<Chick> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chick_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        holder.chick = dataSet.get(listPosition);
        holder.textViewName.setText(dataSet.get(listPosition).getColiseo_plate_number());
        holder.textViewVersion.setText(dataSet.get(listPosition).getBreeder_plate_number());
        holder.textViewVersion2.setText(dataSet.get(listPosition).getBreeder_name());
        Picasso.with(holder.imageViewIcon.getContext()).load(
                "http://192.168.0.136:8000/api/gallera/v1" + dataSet.get(listPosition).getImage_url()
        ).error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher_round).into(holder.imageViewIcon);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Context context = v.getContext();
//                Intent intent = new Intent(context, ChickListActivity.class);
//                intent.putExtra(ChickListActivity.SEARCH_DATE, holder.searchDate);
//                context.startActivity(intent);
            }
        });

//        imageView.setImageResource(dataSet.get(listPosition).getImage());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
