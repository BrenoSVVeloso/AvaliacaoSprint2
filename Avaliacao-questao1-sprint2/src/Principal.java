import java.sql.Connection;
import java.util.Scanner;
public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try(Connection connection = connectionFactory.recuperaConnection()) {
            ProdutoDAO produtoDAO = new ProdutoDAO(connection);
            int escolha = 0;
            System.out.println("Escolha a opção que deseja: \n0 - Sair do programa \n1 - Cadastrar 3 produtos na base. \n2 - Atualizar o primeiro produto. \n3 - Excluir o segundo produto cadastrado");
            escolha = scanner.nextInt();
            //Continua o codigo enquanto a flag não é digitada
            while(escolha!= 0){
               
                switch(escolha){
                    //cadastra 3 produtos
                    case 1:
                        Produto caderno = new Produto("Caderno", "96 folhas", 12.49, 10);
                        produtoDAO.salvar(caderno);

                        Produto mouse = new Produto("Mouse", "Mouse gamer preto", 149.99, 200);
                        produtoDAO.salvar(mouse);

                        Produto celular = new Produto("Smartphone", "Xiaomi azul", 1099.90, 50);
                        produtoDAO.salvar(celular);

                        System.out.println("Produtos cadastrados com sucesso!");
                        break;
                        //atualiza o primeiro produto
                    case 2: 
                        System.out.println("Digite o novo nome do produto: ");
                        String nome = scanner.nextLine();
                        nome = scanner.nextLine();
                        System.out.println("Digite a nova descricao do produto: ");
                        String descricao = scanner.nextLine();
                        System.out.println("Digite o novo preco do produto");
                        double preco = scanner.nextDouble();
                        System.out.println("Digite a quantidade de produtos");
                        int quantidade = scanner.nextInt();
                        produtoDAO.atualizarPrimeiroProduto(nome, descricao, preco, quantidade);
                        break;
                        //remove o segundo produto
                    case 3 :
                        produtoDAO.excluirSegundoProduto();
                }
                System.out.println("Escolha a opção que deseja: \n0 - Sair do programa \n1 - Cadastrar 3 produtos na base. \n2 - Atualizar o primeiro produto. \n3 - Excluir o segundo produto cadastrado");
                escolha = scanner.nextInt();
            }          
        
        } catch (Exception e) {
            e.printStackTrace();
          
        }
        scanner.close();
    }
}
