import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import oracle.jdbc.OracleResultSet;
import oracle.sql.*;

/**
 * Created by mbikov on 14.08.2017.
 */
public class JDBCtest {

    public static void main(String[] args) {

        Connection connection = null;
        //URL к базе состоит из протокола:подпротокола://[хоста]:[порта_СУБД]/[БД] и других_сведений
        String url = "jdbc:oracle:thin:@ 89.108.84.144:1521/BPM8";
        //Имя пользователя БД
        String name = "test01";
        //Пароль
        String password = "test01";
        try {
            //Загружаем драйвер
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Драйвер подключен");
            //Создаём соединение
            connection = DriverManager.getConnection(url, name, password);
            System.out.println("Соединение установлено");
            //Для использования SQL запросов существуют 3 типа объектов:
            //1.Statement: используется для простых случаев без параметров
            Statement statement;

            statement = connection.createStatement();

              ResultSet rset = statement.executeQuery("SELECT * FROM test01.department");
              while (rset.next ())
              {
                  CHAR id = (CHAR) ((OracleResultSet)rset).getOracleObject (1);
                  CHAR department = (CHAR) ((OracleResultSet)rset).getOracleObject (2);
                  System.out.println(id + " " + department);
              }

        } catch (Exception ex) {
            //выводим наиболее значимые сообщения
            Logger.getLogger(JDBCtest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCtest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
}
