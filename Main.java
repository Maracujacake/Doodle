public class Main {
    public static void main(String[] args) {

        DoodleGUI GUI = new DoodleGUI();
        GUI.createFrame(new DoodleGUI.TamanhoCallback(){
            @Override
            public void onTamanhoInserido(int tamanho) {
                // Executar a lógica depois que o tamanho for inserido
                Doodle game = new Doodle();
                System.out.println(tamanho); // O valor inserido pelo usuário
                String[] respostita = game.palavras(tamanho);
                for (String s : respostita) {
                    System.out.println(s);
                }
            }
        });
        /* 
        Doodle game = new Doodle();
        int tam = GUI.getTamanho();
        System.out.println(tam); // aqui ChatGPT
        String[] respostita = game.palavras(tam);
        for(int i = 0; i < respostita.length; i++){
            System.out.println(respostita[i]);
        }
        */
    }
}
