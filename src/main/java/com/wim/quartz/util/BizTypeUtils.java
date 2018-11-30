package com.wim.quartz.util;

import com.wim.quartz.business.entity.BizType;

import java.io.*;
import java.util.List;

/**
 * @author zhuxiaolong
 * @project springboot-vue-quartz
 * @package com.wim.quartz.util
 * @description
 * @time 2018/11/29 17:37
 */
public class BizTypeUtils {

    private static final String parentPath = "D:\\BDC_AUTO";

    public static void autoCodingForAll(List<BizType> bizTypes) {
        if (bizTypes != null && bizTypes.size() > 0) {
            bizTypes.parallelStream().forEach(bizType -> autoCodingForOne(bizType));
        }
    }

    public static void autoCodingForOne(BizType bizType) {
        // 生成kitConfig
        createKitConfig(bizType);
    }

    public static void createKitConfig(BizType bizType) {
        final String kitConfigPath = parentPath + "\\config\\uspc\\kitConfig\\biz_" + bizType.getBizType();
        // 创建convert
        final File convertDir = new File(kitConfigPath.concat("\\convert"));
        final File convertFile =
                new File(convertDir.getAbsolutePath().concat("\\biz").concat(bizType.getBizType()).concat("_convert" +
                        ".xml"));
        if (!convertDir.exists()) {
            convertDir.mkdirs();
        }
        if (!convertFile.exists()) {
            try {
                convertFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        StringBuilder convertContent = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n")
                .append("<TableExtend bizType=\""+bizType.getBizType()+"\" description=\""+bizType.getBizTypeName()+"\">\n")
                .append("\t<fields>\n");

        bizType.getFieldList().parallelStream().forEach(field -> {
            if (field.getConvertField() != null) {
                convertContent.append("\t\t<ConvertFieldInfo name=\""+field.getConvertField()+"\" convertedField=\""+field.getFieldName()+"\" description=\""+field.getDescription()+"\">\n")
                        .append("\t\t\t<valueInfos>\n");
                field.getConvertFieldList().forEach(map->{
                    convertContent.append("\t\t\t\t<ValueInfo  key=\""+map.getFieldKey()+"\"  value=\""+map.getFieldValue()+"\"/>\n");
                });
                convertContent.append("\t\t\t</valueInfos>\n")
                .append("\t\t</ConvertFieldInfo>\n");
            }
        });

        convertContent.append("\t</fields>\n")
                .append("</TableExtend>\t");
        try {
            BufferedWriter writer=new BufferedWriter(new FileWriter(convertFile));
            writer.write(convertContent.toString());
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 创建excel

        // 创建output

        // 创建page

        // 创建sync

        // 创建table

    }

}
