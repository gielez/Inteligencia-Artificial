package com.furb.aluno;

import java.util.List;

import com.furb.disciplina.Disciplina;

public class Aluno
{
	private String nome;
	private List<Disciplina> disciplinasAprovado;

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}
	public List<Disciplina> getDisciplinasAprovado() 
	{
		return disciplinasAprovado;
	}

	public void setDisciplinasAprovado(List<Disciplina> disciplinasAprovado)
	{
		this.disciplinasAprovado = disciplinasAprovado;
	}

}
