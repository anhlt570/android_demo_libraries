package demo.com.demolibraries.main;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import demo.com.demolibraries.R;

public class MenuAdapter extends RecyclerView.Adapter<MainViewHolder> {
    private List<MenuEntity> data;
    private OnItemClickListener onItemClickListener;

    public MenuAdapter(@Nullable List<MenuEntity> menuEntities, OnItemClickListener onItemClickListener) {
        if (menuEntities == null) {
            data = new ArrayList<>();
        } else data = menuEntities;

        this.onItemClickListener = onItemClickListener;
    }


    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false);
        return new MainViewHolder(v, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.bindView(data.get(position));
        holder.showDivder(position < getItemCount() - 1);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
