package com.furb.grade;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.furb.disciplinas.Disciplinas;

public class GradeCurricular {
	private List<Disciplinas> disciplinas = new ArrayList<Disciplinas>();
	private BufferedReader buffer;
	
	
	private void leGrade()
	{
		try {
			FileInputStream stream = new FileInputStream(".\\GradeCienciaComputacao\\GradeCienciaComputacao.txt");
			InputStreamReader reader = new InputStreamReader(stream);
			buffer = new BufferedReader(reader);
			String linha = buffer.readLine();
			while(linha != null) {
				Disciplinas d = new Disciplinas();
				d.setNome(linha);
				linha = buffer.readLine();
				String[] str = linha.split("-");
				d.setDiaSemana(0, str[0].substring(0, 3));
				d.setHorário(0, str[0].substring(3, 10));
				if(str.length > 1){
					d.setDiaSemana(1, str[1].substring(0, 3));
					d.setHorário(1, str[1].substring(3, 10));
				}
				disciplinas.add(d);
			   linha = buffer.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (Disciplinas disciplinas2 : disciplinas) {
			System.out.println("Disciplina: " + disciplinas2.getNome() +"\n");
			for (int i = 0; i < 2; i++) {
				System.out.println(disciplinas2.getDiaSemana(i) + " " + disciplinas2.getHorário(i));
			}
			System.out.println("\n");
		}
		
	}
	
	public static void main(String[] args) {
		GradeCurricular gc = new GradeCurricular();
		gc.leGrade();
	}

}
