package com.gura.step17sqlitedb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/*
    DB 생성 및 업그레이드를 도와주는 도우미 클래스 만들기
    - SQLiteOpenHelper 추상 클래스를 상속 받아서 만든다
 */
public class DBHelper extends SQLiteOpenHelper {
    //생성자
    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //DB가 새로 초기화 될때 호출되는 메소드
    @Override
    public void onCreate(SQLiteDatabase db) {
        //todo라는 이름의 테이블 만들기
        String sql="CREATE TABLE todo " +
                "(num INTEGER PRIMARY KEY AUTOINCREMENT," +
                "content TEXT," +
                "regdate TEXT)"; // SQLLite 에는 DATE TYPE이 없다. TEXT TYPE으로 넣어 줘야함
        db.execSQL(sql);
    }

    //DB 테이블의 구조를 바꾸거나 전체 혹은 부분 업데이트를 할 때 호출되는 메소드
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //todo 테이블을 삭제하고 다시만들어 질 수 있도록
        String sql="DROP TABLE IF EXISTS todo";
        db.execSQL(sql);
        onCreate(db);
    }
}
