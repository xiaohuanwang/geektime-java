package com.example.spring.jdbc;

import java.sql.*;
import java.util.*;

/**
 * @description:
 * 1）使用 JDBC 原生接口，实现数据库的增删改查操作。
 * 2）使用事务，PrepareStatement 方式，批处理方式，改进上述操作。
 * @author wangxiaohuan
 * @Date 6:08 下午 2021/4/18
   No such property: code for class: Script1
 * @return
 */
public class SqlJdbc {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;

    /**
     * @description:创建Connection
     * @author wangxiaohuan
     * @Date 6:11 下午 2021/4/18
       No such property: code for class: Script1
     * @return
     */
    private void createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hzc_o2o_shopjd", "root", "Huan5211314");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @description:插入方法
     * @author wangxiaohuan
     * @Date 6:14 下午 2021/4/18
       No such property: code for class: Script1
     * @return 
     */
    public boolean insert(String table, String column, List<Object> values) {
        try {
            String insertTemplate = buildInsertSqlTemplate(table, column, values.size());
            preparedStatement = connection.prepareStatement(insertTemplate);
            for (int i=1; i<values.size() + 1; i++) {
                preparedStatement.setObject(i, values.get(i-1));
            }
            System.out.println(preparedStatement.toString());

            preparedStatement.execute();
            System.out.println("插入数据成功");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @description:插入语句
     * @author wangxiaohuan
     * @Date 6:14 下午 2021/4/18
       No such property: code for class: Script1
     * @return 
     */
    private String buildInsertSqlTemplate(String table, String column, int valueAmount) {
        final StringBuilder stringBuilder = new StringBuilder();
        String sql="insert into "+table+ column+" values (";
        stringBuilder.append(sql);
        for (int i = 0; i < valueAmount; i++)
        {
            if (i != valueAmount-1)
            {
                stringBuilder.append("?, ");
            }
            else
            {
                stringBuilder.append("?");
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }



    /**
     * @description:查询方法
     * @author wangxiaohuan
     * @Date 6:15 下午 2021/4/18
       No such property: code for class: Script1
     * @return 
     */
    public List<Map<String, Object>> query(String table, Map<String, Object> values, String condition) {
        try {
            String sqlTemplate = buildQuerySqlTemplate(table, values, condition);

            preparedStatement = connection.prepareStatement(sqlTemplate);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Map<String, Object>> list = new ArrayList<>();
            while (resultSet.next()) {
                for (String key: values.keySet()) {
                    values.put(key, resultSet.getObject(key));
                }
                list.add(new HashMap<>(values));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    /**
     * @description:查询语句
     * @author wangxiaohuan
     * @Date 6:15 下午 2021/4/18
       No such property: code for class: Script1
     * @return 
     */
    public String buildQuerySqlTemplate(String table, Map<String, Object> values, String condition) {
        String sqlTemplate = "select " + values.keySet().toString().substring(1,
                values.keySet().toString().length()-1) + " from " + table;
        if (condition != null) {
            sqlTemplate += " where " + condition;
        }
        return sqlTemplate;
    }

    private void close() throws SQLException {
        preparedStatement.close();
        connection.close();
        System.out.println("Connection close");
    }

    public static void main(String[] args) {
        Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("name", "小灰灰");
        valuesMap.put("password", "test123456");
        valuesMap.put("phone", "13466668888");
        System.out.println(valuesMap.keySet().toString().substring(1, valuesMap.keySet().toString().length()-1));

        try {
            SqlJdbc sqlJdbc = new SqlJdbc();
            sqlJdbc.createConnection();

            String table = "users";
            String columns = "(name, password, phone)";
            List<Object> values = Arrays.asList("小灰灰", "test123456", "13466668888");

            sqlJdbc.insert(table, columns, values);

            List<Map<String, Object>> results = sqlJdbc.query(table, valuesMap, null);
            for (Map r: results) {
                System.out.println(r.toString());
            }

            sqlJdbc.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
