package com.ar.mystyle.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.ar.mystyle.adapters.FbAlbumsAdapter;
import com.ar.mystyle.interfaces.ClickListener;
import com.ar.mystyle.models.FbAlbum;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.entities.Album;
import com.sromku.simple.fb.listeners.OnAlbumsListener;
import com.style.facechanger.R;

import java.util.ArrayList;
import java.util.List;

public class FacebookAlbumsActivity extends Activity implements ClickListener{

    private SimpleFacebook mSimpleFacebook;
    private ArrayList<FbAlbum> fbAlbumses;
    private RecyclerView rvAlbums;
    private FbAlbumsAdapter fbAlbumsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_albums);
        rvAlbums=(RecyclerView)findViewById(R.id.rv_album);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this,2);
        rvAlbums.setLayoutManager(mLayoutManager);
        mSimpleFacebook=SimpleFacebook.getInstance();
        fbAlbumses=new ArrayList<>();
        OnAlbumsListener onAlbumsListener = new OnAlbumsListener() {
            @Override
            public void onComplete(List<Album> albums) {
                for(Album album:albums)
                fbAlbumses.add(new FbAlbum("",album.getName(),album.getId(),2));
                fbAlbumsAdapter =new FbAlbumsAdapter(FacebookAlbumsActivity.this,FacebookAlbumsActivity.this,fbAlbumses);
                rvAlbums.setAdapter(fbAlbumsAdapter);
                Log.d("photos","");
            }
        };
        if(onAlbumsListener!=null)
        mSimpleFacebook.getAlbums(onAlbumsListener);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0,R.anim.top_to_bottom);
    }

    @Override
    public void onItemClick(int position, int index) {
     Intent intent=new Intent(FacebookAlbumsActivity.this,FacebookPhotosActivity.class);
        intent.putExtra("data",fbAlbumses.get(position));
        startActivity(intent);
    }


    @Override
    public void onLongItemClick(int position) {

    }
}
