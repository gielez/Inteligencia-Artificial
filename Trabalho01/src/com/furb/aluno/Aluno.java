package com.furb.aluno;

import java.util.List;

import com.furb.disciplinas.Disciplinas;

public class Aluno
{
	private String nome;
	private List<Disciplinas> disciplinasAprovado;

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public List<Disciplinas> getDisciplinasAprovado()
	{
		return disciplinasAprovado;
	}

	public void setDisciplinasAprovado(List<Disciplinas> disciplinasAprovado)
	{
		this.disciplinasAprovado = disciplinasAprovado;
	}

}
