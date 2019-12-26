package cn.zhucongqi.gedid.core.zookeeper;

import cn.zhucongqi.gedid.core.GeneratorConfig;
import cn.zhucongqi.gedid.core.IConfig;
import cn.zhucongqi.gedid.core.IdGenerator;

/**
 * @author ：Jobsz
 * @project ：gedid
 * @date ：Created in 2019/12/27 01:06
 * @description：
 * @modified By：
 * @version:
 */
public class ZookeeperIDGenerator implements IdGenerator {

    public ZookeeperIDGenerator(GeneratorConfig generatorConfig) {

    }

    /**
     * Follow The business with name.
     *
     * @param name
     * @return true, follow success.
     */
    @Override
    public boolean follow(String name) {
        return false;
    }

    /**
     * Next id.
     *
     * @return
     */
    @Override
    public Long next() {
        return null;
    }
}
