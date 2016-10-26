package br.ufrpe.paradigmas.projeto;

import java.io.IOException;
import java.util.ArrayList;

public class Player {

	ArrayList<Carta> carta;
	ArrayList<Maos> mao;
	private byte cartaAlta;
	private byte cartaRanking;

	public Player() {
		this.carta = new ArrayList<Carta>();
		this.mao = new ArrayList<Maos>();
	}

	public void setCarta(byte valor, char naipe) {
		Carta c = new Carta(valor, naipe);
		carta.add(c);
	}

	public void setMao(Maos mao) {
		if (this.mao.size() == 0)
			this.mao.add(mao);
		else
			this.mao.set(this.mao.lastIndexOf(this.mao), mao);
	}

	public byte getCartaAlta() {
		return cartaAlta;
	}

	public void setCartaAlta(byte cartaAlta) {
		this.cartaAlta = cartaAlta;
	}

	public byte getCartaRanking() {
		return cartaRanking;
	}

	public void setCartaRanking(byte cartaRanking) {
		this.cartaRanking = cartaRanking;
	}

	public void printMao() throws IOException {
		for (Carta carta2 : carta) {
			System.out.print(carta2.getValor() + "" + carta2.getNaipe() + " ");
		}
		System.out.println();
	}
}
