package com.ar.mystyle.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.ar.mystyle.Util.Utility;
import com.ar.mystyle.adapters.FbPhotosAdapter;
import com.ar.mystyle.interfaces.ClickListener;
import com.ar.mystyle.models.FbAlbum;
import com.ar.mystyle.models.FbPhoto;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.style.facechanger.R;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

public class FacebookPhotosActivity extends Activity implements ClickListener{
    private ArrayList<FbPhoto> fbPhotos;
    private RecyclerView rvPhotos;
    private FbPhotosAdapter fbPhotosAdapter;
    private FbAlbum fbAlbum;
    private AdView mAdView;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_photos);
        rvPhotos = (RecyclerView) findViewById(R.id.rv_photos);
        fbAlbum = (FbAlbum) getIntent().getSerializableExtra("data");
        mAdView = (AdView) findViewById(R.id.adView);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        rvPhotos.setLayoutManager(mLayoutManager);
        fbPhotos = new ArrayList<>();
        Bundle bundle=new Bundle();
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);
        bundle.putString("fields", "id,name,images");
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading Photos from Album");
        progressDialog.show();
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/" + fbAlbum.getAlbumId() + "/photos",
                bundle,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        if(progressDialog.isShowing())
                            progressDialog.dismiss();
                        fbPhotos=parseData(response);
                        Log.d("photos", "");
                        fbPhotosAdapter=new FbPhotosAdapter(FacebookPhotosActivity.this,FacebookPhotosActivity.this,fbPhotos);
                        rvPhotos.setAdapter(fbPhotosAdapter);
                    }
                }
        )
        .executeAsync();
    }

    private ArrayList<FbPhoto> parseData(GraphResponse response) {
        String data=response.getRawResponse();
        fbPhotos=new ArrayList<>();
        if(!data.equals(""))
        {
            try {
                JSONObject jsonObject = new JSONObject(data);
                JSONArray jsonElements=jsonObject.getJSONArray("data");
                for (int i = 0; i < jsonElements.length(); i++) {
                  String photoName=jsonElements.getJSONObject(i).getString("id");
                    JSONArray imagesArray=jsonElements.getJSONObject(i).getJSONArray("images");
                    String photoUrlSmall=imagesArray.getJSONObject(2).getString("source");
                    String photoUrlLarge=imagesArray.getJSONObject(1).getString("source");
                    fbPhotos.add(new FbPhoto(photoUrlLarge,photoUrlSmall,photoName,photoName,1));
                }

            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return fbPhotos;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0,R.anim.top_to_bottom);
    }

    @Override
    public void onItemClick(int position, int index) {
       Intent photo = new Intent(this, EditorActivity.class);
        photo.putExtra("fbphoto", fbPhotos.get(position).getPhotoLargeUrl());
        startActivity(photo);
    }

    @Override
    public void onLongItemClick(int position) {

    }
    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        Utility.isNetworkConnected(mAdView,this);
        if (mAdView != null) {
            mAdView.resume();
        }
        super.onResume();
    }


    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
}
