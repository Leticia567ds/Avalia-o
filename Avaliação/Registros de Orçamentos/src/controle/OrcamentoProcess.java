package controle;

import java.util.ArrayList;

import modelo.OrcamentoDAO;
import modelo.Orcamento;

public class OrcamentoProcess {

	
	public static ArrayList<Orcamento> orc = new ArrayList<>();
	private static OrcamentoDAO d = new OrcamentoDAO();
	
	public static void abrir() {
		orc = d.ler();
	}
	
	public static void salvar() {
		d.escrever(orc);
	}
	
	public static void carregar() {
		// Limpar a lista
		orc = new ArrayList<>();
		// Preencher com testes
		orc.add(new Orcamento(1, "Intel - XPTO1", "Processador i7 5ºG",800d, false));
		orc.add(new Orcamento(2, "AMD - XPTO1", "Ryzen 5",780d,true));
		orc.add(new Orcamento(3, "AMD - XPTO2", "Ryzen 5 (Fingi)",850d, true));
	}
}