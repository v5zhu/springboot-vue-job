package com.wim.quartz.business.entity;

import com.wim.quartz.business.enumeration.FtpProtocolEnum;
import com.wim.quartz.business.enumeration.FtpStyleEnum;
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
public class NetUnit extends IdEntity {
    private String netUnitCode;

    private String netUnitName;

    private Boolean center;

    private String ftpName;

    private String ftpDescription;

    private FtpProtocolEnum protocol;

    private FtpStyleEnum ftpStyle;

    private String host;

    private Integer port;

    private String username;

    private String password;

    private Integer timeout;

    private String localsenddir;

    private String localsendtempdir;

    private String remotesenddir;

    private String remotesendtempdir;

    private String localreceivedir;

    private String localreceivetempdir;

    private String remotereceivedir;

    private String remotereceivetempdir;

    private Integer order;

}
