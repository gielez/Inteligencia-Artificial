package com.furb.grade;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.furb.disciplina.Disciplina;

public class GradeCurricular
{
	private static List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	private static BufferedReader buffer;

	public static List<Disciplina> retornaDisciplinas()
	{
		leMaterias();
		return disciplinas;
	}

	private static Dia retornaDia(String dia)
	{
		switch (dia)
		{
			case "Seg":
				return Dia.SEG;
			case "Ter":
				return Dia.TER;
			case "Qua":
				return Dia.QUA;
			case "Qui":
				return Dia.QUI;
			case "Sex":
				return Dia.SEX;
		}
		return null;
	}

	private static Horario retornaHorario(String horario)
	{
		switch (horario)
		{
			case "12 / 13":
				return Horario.PRIMEIRO_HORARIO;
			case "14 / 15":
				return Horario.SEGUNDO_HORARIO;
		}
		return null;
	}

	public static void leMaterias()
	{
		FileInputStream stream;
		try
		{
			stream = new FileInputStream(".\\GradeCienciaComputacao\\GradeCienciaComputacao.txt");
			InputStreamReader reader = new InputStreamReader(stream);
			buffer = new BufferedReader(reader);

			String linha = buffer.readLine();
			while (linha != null)
			{
				Disciplina disciplina = new Disciplina();
				disciplina.setNome(linha);

				linha = buffer.readLine();

				String[] str = linha.split("-");
				DiaSemana dia1 = new DiaSemana();
				DiaSemana dia2 = new DiaSemana();
				dia1.setDia(retornaDia(str[0].substring(0, 3)));
				dia1.setHorario(retornaHorario(str[0].substring(3, str[0].length())));

				dia2.setDia(retornaDia(str[1].substring(0, 3)));
				dia2.setHorario(retornaHorario(str[1].substring(3, str[1].length())));

				disciplina.setDia(dia1);
				disciplina.setDia(dia2);
				disciplinas.add(disciplina);

				linha = buffer.readLine();
			}

		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		lePreRequisitos();
	}
	private static void lePreRequisitos(){
		FileInputStream stream;
		try {
			stream = new FileInputStream(".\\GradeCienciaComputacao\\PreRequisitos");
			InputStreamReader reader = new InputStreamReader(stream);
			buffer = new BufferedReader(reader);
			String linha = buffer.readLine();
			while(linha != null)
			{
				String[] str = linha.split("-");
				Disciplina dis = buscaDisciplina(str[0]);
				dis.setPreRequisito(buscaDisciplina(str[1]));

				linha = buffer.readLine();
			}
			
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	private static Disciplina buscaDisciplina(String nome){
		for (Disciplina disciplina : disciplinas) {
			if(disciplina.getNome().equals(nome)){
				return disciplina;
			}
		}
		return null;
	}
}
