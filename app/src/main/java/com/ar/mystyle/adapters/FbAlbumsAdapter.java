package com.ar.mystyle.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.ar.mystyle.interfaces.ClickListener;
import com.ar.mystyle.models.FbAlbum;
import com.style.facechanger.R;
import java.util.ArrayList;

/**
 * Created by sajid on 18/3/16.
 */
public class FbAlbumsAdapter extends RecyclerView.Adapter<FbAlbumsAdapter.ViewHolder1>  {
    private ArrayList<FbAlbum> fbalba;
    private Context context;
    private ClickListener clickListener;

    public FbAlbumsAdapter(Context context, ClickListener clickListener, ArrayList<FbAlbum> fbalba)
    {
        this.fbalba = fbalba;
        this.context=context;
        this.clickListener=clickListener;
    }

    @Override
    public FbAlbumsAdapter.ViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {
        View v1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_albums, parent, false);
        ViewHolder1 view_holder=new ViewHolder1(v1);
        return view_holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder1 holder, final int position) {
        FbAlbum fbAlbum= fbalba.get(position);
        holder.tvAlbumName.setText(fbAlbum.getAlbumName());
        //holder.tvAlbumCount.setText("AlbumCount "+fbAlbum.getTotalCount());

           holder.tvAlbumName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(position,0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return fbalba.size();
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    class ViewHolder1 extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView tvAlbumName,tvAlbumCount;
        public ViewHolder1(View itemView) {
            super(itemView);
            //imgPhoto = (ImageView) itemView.findViewById(R.id.img_first);
            tvAlbumName=(TextView)itemView.findViewById(R.id.album_name);
            tvAlbumCount=(TextView)itemView.findViewById(R.id.album_count);
        }
    }
}
