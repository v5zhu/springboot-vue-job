package com.wim.quartz.business.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuxiaolong
 * @project Project Collections
 * @package com.wim.quartz.business.entity
 * @description
 * @time 2018/11/16 22:01
 */
@Getter
@Setter
public class NetUnit {
    private String netUnitCode;

    private String netUnitName;

    private Boolean center;

    private String ftpName;

    private String ftpDescription;

    private String host;

    private Integer port;

    private String username;

    private String password;

    private String localsenddir;

    private String localsendtempdir;

    private String remotesenddir;

    private String remotesendtempdir;

    private String localreceivedir;

    private String localreceivetempdir;

    private String remotereceivedir;

    private String remotereceivetempdir;

}
