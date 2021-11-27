import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
    private DataSource dataSource;
    //Cria a conexão com o mysql
    //Cria o Pool e o Data Source para administrar q quantidade de requisições recebidas, para não sobrecarregar o servidor
    //DATABASE = loja
    public ConnectionFactory(){
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/streaming?useTimezone=true&serverTimezone=UTC");
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("root");
        comboPooledDataSource.setMaxPoolSize(15);
        this.dataSource = comboPooledDataSource;
    }
    //retorna a conexao criada
    public Connection recuperaConnection() throws SQLException{
        return this.dataSource.getConnection();
    }
}
