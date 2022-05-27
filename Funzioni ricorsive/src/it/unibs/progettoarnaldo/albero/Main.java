package it.unibs.progettoarnaldo.albero;

import it.unibs.progettoarnaldo.eulero.InputDati;

public class Main {

	public static void main(String[] args) {
	
		//non metto il private poichè sono dentro un metodo, solo nelle classi devi metterli privati
		String espressione = "";
		espressione = InputDati.leggiStringaNonVuota("inserire un'espressione aritmetica");
		espressione = espressione.replaceAll(" ", ""); //elimino gli spazi vuoti
		System.out.println("il risultato di " + espressione + " è " + calcolaEspressione(espressione));
	}
	
	/**
	 * metodo principale che calcola l'espressione in modo ricorsivo
	 * @param espressione
	 * @return
	 */
	public static String calcolaEspressione(String espressione) {
		
		double calcolo = 0;
		int posizioneParentesi = -1;
		int j = 0;
		int l = 0;
		boolean trovato = false;
		boolean nessunaOperazione = false;
		boolean togliParentesi = false;
		String temp01 = "";
		String temp02 = "";
		
		//posizione parentesi inizio e fine
		for(int i = 0; i < espressione.length(); i++) {
			if(espressione.charAt(i) == '(') {	
				posizioneParentesi = i;
				for(j = i + 1; espressione.charAt(j) != ')'; j++) {
					if(espressione.charAt(j) == '(') {
						posizioneParentesi = j;
					}
				}
			}
		}	
		//calcolo operazione aritmetica dentro la parentesi trovata
		if(posizioneParentesi != -1) {
			for(int k = posizioneParentesi; k < j; k++) {
				if(espressione.charAt(k) == '*') { //caso di moltiplicazione calcolando prima e dopo l'operatore e rimodificando la stringa
					temp01 = componiNumero1(espressione, k);
					temp02 = componiNumero2(espressione, k);
					calcolo = Double.parseDouble(temp01) * Double.parseDouble(temp02);
					espressione = espressione.substring(0, k - temp01.length()) + calcolo + espressione.substring(k + temp02.length());
					trovato = true;
					break;
				}
				else if(espressione.charAt(k) == '/' ) { //caso di divisione calcolando prima e dopo l'operatore e rimodificando la stringa
					temp01 = componiNumero1(espressione, k);
					temp02 = componiNumero2(espressione, k);
					calcolo = Double.parseDouble(temp01) / Double.parseDouble(temp02);
					espressione = espressione.substring(0, k - temp01.length()) + calcolo + espressione.substring(k + temp02.length());
					trovato = true;
					break;
				}
			}
			//caso in cui non siano più presenti il "*" e "/" dunque effettuo parte rimanente con addizione e sotrazzione
			if(!trovato) {
				for(l = posizioneParentesi; l < j; l++) {
					if(espressione.charAt(l) == '+') {
						temp01 = componiNumero1(espressione, l);
						temp02 = componiNumero2(espressione, l);
						calcolo = Double.parseDouble(temp01) + Double.parseDouble(temp02);
						espressione = espressione.substring(0, l - temp01.length()) + calcolo + espressione.substring(l + temp02.length());
						nessunaOperazione = true;
						break;
					}
					else if(espressione.charAt(l) == '-' ) {
						temp01 = componiNumero1(espressione, l);
						temp02 = componiNumero2(espressione, l);
						calcolo = Double.parseDouble(temp01) - Double.parseDouble(temp02);
						espressione = espressione.substring(0, l - temp01.length()) + calcolo + espressione.substring(l + temp02.length());
						nessunaOperazione = true;
						break;
					}
				}
				//le operazioni aritmetiche sono finite, tolgo le parentesi
				for(int i = posizioneParentesi + 1; i < l + 1; i++) {
					if(espressione.charAt(i) == '*'||espressione.charAt(i) == '/'||espressione.charAt(i) == '+'||espressione.charAt(i) == '-') {
						togliParentesi = true;
					}
				}			
				if(!togliParentesi) {
					espressione = espressione.replace(espressione.charAt(l - componiNumero1(espressione, l + 1).length()), ' ');
					espressione = espressione.replace(espressione.charAt(l + 1), ' '); //posizione ultima parentesi
					espressione = espressione.replaceAll(" ", "");
					
				}		
			}
		}
		//stesso procedimento rispetto all'ultimo solo senza le parentesi, dunque calcolando l'espressione totale
		else if(!nessunaOperazione){
			for(int k = 0; k < espressione.length(); k++) {
				if(espressione.charAt(k) == '*') {
					temp01 = componiNumero1(espressione, k);
					temp02 = componiNumero2(espressione, k);
					calcolo = Double.parseDouble(temp01) * Double.parseDouble(temp02);
					espressione = espressione.substring(0, k - temp01.length()) + calcolo + espressione.substring(k + temp02.length());
					trovato = true;
					break;
				}
				else if(espressione.charAt(k) == '/' ) {
					temp01 = componiNumero1(espressione, k);
					temp02 = componiNumero2(espressione, k);
					calcolo = Double.parseDouble(temp01) / Double.parseDouble(temp02);
					espressione = espressione.substring(0, k - temp01.length()) + calcolo + espressione.substring(k + temp02.length());
					trovato = true;
					break;
				}
			}
			if(!trovato) {
				for(int k = 0; k < espressione.length(); k++) {
					if(espressione.charAt(k) == '+') {
						temp01 = componiNumero1(espressione, k);
						temp02 = componiNumero2(espressione, k);
						calcolo = Double.parseDouble(temp01) + Double.parseDouble(temp02);
						espressione = espressione.substring(0, k - temp01.length()) + calcolo + espressione.substring(k + temp02.length());
						nessunaOperazione = true;
						break;
					}
					else if(espressione.charAt(k) == '-' ) {
						temp01 = componiNumero1(espressione, k);
						temp02 = componiNumero2(espressione, k);
						calcolo = Double.parseDouble(temp01) - Double.parseDouble(temp02);
						espressione = espressione.substring(0, k - temp01.length()) + calcolo + espressione.substring(k + temp02.length());
						nessunaOperazione = true;
						break;
					}
				}
			}
		}
		//caso finale in cui siano state fatte tutte le operazioni e non siano più presenti le parentesi
		if(!nessunaOperazione && !trovato) {
			return espressione;
		}
		//ricorsione nel caso fosse presente un'altra operazione aritmetica 
		else {
			return calcolaEspressione(espressione);
		}
	}
	
	/**
	 * metodo che controlla i caratteri prima dell'operatore aritmetico
	 * @param espressione
	 * @param pos
	 * @return
	 */
	public static String componiNumero1(String espressione, int pos) {
		
		String temp1 = "";
		int i = 0;
		//va indietro dalla posizione passata finchè non incontra un'operatore o una parentesi
		for (i = pos - 1; i >= 0; i--) {
			if(espressione.charAt(i) == '('||espressione.charAt(i) == ')'||espressione.charAt(i) == '*'||espressione.charAt(i) == '/'||espressione.charAt(i) == '+'||espressione.charAt(i) == '-') {
				break;
			}
		}	
		//salva i valori nella stringa passata al contrario
		for(int j = i +1; j < pos; j++) {			
			temp1 += espressione.charAt(j);
		}	
		return temp1;
	}
	
	/**
	 * metodo che controlla i caratteri dopo l'operatore aritmetico
	 * @param espressione
	 * @param pos
	 * @return
	 */
	public static String componiNumero2(String espressione, int pos) {
		
		String temp2 = "";
		//va avanti dalla posizione passata finchè non incontra un'operatore o una parentesi
		for(int i = pos + 1; i < espressione.length(); i++) {
			if(espressione.charAt(i) == '('||espressione.charAt(i) == ')'||espressione.charAt(i) == '*'||espressione.charAt(i) == '/'||espressione.charAt(i) == '+'||espressione.charAt(i) == '-') {
				break;
			}
			//salva i valori nella stringa passata
			else {
				temp2 += espressione.charAt(i);
			}
		}	
		return temp2;
	}

}
