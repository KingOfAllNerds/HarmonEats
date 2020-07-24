package com.techpointsos.harmoneats;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private static final String TAG = "SearchAdapter";
    private RecyclerViewClickInterface recyclerViewClickInterface;
    List<HashMap<String,Object>> restaurantList;

    public SearchAdapter(List<HashMap<String,Object>> restaurantList, RecyclerViewClickInterface recyclerViewClickInterface) {
        this.restaurantList = restaurantList;
        this.recyclerViewClickInterface = recyclerViewClickInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.search_item,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.briefDesc.setText((String) restaurantList.get(position).get("description"));
        holder.restaurantName.setText((String) restaurantList.get(position).get("name"));
//        holder.imageView.setImageIcon((Icon) restaurantList.get(position).get("icon"));
    }

    //Should return number of restaurants in search list
    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public void filterList(List<HashMap<String, Object>> filteredList) {
        restaurantList = filteredList;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView restaurantName, briefDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            restaurantName = itemView.findViewById(R.id.itemName);
            briefDesc = itemView.findViewById(R.id.briefDesc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerViewClickInterface.onItemClick(getAdapterPosition());
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    recyclerViewClickInterface.onLongItemClick(getAdapterPosition());
                    return true;
                }
            });
        }

    }
}
