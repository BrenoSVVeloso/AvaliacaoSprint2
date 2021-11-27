import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProdutoDAO {
    
    private Connection connection;

    public ProdutoDAO(Connection connection){
        this.connection = connection;
    }

    public void salvar(Produto produto) throws SQLException{
        int ordenacao = 0;
        String sql = "SELECT MAX(ORDENACAO) AS MAXORDENACAO FROM PRODUTO";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while(resultSet.next()){
                ordenacao = resultSet.getInt(1);
            }
        }


        sql = "INSERT INTO PRODUTO (nome, descricao, preco, ordenacao, quantidade) VALUES (?, ?, ?, ?, ?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            preparedStatement.setString(1, produto.getNome());
            preparedStatement.setString(2, produto.getDescricao());
            preparedStatement.setDouble(3, produto.getPreco());
            preparedStatement.setInt(4, (ordenacao+1));
            preparedStatement.setInt(5,produto.getQuantidade());
            
            preparedStatement.execute();

            try(ResultSet resultset = preparedStatement.getGeneratedKeys()){
            
                while(resultset.next()){
                    produto.setId(resultset.getInt(1));
                }
            }
        }
    }

    public void excluirSegundoProduto()throws SQLException{
        
        String sql = "DELETE FROM PRODUTO WHERE ORDENACAO = 2";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.execute();
            int linhasModificadas = preparedStatement.getUpdateCount();
            
            if(linhasModificadas > 0){
                System.out.println("Segundo produto excluido com sucesso");
            }
        }
        sql = "UPDATE PRODUTO SET ORDENACAO = ORDENACAO - 1 WHERE ORDENACAO > 2";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.execute();
        }
    }

    public void atualizarPrimeiroProduto(String nome, String descricao, double preco, int quantidade) throws SQLException{
        String sql = "UPDATE PRODUTO SET NOME = ?, DESCRICAO = ?, PRECO = ?, QUANTIDADE = ? WHERE ORDENACAO = 1";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, descricao);
            preparedStatement.setDouble(3, preco);
            preparedStatement.setInt(4, quantidade);
            preparedStatement.execute();
            
            System.out.println("Produto atualizado com sucesso");

        }
    }
}
