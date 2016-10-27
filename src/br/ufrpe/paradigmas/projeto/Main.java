package br.ufrpe.paradigmas.projeto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

public class Main {

	private static BufferedReader in;
	private static PrintWriter out;

	public static void main(String[] args) throws IOException {

		// CRIAÇÃO DOS ARQUIVOS DE LEITURA E ESCRITA
		in = new BufferedReader(new FileReader("ArquivosEntrada/pokerK.txt"));
		out = new PrintWriter(new FileWriter("ArquivosEntrada/pokerK_out.txt"));

		// JOGADORES
		Player p1 = new Player();
		Player p2 = new Player();

		String poker = in.readLine();
		byte value;
		int rodada = 1;

		do {
			for (byte i = 0; i < poker.length(); i++) {
				// INSERÇÃO DAS CARTAS DO PRIMEIRO JOGADOR
				if (i < poker.length() / 2) {
					// VERIFICAÇÃO DO TIPO DE CARTA (A, K, Q, J ,T)
					value = verificaNumero(poker.charAt(i++));
					p1.setCarta(value, poker.charAt(i++));
				}

				// INSERÇÃO DAS CARTAS DO SEGUNDO JOGADOR
				if ((i > poker.length() / 2)) {
					value = verificaNumero(poker.charAt(i++));
					p2.setCarta(value, poker.charAt(i++));
				}
			}

			// ORDENAÇÃO DAS CARTAS DOS JOGADORES
			Collections.sort(p1.carta, new ComparadorDeCartas());
			Collections.sort(p2.carta, new ComparadorDeCartas());

			// EXECUÇÃO DA VERIFICAÇÃO DAS MAÕS DE POKER
			out.println("JOGO " + rodada++);
			p1.ranking.rankingMao(p1);
			p2.ranking.rankingMao(p2);

			verificaVencedor(p1, p2, out);

			// LIMPEZA DO ARRAY PARA NOVAS PARTIDAS
			p1 = new Player();
			p2 = new Player();

			// LEITURA DA PRÓXIMA LINHA DO ARQUIVO
			poker = in.readLine();
		} while (poker != null);
	}

	// VERIFICAÇÃO DA MÃO VENCEDORA
	public static void verificaVencedor(Player p1, Player p2, PrintWriter out) {
		byte cardP1 = (byte) p1.mao.get(p1.mao.size() - 1).ordinal();
		byte cardP2 = (byte) p2.mao.get(p2.mao.size() - 1).ordinal();

		// JOGADOR 1
		out.print("Mão Player 1: " + p1.mao.toString() + "\nCartas: ");
		for (Carta card : p1.carta) {
			out.print(card.getValor() + "" + card.getNaipe() + " ");
		}
		out.println("\nCarta Ranking: " + p1.getCartaRanking());
		out.println("Carta Alta: " + p1.getCartaAlta());
		out.println();
		// JOGADOR 2
		out.print("Mão Player 2: " + p2.mao.toString() + "\nCartas: ");
		for (Carta card : p2.carta) {
			out.print(card.getValor() + "" + card.getNaipe() + " ");
		}
		out.println("\nCarta Ranking: " + p2.getCartaRanking());
		out.println("Carta Alta: " + p2.getCartaAlta());
		out.println();

		if (cardP1 > cardP2)
			out.println("Vencedor: Player 1");
		else if (cardP1 < cardP2)
			out.println("Vencedor: Player 2");

		else if (p1.getCartaRanking() > p2.getCartaRanking())
			out.println("Vencedor: Player 1");
		else if (p1.getCartaRanking() < p2.getCartaRanking())
			out.println("Vencedor: Player 2");

		else if (p1.getCartaAlta() > p2.getCartaAlta())
			out.println("Vencedor: Player 1");
		else if (p1.getCartaAlta() < p2.getCartaAlta())
			out.println("Vencedor: Player 2");

		else if (p1.getKicker() > p2.getKicker())
			out.println("Vencedor: Player 1");
		else if (p1.getKicker() < p2.getKicker())
			out.println("Vencedor: Player 2");
		
		else
			out.println("Empate");
		out.println();

	}

	// FUNÇÃO PARA ADICIONAR O VALOR REAL DAS CARTAS (A, K, Q, J, T)
	private static byte verificaNumero(char c) {
		byte number = 0;

		switch (c) {
		case 'A':
			number = 14;
			break;
		case 'K':
			number = 13;
			break;
		case 'Q':
			number = 12;
			break;
		case 'J':
			number = 11;
			break;
		case 'T':
			number = 10;
			break;
		default:
			number = (byte) Character.getNumericValue(c);
		}
		return number;
	}
}
