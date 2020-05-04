package com.ncnendoroid.accessappexercise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Table> tableList = new ArrayList<>();
    private final Context context;
    private Listener listener;


    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_main,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return  viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Table table = tableList.get(position);
        holder.info_name.setText(table.getLogin());
        if (table.isSite_admin()){
            holder.info_staff.setVisibility(View.VISIBLE);
        }else {
            holder.info_staff.setVisibility(View.GONE);
        }
        Picasso.get().load(table.getAvatar_url()).transform(new CircleTransform()).into(holder.info_image);
        holder.info_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null){
                    listener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return tableList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView info_name,info_staff;
        public ImageView info_image;
        public RelativeLayout info_layout;
        public ViewHolder(View itemView) {
            super(itemView);
            info_name = itemView.findViewById(R.id.info_name);
            info_staff = itemView.findViewById(R.id.info_staff);
            info_image = itemView.findViewById(R.id.info_image);
            info_layout = itemView.findViewById(R.id.info_layout);
        }
    }

    public MyAdapter(Context context){
        this.context = context;
    }

    public void tableList(List<Table> tableList){
        this.tableList.addAll(tableList);
        notifyDataSetChanged();
    }

    public interface Listener{
        void onClick(int position);
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }

}
