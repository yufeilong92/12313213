package com.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.QuickContactBadge;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.List;

public class Main5Activity extends AppCompatActivity {

    private int index = 0;
    private List<Integer> mImagerList;
    private TextSwitcher tvswt;
    private ImageSwitcher ivSwitcher;
    private Button btnShowDialog;
    private Context mContext;
    private Button gps;
    private TextView mTvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        initView();
        initData();
    }

    private void initData() {
        btnShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 50; i++) {
                    list.add("字符串" + i);
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_showdialog, null);
                RecyclerView rlv = V.f(view, R.id.rlv);
                builder.setView(view);
                builder.create();
                AlertDialog show = builder.show();
                GridLayoutManager manager = new GridLayoutManager(mContext, 1);
                manager.setOrientation(GridLayoutManager.VERTICAL);
                rlv.setLayoutManager(manager);
                RlvAdapter adapter = new RlvAdapter(mContext, list);
                rlv.setAdapter(adapter);
            }
        });

    }

    private void initView() {
        mContext = this;
        btnShowDialog = V.f(this, R.id.btn_showDialog);
        Button back = V.f(this, R.id.btn_back);
        Button next = V.f(this, R.id.btn_next);
        Button gps = V.f(this, R.id.btn_gps);
        mTvContent = V.f(this, R.id.tv_content);
        QuickContactBadge qubage = V.f(this, R.id.qucbade);
//        qubage.assignContactFromEmail("18317837561",true);
        qubage.assignContactFromPhone("18317837561", true);
        tvswt = V.f(this, R.id.tv_sw);
        ivSwitcher = V.f(this, R.id.img_swith);
        setOnclickListener(back, 0);
        setOnclickListener(next, 1);
        tvswt.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                return new TextView(Main5Activity.this);
            }
        });
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("循环说据" + i);
        }
        TextSwitCherAnimation switCherAnimation = new TextSwitCherAnimation(tvswt, list);
        switCherAnimation.setDelayTime(500);
        switCherAnimation.create();

        mImagerList = new ArrayList<>();
        mImagerList.add(R.mipmap.a);
        mImagerList.add(R.mipmap.b);
        ivSwitcher.setInAnimation(AnimationUtils.loadAnimation(Main5Activity.this, android.R.anim.fade_in));
        ivSwitcher.setOutAnimation(AnimationUtils.loadAnimation(Main5Activity.this, android.R.anim.fade_out));
        ivSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(Main5Activity.this);
                imageView.setMinimumHeight(100);
                imageView.setMinimumWidth(100);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT));

                return imageView;
            }
        });
        ivSwitcher.setImageResource(mImagerList.get(0));
        gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLoaderGPS();
            }
        });
    }

    private void getLoaderGPS() {
        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        List<String> allProviders = lm.getAllProviders();
        if (allProviders == null || allProviders.isEmpty()) return;
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < allProviders.size(); i++) {
            buffer.append(allProviders.get(i));
            buffer.append(",");
        }
        mTvContent.setText(buffer.toString());
        Criteria criteria = new Criteria();
        criteria.setCostAllowed(false);
        criteria.setAltitudeRequired(true);
        criteria.setBearingRequired(true);
        List<String> providers = lm.getProviders(criteria, true);
        buffer.append("\n");
        buffer.append("=============");
        for (int i = 0; i < providers.size(); i++) {
            buffer.append(providers.get(i));
            buffer.append(",");
        }
        mTvContent.setText(buffer.toString());
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 8, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });
    }


    public void setOnclickListener(Button button, final int i) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (i) {
                    case 0:
                        --index;
                        if (index <= 0) {
                            index = 0;
                            ivSwitcher.setImageResource(mImagerList.get(0));
                        } else {
                            ivSwitcher.setImageResource(mImagerList.get(index));
                        }
                        break;
                    case 1:
                        ++index;
                        if (index >= mImagerList.size()) {
                            index = mImagerList.size();
                            ivSwitcher.setImageResource(mImagerList.get(mImagerList.size() - 1));
                        } else {
                            ivSwitcher.setImageResource(mImagerList.get(index));
                        }
                        break;
                    default:

                }
            }
        });
    }
}
