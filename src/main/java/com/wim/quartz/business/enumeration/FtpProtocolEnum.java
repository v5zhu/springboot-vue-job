package com.wim.quartz.business.enumeration;

/**
 * @author zhuxiaolong
 * @project Project Collections
 * @package com.wim.quartz.business
 * @description
 * @time 2018/11/30 10:31
 */
public enum FtpProtocolEnum implements CodeBaseEnum<String> {
    /**
     * 全网
     */
    FTP("ftp"),
    /**
     * 本省
     */
    SFTP("sftp");

    private String protocol;

    FtpProtocolEnum(String protocol) {
        this.protocol = protocol;
    }


    @Override
    public String getValue() {
        return this.protocol;
    }
}
