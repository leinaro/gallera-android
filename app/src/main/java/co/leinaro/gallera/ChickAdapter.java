package co.leinaro.gallera;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ChickAdapter extends RecyclerView.Adapter<ChickAdapter.MyViewHolder> {

    private List<Chick> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewVersion;
        ImageView imageViewIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            this.textViewVersion = (TextView) itemView.findViewById(R.id.textViewVersion);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    public ChickAdapter(List<Chick> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chick_item, parent, false);

        view.setOnClickListener(GalleraListFragment.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewName = holder.textViewName;
        TextView textViewVersion = holder.textViewVersion;
        ImageView imageView = holder.imageViewIcon;

        textViewName.setText(dataSet.get(listPosition).getToken());
        textViewVersion.setText(dataSet.get(listPosition).getCastadorTag());
        Picasso.with(imageView.getContext()).load(
                "https://oggiscienza.files.wordpress.com/2010/01/215310099_5ccec08156.jpg?w=350&h=232"
        ).error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher_round).into(imageView);

//        imageView.setImageResource(dataSet.get(listPosition).getImage());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
