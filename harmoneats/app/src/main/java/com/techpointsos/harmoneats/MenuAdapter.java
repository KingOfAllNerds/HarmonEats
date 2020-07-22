package com.techpointsos.harmoneats;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.ar.sceneform.assets.RenderableSource;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    private static final String TAG = "MenuAdapter";
    private RecyclerViewClickInterface recyclerViewClickInterface;
    List<HashMap<String,String>> menu;
    private Button arButton;
    private StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();

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
        holder.priceTag.setText("$"+menu.get(position).get("price"));
    }

    //Should return number of restaurants in search list
    @Override
    public int getItemCount() {
        return menu.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView restaurantName, briefDesc, priceTag;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            restaurantName = itemView.findViewById(R.id.itemName);
            briefDesc = itemView.findViewById(R.id.briefDesc);
            priceTag = itemView.findViewById(R.id.priceTag);
            arButton = itemView.findViewById(R.id.arButton);

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

            arButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        public void buildModel(File f){
            RenderableSource renderableSource = RenderableSource.builder().setSource(this, Uri.parse(f.getPath()) ,RenderableSource.SourceType.GLB));
        }
    }
}
