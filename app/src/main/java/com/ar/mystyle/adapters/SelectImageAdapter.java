package com.ar.mystyle.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ar.mystyle.Util.Utility;
import com.ar.mystyle.interfaces.ClickListener;
import com.mystyle.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sajid on 18/3/16.
 */
public class SelectImageAdapter extends RecyclerView.Adapter<SelectImageAdapter.ViewHolder1>  {
    private List<Drawable> images;
    private Context context;
    private ClickListener clickListener;
    private int type;

    public SelectImageAdapter(int type,List<Drawable> images, Context context, ClickListener clickListener)
    {
        this.images=images;
        this.context=context;
        this.clickListener=clickListener;
        this.type=type;
    }

    @Override
    public SelectImageAdapter.ViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {
        View v1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_select_image_first, parent, false);
        ViewHolder1 view_holder=new ViewHolder1(v1);
        return view_holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder1 holder, final int position) {
       /* if(position%3==0)
            holder.imageView.getLayoutParams().height= Utility.dpToPx(300,context);*/
        /*Uri uri = Uri.fromFile(new File(images.get(position)));
        Picasso.with(context).load(uri)
                .into(holder.imageView);*/
        holder.imageView.setImageDrawable(images.get(position));
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(position);
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
