package com.gura.drawpanel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static final int ALLOW=0;
    DrawPanel panel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        /*
            1. layout 안의 다른 객체와 헷갈리지 않도록 drawpanel에 id부여
            2. layout이 전개 되면서 drawpanel과 LinearLayout이 그려지도록 setContentView()메소드 호출시 R.layout.activity_main layout의 참조값을 보낸다.
            3. layout이 전개 되면서 객체가 간접생성되었으므로 원하는 객체를 사용할때는 findViewById() 메소드를 사용하면된다.
         */

        setContentView(R.layout.activity_main);
        //그림판의 참조값
        panel=findViewById(R.id.drawPanel);
    }

    //저장하기 버튼을 눌렀을때 외부 저장 장치(SDcard)에 저장하기
    public void save(View v){
        /*
            휴대폰에는 drive가 없다. 대신 SDcard가 drive의 역할을 한다.

            SD 카드에 저장하기 위해서는 사용자의 허가를 받아야한다.
            사용자의 허가를 받기 위해서는 아래의 몇 가지 사항 고려해야한다.

            1. 사용자의 허가를 받기위해서는 Manifest.xml 문서에서 명시 해놓아야한다.
            2. 사용자가 접근 허가를 했는지 확인하고(퍼미션 체크),
            3. 접근 허가를 하지 않았다면 설정할 수 있도록 다이얼로그 창을 띄워준다.(권한을 얻어내도록 유도(운영체제 에게 맡긴다))
            4. 설정 창에서 외부 저장 장치에 접근 불허를 선택하면 실행할 동작을 설정한다.(퍼미션 결과를 받아낸다.)
         */

        //2.
        int permissionCheck= ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        //2.
        if(permissionCheck != PackageManager.PERMISSION_GRANTED){
            /*
                3.
                권한을 얻어내도록 유도한다.(권한이 여러개일 수도 있으므로 String[]에 저장한다.)
             */
            String[] permissions={Manifest.permission.WRITE_EXTERNAL_STORAGE};
            //운영체제가 팝업창을 띄울 수 있도록 메소드 호출
            ActivityCompat.requestPermissions(this,
                    permissions, //요청 퍼미션의 목록
                    ALLOW); //요청의 식별값

        }else {//권한이 있다면
            //파일에 저장한다.
            saveToFile();
        }

    } //save()

    //퍼미션 다이얼로그를 띄운 다음 결과 값을 받을 메소드를 오버라이드 한다.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //요청 코드에 따라 분기한다.
        switch (requestCode){
            case ALLOW:
                if(grantResults[ALLOW]==PackageManager.PERMISSION_GRANTED){
                    //파일에 저장한다.
                    saveToFile();
                }else { //4. Allow 하지 않았을 경우
                    Toast.makeText(this, "파일에 저장하기 싫구나", Toast.LENGTH_LONG).show();
                }

                break;

        }//switch

    }//onRequestPermissionsResult()

    //그림 정보를 저장할 메소드
    public void saveToFile(){
        /*
            java에는 Object를 저장하고 복구 시켜줄 수 있는 기능이 있다.
            (ObjectInputStream, ObjectOutPutStream 객체 사용)

            FilInputStream과 FilOutPutStream을 ObjectInputStream, ObjectOutPutStream으로 포장해서 사용한다.
            1. Point 객체를 파일에 저장하고 읽어들이기 위해 Serializable을 구현해준다.
            2. List 객체는 이미 Serializable 되어 있어서 처리 안 해줘도됨
         */
        //파일에 저장할 객체의 참조값 얻어오기
        List<Point> pointList=panel.getPointList();
        //위부 저장 장치의 절대경로(기기 마다 어디가 절대 경로인지 알 수 없으므로 getAbsolutePath()메소드로 절대경로를 얻어온다.)
        String path=getExternalFilesDir(null).getAbsolutePath();

        File file=new File(path+"/data.dat");

        FileOutputStream fos=null;
        ObjectOutputStream oos=null;
        try{
            if(!file.exists()){
                file.createNewFile();
            }
            //파일에 출력할 객체
            fos=new FileOutputStream(file);
            //객체를 파일에 출력할 수 있는 객체
            oos=new ObjectOutputStream(fos);
            oos.writeObject(pointList);
            Toast.makeText(this, "파일 저장 성공", Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Log.e("MainActivity", e.getMessage());
        }finally {
            try{
                if(fos!=null)fos.close();
                if(oos!=null)oos.close();
            }catch (Exception e){}

        }
    } //saveToFile()

    //불러오기 버튼을 눌렀을때 호출되는 메소드
    public void load(View v){
        //외부 저장장치의 절대 경로
        String path=getExternalFilesDir(null).getAbsolutePath();
        File file=new File(path+"/data.dat");
        if(!file.exists()){//파일이 존재하지 않으면
             return; //여기서 메소드를 끝낸다
        }

        FileInputStream fis=null;
        ObjectInputStream ois=null;
        try{
            fis=new FileInputStream(file);
            ois=new ObjectInputStream(fis);
            //읽어온 Object를 원래 type인 List로 casting 하기
            List<Point> pointList=(List)ois.readObject();
            //DrawPanel View 에 전달해서 화면이 다시 그려지도록 한다.
            panel.setPointList(pointList);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(ois!=null)ois.close();
                if(fis!=null)fis.close();
            }catch (Exception e){}
        }
    }//load()
}
