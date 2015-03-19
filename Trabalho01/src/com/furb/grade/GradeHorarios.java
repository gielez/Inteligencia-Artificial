package com.furb.grade;

import java.util.ArrayList;
import java.util.List;

import com.furb.disciplinas.Disciplinas;

public class GradeHorarios
{	
	private List<DisciplinasSemestre> ds;
	
	public GradeHorarios()
	{
		ds = new ArrayList<DisciplinasSemestre>(8);
	}

	public List<DisciplinasSemestre> getDisciplinasSemestre()
	{
		return ds;
	}
	
}

class DisciplinasSemestre
{
	private DisciplinaDoDia[] dias;
	
	public DisciplinasSemestre()
	{
		dias = new DisciplinaDoDia[5];
	}
	
	public DisciplinaDoDia[] getDias()
	{
		return dias;
	}
}

class DisciplinaDoDia
{
	private Disciplinas primeiraAula;
	private Disciplinas segundaAula;
	
	public Disciplinas getPrimeiraAula()
	{
		return primeiraAula;
	}
	public void setPrimeiraAula(Disciplinas primeiraAula)
	{
		this.primeiraAula = primeiraAula;
	}
	public Disciplinas getSegundaAula()
	{
		return segundaAula;
	}
	public void setSegundaAula(Disciplinas segundaAula)
	{
		this.segundaAula = segundaAula;
	}	
	
}
