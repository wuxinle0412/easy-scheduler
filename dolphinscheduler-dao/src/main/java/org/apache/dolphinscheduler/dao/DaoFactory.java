package org.apache.dolphinscheduler.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wuxinle
 * @version 1.0
 * @date 2021/1/29 16:22
 *
 *  dao factory
 */
public class DaoFactory {
    private static final Logger logger = LoggerFactory.getLogger(DaoFactory.class);

    private static Map<String, AbstractBaseDao> daoMap = new ConcurrentHashMap<>();

    private DaoFactory() {

    }

    public static <T extends AbstractBaseDao> T getDaoInstance(Class<T> clazz) {
        String className = clazz.getName();

        synchronized (daoMap) {
            if (!daoMap.containsKey(className)) {
                try {
                    T t = clazz.getConstructor().newInstance();
                    //init
                    t.init();

                    daoMap.put(className, t);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }

        return (T) daoMap.get(className);
    }
}
