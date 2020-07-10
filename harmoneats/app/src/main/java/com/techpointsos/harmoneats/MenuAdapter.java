package com.techpointsos.harmoneats;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    private static final String TAG = "MenuAdapter";
    private RecyclerViewClickInterface recyclerViewClickInterface;
    List<HashMap<String,String>> menu;

    public MenuAdapter(List<HashMap<String,String>> menu, RecyclerViewClickInterface recyclerViewClickInterface) {
        this.menu = menu;
        this.recyclerViewClickInterface = recyclerViewClickInterface;
    }

    @NonNull
    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.menu_item,parent,false);

        MenuAdapter.ViewHolder viewHolder = new MenuAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.briefDesc.setText(menu.get(position).get("description"));
        holder.restaurantName.setText(menu.get(position).get("item"));
    }

    //Should return number of restaurants in search list
    @Override
    public int getItemCount() {
        return menu.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView restaurantName, briefDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            restaurantName = itemView.findViewById(R.id.restaurantName);
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
