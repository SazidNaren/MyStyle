package com.ar.mystyle.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ar.mystyle.interfaces.ClickListener;
import com.ar.mystyle.models.FbPhoto;
import com.squareup.picasso.Picasso;
import com.style.facechanger.R;

import java.util.ArrayList;

/**
 * Created by sajid on 18/3/16.
 */
public class FbPhotosAdapter extends RecyclerView.Adapter<FbPhotosAdapter.ViewHolder1>  {
    private ArrayList<FbPhoto> fbphoto;
    private Context context;
    private ClickListener clickListener;

    public FbPhotosAdapter(Context context, ClickListener clickListener, ArrayList<FbPhoto> fbphoto)
    {
        this.fbphoto = fbphoto;
        this.context=context;
        this.clickListener=clickListener;
    }

    @Override
    public FbPhotosAdapter.ViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {
        View v1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_fb_photes, parent, false);
        ViewHolder1 view_holder=new ViewHolder1(v1);
        return view_holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder1 holder, final int position) {
        FbPhoto fbPhoto= fbphoto.get(position);
        holder.tvPhotoName.setText(fbPhoto.getPhotoName());
        Picasso.with(context).load(fbPhoto.getPhotoSmallUrl())
                .into(holder.imgPhoto);
        //holder.tvAlbumCount.setText("AlbumCount "+fbAlbum.getTotalCount());
           holder.imgPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(position,0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return fbphoto.size();
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    class ViewHolder1 extends RecyclerView.ViewHolder{
        ImageView imgPhoto;
        TextView tvPhotoName;
        public ViewHolder1(View itemView) {
            super(itemView);
            imgPhoto = (ImageView) itemView.findViewById(R.id.img_photos);
            tvPhotoName =(TextView)itemView.findViewById(R.id.photo_name);
        //    tvPhotoCount=(TextView)itemView.findViewById(R.id.photo_name);
        }
    }
}
