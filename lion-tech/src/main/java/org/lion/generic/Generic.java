package org.lion.generic;

import java.util.ArrayList;
import java.util.List;

public class Generic {
    static class Fruit {}
    static class Apple extends Fruit {}
    static class Jon extends Apple {}
    static class Orange extends Fruit {}

    public static void main(String[] args) {
        Number num = new Integer(1);
//        ArrayList<Number> nums = new ArrayList<Integer>(); // 类型错误
        List<? extends Number> nums = new ArrayList<Number>();
//        nums.add(new Integer(1));

    Fruit[] fruit = new Apple[10];
    fruit[0] = new Apple();
    fruit[1] = new Jon();
    fruit[0] = new Fruit();
//    fruit[0] = new Orange();

    }
}
