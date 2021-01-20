package org.apache.dolphinscheduler.common;

/**
 * @author wuxinle
 * @version 1.0
 * @date 2021/1/20 20:26
 */

/**
 *   server stop interface
 * */
public interface IStoppable {
    /**
     * Stop this service.
     * @param cause why stopping
     * */
    void stop(String cause);
}
