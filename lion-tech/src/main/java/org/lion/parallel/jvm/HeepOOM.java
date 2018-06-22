package org.lion.parallel.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xms20m -Xmx20m -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=10M -XX:+DisableExplicitGC -Xloggc:F:\gc.log
 */
public class HeepOOM {


    private static class OOM {}

    public static void main(String[] args) {
        List<OOM> ooms = new ArrayList<>();
        for (;;) {
            ooms.add(new OOM());
        }
    }
}
