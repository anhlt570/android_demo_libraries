package demo.com.demolibraries.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import demo.com.demolibraries.R;

public class MainViewHolder extends RecyclerView.ViewHolder {
    private OnItemClickListener onItemClickListener;
    private MenuEntity entity;

    private ImageView imgIcon;
    private TextView tvTitle, tvDescription;

    public MainViewHolder(View itemView, OnItemClickListener onItemClick) {
        super(itemView);
        imgIcon = itemView.findViewById(R.id.img_icon);
        tvTitle = itemView.findViewById(R.id.tv_title);
        tvDescription = itemView.findViewById(R.id.tv_description);
        this.onItemClickListener = onItemClick;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(entity);
            }
        });
    }

    public void bindView(MenuEntity entity) {
        this.entity = entity;
        if (entity.getIconId() >= 0) {
            imgIcon.setImageResource(entity.getIconId());
        }
        tvTitle.setText(entity.getTitle());
        tvDescription.setText(entity.getDescription());
    }

    public void showDivder(boolean isShow) {
        if (isShow) {
            itemView.findViewById(R.id.divider).setVisibility(View.VISIBLE);
        } else {
            itemView.findViewById(R.id.divider).setVisibility(View.INVISIBLE);
        }
    }
}
