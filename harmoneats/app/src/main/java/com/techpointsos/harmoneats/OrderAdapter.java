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

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private static final String TAG = "OrderAdapter";
    private RecyclerViewClickInterface recyclerViewClickInterface;
    List<HashMap<String,Object>> order;

    public OrderAdapter(List<HashMap<String,Object>> order, RecyclerViewClickInterface recyclerViewClickInterface) {
        this.order = order;
        this.recyclerViewClickInterface = recyclerViewClickInterface;
    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.order_item,parent,false);

        OrderAdapter.ViewHolder viewHolder = new OrderAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemCount.setText("Quantity: "+(Integer) order.get(position).get("count"));
        holder.itemName.setText((String) order.get(position).get("name"));
        holder.price.setText("$"+order.get(position).get("price"));

        String specialRequests = order.get(position).get("requests").toString();
        if(!specialRequests.isEmpty()) {
            holder.requests.setText("Special Requests: "+specialRequests);
        } else {
            holder.requests.setText("Special Requests: None");
        }

    }

    //Should return number of restaurants in search list
    @Override
    public int getItemCount() {
        return order.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView itemName, itemCount, price, requests;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.order_item_name);
            itemCount = itemView.findViewById(R.id.order_item_count);
            price = itemView.findViewById(R.id.order_item_price);
            requests = itemView.findViewById(R.id.order_requests);

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
