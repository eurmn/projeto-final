# Lucas Garrafielo - Projeto Final de Linguagem de Programação I: Jogo da Velha

Grupo: Lucas Garrafielo

Foi utilizado na construção do código a ferramenta Gradle, todavia, é possível compilar o código manualmente também.

## Introdução

O projeto escolhido foi o de jogo da velha. Mesmo sendo um projeto simples, tentei aplicar o máximo dos conceitos requisitados no arquivo de orientações.

## Modelagem do Problema

O programa é formado de 5 classes:

### Jogador.java:

Uma classe abstrata que representa o jogador. Jogadores reais do jogo devem ser instanciados a partir de suas respectivas classes específicas `JogadorX` ou `JogadorO`, dependendo de qual símbolo o jogador representa no tabuleiro.

Possui 2 variáveis e 3 métodos, sendo as variáveis: o caractere que representa o jogador no tabuleiro (`X` ou `O`) e a sua quantidade de vitórias. Os seus métodos são: seu construtor, que recebe como parâmetro o caractere do jogador, o método `getVitorias` que retorna a variável privada de vitórias (encapsulamento) e `ganhar`, que adiciona uma vitória.

### JogadorX.java:

Um jogador que possui o caractere `X` no tabuleiro.

### JogadorY.java:

Um jogador que possui o caractere `Y` no tabuleiro.

### Jogo.java:

Uma classe estática que permite toda a manipulação do jogo, inclusive:
 - Jogar;
 - Buscar por vitórias;
 - Renderizar o tabuleiro;
 - Ganhar com vencedor;
 - Ganhar com velha;
 - Reiniciar jogo;
 - Perguntar se o usuário quer sair do jogo.

### App.java:

Contém apenas a função `main` para iniciar o programa (chama o método `iniciarJogo` da classe `Jogo`).