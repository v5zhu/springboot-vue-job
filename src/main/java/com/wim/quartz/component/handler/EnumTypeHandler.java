package com.wim.quartz.component.handler;

import com.wim.quartz.business.enumeration.CodeBaseEnum;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zhuxiaolong
 * @project springboot-vue-quartz
 * @package com.wim.quartz.component.handler
 * @description
 * @time 2018/11/30 12:45
 */
public class EnumTypeHandler<E extends Enum<?> & CodeBaseEnum<Object>> extends BaseTypeHandler<CodeBaseEnum<Object>> {
    private Class<E> clazz;

    public EnumTypeHandler(Class<E> enumType) {
        if (enumType == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }

        this.clazz = enumType;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, CodeBaseEnum parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setObject(i, parameter.getValue());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return codeOf(clazz, rs.getObject(columnName));
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return codeOf(clazz, rs.getObject(columnIndex));
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return codeOf(clazz, cs.getObject(columnIndex));
    }


    public static <E extends Enum<?> & CodeBaseEnum> E codeOf(Class<E> enumClass, Object code) {
        E[] enumConstants = enumClass.getEnumConstants();
        for (E e : enumConstants) {
            final Object v = e.getValue();
            if (v != null && code != null && v.equals(code)){
                return e;
            }
        }
        return null;
    }
}
