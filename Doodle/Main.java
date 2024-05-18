public class Main {
    public static void main(String[] args) {
        Doodle game = new Doodle();
        String[] respostita = game.palavras();
        for(int i = 0; i < respostita.length; i++){
            System.out.println(respostita[i]);
        }
    }
}
