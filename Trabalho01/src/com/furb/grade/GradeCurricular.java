package com.furb.grade;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.furb.disciplina.Disciplina;

public class GradeCurricular {
	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	private GradeHorarios grade = new GradeHorarios();
	private DisciplinasSemestre semestre = new DisciplinasSemestre();
	
	private BufferedReader buffer;
	
	private Disciplina[] retornaDia() throws IOException
	{
		Disciplina[] dia = new Disciplina[2];
		String linha = buffer.readLine();
		String[] str = linha.split("-");
		dia[0] = buscaDisciplina(str[0]);
		dia[1] = buscaDisciplina(str[1]);
		
		return dia;
		
	}
	
	public void leMaterias()
	{
		FileInputStream stream;
		try {
			stream = new FileInputStream(".\\GradeCienciaComputacao\\PreRequisitos");
			InputStreamReader reader = new InputStreamReader(stream);
			buffer = new BufferedReader(reader);

			String linha = buffer.readLine();
			while(linha != null)
			{
				Disciplina disciplina = new Disciplina();
				String[] str = linha.split("-");
				
				disciplina.setNome(str[0]);
				if(str.length > 1)
				{
					Disciplina pre = new Disciplina();
					pre.setNome(str[1]);
					disciplina.setPreRequisito(pre);
				}
				disciplinas.add(disciplina);
				linha = buffer.readLine();
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		leMateriasGrade();
		imprimeDisciplinas();

	}
	
	private void leMateriasGrade()
	{
		try {
			FileInputStream stream = new FileInputStream(".\\GradeCienciaComputacao\\GradeCienciaComputacao.txt");
			InputStreamReader reader = new InputStreamReader(stream);
			buffer = new BufferedReader(reader);

			String linha = buffer.readLine();
			
			DisciplinaDoDia[] dia = new DisciplinaDoDia[5];
			while(linha != null) {
				if(linha.equals("***"))
				{
					semestre.setDias(dia);
					dia = new DisciplinaDoDia[5];
					grade.setDisciplinasSemestre(semestre);
					semestre = new DisciplinasSemestre();
					linha = buffer.readLine();
				}
				
				Disciplina[] disciplinas;
				switch (linha) {
				case "Seg":
					disciplinas = retornaDia();
					DisciplinaDoDia seg = new DisciplinaDoDia();
					seg.setPrimeiraAula(disciplinas[0]);
					seg.setSegundaAula(disciplinas[1]);
					dia[0] = seg;
					break;
				case "Ter":
					disciplinas = retornaDia();
					DisciplinaDoDia ter = new DisciplinaDoDia();
					ter.setPrimeiraAula(disciplinas[0]);
					ter.setSegundaAula(disciplinas[1]);
					dia[1] = ter;
					break;
				case "Qua":
					disciplinas = retornaDia();
					DisciplinaDoDia qua = new DisciplinaDoDia();
					qua.setPrimeiraAula(disciplinas[0]);
					qua.setSegundaAula(disciplinas[1]);
					dia[2] = qua;
					break;
				case "Qui":
					disciplinas = retornaDia();
					DisciplinaDoDia qui = new DisciplinaDoDia();
					qui.setPrimeiraAula(disciplinas[0]);
					qui.setSegundaAula(disciplinas[1]);
					dia[3] = qui;
					break;
				case "Sex":
					disciplinas = retornaDia();
					DisciplinaDoDia sex = new DisciplinaDoDia();
					sex.setPrimeiraAula(disciplinas[0]);
					sex.setSegundaAula(disciplinas[1]);
					dia[4] = sex;
					break;
				}
				linha = buffer.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private Disciplina buscaDisciplina(String nome)
	{
		for (Disciplina disciplinas2 : disciplinas) {
			if(disciplinas2.getNome().equals(nome)){
				return disciplinas2;
			}
		}
		
		return null;
	}
	
	
	private void imprimeDisciplinas()
	{
		for (DisciplinasSemestre semestre : grade.getDisciplinasSemestre()) {
			DisciplinaDoDia[] dias = semestre.getDias();
			for (int i = 0; i < dias.length; i++) {
				System.out.println(i + " - "+ dias[i].getPrimeiraAula().getNome()+ " / "+dias[i].getSegundaAula().getNome());
			}
			System.out.println("\n\n");
			
		}
	}
	
//	public static void main(String[] args) { //TODO 
//		GradeCurricular gc = new GradeCurricular();
//		gc.leMaterias();
//		gc.leMateriasGrade();
//		gc.imprimeDisciplinas();
//	}

}
