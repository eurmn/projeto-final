package proj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Jogo {
    private final static Jogador[] jogadores = new Jogador[2];
    private static int jogadorAtual = 0;

    private static List<List<Integer>> tabuleiro = new ArrayList<List<Integer>>() {
        {
            add(Arrays.asList(-1, -1, -1));
            add(Arrays.asList(-1, -1, -1));
            add(Arrays.asList(-1, -1, -1));
        }
    };

    private static Scanner scanner = new Scanner(System.in);

    public static void limparTabuleiro() {
        tabuleiro = new ArrayList<List<Integer>>() {
            {
                add(Arrays.asList(-1, -1, -1));
                add(Arrays.asList(-1, -1, -1));
                add(Arrays.asList(-1, -1, -1));
            }
        };
    }

    public static void fechar() {
        scanner.close();
        System.exit(0);
    }

    public static void reiniciarJogo() {
        scanner.nextLine();
        System.out.println("Você deseja jogar novamente? [S/N]");
        String resposta = scanner.nextLine();

        if (resposta.equals("N")) {
            fechar();
        } else if (resposta.equals("S")) {
            limparTabuleiro();
        } else {
            System.out.println("Resposta inválida, tente novamente.");
            reiniciarJogo();
        }
    }

    public static void finalizarComVelha() {
        System.out.println("Deu velha!");
        reiniciarJogo();
    }

    public static void finalizarComVencedor(Jogador vencedor) {
        vencedor.ganhar();

        if (jogadores[0] == vencedor) {
            jogadorAtual = 1;
        } else {
            jogadorAtual = 0;
        }

        System.out.println("Parabéns, jogador [" + vencedor.CaractereDeJogador + "], você ganhou ("
                + vencedor.getVitorias() + " vitórias)!");
    }

    private static void renderizarTabuleiro() {
        System.out.println("Tabuleiro:");
        System.out.println("-------------");

        for (int linha = 0; linha < 3; linha++) {
            System.out.print("| ");

            for (int coluna = 0; coluna < 3; coluna++) {
                if (tabuleiro.get(linha).get(coluna) == -1) {
                    System.out.print("  | ");
                } else {
                    System.out.print(jogadores[tabuleiro.get(linha).get(coluna)].CaractereDeJogador + " | ");
                }
            }

            System.out.println();
            System.out.println("-------------");
        }
    }

    public static boolean verificarVitoria() {
        for (int linha = 0; linha < 3; linha++) {
            if (tabuleiro.get(linha).get(0) == jogadorAtual && tabuleiro.get(linha).get(1) == jogadorAtual
                    && tabuleiro.get(linha).get(2) == jogadorAtual) {
                return true;
            }
        }

        for (int coluna = 0; coluna < 3; coluna++) {
            if (tabuleiro.get(0).get(coluna) == jogadorAtual && tabuleiro.get(1).get(coluna) == jogadorAtual
                    && tabuleiro.get(2).get(coluna) == jogadorAtual) {
                return true;
            }
        }

        if (tabuleiro.get(0).get(0) == jogadorAtual && tabuleiro.get(1).get(1) == jogadorAtual
                && tabuleiro.get(2).get(2) == jogadorAtual) {
            return true;
        }

        if (tabuleiro.get(0).get(2) == jogadorAtual && tabuleiro.get(1).get(1) == jogadorAtual
                && tabuleiro.get(2).get(0) == jogadorAtual) {
            return true;
        }

        return false;
    }

    public static void iniciarJogo() {
        System.out.println("Olá, bem-vindo ao jogo da velha!");

        jogadores[0] = new JogadorX();
        jogadores[1] = new JogadorO();
        while (true) {
            renderizarTabuleiro();

            System.out.println("Jogador [" + jogadores[jogadorAtual].CaractereDeJogador + "], é a sua vez.");
            System.out.println("Digite a casa que deseja jogar (valor numérico).");

            int posicao = scanner.nextInt();

            if (posicao < 1 || posicao > 9) {
                System.out.println("Você deve digitar um número entre 1 e 9. Seu turno será reiniciado.");
                continue;
            }

            int linha = (posicao - 1) / 3;
            int coluna = (posicao - 1) % 3;

            if (tabuleiro.get(linha).get(coluna) != -1) {
                System.out.println("Posição já ocupada, tente novamente.");
                continue;
            }

            tabuleiro.get(linha).set(coluna, jogadorAtual);

            if (verificarVitoria()) {
                renderizarTabuleiro();
                finalizarComVencedor(jogadores[jogadorAtual]);
                jogadores[jogadorAtual].ganhar();

                reiniciarJogo();
            } else {
                // check if there is any -1 in the board
                boolean hasEmpty = false;

                for (int linhaAtual = 0; linhaAtual < 3; linhaAtual++) {
                    for (int colunaAtual = 0; colunaAtual < 3; colunaAtual++) {
                        if (tabuleiro.get(linhaAtual).get(colunaAtual) == -1) {
                            hasEmpty = true;
                        }
                    }
                }

                if (!hasEmpty) {
                    renderizarTabuleiro();
                    finalizarComVelha();
                    reiniciarJogo();
                }
            }

            if (jogadorAtual == 0) {
                jogadorAtual = 1;
            } else {
                jogadorAtual = 0;
            }
        }
    }
}
