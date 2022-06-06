package controle;

import java.util.ArrayList;

import modelo.OrcamentoDAO;
import modelo.Orcamento;

public class OrcamentoProcess {

	
	public static ArrayList<Orcamento> orc = new ArrayList<>();
	private static OrcamentoDAO d = new OrcamentoDAO();
	
	public static void comprarPrdutos(String produto) {
		int index = 0;
		double maisbarato = 1000000;
		for (Orcamento orcamento : orc) {
			if (orcamento.getProduto().equals(produto) && orcamento.getPreco() < maisbarato) {
				index = orc.indexOf(orcamento);
				maisbarato = orcamento.getPreco();
			}
		}
		
		for (Orcamento orcamento : orc) {
			if (orc.indexOf(orcamento) == index) {
				orcamento.setMaisbarato(true);
			} else if(orcamento.getProduto() == produto){
				orcamento.setMaisbarato(false);
			}
		}
		
	}
	
	public static void abrir() {
		orc = d.ler();
	}
	
	public static void salvar() {
		d.escrever(orc);
		abrir();
	}
	
}