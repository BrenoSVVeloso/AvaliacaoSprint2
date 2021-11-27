
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws SQLException{
        Scanner verificador;
        Scanner scanner = new Scanner(System.in);
        List<Filme> filmes = new ArrayList<Filme>();
        
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try(Connection connection = connectionFactory.recuperaConnection()){
            
            FilmeDAO filmeDAO = new FilmeDAO(connection);
            cadastraFilmes(filmeDAO);
            int qtdPaginas;
        do{
            System.out.println("Olá usuário, quantas páginas voce quer acessar?");
                
             qtdPaginas = scanner.nextInt();
            
             if(qtdPaginas <=0){
                System.out.println("Desculpe você digitou um número inválido.");
            }
        }while(qtdPaginas <= 0);
        filmes = filmeDAO.buscar();
        filtrar(qtdPaginas, filmes);
            
        
        }
        scanner.close();
    }
    public static void cadastraFilmes(FilmeDAO filmeDAO) throws SQLException{

        Filme montyPython = new Filme("Monty Python em busca do Cálice Sagrado", "Comédia.", 1975);
        filmeDAO.cadastrar(montyPython);
        Filme apertemCintos = new Filme("Apertem os Cintos, o Piloto Sumiu", "Comédia.", 1980);
        filmeDAO.cadastrar(apertemCintos);
        Filme et = new Filme("ET: O Extraterrestre", "Aventura.", 1982);
        filmeDAO.cadastrar(et);
        Filme clubeCinco = new Filme("Clube dos Cinco", "Drama/Adolescente.", 1985);
        filmeDAO.cadastrar(clubeCinco);
        Filme cidadeAmaldicoados = new Filme("A cidade dos Amaldiçoados", "Terror/Ficção científica.", 1995);
        filmeDAO.cadastrar(cidadeAmaldicoados);
        Filme dracula = new Filme("Drácula de Bram Stoker", "Terror/Romance.", 1992);
        filmeDAO.cadastrar(dracula);
        Filme pulpFiction = new Filme("Pulp Fiction: Tempo de Violência", "Crime/Drama.", 1994);
        filmeDAO.cadastrar(pulpFiction);
        Filme oeste = new Filme("Era uma vez no Oeste", "Faroeste/Ação.", 1968);
        filmeDAO.cadastrar(oeste);
        Filme intocaveis = new Filme("Os Intocaveis", "Crime/Drama.", 1987);
        filmeDAO.cadastrar(intocaveis);
        Filme scarface = new Filme("Scarface", "Crime/Drama.", 1983);
        filmeDAO.cadastrar(scarface);
        Filme poderosoChefao = new Filme("O Poderoso Chefão", "Crime/Drama.", 1971);
        filmeDAO.cadastrar(poderosoChefao);
        Filme cavaleiros = new Filme("Os Cavaleiros do Asfalto", "Drama/Suspense.", 1973);
        filmeDAO.cadastrar(cavaleiros);
        Filme sofia = new Filme("A Escolha de Sofia", "Drama/Romance.", 1982);
        filmeDAO.cadastrar(sofia);
        Filme bonequinhaDeLuxo = new Filme("Bonequinha de Luxo", "Romance/Comédia.", 1961);
        filmeDAO.cadastrar(bonequinhaDeLuxo);
        Filme osVingadores = new Filme("Os Vingadores", "Ação/Aventura. ", 2012);
        filmeDAO.cadastrar(osVingadores);
        Filme perfumeDeMulher = new Filme("Perfume de Mulher", "Drama/Coming of age.", 1992);
        filmeDAO.cadastrar(perfumeDeMulher);
        Filme topGun = new Filme("Top Gun: Ases Indomáveis", "Ação/Aventura. ", 1986);
        filmeDAO.cadastrar(topGun);
        Filme easyRyder = new Filme("Easy Ryder", "Drama/Filme de estrada.", 1969);
        filmeDAO.cadastrar(easyRyder);
        Filme spartacus = new Filme("Spartacus", "Filme peplum.", 1960);
        filmeDAO.cadastrar(spartacus);
        Filme poetaMortos = new Filme("Sociedade dos Poetas Mortos", "Drama/Adolescente.", 1989);
        filmeDAO.cadastrar(poetaMortos);    
    
    }



    public static void filtrar(int qtdPaginas, List<Filme> filmes){
        Scanner scanner = new Scanner(System.in);
        Scanner verificador;
        String escolha ="";
        int i = 0;
        int filmesPorPagina = 20/qtdPaginas;
        boolean continua;
        int qtdParaPrintar = filmesPorPagina;
        System.out.println("Aqui está seus filmes: ");
        do{
            System.out.println("------------------------------------------");
            continua = true;
            if(escolha.equals("next")){
               if((i+filmesPorPagina) <=20 && (qtdParaPrintar+filmesPorPagina) <=21){
                i += filmesPorPagina;
                qtdParaPrintar += filmesPorPagina;
               }else{
                   if(filmes.size() - qtdParaPrintar <= 0 ){
                    System.out.println("Desculpe você já está na ultima página");
                    continua = false;
                   }else{
                    i += filmesPorPagina;
                    qtdParaPrintar += filmesPorPagina;
                   }
               }
            }else if(escolha.equals("back")){
                if((i-filmesPorPagina) >=0){
                    i -= filmesPorPagina;
                    qtdParaPrintar -= filmesPorPagina;
                } else{
                    System.out.println("Desculpe você já está na primeira página");
                    continua = false;
                }
            }
            for(int k = i ; k< qtdParaPrintar && continua && k <filmes.size(); k++){  
                    System.out.println("Nome: "+ filmes.get(k).getNome() + ". \nDescricao: "+ filmes.get(k).getDescricao() +". \nAno: "+filmes.get(k).getAno()+"."); 
                    System.out.println("------------------------------------------");
                }
            do{
                System.out.println("Se deseja continuar para a proxima página digite (next). \nSe deseja ir para a página anterior digite (back). \nSe deseja sair digite (sair)");
                escolha = scanner.nextLine();
                escolha.toLowerCase();
                verificador = new Scanner(escolha.trim());
                if(verificador.hasNextInt()){
                    System.out.println("Desculpe você inseriu um valor inválido.");
                }
            }while(verificador.hasNextInt());
        }while(!escolha.equals("sair"));
        
       
        scanner.close();
        
    }
}
