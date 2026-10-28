package simulazione11;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class RegistroPacchi {
	private String nomeCentro;
	protected Map<Integer, Integer> postiDisponibili;
	protected List<Pacco> pacchi;
	
	public RegistroPacchi(String nomeCentro, Map<Integer, Integer> postiDisponibili) throws PaccoException{
		if(nomeCentro == null || nomeCentro.trim().isEmpty()){
			throw new PaccoException("errore stringa");
		}
		if(postiDisponibili == null) {
			throw new PaccoException("no posti disponibili");
		}
		this.nomeCentro = nomeCentro;
		this.postiDisponibili = postiDisponibili;
		this.pacchi = new LinkedList<>();
	}
	
	public String getNomeCentro() {return nomeCentro;}
	public int getNumeroPacchi() {return pacchi.size();}
	
	protected abstract double calcolaCosto(Pacco pacco) throws PaccoException;
	
	public void aggiungiPacco(Pacco pacco) throws PaccoException{
		if(pacco == null) {
			throw new PaccoException("pacco null");
		}
		if(!postiDisponibili.containsKey(pacco.getTipo())) {
			throw new PaccoException("tipo non presente");
		}
		int quantita = postiDisponibili.get(pacco.getTipo());
		if(quantita <= 0) {
			throw new PaccoException("non ci son posti disponibili per quel tipo");
		}
		if(pacchi.contains(pacco)) {
			throw new PaccoException("pacco gia presente");
		}		
		pacchi.add(pacco);
		postiDisponibili.put(pacco.getTipo(), quantita - 1);
	}
	
	public boolean consegnaPacco(String codice) throws PaccoException{
		if(codice == null || codice.trim().isEmpty()) {
			throw new PaccoException("codice non valido");
		}
		for(Pacco p : pacchi) {
			if(codice.equals(p.getCodice())) {
				p.consegna();
				return true;
			}
		}
		return false;
	}
	
	public List<Pacco> pacchiPerTipo(int tipo) throws PaccoException{
		if(!TipoPacco.valido(tipo)){
			throw new PaccoException("errore tipo");
		}
		List<Pacco> nuova = new LinkedList<>();
		for(Pacco p : pacchi) {
			if(p.getTipo() == tipo) {
				nuova.add(p);
			}
		}
		return new LinkedList<>(nuova);
	}
	
	public double pesoTotaleNonConsegnati() {
		double conto=0;
		for(Pacco p : pacchi) {
			if(!p.isConsegnato()) {
				conto += p.getPeso();
			}
		}
		return conto;
	}
	
	@SuppressWarnings("resource")
	public void salvaReport(String nomeFile) throws PaccoException{
		if(nomeFile == null || nomeFile.trim().isEmpty()) {
			throw new PaccoException("errore nome");
		}
		try {
			PrintWriter pw = new PrintWriter(new FileOutputStream(nomeFile));
			
			int numeroCosengati = 0;
			int	numeroNonConsengati = 0;
			
			for(Pacco p : pacchi) {
				if(p.isConsegnato()) {
					numeroCosengati++;
				}
				else {
					numeroNonConsengati++;
				}
			}
		
			String stringa = "\n REPORT PACCHI\n \n --------------------\n"
					+ "\n||centro: " + nomeCentro
					+ "\n||totale pacchi: " +getNumeroPacchi()
					+ "\n||consegnati: " + numeroCosengati 
					+ "\n||numeroNonConsegnati: " + numeroNonConsengati
					+ "\n||peso non consengati: " + pesoTotaleNonConsegnati();
			
			pw.println(stringa);
			if(pw.checkError()) {
				throw new PaccoException("errore scrittura prima parte");
			}
			pw.close();
		}catch(IOException e) {
			throw new PaccoException("errore scrittura prima parte");
		}
	}
	
	
	
	
	
}
