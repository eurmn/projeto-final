package proj;

public abstract class Jogador {
    public final char CaractereDeJogador;
    private int vitorias = 0;

    public Jogador(char caractereDeJogador) {
        CaractereDeJogador = caractereDeJogador;
    }
    
    public int getVitorias() {
        return vitorias;
    }

    public void ganhar() {
        vitorias++;
    }
}
