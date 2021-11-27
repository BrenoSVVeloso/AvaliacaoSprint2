import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class FilmeDAO {
    Connection connection;

    public FilmeDAO(Connection connection){
        this.connection = connection;
    }

    public void cadastrar(Filme filme) throws SQLException{
        // int ordenacao = 0;
        // String sql = "SELECT MAX(ORDENACAO) AS MAXORDENACAO FROM FILME";
        // try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
        //     preparedStatement.execute();
        //     ResultSet resultSet = preparedStatement.getResultSet();
        //     while(resultSet.next()){
        //         ordenacao = resultSet.getInt(1);
        //     }
        // }


        String sql = "INSERT INTO FILME (nome, descricao, ano) VALUES (?, ?, ?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            preparedStatement.setString(1, filme.getNome());
            preparedStatement.setString(2, filme.getDescricao());
            preparedStatement.setInt(3, filme.getAno());
            //preparedStatement.setInt(4, (ordenacao+1));
            
            preparedStatement.execute();

            try(ResultSet resultset = preparedStatement.getGeneratedKeys()){
            
                while(resultset.next()){
                    filme.setId(resultset.getInt(1));
                }
            }
        }
    }

    public List<Filme> buscar() throws SQLException{
        List<Filme> filmes = new ArrayList<Filme>();
        
        String sql = "SELECT NOME, DESCRICAO, ANO FROM FILME ORDER BY NOME ASC";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.execute();

            try(ResultSet resultSet = preparedStatement.getResultSet()){
                while(resultSet.next()){
                    Filme filme = new Filme(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3));
                    filmes.add(filme);
                }
            }
        }
        return filmes;
    }
}
