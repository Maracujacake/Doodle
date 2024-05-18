import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DoodleGUI {

    private Doodle doodle;
    private int tam;

    public DoodleGUI() {
        this.doodle = new Doodle();
        this.tam = 0;
    }

    public void createFrame(TamanhoCallback callback){
        // criação da tela
        JFrame tela = new JFrame("Doodle");
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        // criação do painel
        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout());

        JLabel labelTamanho = new JLabel("");
        painel.add(labelTamanho, BorderLayout.NORTH);
        JButton botao = new JButton("Qual o tamanho da palavra?");
        painel.add(botao, BorderLayout.SOUTH);
        
        
        botao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inpute = JOptionPane.showInputDialog(tela, "Qual o tamanho da palavra?");
                int tamanho= Integer.parseInt(inpute);
                tam = tamanho;
                int resultado = doodle.checagemTamanho(tamanho);
                labelTamanho.setText("tamanho da palavra:" + resultado);
                callback.onTamanhoInserido(resultado);
            }
        });

        tela.getContentPane().add(painel);
        tela.setSize(500, 500);
        tela.setVisible(true);
    }

    public int getTamanho(){
        return tam;
    }

    public interface TamanhoCallback {
        void onTamanhoInserido(int tamanho);
    }
}

