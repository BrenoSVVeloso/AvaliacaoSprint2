public class Filme {
    private int id;
    private String nome;
    private String descricao;
    private int ano;
    private int paginacao;
    
    //Cria o Filme
    public Filme(String nome, String descricao, int ano){
        this.nome = nome;
        this.descricao = descricao;
        this.ano = ano;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getPaginacao() {
        return paginacao;
    }

    public void setPaginacao(int paginacao) {
        this.paginacao = paginacao;
    }

}

