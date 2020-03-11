package com.gura.step15httprequest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText inputUrl, console;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //res/layout/activity_main.xml 문서를 전개해서 화면구성하기
        setContentView(R.layout.activity_main);
        //id가 requestBtn인 Button객체의 참조값을 얻어와서 requestBtn이라는 지역변수에 담기
        Button requestBtn=findViewById(R.id.requestBtn);
        //버튼에 리스너 등록하기
        requestBtn.setOnClickListener(this);

        inputUrl=findViewById(R.id.inputUrl);
        console=findViewById(R.id.console);
    }

    @Override
    public void onClick(View v) {
        //입력한 url 주소를 읽어온다.
        String urlAddr=inputUrl.getText().toString();
        //Get방식 http 요청을 해서 결과 문자열을 읽어오고 (Other Thread)
        GetRequestTask task=new GetRequestTask();
        task.execute(urlAddr);
        //console(EditText)에 출력하기 (Ui Thread)
        //출력은 onPostExecute()메소드에서 한다.

    }

    public class GetRequestTask extends AsyncTask<String, Void, String>{

        //백그라운드에서 할 작업 (Other Thread)
        @Override
        protected String doInBackground(String... strings) { //String... strings 에서 ... 는 strings 변수에 담긴 동적 배열을 의미한다.
            //요청할 url 주소를 읽어온다.
            String urlAddr=strings[0];

            //서버가 http 요청에 대해서 응답하는 문자열을 누적할 객체
            StringBuilder builder=new StringBuilder();
            HttpURLConnection conn=null;
            InputStreamReader isr=null; //html 문서를 readline메서드로 한줄씩 읽어온다. 그리고 StringBuilder객체에 append 해서 읽어온 문자열로 html 문서를 구성한다.
            BufferedReader br=null;

            try{
                URL url=new URL(urlAddr);
                //HttpURLConnection 객체의 참조값 얻어오기
                conn=(HttpURLConnection)url.openConnection();
                if(conn!=null) {//연결이 되었다면
                    conn.setConnectTimeout(20000); //응답을 기다리는 최대 대기 시간
                    conn.setRequestMethod("GET");//Default 설정
                    conn.setUseCaches(false);//케쉬 사용 여부
                    //응답 코드를 읽어온다.
                    int responseCode = conn.getResponseCode();
                    if (responseCode == 200) {//정상 응답이라면...
                        //서버가 출력하는 문자열을 읽어오기 위한 객체
                        isr = new InputStreamReader(conn.getInputStream());
                        br = new BufferedReader(isr);
                        //반복문 돌면서 읽어오기
                        while (true) {
                            //한줄씩 읽어들인다.
                            String line = br.readLine();
                            //더이상 읽어올 문자열이 없으면 반복문 탈출
                            if (line == null) break;
                            //읽어온 문자열 StringBuilder객체에 누적 시키기
                            builder.append(line);
                        }//while end
                    }//if end
                }//if end
            }catch (Exception e){
                final String msg=e.getMessage();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        console.setText(msg);
                    }
                });

            }finally {
                try{
                    if(isr!=null)isr.close();
                    if(br!=null)br.close();
                    if(conn!=null)conn.disconnect();
                }catch(Exception e){}
            }

            //결과 데이터를 문자열로 리턴해준다.
            //리턴된 결과 값은 onPostExecute()메소드의 인자로 전달된다.
            return builder.toString();

        }

        //작업 결과를 UI로 출력할 메소드 (Ui Thread)
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //읽어낸 문자열을 EditText에 출력하기
            console.setText(s);
        }
    }
}
