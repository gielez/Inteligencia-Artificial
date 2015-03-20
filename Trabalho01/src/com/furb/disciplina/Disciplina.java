package com.furb.disciplina;

public class Disciplina {
	private String nome;
	private Disciplina preRequisito;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Disciplina getPreRequisito() {
		return preRequisito;
	}
	public void setPreRequisito(Disciplina preRequisito) {
		this.preRequisito = preRequisito;
	}
	
	

}
