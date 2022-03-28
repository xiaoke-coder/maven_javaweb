package com.atguigu.imperial.court.dao;

import com.atguigu.imperial.court.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BaseDao<T> {
    private QueryRunner runner = new QueryRunner();
    /**
     * description  通用的增删改方法
     * date         2022/3/27 10:38
     * @param       sql 语句
     * @param       parameters 语句参数
     * @return      int 受影响的行数
     */
    public int update(String sql, Object ... parameters) {
        try {
            Connection connection = JDBCUtils.getConnection();
            int affectedRowNumbers = runner.update(connection, sql, parameters);
            return affectedRowNumbers;
        } catch (SQLException e) {
            e.printStackTrace();

            // 如果真的抛出异常，则将编译时异常封装为运行时异常抛出，让过滤器处理（回滚或者提交）
            new RuntimeException(e);

            return 0;
        }
    }
    /**
     * description  查询单个对象
     * date         2022/3/27 10:51
     * @param       sql
     * @param       entityClass
     * @param       parameters
     * @return      T
     */
    public T getSingleBean(String sql, Class<T> entityClass, Object ... parameters) {
        try {
            Connection connection = JDBCUtils.getConnection();
            return runner.query(connection, sql,new BeanHandler<>(entityClass), parameters);
        } catch (SQLException e) {
            e.printStackTrace();

            // 如果真的抛出异常，则将编译时异常封装为运行时异常抛出
            new RuntimeException(e);
        }
        return null;
    }
    /**
     * description  查询返回的多个对象
     * date         2022/3/27 10:58
     * @param       sql
     * @param       entityClass
     * @param       parameters
     * @return      java.util.List<T>
     */
    public List<T> getBeanList(String sql, Class<T> entityClass, Object... parameters){
        try {
            Connection connection = JDBCUtils.getConnection();
            return runner.query(connection, sql,new BeanListHandler<>(entityClass),parameters);
        } catch (SQLException e) {
            e.printStackTrace();
            new RuntimeException(e);
        }
        return null;
    }
}
