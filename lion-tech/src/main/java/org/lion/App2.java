package org.lion;

import java.nio.ByteBuffer;

public class App2 {

    public static void main(String[] args) {
        System.out.println("before alocate:" + Runtime.getRuntime().freeMemory());
        ByteBuffer buffer = ByteBuffer.allocate(10240000);
        System.out.println("buffer = " + buffer);
        System.out.println("after  alocate:" + Runtime.getRuntime().freeMemory());
    }
}
