package cn.zhucongqi.gedid.core;

/**
 * @author ：Jobsz
 * @project ：gedid
 * @date ：Created in 2019/12/27 01:08
 * @description：
 * @modified By：
 * @version:
 */
public class GeneratorConfig {

    /**
     * DC Server Ip
     */
    private String ip;

    /**
     * DC server port
     */
    private Integer port;

    /**
     * DC(Redis) auth password
     */
    private String auth;

    /**
     * The Start ID default 1L
     */
    private Long startId;

    private Type type;

    public enum Type {
        Redis,
        Zookeeper;
    }

    public static GeneratorConfig defaultRedisConfig() {
        return (new GeneratorConfig("127.0.0.1", 6379, "", 1L, Type.Redis));
    }

    public static GeneratorConfig defaultZookeeperConfig() {
        return (new GeneratorConfig("127.0.0.1", 2181, "", 1L, Type.Zookeeper));
    }

    public GeneratorConfig(String ip, Integer port, String auth, Long startId, Type type) {
        this.ip = ip;
        this.port = port;
        this.auth = auth;
        this.startId = startId;
        this.type = type;
    }

    public GeneratorConfig(String ip, Integer port, Long startId, Type type) {
        this.ip = ip;
        this.port = port;
        this.startId = startId;
        this.type = type;
    }

    public String getIp() {
        return ip;
    }

    public Integer getPort() {
        return port;
    }

    public String getAuth() {
        return auth;
    }

    public Long getStartId() {
        return startId;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return this.getClass()+"{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                ", auth='" + auth + '\'' +
                ", startId=" + startId +
                '}';
    }
}
