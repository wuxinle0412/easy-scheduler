package org.apache.dolphinscheduler.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @author wuxinle
 * @version 1.0
 * @date 2021/1/20 20:39
 */

/**
 * alert status
 * */
public enum AlertStatus {

    /**
     * 0 waiting executed; 1 execute successfully，2 execute failed
     */
    WAIT_EXECUTION(0, "waiting executed"),
    EXECUTION_SUCCESS(1, "execute successfully"),
    EXECUTION_FAILURE(2, "execute failed");


    AlertStatus(int code, String descp){
        this.code = code;
        this.descp = descp;
    }

    /**
     *   定义一个枚举，在需要存入数据库的字段上加上@EnumValue注解
     * */
    @EnumValue
    private final int code;
    private final String descp;

    public int getCode() {
        return code;
    }

    public String getDescp() {
        return descp;
    }

}
