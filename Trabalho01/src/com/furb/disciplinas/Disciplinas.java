package com.furb.disciplinas;

public class Disciplinas {
	private String nome;
	private String[] hor�rio = new String[2];
	private String[] diaSemana = new String[2];
	private Disciplinas preRequisito;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getHor�rio(int posicao) {
		return hor�rio[posicao];
	}
	public void setHor�rio(int posicao, String hor�rio) {
		this.hor�rio[posicao] = hor�rio;
	}
	public String getDiaSemana(int posicao) {
		return diaSemana[posicao];
	}
	public void setDiaSemana(int posicao, String diaSemana) {
		this.diaSemana[posicao] = diaSemana;
	}
	public Disciplinas getPreRequisito() {
		return preRequisito;
	}
	public void setPreRequisito(Disciplinas preRequisito) {
		this.preRequisito = preRequisito;
	}
	
	

}
