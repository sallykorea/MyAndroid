package com.gura.step17sqlitedb;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Util {

    //키보드 숨기는 메소드
    public static void hideKeyboard(Activity activity){
        InputMethodManager iManager=(InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if(activity.getCurrentFocus()==null)return;
        iManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    //메소드의 인자로 전달되는 View 객체의 포커스 해제하는 메소드
    public static void releaseFocus(View view) {
        ViewParent parent = view.getParent();
        ViewGroup group = null;
        View child = null;
        while (parent != null) {
            if (parent instanceof ViewGroup) {
                group = (ViewGroup) parent;
                for (int i = 0; i < group.getChildCount(); i++) {
                    child = group.getChildAt(i);
                    if(child != view && child.isFocusable())
                        child.requestFocus();
                }
            }
            parent = parent.getParent();
        }
    }

}
