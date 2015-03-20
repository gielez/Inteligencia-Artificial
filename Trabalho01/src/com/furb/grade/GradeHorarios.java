package com.furb.grade;

import java.util.ArrayList;
import java.util.List;

import com.furb.disciplina.Disciplina;

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

	public void setDisciplinasSemestre(DisciplinasSemestre semestre) {
		this.ds.add(semestre);
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

	public void setDias(DisciplinaDoDia[] dia) {
		this.dias = dia;
	}
}

class DisciplinaDoDia
{
	private Disciplina primeiraAula;
	private Disciplina segundaAula;
	
	public Disciplina getPrimeiraAula()
	{
		return primeiraAula;
	}
	public void setPrimeiraAula(Disciplina primeiraAula)
	{
		this.primeiraAula = primeiraAula;
	}
	public Disciplina getSegundaAula()
	{
		return segundaAula;
	}
	public void setSegundaAula(Disciplina segundaAula)
	{
		this.segundaAula = segundaAula;
	}	
	
}
