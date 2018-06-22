package org.lion.parallel.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xms20m -Xmx20m -Xss5m -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=10M -XX:+DisableExplicitGC -Xloggc:F:\gc.log
 */
public class StackOOM {
    private long times = 0L;

    public void call() {
        times++;
//        System.out.println(times);
        call();
    }

    public static void main(String[] args) {
        StackOOM stackOOM = new StackOOM();
        stackOOM.call();
    }
}
