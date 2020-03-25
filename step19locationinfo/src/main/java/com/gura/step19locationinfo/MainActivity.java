package com.gura.step19locationinfo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {
    //위치정보를 얻어낼 수 있는 클라이언트 객체
    private FusedLocationProviderClient client;
    private GoogleMap mMap;
    private double latitude;
    private double longitude;
    private SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //클라이언트의 참조값 얻어내서 필드에 저장하기
        client = LocationServices.getFusedLocationProviderClient(this);
        mapFragment=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        int permissionCheck=
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION);
        if(permissionCheck != PackageManager.PERMISSION_GRANTED){ //권한이 없다면
            //권한을 얻어야하는 퍼미션 목록
            String[] permissions={Manifest.permission.ACCESS_FINE_LOCATION};
            //권한을 얻어내도록 유도한다.
            ActivityCompat.requestPermissions(this, permissions,0);
        }else{//권한이 있다면
            //getLastLocation();
            mapFragment.getMapAsync(this);
        }

        mapFragment.getMapAsync(this);
        Button button=findViewById(R.id.getLoctionBtn);
        button.setOnClickListener(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 0:
                //퍼미션을 Allow 했을경우
                if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    //getLastLocation();
                    mapFragment.getMapAsync(this);
                }else{// Allow 하지 않았을 경우
                    Toast.makeText(this, "퍼미션을 체크해 주세요", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    //가장 최근의 위치 정보를 얻어내는 메소드
    public void getLastLocation(){
        //클라이언트 객체를 이용해서 얻어낸다.
        client.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() { //비동기 식으로 작업이 진행된다.
            //성공했을때 호출되는 메소드
            @Override
            public void onSuccess(Location location) {
                // Got last known location. In some rare situations this can be null.
                if (location != null) {
                    //위도
                    latitude=location.getLatitude();
                    //경도
                    longitude=location.getLongitude();

                    TextView textView=findViewById(R.id.textView);
                    String info="latitude : "+latitude+" | longitude : "+longitude;
                    textView.setText(info);

                    //에이콘 아카데미 : 37.498893, 127.031561
                    LatLng acorn = new LatLng(latitude, longitude);
                    //마커 옵션 객체
                    MarkerOptions options=new MarkerOptions();
                    options.position(acorn); //마커의 위치
                    options.title("Acorn Academy"); //마커의 제목
                    //지도상에 마커 표시하기
                    mMap.addMarker(options);
                    //지정한 위치와 배율로 카메라 이동하기
                    CameraUpdate cu=CameraUpdateFactory.newLatLngZoom(acorn,15);
                    mMap.animateCamera(cu);

                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public void onClick(View v) {
        getLastLocation();
    }
}
