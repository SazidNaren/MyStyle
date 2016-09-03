package com.ar.mystyle.models;

import java.io.Serializable;

/**
 * Created by SAZID ALI on 20/08/2016.
 */
public class FbPhoto implements Serializable {
    private String photoUrlSmall,photoUrlLarge, photoName;
    private String photoId;

    public String getPhotoLargeUrl() {
        return photoUrlLarge;
    }
    public String getPhotoSmallUrl() {
        return photoUrlSmall;
    }

    public String getPhotoName() {
        return photoName;
    }
    public String getPhotoId() {
        return photoId;
    }

    public FbPhoto(String photoUrlLarge,String photoUrlSmall, String photoName, String photoId, int totalCount) {
        this.photoUrlLarge = photoUrlLarge;
        this.photoUrlSmall=photoUrlSmall;
        this.photoName = photoName;
        this.photoId = photoId;
    }
}
