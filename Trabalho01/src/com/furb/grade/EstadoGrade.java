package com.furb.grade;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.furb.aluno.Aluno;
import com.furb.disciplina.Disciplina;

import busca.Antecessor;
import busca.BuscaLargura;
import busca.Estado;
import busca.Nodo;

public class EstadoGrade implements Estado, Antecessor{
	final List<Disciplina> novaGradeAluno;
	final List<Disciplina> disciplinasCursadas;
	final List<Disciplina> disciplinasOfertadas;
	final Aluno aluno;
	
	public EstadoGrade(Aluno aluno, List<Disciplina> ofertadas, List<Disciplina> novo) {
		this.aluno = aluno;
		this.disciplinasCursadas = aluno.getDisciplinasAprovado();
		this.disciplinasOfertadas = ofertadas; 
		this.novaGradeAluno = novo;
	}

	@Override
	public int custo() {
		return 1;
	}

	@Override
	public boolean ehMeta() {
		//TODO: Meta: maior preenchimento possivel da carga horária
		if(novaGradeAluno.size() == 5){
			return true;
		}
		return false;
	}

	@Override
	public List<Estado> sucessores() {
		List<Estado> suc = new LinkedList<Estado>(); // a lista de sucessores
		
		//se o horario esta vago preenche
		for (Disciplina disciplina : disciplinasOfertadas) {
			if(ehValido(disciplina))
			{
				novaGradeAluno.add(disciplina);
				EstadoGrade novo = new EstadoGrade(this.aluno , this.disciplinasOfertadas, this.novaGradeAluno);
				suc.add(novo);
			}
			
		}
		
		return suc;
	}
	
	public boolean ehValido(Disciplina disciplina){
		//não esta na lista de matérias concluidas
		if(disciplinasCursadas.contains(disciplina)){
			return false;
		}
		
		//existe outra matéria selecionada nesse horário
		List<DiaSemana> dia = disciplina.getDiaHorario();
		for (DiaSemana diaSemana : dia) {//pega os 2 dias que possuem a disciplina
			for (Disciplina discAluno : novaGradeAluno) {//percorre a grade do aluno(que pode conter até 5 disciplinas)
				List<DiaSemana> diaAluno = discAluno.getDiaHorario();
				for (DiaSemana horarioAluno : diaAluno) {//percorre a lista de disciplinas do aluno, validando se alguma disciplina coincide com o horario e dia da semana
					if(diaSemana.getDia() == horarioAluno.getDia() && diaSemana.getHorario() == horarioAluno.getHorario()){
						return false;
					}
					
				}
				
			}
		}
		
		//possui o pre requisito em aberto
		if(disciplina.getPreRequisito() != null){
			if(!disciplinasCursadas.contains(disciplina.getPreRequisito())){
				return false;
			}
			
		}
		
		return true;
	}

	@Override
	public List<Estado> antecessores() {
		//busca bidirecional(Ex: MissionarioCanibal
		return sucessores();
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aluno == null) ? 0 : aluno.hashCode());
		result = prime
				* result
				+ ((disciplinasCursadas == null) ? 0 : disciplinasCursadas
						.hashCode());
		result = prime
				* result
				+ ((disciplinasOfertadas == null) ? 0 : disciplinasOfertadas
						.hashCode());
		result = prime * result
				+ ((novaGradeAluno == null) ? 0 : novaGradeAluno.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstadoGrade other = (EstadoGrade) obj;
		if (aluno == null) {
			if (other.aluno != null)
				return false;
		} else if (!aluno.equals(other.aluno))
			return false;
		if (disciplinasCursadas == null) {
			if (other.disciplinasCursadas != null)
				return false;
		} else if (!disciplinasCursadas.equals(other.disciplinasCursadas))
			return false;
		if (disciplinasOfertadas == null) {
			if (other.disciplinasOfertadas != null)
				return false;
		} else if (!disciplinasOfertadas.equals(other.disciplinasOfertadas))
			return false;
		if (novaGradeAluno == null) {
			if (other.novaGradeAluno != null)
				return false;
		} else if (!novaGradeAluno.equals(other.novaGradeAluno))
			return false;
		return true;
	}
	
	
	
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		for (Disciplina disciplina : novaGradeAluno) {
			str.append(disciplina.getNome() + "\n ");
			str.append("	" + disciplina.getDiaHorario().get(0).getDia() + " " + disciplina.getDiaHorario().get(0).getHorario() + "\n ");
			str.append("	" + disciplina.getDiaHorario().get(1).getDia() + " " + disciplina.getDiaHorario().get(1).getHorario()+ "\n ");
		}
		
		return str.toString();
	}

	public static void main(String[] args) {
		List<Disciplina> disciplinasOfertadas = GradeCurricular.retornaDisciplinas();

		List<Disciplina> disciplinasCursadas = new ArrayList<Disciplina>();
		for (int i = 0; i < 5; i++) {
			disciplinasCursadas.add(disciplinasOfertadas.get(i));
		}
		
		Aluno aluno = new Aluno();
		aluno.setNome("teste");
		aluno.setDisciplinasAprovado(disciplinasCursadas);
		
		List<Disciplina> novaGradeAluno = new  ArrayList<Disciplina>(5);
		EstadoGrade grade = new EstadoGrade(aluno, disciplinasOfertadas, novaGradeAluno);
		
		Nodo n = new BuscaLargura().busca(grade);
		if (n == null) {
            System.out.println("Sem Solucao!");
        } else {
            System.out.println("Solucao:\n" + n.montaCaminho() + "\n\n");
        }
	}

}
