package org.apache.dolphinscheduler.common.process;

/**
 * @author wuxinle
 * @version 1.0
 * @date 2021/1/20 20:36
 */

/**
 *   resource info
 * */
public class ResourceInfo {
    /**
     *   res the name of the resource that was uploaded
     * */
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String res;

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

}
