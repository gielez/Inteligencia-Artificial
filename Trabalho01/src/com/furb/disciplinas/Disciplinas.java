package com.furb.disciplinas;

public class Disciplinas
{
	private String nome;
	private Disciplinas preRequisito;

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public Disciplinas getPreRequisito()
	{
		return preRequisito;
	}

	public void setPreRequisito(Disciplinas preRequisito)
	{
		this.preRequisito = preRequisito;
	}

}