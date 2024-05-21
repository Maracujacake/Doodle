import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DoodleGUI {

    private Doodle doodle;
    private int tam;

    public DoodleGUI() {
        this.tam = 0;
    }

    public void createFrame(){
        // criação da tela
        JFrame tela = new JFrame("Doodle");
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        // criação do painel
        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Qual o tamanho da palavra?");
        painel.add(label, BorderLayout.NORTH);

        JButton button = new JButton("Inserir Tamanho");
        painel.add(button, BorderLayout.CENTER);

        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        painel.add(new JScrollPane(resultArea), BorderLayout.NORTH);

        doodle = new Doodle(tela, resultArea);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(tela, "Qual o tamanho da palavra?");
                if (input != null && !input.isEmpty()) {
                    try {
                        int tamanho = Integer.parseInt(input);
                        int resultado = doodle.checagemTamanho(tamanho);
                        label.setText("Tamanho da palavra: " + resultado);
                        
                        // Aqui está a lógica adicional que você quer executar
                        String[] respostas = doodle.palavras(tamanho);
                        StringBuilder resultadoTexto = new StringBuilder();
                        for (String resposta : respostas) {
                            resultadoTexto.append(resposta).append("\n");
                        }
                        resultArea.setText(resultadoTexto.toString());
                    } 
                    catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(tela, "Por favor, insira um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        tela.getContentPane().add(painel);
        tela.setSize(500, 500);
        tela.setVisible(true);
    }

    public int getTamanho(){
        return tam;
    }
    /* 
    public interface TamanhoCallback {
        void onTamanhoInserido(int tamanho);
    }
    */
}

