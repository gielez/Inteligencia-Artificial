package com.furb.disciplinas;

public class Disciplinas {
	private String nome;
	private String[] horário = new String[2];
	private String[] diaSemana = new String[2];
	private Disciplinas preRequisito;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getHorário(int posicao) {
		return horário[posicao];
	}
	public void setHorário(int posicao, String horário) {
		this.horário[posicao] = horário;
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
