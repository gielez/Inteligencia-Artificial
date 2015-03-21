package com.furb.grade;

import java.util.LinkedList;
import java.util.List;

import com.furb.aluno.Aluno;
import com.furb.disciplina.Disciplina;

import busca.Antecessor;
import busca.Estado;

public class EstadoGrade implements Estado, Antecessor{
	final DisciplinaDoDia gradeSemestreAluno = new DisciplinaDoDia();
	final List<Disciplina> disciplinasCursadas;
	final GradeCurricular grade;
	
	public EstadoGrade(Aluno aluno, GradeCurricular grade) {
		disciplinasCursadas = aluno.getDisciplinasAprovado();
		this.grade = grade; 
	}

	@Override
	public int custo() {
		return 1;
	}

	@Override
	public boolean ehMeta() {
		//TODO: Meta: maior preenchimento possivel da carga hor�ria
		return false;
	}

	@Override
	public List<Estado> sucessores() {
		List<Estado> suc = new LinkedList<Estado>(); // a lista de sucessores
		
		//se o horario esta vago preenche
		
		return null;
	}
	
	public boolean ehValido(){
		//n�o esta na lista de mat�rias concluidas
		
		//existe outra mat�ria selecionada nesse hor�rio
		
		//possui o pre requisito em aberto
		
		return false;
	}

	@Override
	public List<Estado> antecessores() {
		//busca bidirecional(Ex: MissionarioCanibal
		return sucessores();
	}

}
