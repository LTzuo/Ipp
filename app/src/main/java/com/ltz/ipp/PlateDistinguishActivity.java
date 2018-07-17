package com.ltz.ipp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fosung.libeasypr.view.EasyPRPreSurfaceView;
import com.fosung.libeasypr.view.EasyPRPreView;

/**
 * 车牌识别
 */
public class PlateDistinguishActivity extends AppCompatActivity {

    private EasyPRPreView easyPRPreView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plate_distinguish);
        initData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (easyPRPreView != null) {
            easyPRPreView.onStart();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (easyPRPreView != null) {
            easyPRPreView.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (easyPRPreView != null) {
            easyPRPreView.onDestroy();
        }
    }

    private void initData() {
        easyPRPreView = (EasyPRPreView) findViewById(R.id.preSurfaceView);
        final Button btnCamera = (Button) findViewById(R.id.btnShutter);
        final TextView tvRecogResult = (TextView) findViewById(R.id.lblRecogResult);
        final ImageView iv = (ImageView) findViewById(R.id.iv);

        easyPRPreView
                .setRecognizedListener(new EasyPRPreSurfaceView.OnRecognizedListener() {
                    @Override
                    public void onRecognized(String text) {
                        if (text == null || text.equals("0")) {
                            Toast.makeText(PlateDistinguishActivity.this, "换个姿势试试", Toast.LENGTH_SHORT)
                                    .show();
                        } else {
                            tvRecogResult.setText(text);
                        }
                    }
                })
                .setPictureTakenListener(new EasyPRPreSurfaceView.OnPictureTakenListener() {
                    @Override
                    public void onPictureTaken(String[] files) {
                        //                              Bitmap map = BitmapUtil.decodeBitmap(path);
                        //                              iv.setImageBitmap(map);
                    }
                });

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                easyPRPreView.recognize();
            }
        });
    }

}
