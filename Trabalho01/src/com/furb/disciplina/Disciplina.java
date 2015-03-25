package com.furb.disciplina;

import java.util.ArrayList;
import java.util.List;

import com.furb.grade.DiaSemana;

public class Disciplina
{
	private String nome;
	private Disciplina preRequisito;
	private List<DiaSemana> dia = new ArrayList<DiaSemana>(2);

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public Disciplina getPreRequisito()
	{
		return preRequisito;
	}

	public void setPreRequisito(Disciplina preRequisito)
	{
		this.preRequisito = preRequisito;
	}

	public List<DiaSemana> getDiaHorario()
	{
		return dia;
	}

	public void setDia(DiaSemana novoDia)
	{
		this.dia.add(novoDia);
	}

}
