package br.ufrpe.paradigmas.projeto;

import java.io.IOException;
import java.util.ArrayList;

public class Player {

	ArrayList<Carta> carta;
	ArrayList<Maos> mao;
	Regras ranking;
	private int cartaAlta;
	private int cartaRanking;
	private int kicker;

	public Player() {
		this.carta = new ArrayList<Carta>();
		this.mao = new ArrayList<Maos>();
		this.ranking = new Regras();
	}

	public void setCarta(int valor, char naipe) {
		Carta c = new Carta(valor, naipe);
		carta.add(c);
	}

	public void setMao(Maos mao) {
		this.mao.add(mao);
	}

	public int getCartaAlta() {
		return cartaAlta;
	}

	public void setCartaAlta(int cartaAlta) {
		this.cartaAlta = cartaAlta;
	}

	public int getCartaRanking() {
		return cartaRanking;
	}

	public void setCartaRanking(int cartaRanking) {
		this.cartaRanking = cartaRanking;
	}

	public int getKicker() {
		return kicker;
	}

	public void setKicker(int kicker) {
		this.kicker = kicker;
	}
	
//	public void printMao() throws IOException {
//		for (Carta carta2 : carta) {
//			System.out.print(carta2.getValor() + "" + carta2.getNaipe() + " ");
//		}
//		System.out.println();
//	}
}
