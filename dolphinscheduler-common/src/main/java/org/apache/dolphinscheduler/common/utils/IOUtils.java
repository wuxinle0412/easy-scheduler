package org.apache.dolphinscheduler.common.utils;

/**
 * @author wuxinle
 * @version 1.0
 * @date 2021/1/29 17:06
 */
import java.io.Closeable;
import java.io.IOException;

public class IOUtils {

    public static void closeQuietly(Closeable closeable){
        if(closeable != null){
            try {
                closeable.close();
            } catch (IOException ignore) {
                // nothing need to do
            }
        }
    }
}

