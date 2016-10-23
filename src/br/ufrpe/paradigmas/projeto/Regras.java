package br.ufrpe.paradigmas.projeto;

//REGRAS DO POKER - https://www.pokerstars.com/br/poker/games/rules/hand-rankings/

public class Regras {

	private boolean verifica;
	private static byte v;
	private char naipe;
	private boolean par = false;
	private boolean doisPares = false;
	private boolean trinca = false;
	private boolean quadra = false;

	// VERIFICA SE TODAS AS CARTAS POSSUEM NAIPES IGUAIS
	public boolean verificaNaipes(Player player) {
		this.naipe = player.getNaipeIndex((byte) 0);
		for (byte i = 1; i < 5; i++) {
			if (this.naipe != player.getNaipeIndex(i))
				return false;
		}
		return true;
	}
	
	// VERIFICA SE EXISTE UMA SEQU�NCIA
	public boolean verificaSequencia(Player player){
		v = player.getValorIndex((byte) 0); // PEGA O 1� VALOR
		for (byte i = 1; i < 5; i++) {
			if ((v + 1) == player.getValorIndex(i)) // COMPARA COM O VALOR SEGUINTE A SOMA DE 1
				v = player.getValorIndex(i); 		// CASO SEQUENCIE ADICIONA O VALOR A v
			else
				return false; // CASO N�O, ALTERA VERIFICA PARA FALSE E SAI DO LA�O
		}
		return true;
	}

	//ANALISE DOS RANKINGS RELACIONADOS A M�O DO JOGADOR
	public void rankingMao(Player player) {
		
		/**
		 * CARTA ALTA
		 */
		player.setCartaAlta(player.getValorIndex((byte)4));
		
		/**
		 * PAR
		 */
		
		v = player.getValorIndex((byte)0); //v recebe a primeira carta
		byte b = 0;
		for(byte i = 1; i < 5;){       //i recebe valores de 1 a 4, pois al�m da carta 0, teremos mais 4 cartas
		    byte a = 1;                    //a vari�vel a serve para no fim do for chamar o proximo valor de v 
		    
			if(v == player.getValorIndex(i)){  //se a carta v for igual a carta correspondente i
				par = true;					   //teremos duas cartas iguais
				a += 1;                        //incrementos a com mais 1, pois temos um par
				
				if(player.getCartaRanking() == 0) //se a o valor da carta ranking estiver com o valor 0 � porque n�o temos nenhuma ranking
					player.setCartaRanking(v);    //e portanto setamos-a com o valor da carta v
				
				else 
				{							  	
					player.setCartaRanking(v);    //sen�o estiver com o valor 0, setamos com o novo valor de v
					doisPares = true; 			  //mas teremos dois pares nas cartas, setamos dois pares como true
					par = false;                  //e tornamos par como false
				}
				if(v == player.getValorIndex((byte) (i+1)) && i < 4){//podemos ter uma trinca se v tem o valor igual a posi��o do conteudo 
																	 //do index i+1 e testamos se i � menor que 4, pois sen�o i poderia ser
																	 //5 e o valor 5 n�o � valido para i.
					player.setCartaRanking(v);
					trinca = true;                                   //trinca setada como true
					par = false; 									 //par setada como false, pois uma trinca tem valor maior 
																	 //que um par e o que nos importa � o peso maior
					a += 1;											 //a � incrementado pois dentro do IFs aninhados encontramos uma trinca
					
					if(v == player.getValorIndex((byte)(i+2)) && i < 3){//podemos ter uma quadra se v tem o valor igual a posi��o do  
																		//conteudo do index i+2 e testamos se i � menor que 3, pois sen�o i 
																		//poderia ser 5 e o valor 5 n�o � valido para i.
						player.setCartaRanking(v);
						quadra = true;									//quadra setada como true;
						trinca = false;									//trinca setada como false, pois uma quadra tem valor maior 
						 												//que um trinca e o que nos importa � o peso maior
						a += 1;                                      //a � incrementado pois dentro do IFs aninhados encontramos uma trinca
					}
				}
			}
			switch (a) { //switch case utilizado para incrementar i pela posi��o que encontramos solu��es de par, trinca, quadra no codigo
			case 2: //par
				i += 2;
				break;
			case 3: //trinca
				i += 3;
				break;
			case 4: //quadra
				i += 4;
				break;
			default:
				i += 1;
				break;
			}
			b += a;
			
			if (b < 4){
				v = player.getValorIndex(b); //proxima posi��o de v relativo ao valor que temos de a depois de executar o codigo for
			}
			//PODE USAR O BREAK NESSAS CONDI��ES, COMO UMA CONDI��O DE PARADA
			else{
				break;
			}
		}
		
		
		/**
		 * DOIS PARES
		 */
		
		/**
		 * TRINCA
		 */
		
		/**
		 * SEQU�NCIA
		 */
		// VERIFICA��O DA SEQUENCIA
		verifica = verificaSequencia(player);
		// SE A VERIFICA INDICAR UMA SEQUENCIA  
		if (verifica == true)
			player.setMao(Maos.sequencia);
		
		/**
		 * FLUSH
		 */
		// VERIFICA SE OS NAIPES SAO IGUAIS, SE SIM INDICA QUE EXISTE UM FLUSH
		verifica = verificaNaipes(player);
		if(verifica == true)
			player.setMao(Maos.flush);

		/**
		 * FULL HOUSE  (UM PAR E UMA TRINCA)
		 */
		
		
		/**
		 * QUADRA
		 */

		/**
		 * STRAIGHT FLUSH
		 */
		
		// S� ENTRA NO CASO SE OS NAIPES DE TODAS AS CARTAS FOREM O MESMO
		if (verifica == true) {
			verifica = verificaSequencia(player);
			// SE A COMPARA��O INDICAR UMA SEQUENCIA RETORNA STRAIGHT FLUSH
			if (verifica == true)
				player.setMao(Maos.straightFlush);
		}
		
		/**
		 * ROYAL FLUSH
		 */
		
		verifica = verificaNaipes(player);

		// SE TODOS OS NAIPES FOREM IGUAIS, VERIFICA SE EST� SEQUENCIADO DE (A, K, Q, J, T)
		if (verifica == true) {
			if (player.getValorIndex((byte) 4) == 14)
				if (player.getValorIndex((byte) 3) == 13)
					if (player.getValorIndex((byte) 2) == 12)
						if (player.getValorIndex((byte) 1) == 11)
							if (player.getValorIndex((byte) 0) == 10)
								player.setMao(Maos.royalFlush);
		}


		
	}
}
