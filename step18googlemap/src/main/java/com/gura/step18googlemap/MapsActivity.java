package com.gura.step18googlemap;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //layout안에 button과 fragment가 있다.
        //setContentView()메소드에의해 layout이 전개되면 findViewById()메소드를 객체의 참조값을 찾아서 쓸 수 있다.
        setContentView(R.layout.activity_main);
        //activity_main.xml에 있는 SupportMapFragment 객체의 참조값 얻어내기
        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        //지도가 동작할 준비가 완료됨을 감사힐 리스너 객체 등록하기
        mapFragment.getMapAsync(this); //OnMapReadyCallback type을 인자로 보내준다.
        /*
             fragment는 activity에의해 관리되는 객체.
             fragment는 따로 UI를 가질 수 있다.
             fragment 자체는 view가 아니다. 따라서 findViewById() 메소드를 이용해서 참조값을 얻어낼 수 없다.
             따라서 FragmentManger 객체를 이용해서 findFragmentById() 메소드를 이용해서 map 객체의 참조값을 찾아야한다.

         */

        Button moveBtn=findViewById(R.id.moveBtn);
        moveBtn.setOnClickListener(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //에이콘 아카데미의 위도 경도 정보를 가지고 있는 Lating 객체
        LatLng  citiyHall= new LatLng(37.5662952,126.9604356); // 서울시청 : 37.5662952,126.9604356
        //마커 옵션 객체
        MarkerOptions options=new MarkerOptions();
        options.position(citiyHall); //마커의 위치
        options.title("Acorn Academy"); //마커의 제목
        //지도상에 마커 표시하기
        mMap.addMarker(options);

        //지정한 위치와 배율로 카메라 이동하기
        CameraUpdate cu=CameraUpdateFactory.newLatLngZoom(citiyHall,15);
        mMap.animateCamera(cu); //animateCamera() 메소드 : 카메라가 이동하는 중간과정을 보여준다.
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.moveBtn:
                //에이콘 아카데미 : 37.498893, 127.031561
                LatLng acorn = new LatLng(37.498893, 127.031561);
                //마커 옵션 객체
                MarkerOptions options=new MarkerOptions();
                options.position(acorn); //마커의 위치
                options.title("Acorn Academy"); //마커의 제목
                //지도상에 마커 표시하기
                mMap.addMarker(options);
                //지정한 위치와 배율로 카메라 이동하기
                CameraUpdate cu=CameraUpdateFactory.newLatLngZoom(acorn,15);
                mMap.animateCamera(cu);
                break;
        }
    }
}
