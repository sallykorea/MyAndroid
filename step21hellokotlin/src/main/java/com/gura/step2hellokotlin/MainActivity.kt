package com.gura.step2hellokotlin

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemLongClickListener {



    //필드(property)
    //var inputMsg:EditText?=null // null로 property를 초기화 하기
    lateinit var inputMsg:EditText // lateinit(예약어|keyword)를 이용해 초기화(init)를 나중에 할 수 있다.
    lateinit var adapter: ArrayAdapter<String>
    lateinit var msgList: MutableList<String>
    var selectedIndex:Int=0

    override fun onClick(v: View?) {

        when(v?.id){
            R.id.addBtn->{
                //var text=inputMsg.text.toString()
                var msg=inputMsg.text.toString()
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

                //입력한 문자열을 모델에 추가하고
                msgList.add(msg)
                //ListView 가 업데이트 되도록 어답터에 알린다.
                adapter.notifyDataSetChanged()
                inputMsg.text.clear()
            }

            R.id.clearBtn->{
                msgList.clear()
                adapter.notifyDataSetChanged()
            }

        }

    }

    var listener=object : DialogInterface.OnClickListener{
        override fun onClick(dialog: DialogInterface?, which: Int) {
            when(which){
                Dialog.BUTTON_POSITIVE -> {
                    var item=msgList.get(selectedIndex)
                    //this@MainActivity 는 마치 java의 Mainactivity.this 와 같다.
                    val intent=Intent(this@MainActivity, DetailActivity::class.java)
                    intent.putExtra("msg", item)
                    startActivity(intent)
                }
                Dialog.BUTTON_NEGATIVE -> {

                }
            }

        }
    }

    override fun onItemLongClick(parent: AdapterView<*>?, view: View?, i: Int, id: Long): Boolean {
        selectedIndex=i

        with(AlertDialog.Builder(this)){
            setTitle("자세히보기로 이동하시겠습니까?")
            setNegativeButton("아니오",listener)
            setPositiveButton("네",listener)
            create()
            show()
        }
        /*AlertDialog.Builder(this)
                .setTitle("자세히보기로 이동하시겠습니까?")
                .setNegativeButton("아니오", listener)
                .setPositiveButton("네",listener)
                .create()
                .show()
*/
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //EditText 객체의 참조값(type을 명시하지 않으면 제너릭 type을 작성해주어야한다.)
        inputMsg=findViewById<EditText>(R.id.inputMsg)
        //Button 객체의 참조값(type을 명시하면 제너릭 type을 작성해주지 않아도 된다.)
        val addBtn:Button=findViewById(R.id.addBtn)
        addBtn.setOnClickListener(this)
        clearBtn.setOnClickListener(this)

        //Mutable 객체의 참조값을 얻어내서 property에 저장하기
        msgList= mutableListOf()
        //ArrayAdapter 객체의 참조값을 얻어내서 property에 저장하기
        adapter=ArrayAdapter(this, android.R.layout.simple_list_item_1, msgList)

        //ListView에 어답터 연결하기
        //findViewById<ListView>(R.id.myListView).adapter=adapter // setAdapter(adapter) 과 같다.
        myListView.adapter=adapter
        myListView.setOnItemLongClickListener(this)
        /*
            [ Kotlin에서 view의 참조 값을 얻어내는 방법 ] - findViewById<ListView>(R.id.myListView)
            Kotlin에서는 import kotlinx.android.synthetic.main.activity_main.* .xml문서에 있는 모든 view 들을 import 해서 사용할 수 있게 해준다.
            즉, findViewById<ListView>(R.id.myListView) 라고 코드를 길게 적을 필요 없이
            xml 문서에 등록해 놓은 id만 myListView 입력해주면 자동으로 xml문서의 어떤 id를 찾는 메소드가 실행된다.
         */
    }
}
