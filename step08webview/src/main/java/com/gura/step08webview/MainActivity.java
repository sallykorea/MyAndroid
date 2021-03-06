package com.gura.step08webview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //WebView 의 참조값 얻어오기
        WebView webView=findViewById(R.id.webView);
        //WebSettings 객체
        WebSettings ws=webView.getSettings();
        ws.setJavaScriptEnabled(true); //javascript 사용을 위해

        //필요한 객체 생성해서 넣어주기
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new MyWebViewClient());
        //url 로딩 시키기
        webView.loadUrl("http://192.168.0.35:8811/exhibition/");
    }

    //클래스 정의
    public class MyWebViewClient extends WebChromeClient {
        // For Android Version < 3.0
        public void openFileChooser(ValueCallback<Uri> uploadMsg) {
            //System.out.println("WebViewActivity OS Version : " + Build.VERSION.SDK_INT + "\t openFC(VCU), n=1");
            mUploadMessage = uploadMsg;
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType(TYPE_IMAGE);
            startActivityForResult(intent, INPUT_FILE_REQUEST_CODE);
        }

        // For 3.0 <= Android Version < 4.1
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {

            openFileChooser(uploadMsg, acceptType, "");
        }

        // For Android 4.1+

        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {

            openFileChooser(uploadMsg, acceptType);
        }

        // For Android 5.0+
        //WebView에서 <input type="file">을 선택했을때 android에서 저장된 파일을 띄워주는 역할을 한다.
        @Override
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> uploadFile, WebChromeClient.FileChooserParams fileChooserParams) {


            if(mFilePathCallback !=null) {
                mFilePathCallback.onReceiveValue(null);
                mFilePathCallback = null;
            }

            /*
                운영체제 에게 Intent 객체를 던져서 다른 application에 있는 Activity도 불러오도록한다.
             */
            mFilePathCallback = uploadFile;
            Intent i =new Intent(Intent.ACTION_GET_CONTENT); //Intent 객체 생성
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("image/*"); //어떤 type을 갖고 올지

            //운영체제 에게 Intent 객체를 던져서 다른 application에 있는 Activity도 불러오도록한다.
            //application이 여러개인 경우, 선택할 수 있도록 한다.
            startActivityForResult(Intent.createChooser(i, "File Chooser"), INPUT_FILE_REQUEST_CODE);
            //동작을 다하면 startActivityForResult(intent, requestCode) 메소드를 호출한다.

            return true;

        }


        private void imageChooser() {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(MainActivity.this.getPackageManager()) != null) {
                // Create the File where the photo should go
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                    takePictureIntent.putExtra("PhotoPath", mCameraPhotoPath);
                } catch (IOException ex) {
                    // Error occurred while creating the File
                    Log.e(getClass().getName(), "Unable to create Image File", ex);
                }

                // Continue only if the File was successfully created
                if (photoFile != null) {
                    mCameraPhotoPath = "file:"+photoFile.getAbsolutePath();
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                            Uri.fromFile(photoFile));
                } else {
                    takePictureIntent = null;
                }
            }

            Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
            contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
            contentSelectionIntent.setType(TYPE_IMAGE);

            Intent[] intentArray;
            if(takePictureIntent != null) {
                intentArray = new Intent[]{takePictureIntent};
            } else {
                intentArray = new Intent[0];
            }

            Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
            chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
            chooserIntent.putExtra(Intent.EXTRA_TITLE, "Image Chooser");
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);

            startActivityForResult(chooserIntent, INPUT_FILE_REQUEST_CODE);
        }


    } // class end
    //변수
    private static final String TYPE_IMAGE = "image/*";
    private static final int INPUT_FILE_REQUEST_CODE = 1;

    private ValueCallback<Uri> mUploadMessage;
    private ValueCallback<Uri[]> mFilePathCallback;
    private String mCameraPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File imageFile = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        return imageFile;
    }

    //camera 어플의 activity가 끝나면 onActivityResult() 메소드가 호출되면서 그 결과가 인자로 들어온다.
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == INPUT_FILE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    if (mFilePathCallback == null) {
                        super.onActivityResult(requestCode, resultCode, data);
                        return;
                    }
                    //폰 내부에서 어떤 파일을 선택했을때, 그 파일의 정보를 정확히 지칭할 수 있는 객체
                    Uri[] results = new Uri[]{data.getData()};

                    mFilePathCallback.onReceiveValue(results);
                    mFilePathCallback = null;
                } else {
                    if (mUploadMessage == null) {
                        super.onActivityResult(requestCode, resultCode, data);
                        return;
                    }
                    Uri result = data.getData();

                    Log.d(getClass().getName(), "openFileChooser : " + result);
                    mUploadMessage.onReceiveValue(result);
                    mUploadMessage = null;
                }
            } else {
                if (mFilePathCallback != null) mFilePathCallback.onReceiveValue(null);
                if (mUploadMessage != null) mUploadMessage.onReceiveValue(null);
                mFilePathCallback = null;
                mUploadMessage = null;
                super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

}
