package com.haojing.dicing.dao;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yeweiping on 2020/9/16.
 */
public class IntArrayCommaTypeHandler implements TypeHandler<int[]> {
    @Override
    public void setParameter(PreparedStatement ps, int i, int[] parameter, JdbcType jdbcType) throws SQLException {
        StringBuilder sb = new StringBuilder();
        for (int c : parameter) {
            sb.append(c).append(",");
        }
        ps.setString(i, sb.substring(0, sb.length() - 1));
    }

    @Override
    public int[] getResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return getResult(value);
    }

    @Override
    public int[] getResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return getResult(value);
    }

    private int[] getResult(String value) {
        if (value == null) {
            return null;
        } else {
            String[] values = value.trim().split(",");
            int[] result = new int[values.length];
            for (int i = 0; i < values.length; i++) {
                result[i] = Integer.parseInt(values[i]);
            }
            return result;
        }
    }

    @Override
    public int[] getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return getResult(value);
    }
}
