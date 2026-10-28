package simulazione11;

import java.util.Objects;

public class Pacco {
	private String codice;
	private String destinatario;
	private double peso;
	private int tipo;
	private boolean consegnato;
	
	
	public Pacco(String codice, String destinatario, double peso, int tipo) throws PaccoException{
		if(codice == null || codice.trim().isEmpty() || destinatario == null || destinatario.trim().isEmpty()) {
			throw new PaccoException("errore nelle stringhe");
		}
		if(peso <= 0 || !TipoPacco.valido(tipo)) {
			throw new PaccoException("errore nel peso o tipo");
		}
		
		this.codice = codice;
		this.destinatario = destinatario;
		this.peso = peso;
		this.tipo = tipo;
		this.consegnato = false;
	}
	
	public String getCodice() {return codice;}
	public String getDestinatario() {return destinatario;}
	public double getPeso() {return peso;}
	public int getTipo() {return tipo;}
	public boolean isConsegnato() {return consegnato;}
	
	public void consegna() {
		this.consegnato = true;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null || !(obj instanceof Pacco)) {
			return false;
		}
		
		Pacco app = (Pacco) obj;
		
		return codice.equals(app.getCodice());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(codice);
	}
	
	public String toString() {
		return "\ncodice: " + codice +"\ndestinatario: " + destinatario + "\npeso: " + peso + "\ntipo: " + tipo + "\nconsegnato: " + consegnato;
	}
}
