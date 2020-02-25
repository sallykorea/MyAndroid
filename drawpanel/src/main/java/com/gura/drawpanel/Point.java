package com.gura.drawpanel;

import java.io.Serializable;

public class Point implements Serializable { // 객체를 파일에 저장하고 읽어들이기 위해 Serializable을 구현해준다.
    public int x;
    public int y;
    // 선의 시작점인지 여부
    public boolean isStartPoint;
}
