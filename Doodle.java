import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Doodle {
    
    private JTextArea outputArea;
    private JFrame frame;

    // construtor
    public Doodle(JFrame frame, JTextArea outputArea) {
        this.frame = frame;
        this.outputArea = outputArea;
    }

    // print na tela da aplicação
    private void printOutput(String message){
        outputArea.append(message + "\n");
    }

    // pega o input do usuário ao invés de utilizar o scanner para o console
    private String getInputUsuario(String message){
        return JOptionPane.showInputDialog(frame, message);
    }



    // checa o tamanho da palavra
    public int checagemTamanho(int tamanho) {
        //int tamanho = console.nextInt();
        return tamanho;
    }

    // chama todas as funções e faz as operações com todas as palavras do words.txt
    public String[] palavras(int tam) {
        ArrayList<String> respostasFinais = new ArrayList<>();
        ArrayList<Character> letrasIncorretas = new ArrayList<>(); // armazena as letras que não podem ter
        HashMap<Character, Integer> letrasCorretas = new HashMap<>(); // armazena as letras que DEVEM ter
        ArrayList<String> palavrasCorretas = new ArrayList<>(); // armazena todas as palavras para comparação
        reader(palavrasCorretas, tam);
        leituraLetrasCorretas(letrasCorretas); // le todas as letras que o usuario passar e sua posição
        leituraLetrasIncorretas(letrasIncorretas); // le todas as letras que o usuario passar
        
        respostasFinais = percorrePalavras(palavrasCorretas, letrasCorretas, letrasIncorretas); // compara todas as palavras do arquivo com as letras proibidas e permitidas

        // verifica se há letras que não sabemos da posição e estão na palavra, logo, exclui todas as que não se enquadram
        ArrayList<Character> penteFino = new ArrayList<>();
        penteFino = leituraIncorretasUnknown(penteFino);
        respostasFinais = AUXleituraLetrasCorretasUNKNOWN(respostasFinais, penteFino);

        return respostasFinais.toArray(new String[0]);
    }


    // le as letras que devem estar na palavra mas nao sabemos a posicao
    public ArrayList<Character> leituraIncorretasUnknown(ArrayList<Character> x){
        String input = getInputUsuario("Quantas letras sabemos que estão na palavra mas não sabemos a posição?");
        int qtdeLetras = Integer.parseInt(input);

        for (int i = 0; i < qtdeLetras; i++) {
            String charInput = getInputUsuario("Digite a letra numero " + (i + 1));
            char charAtual = charInput.charAt(0);
            x.add(charAtual);
        }

        return x;
    }

    // após todas as funções, verifica se deve adicionar a palavra ou não no resultado final com base nas letras que DEVEM estar
    // verifica quais palavras possuem as letras necessarias para serem consideradas
    public ArrayList<String> AUXleituraLetrasCorretasUNKNOWN(ArrayList<String> x, ArrayList<Character> caracteres){
        ArrayList<String> resultado = new ArrayList<>();
        for (String palavra : x) {
            if (AUX2leituraLetrasCorretasUNKNOWN(palavra, caracteres)) {
                resultado.add(palavra);
            }
        }
        return resultado;
    }

    // funcao auxiliar da anterior, verificar se a string atual possui as letras da palavra alvo
    public boolean AUX2leituraLetrasCorretasUNKNOWN(String x, ArrayList<Character> y){
        for (Character c : y) {
            if (!x.contains(c.toString())) {
                return false;
            }
        }
        return true;
    }

    // lê quais letras DEVEM estar na palavra e SE SABE A POSIÇÃO
    public void leituraLetrasCorretas(HashMap<Character, Integer> x) {
        String input = getInputUsuario("Quantas letras sabemos que DEVEM estar na palavra?");
        int qtdeLetras = Integer.parseInt(input);

        for (int i = 0; i < qtdeLetras; i++) {
            String charInput = getInputUsuario("Digite a letra numero " + (i + 1));
            char charAtual = charInput.charAt(0);
            String posicaoInput = getInputUsuario("Em que posição ela DEVE estar? Começando de 0");
            int posicaoProibida = Integer.parseInt(posicaoInput);
            x.put(charAtual, posicaoProibida);
        }
    }

    // lê quais letras NÃO devem estar na palavra
    public void leituraLetrasIncorretas(ArrayList<Character> x) {
        String input = getInputUsuario("Quantas letras sabemos que não estão na palavra?");
        int qtdeLetras = Integer.parseInt(input);

        for (int i = 0; i < qtdeLetras; i++) {
            String inputAtual = getInputUsuario("Digite a letra numero " + (i + 1));
            x.add(inputAtual.charAt(0));
        }
    }

    // percorre todas as palavras procurando as que não possuem letras incorretas e as que possuem corretas na posição também correta
    public ArrayList<String> percorrePalavras(ArrayList<String> x, HashMap<Character, Integer> corretas, ArrayList<Character> incorretas) {
        ArrayList<String> respostao = new ArrayList<>();

        for (String palavra : x) {
            if (!possuiIncorretas(palavra, incorretas) && todasLetrasCorretas(palavra, corretas)) {
                respostao.add(palavra);
            }
        }

        return respostao;
    }

    // auxiliar da funcao acima, verifica se a palavra possui letras incorretas
    private boolean possuiIncorretas(String palavra, ArrayList<Character> incorretas) {
        for (char letra : incorretas) {
            if (palavra.contains(String.valueOf(letra))) {
                return true;
            }
        }
        return false;
    }


    // verifica se a palavra possui os carateres conhecidos e corretos e se estão na posição correta
    private boolean todasLetrasCorretas(String palavra, HashMap<Character, Integer> corretas) {
        for (Map.Entry<Character, Integer> entry : corretas.entrySet()) {
            char chave = entry.getKey();
            int posicao = entry.getValue();
            if (palavra.charAt(posicao) != chave || !palavra.contains(String.valueOf(chave))) {
                return false;
            }
        }
        return true;
    }

    // adiciona as strings do arquivo numa lista com base no tamanho passado pelo usuário
    public static void reader(ArrayList<String> x, int tamanho) {
        try {
            File myObj = new File("words.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String palavra = myReader.nextLine();
                if (palavra.length() == tamanho) {
                    x.add(palavra);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
