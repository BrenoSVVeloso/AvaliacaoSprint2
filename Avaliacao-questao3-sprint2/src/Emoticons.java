import java.util.Scanner;

public class Emoticons {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scanner verificador;
        String entrada = "";
        //roda o código até a flag "sair";
        do{
            //verifica a validação da entrada;
            do{
                System.out.print("Olá usuário, digite a frase desejada ou digite sair para sair do programa: ");
                entrada = scanner.nextLine();
                verificador = new Scanner(entrada.trim());
                if(verificador.hasNextInt()){
                    System.out.println("Desculpe você digitou uma frase inválida.");
                }
            }while(verificador.hasNextInt());
            if(!entrada.equals("sair")){
                //printa o sentimento expressado
                System.out.println(calculaSentimentoExpressado(entrada));
                
            }else{
                entrada.toLowerCase();  
            }
                  
        }while(!entrada.equals("sair"));
        
        scanner.close();
    }

    //Calcula o sentimento expressado na frase
    public static String calculaSentimentoExpressado(String entrada){
        String sentimentoExpressado = "";
        int chateado = 0;
        int divertido = 0;
        for(int i = 0; i< entrada.length() && (i + 2) < entrada.length() ; i++){
            if(entrada.charAt(i) == ':'){
                if(entrada.charAt(i+1) == '-'){
                    if(entrada.charAt(i+2) == ')'){
                        divertido++;
                    }else{
                        if(entrada.charAt(i+2) == '('){
                            chateado++;
                        }
                    }
                }
            }
        }
        //verifica qual é o sentimento expressado
        if(divertido > chateado){
            sentimentoExpressado = "divertido";
        }else if(divertido < chateado){
            sentimentoExpressado = "chateado";
        }else{
            sentimentoExpressado = "neutro";
        }
        
        return sentimentoExpressado;
    }
}
