package com.ar.mystyle.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.ar.mystyle.interfaces.ClickListener;
import com.style.facechanger.R;
import java.util.List;

/**
 * Created by sajid on 18/3/16.
 */
public class SelectImageAdapterVert extends RecyclerView.Adapter<SelectImageAdapterVert.ViewHolder1>  {
    private List<Drawable> images;
    private ClickListener clickListener;
    static SelectImageAdapterVert selectImageAdapterVert;
    private int type;

    public SelectImageAdapterVert(int type, List<Drawable> images, Context context, ClickListener clickListener)
    {
        this.images=images;
        this.clickListener=clickListener;
        this.type=type;
        selectImageAdapterVert=this;
    }

    @Override
    public SelectImageAdapterVert.ViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {
        View v1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_show_selection_icons, parent, false);
        ViewHolder1 view_holder=new ViewHolder1(v1);
        return view_holder;

    }

    @Override
    public void onBindViewHolder(final ViewHolder1 holder, final int position) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                holder.imageView.setImageDrawable(images.get(position));
            }
        });
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(type,position);
            }
        });
        holder.imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                clickListener.onLongItemClick(position);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.size();
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    class ViewHolder1 extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public ViewHolder1(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.img_first);
        }
    }
}
