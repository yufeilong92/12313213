package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class Main6Activity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnXiangce;
    private ImageView mIvImager;
    private Bitmap selectdBitmap;
    private  int REQUEST_GET_IMAGE = 1;
    private  int MAX_SIZE = 769;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        initView();
    }

    private void initView() {
        mBtnXiangce = (Button) findViewById(R.id.btn_xiangce);
        mIvImager = (ImageView) findViewById(R.id.iv_imager);

        mBtnXiangce.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_xiangce:
             /*   Intent intent = new Intent("android.intent.action.PICK");
                intent.setType("image/*");
                startActivityForResult(intent, 3);*/
                Intent intent = new Intent();
   /*             intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);*/
                if (Build.VERSION.SDK_INT < 19) {
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");

                } else {
                    intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
                }
                startActivityForResult(intent,REQUEST_GET_IMAGE);

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ( resultCode == RESULT_OK && data != null&&requestCode==REQUEST_GET_IMAGE){
            Uri uri = data.getData();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            try{
                InputStream inputStream = getContentResolver().openInputStream(uri);
                BitmapFactory.decodeStream(inputStream,null,options);
                inputStream.close();
                int height = options.outHeight;
                int width = options.outWidth;
                int sampleSize = 1;
                int max = Math.max(height,width);
                if (max>MAX_SIZE){
                    int nw = width/2;
                    int nh = height/2;
                    while ((nw/sampleSize)> MAX_SIZE || (nh / sampleSize)>MAX_SIZE){
                        sampleSize *=2;
                    }
                }
                options.inSampleSize = sampleSize;
                options.inJustDecodeBounds = false;
                selectdBitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri),null,options);
                mIvImager.setImageBitmap(selectdBitmap);
            }catch (IOException ioe){
                Log.i("====", ioe.getMessage());
            }

        }
    }
}
