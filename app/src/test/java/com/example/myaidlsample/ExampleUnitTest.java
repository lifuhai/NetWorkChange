package com.example.myaidlsample;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        try {
            Class<?> arrayList1 =Class.forName("java.util.ArrayList");
//            Field elementData = arrayList1.getDeclaredField("elementData");
//            elementData.setAccessible(true);
//            elementData.set(arrayList1,String.class);
            Method[] methods = arrayList1.getMethods();
            Method add = arrayList1.getMethod("add", String.class);
            add.setAccessible(true);
            add.invoke(arrayList1,"1");
//            elementData.setAccessible(true);
//            elementData.set(arrayList1,Integer.class);
//            add.setAccessible(true);
//            add.invoke(arrayList1,123);
            ArrayList arrayList = (ArrayList) arrayList1.newInstance();
            System.out.println(arrayList.get(0));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}