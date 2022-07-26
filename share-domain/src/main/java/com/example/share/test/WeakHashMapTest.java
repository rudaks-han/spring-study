package com.example.share.test;

import java.util.HashMap;
import java.util.WeakHashMap;

public class WeakHashMapTest {

    public static void main(String[] args) {
        WeakHashMap map = new WeakHashMap();
        //HashMap map = new HashMap();
        Demo d = new Demo();

        map.put(d, " Hi ");
        System.out.println(map);

        //d = null;
        map.clear();
        //System.gc();

        for (int i=0; i<10; i++) {
            System.err.println(map);
            if (map == null) {
                System.err.println("Map is null");
                break;
            } else {
                System.err.println("Map size : " + map.size());
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println(map);
    }

}
