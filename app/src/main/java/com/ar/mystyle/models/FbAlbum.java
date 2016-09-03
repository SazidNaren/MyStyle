package com.ar.mystyle.models;

import java.io.Serializable;

/**
 * Created by SAZID ALI on 20/08/2016.
 */
public class FbAlbum implements Serializable {
    private String albumUrl,albumName;
    private String albumId;
    private int totalCount;

    public String getAlbumUrl() {
        return albumUrl;
    }

    public String getAlbumName() {
        return albumName;
    }
    public int getTotalCount() {
        return totalCount;
    }
    public String getAlbumId() {
        return albumId;
    }

    public FbAlbum(String albumUrl, String albumName, String albumId, int totalCount) {
        this.albumUrl = albumUrl;
        this.albumName = albumName;
        this.albumId = albumId;
        this.totalCount=totalCount;
    }
}
