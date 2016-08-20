/*
package com.ar.mystyle.activities;

*/
/**
 * Created by SAZID ALI on 17/04/2016.
 *//*

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mystyle.R;
import com.naver.android.helloyako.imagecrop.view.ImageCropView;
import com.naver.android.helloyako.imagecrop.util.BitmapLoadUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class CropActivity extends Activity {
    public static final String TAG = "CropActivity";
    private Bitmap bitmap;
    private ImageCropView imageCropView;
    private float[] positionInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_crop_and_zoomer);
        imageCropView = (ImageCropView) findViewById(R.id.image);

        if (getIntent().hasExtra("data")) {
            bitmap = (Bitmap) getIntent().getExtras().get("data");
            imageCropView.setImageBitmap(bitmap);
        }
        else if (getIntent().hasExtra("path")) {
          //  bitmap = decodeSampledBitmapFromResource(getResources(),
            //        getIntent().getStringExtra("path"), dWidth/2, dHeight/2);
            imageCropView.setImageFilePath(getIntent().getStringExtra("path"));
        }
      //  Intent i = getIntent();
        //Uri uri = i.getData();

//        DisplayMetrics metrics = getResources().getDisplayMetrics();
//        int imageWidth = (int) ( (float) metrics.widthPixels / 1.5 );
//        int imageHeight = (int) ( (float) metrics.heightPixels / 1.5 );
//
//        bitmap = BitmapLoadUtils.decode(uri.toString(), imageWidth, imageHeight);
//
//        imageCropView.setImageBitmap(bitmap);
    //    imageCropView.setImageFilePath(uri.toString());
        imageCropView.setAspectRatio(1,1);
        findViewById(R.id.ratio11btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "click 1 : 1");
                if(isPossibleCrop(1,1)){
                    imageCropView.setAspectRatio(1, 1);
                } else {
                    Toast.makeText(CropActivity.this,R.string.can_not_crop,Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.ratio34btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "click 3 : 4");
                if(isPossibleCrop(3,4)){
                    imageCropView.setAspectRatio(3, 4);
                } else {
                    Toast.makeText(CropActivity.this,R.string.can_not_crop,Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.ratio43btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "click 4 : 3");
                if(isPossibleCrop(4,3)){
                    imageCropView.setAspectRatio(4, 3);
                } else {
                    Toast.makeText(CropActivity.this,R.string.can_not_crop,Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.ratio169btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "click 16 : 9");
                if(isPossibleCrop(16,9)){
                    imageCropView.setAspectRatio(16, 9);
                } else {
                    Toast.makeText(CropActivity.this,R.string.can_not_crop,Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.ratio916btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "click 9 : 16");
                if(isPossibleCrop(9,16)){
                    imageCropView.setAspectRatio(9, 16);
                } else {
                    Toast.makeText(CropActivity.this,R.string.can_not_crop,Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.crop_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!imageCropView.isChangingScale()) {
                    Bitmap b = imageCropView.getCroppedImage();
                    bitmapConvertToFile(b);
                }
            }
        });
    }

    private boolean isPossibleCrop(int widthRatio, int heightRatio){
        int bitmapWidth = imageCropView.getViewBitmap().getWidth();
        int bitmapHeight = imageCropView.getViewBitmap().getHeight();
        if(bitmapWidth < widthRatio && bitmapHeight < heightRatio){
            return false;
        } else {
            return true;
        }
    }

    public File bitmapConvertToFile(Bitmap bitmap) {
        FileOutputStream fileOutputStream = null;
        File bitmapFile = null;
        try {
            File file = new File(Environment.getExternalStoragePublicDirectory("image_crop_sample"),"");
            if (!file.exists()) {
                file.mkdir();
            }

            bitmapFile = new File(file, "IMG_" + (new SimpleDateFormat("yyyyMMddHHmmss")).format(Calendar.getInstance().getTime()) + ".jpg");
            fileOutputStream = new FileOutputStream(bitmapFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            MediaScannerConnection.scanFile(this, new String[]{bitmapFile.getAbsolutePath()}, null, new MediaScannerConnection.MediaScannerConnectionClient() {
                @Override
                public void onMediaScannerConnected() {

                }

                @Override
                public void onScanCompleted(String path, Uri uri) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(CropActivity.this,"file saved",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (Exception e) {
                }
            }
        }

        return bitmapFile;
    }

    public void onClickSaveButton(View v) {
        positionInfo = imageCropView.getPositionInfo();
        View restoreButton = findViewById(R.id.restore_btn);
        if (!restoreButton.isEnabled()) {
            restoreButton.setEnabled(true);
        }
    }

    public void onClickRestoreButton(View v) {
        imageCropView.applyPositionInfo(positionInfo);
    }
    public static Bitmap decodeSampledBitmapFromResource(Resources res,
                                                         String respath, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(respath, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth,
                reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(respath, options);
    }
    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
*/
