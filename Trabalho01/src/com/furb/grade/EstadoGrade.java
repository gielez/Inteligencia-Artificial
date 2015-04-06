package com.furb.grade;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import busca.Antecessor;
import busca.BuscaIterativo;
import busca.BuscaLargura;
import busca.BuscaProfundidade;
import busca.Estado;
import busca.MostraStatusConsole;
import busca.Nodo;

import com.furb.aluno.Aluno;
import com.furb.disciplina.Disciplina;

public class EstadoGrade implements Estado, Antecessor
{
	final List<Disciplina> novaGradeAluno;
	final List<Disciplina> disciplinasCursadas;
	final List<Disciplina> disciplinasOfertadas;
	final Aluno aluno;
	
	public EstadoGrade(Aluno aluno, List<Disciplina> ofertadas, List<Disciplina> novo) 
	{
		this.aluno = aluno;
		this.disciplinasCursadas = aluno.getDisciplinasAprovado();
		this.disciplinasOfertadas = ofertadas;
		this.novaGradeAluno = novo;
	}

	@Override
	public int custo()
	{
		return 1;
	}

	@Override
	public boolean ehMeta() 
	{
		//Meta: maior preenchimento possivel da carga horária
		if(novaGradeAluno.size() == 5 && validaMaterias()){
			return true;
		}
		
		return false;
	}

	@Override
	public List<Estado> sucessores()
	{
		List<Estado> suc = new LinkedList<Estado>(); // a lista de sucessores

		for (int i = 0; i < disciplinasOfertadas.size(); i++) {
			if(ehValido(disciplinasOfertadas.get(i))){
				List<Disciplina> ofertadas = new ArrayList<Disciplina>(disciplinasOfertadas);
				Disciplina disciplina = ofertadas.get(i);
				List<Disciplina> nova = new ArrayList(novaGradeAluno);
				EstadoGrade novo = new EstadoGrade(aluno , ofertadas, nova);
				insereDisciplina(disciplina, nova);
				suc.add(novo);
				}
			}
		return suc;
	}
	
	private void insereDisciplina(Disciplina dis, List<Disciplina> nova)
	{
		if(validaMateria1(dis, nova)){
			novaGradeAluno.add(dis);
		}
						
	}
	
//	private boolean validaMateria(Disciplina dis, List<Disciplina> nova){
//		for (Disciplina disciplina : nova) {
//			List<DiaSemana> diasGrade = disciplina.getDiaHorario();
//			List<DiaSemana> diasDisc = dis.getDiaHorario();
//			
//			if(mesmoHorario(dis, disciplina)){
//				return false;
//			}
//			
//			
////			for (DiaSemana diaSemana : diasGrade) {
////				for (DiaSemana diaDisc : diasDisc) {
////					if(diaSemana.getDia() == diaDisc.getDia()){
////						if(diaSemana.getHorario() == diaDisc.getHorario()){
////							return false;
////						}
////					}
////				}
//			}
//		return true;
//		}
	
	private boolean validaMaterias(){
		for (int i = 0; i < novaGradeAluno.size(); i++) {
			List<DiaSemana> diasGrade = novaGradeAluno.get(i).getDiaHorario();
			for (int j = i + 1; j < novaGradeAluno.size(); j++) {
				List<DiaSemana> diasDisc = novaGradeAluno.get(j)
						.getDiaHorario();
				for (DiaSemana diaSemana : diasGrade) {
					for (DiaSemana diaDisc : diasDisc) {
						if (diaSemana.getDia() == diaDisc.getDia()) {
							if (diaSemana.getHorario() == diaDisc.getHorario()) {
								return false;
							}
						}
					}
				}
			}
		}
		return true;
	}
	
	public boolean valida(DiaSemana aula1, DiaSemana aula2) {
		return aula1.getDia().equals(aula2.getDia()) && aula1.getHorario().equals(aula2.getHorario());
	}
	
	private boolean validaMateria1(Disciplina dis, List<Disciplina> nova){
		boolean ok = false;
		
		if(novaGradeAluno.size() == 0){
			ok = true;
		}
		
		// existe outra matÃ©ria selecionada nesse horÃ¡rio
		List<DiaSemana> dia = dis.getDiaHorario();
		for (Disciplina discAluno : nova) {// percorre a grade do aluno(que pode
											// conter até 5 disciplinas)
			List<DiaSemana> diaAluno = discAluno.getDiaHorario();

			if (!novaGradeAluno.contains(dis)) {
				// se os dias são diferente = não valida horário
				if (dia.get(0).getDia() != diaAluno.get(0).getDia()) {
					if (dia.get(1).getDia() != diaAluno.get(1).getDia()) {
						ok = true;
					}
				}
				
				//se os dois dias são iguais, valida os horários
				if (dia.get(0).getDia() == diaAluno.get(0).getDia()) {
					if (dia.get(0).getHorario() != diaAluno.get(0).getHorario()) {
						if (dia.get(1).getDia() == diaAluno.get(1).getDia()) {
							if (dia.get(1).getHorario() != diaAluno.get(1).getHorario()) {
								ok = true;
							}
						}
					}
				
					//primeiro é diferente e o outro é igual
					if (dia.get(0).getDia() != diaAluno.get(0).getDia()) {
						if (dia.get(1).getDia() == diaAluno.get(1).getDia()) {
							if (dia.get(1).getHorario() != diaAluno.get(1).getHorario()) {
								ok = true;
							}
						}
					}
					
					//primeiro é igual e o outro é diferente
					if (dia.get(0).getDia() == diaAluno.get(0).getDia()) {
						if (dia.get(1).getHorario() != diaAluno.get(1).getHorario()) {
								if (dia.get(1).getDia() != diaAluno.get(1).getDia()) {
									ok = true;
							}
						}
					}
				
				
				}
			}
		}
		return ok;
	}
	
	public boolean ehValido(Disciplina disciplina)
	{
		// possui o pré requisito em aberto
		if (disciplina.getPreRequisito() != null)
		{
			if (!disciplinasCursadas.contains(disciplina.getPreRequisito()))
			{
				return false;
			}
		}

		return true;
	}

	@Override
	public List<Estado> antecessores()
	{
		// busca bidirecional(Ex: MissionarioCanibal
		return sucessores();
	}

	@Override
	public int hashCode()
	{
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
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstadoGrade other = (EstadoGrade) obj;
		if (aluno == null)
		{
			if (other.aluno != null)
				return false;
		} else if (!aluno.equals(other.aluno))
			return false;
		if (disciplinasCursadas == null)
		{
			if (other.disciplinasCursadas != null)
				return false;
		} else if (!disciplinasCursadas.equals(other.disciplinasCursadas))
			return false;
		if (disciplinasOfertadas == null)
		{
			if (other.disciplinasOfertadas != null)
				return false;
		} else if (!disciplinasOfertadas.equals(other.disciplinasOfertadas))
			return false;
		if (novaGradeAluno == null)
		{
			if (other.novaGradeAluno != null)
				return false;
		} else if (!novaGradeAluno.equals(other.novaGradeAluno))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < novaGradeAluno.size(); i++) {
			str.append(" \n" +novaGradeAluno.get(i).getNome() + " \n\n" );
			for (DiaSemana dia : novaGradeAluno.get(i).getDiaHorario()) {
				str.append(dia.getDia() + " " +dia.getHorario()+ " \n" );
				
			}
		}
		
		return str.toString();
	}

	public static void main(String[] args)
	{
		List<Disciplina> disciplinasOfertadas = GradeCurricular.retornaDisciplinas();		

		List<Disciplina> disciplinasCursadas = new ArrayList<Disciplina>();
		for (int i = 0; i < 5; i++)
		{
			disciplinasCursadas.add(disciplinasOfertadas.get(i));
		}

		Aluno aluno = new Aluno();
		aluno.setNome("teste");
		aluno.setDisciplinasAprovado(disciplinasCursadas);
		
		List<Disciplina> disciplinasNaoConcluidas = new ArrayList<Disciplina>();
		for (Disciplina disciplina : disciplinasOfertadas) {
			if(!disciplinasCursadas.contains(disciplina)){
				disciplinasNaoConcluidas.add(disciplina);
			}
		}

		List<Disciplina> novaGradeAluno = new ArrayList<Disciplina>(5);
		EstadoGrade grade = new EstadoGrade(aluno, disciplinasNaoConcluidas, novaGradeAluno);

//		Nodo n = new BuscaLargura(new MostraStatusConsole()).busca(grade);
//		System.out.println(n == null ? "Sem Solucao!" : "Solucao:\n" + "	"  + n.montaCaminho()  + "\n\n");
		
		Nodo n = new BuscaProfundidade(new MostraStatusConsole()).busca(grade);
		System.out.println(n == null ? "Sem Solucao!" : "Solucao:\n" + "	" +  n.montaCaminho() +  "\n\n");
//		
//		Nodo n = new BuscaIterativo(new MostraStatusConsole()).busca(grade);
//		System.out.println(n == null ? "Sem Solucao!" : "Solucao:\n" + "	" +  n.montaCaminho() + "\n\n");
	}
	

}
