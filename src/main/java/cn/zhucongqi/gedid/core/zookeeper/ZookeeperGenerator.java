package cn.zhucongqi.gedid.core.zookeeper;

import cn.zhucongqi.gedid.core.GeneratorConfig;
import cn.zhucongqi.gedid.core.IGenerator;

/**
 * @author ：Jobsz
 * @project ：gedid
 * @date ：Created in 2019/12/27 01:06
 * @description：
 * @modified By：
 * @version:
 */
public class ZookeeperGenerator implements IGenerator {

    public ZookeeperGenerator(GeneratorConfig generatorConfig) {

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
     * Current id.
     *
     * @return
     */
    @Override
    public Long current() {
        return null;
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
