package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import modelo.Orcamento;

public class OrcamentoDAO {

	private BufferedReader br;
	private BufferedWriter bw;
	private String arquivo = "C:\\Users\\des\\Desktop\\Avalia-o\\Avalia��o\\Registros de Or�amentos\\Dados\\Arquivo.csv";

	public ArrayList<Orcamento> ler() {
		ArrayList<Orcamento> linhas = new ArrayList<>();
		Orcamento p;
		try {
			br = new BufferedReader(new FileReader(arquivo));
			String linha = br.readLine();
			while (linha != null) {
				p = new Orcamento(linha);
				linhas.add(p);
				linha = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
		return linhas;
	}

	public void escrever(ArrayList<Orcamento> linhas) {
		try {
			bw = new BufferedWriter(new FileWriter(arquivo));
			for (Orcamento m : linhas) {
				bw.write(m.tocsv());
			}
			bw.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
