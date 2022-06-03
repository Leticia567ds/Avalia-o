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
		
	}
}