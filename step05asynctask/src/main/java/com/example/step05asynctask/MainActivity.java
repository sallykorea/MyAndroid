package com.example.step05asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    //진행 시작, 진행 과정, 결과를 표시할 TextView
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView);


    }

    //작업하기 버튼을 눌렀을때 호출되는 메소드
    public void start(View v){
        /*
            버튼을 누르면 여기에 실행순서가 들어온다.
            그 스레드는 UI 스레드 (main 스레드)이다.
            UI 스레드에서 시간이 오래 걸리거나 언제 끝날지 모르는 불확실한 작업을 하면 안 된다.

            즉, 비동기 방식으로 스레드가 진행되어야한다.
            비동기 방식으로 프로그래밍을 할 수 있도록 Android OS에서 AsyncTask 클래스를 제공해준다.
         */

        //비동기 작업의 시작은 객체를 생성하고
        CounterTask task=new CounterTask();
        //execute() 메소드를 호출하면 된다.
        //execute() 메소드를 호출하면 doInBackground() 메소드가 동작하고 전달하는 인자가 있다면 배열로 전달된다.
        task.execute("김구라", "해골", "원숭이");

    }
    /*
        extends AsyncTask<전달 받는 type, 진행중 반환(publish)하는 type, 결과 type>

        type이 필요 없으면 Void type을 사용하면 된다.
        예) extends AsyncTask<String, Void, Void>
     */
    public class CounterTask extends AsyncTask<String, Integer, String>{

        // 2. 진행중 반환(publish)을 위해 사용하는 method : UI Thread
        //publishProgress() 메소드를 호출하면 아래의 메소드가 간접적으로 호출된다.
        //onProgressUpdate() UI Thread를 잠깐 끌고 오늘 메소드이다.
        @Override
        protected void onProgressUpdate(Integer... values) { // '...'은 동적인 배열을 의미한다. 동적으로 배열의 방을 조절할 수 있다.
            super.onProgressUpdate(values);
            //publishProgress() 메소드에 전달된 인자가 이 메소드의 인자로 전달된다.
            int count=values[0]; //Integer 배열의 0번방에 값이 들어 있다
            textView.setText(Integer.toString(count));
        }

        // 3. 진행된 결과를 return할 method : UI Thread
        @Override
        protected void onPostExecute(String s) { //doInBackground()의 return 값이 이 메소드의 인자로 들어온다.
            super.onPostExecute(s);
            textView.setText(s);
        }

        //4. doInBackground() 메소드가 호출되기 전에 수행할 작업을 작성하는 method : UI Thread
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            textView.setText("숫자 세기를 시작합니다.");
        }

        //5. background에서 작업하던 내용이 어떤 이유에서 취소되었을때 출력할 method : UI Thread
        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        // 1. Background에서 수행할 동작을 작성하는 method
        /*
            버튼을 여러번 누른다고 해서 새로운 Thread가 새로 동작하는 것은 아니다.
            버튼이 클릭된 만큼 작업할 내용을 만들어 놓고 하나의 작업이 수행완료 된 뒤 다음 작업을 수행한다.
         */
        @Override
        protected String doInBackground(String... strings) {

            String name1=strings[0]; //김구라
            String name2=strings[1]; //해골
            String name3=strings[2]; //원숭이

            int count=0;
            //백그라운드에서 작업할 내용을 여기서 하면된다.
            for(int i=0; i<20; i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;

                /*
                    UI 출력은 UI Thread가 들어오는 Class에서만 가능하다.
                    AsyncTask 클래스를 상속받은 클래스는 백그라운드에서만 돌아가므로 UI Thread가 들어오지 않는다.
                    따라서 UI 출력을 할 수 없고, 에러 메시지가 뜬다.

                    textView.setText(Integer.toString(CounterTask.count));

                    다른 UI에서 어떤 작업을 하는 중에 AsyncTask를 상속받은 클래스에서 진행이 어떻게 되어가고 있는지
                    사용자에게 UI로 표시 해주고 싶은 경우,
                    1. publishProgress() 메소드를 호출하면서 인자를 전달하고,
                    2. UI Thread를 잠깐 끌고 오는 메소드인 onProgressUpdate() 메소드를 override해서 publishProgress()로 부터 인자를 전달받는다.
                       publishProgress() 메소드를 호출하면 onProgressUpdate() 메소드가 간접적으로 호출된다.
                */
                publishProgress(count);

            }
            String result="숫자 세기 성공";
            return result;
        }
    }
}
