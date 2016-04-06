package com.ar.mystyle.adapters;

import android.content.Context;
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

/**
 * Created by sajid on 18/3/16.
 */
public class ShowImageAdapter extends RecyclerView.Adapter<ShowImageAdapter.ViewHolder1>  {
    private ArrayList<String> images;
    private Context context;
    private ClickListener clickListener;

    public ShowImageAdapter(ArrayList<String> images,Context context,ClickListener clickListener)
    {
        this.images=images;
        this.context=context;
        this.clickListener=clickListener;
    }

    @Override
    public ShowImageAdapter.ViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {
        View v1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_image_first, parent, false);
        ViewHolder1 view_holder=new ViewHolder1(v1);
        return view_holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder1 holder, final int position) {
        if(position%3==0)
            holder.imageView.getLayoutParams().height= Utility.dpToPx(300,context);
        Uri uri = Uri.fromFile(new File(images.get(position)));
        Picasso.with(context).load(uri)
                .into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(position,0);
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
