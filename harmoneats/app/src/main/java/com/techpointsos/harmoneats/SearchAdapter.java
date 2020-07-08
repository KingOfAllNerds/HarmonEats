package com.techpointsos.harmoneats;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private static final String TAG = "SearchAdapter";
    List<HashMap<String,Object>> restaurantList;

    public SearchAdapter(List<HashMap<String,Object>> restaurantList) {
        this.restaurantList = restaurantList;
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

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView restaurantName, briefDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            restaurantName = itemView.findViewById(R.id.restaurantName);
            briefDesc = itemView.findViewById(R.id.briefDesc);

            itemView.setOnClickListener(this);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    restaurantList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    return true;
                }
            });
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(),(String) restaurantList.get(getAdapterPosition()).get("name"),Toast.LENGTH_SHORT).show();
        }
    }
}
