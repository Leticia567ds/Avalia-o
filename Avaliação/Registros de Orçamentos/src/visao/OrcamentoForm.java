package visao;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controle.OrcamentoProcess;
import modelo.Orcamento;

public class OrcamentoForm  extends JFrame  implements ActionListener{
	
	private static final long seralVersionUID = 1L;
	private JPanel painel;
	private JScrollPane rolagem;
    private JLabel id,fornecedor,produto,preco;
    private JTextField tfid, tffornecedor,tfproduto,tfpreco;
    private JButton adicionar, buscar, deletar, alterar;
    private JTextArea verResultados;
    private int autoId = OrcamentoProcess.orc.size() + 1;
	private String texto = "";
    
    
    OrcamentoForm(){
    	setTitle("Registros de Or?amento");
    	setBounds(400,100, 750, 545);
    	painel = new JPanel();
    	painel.setBackground(new Color(211,211,211));
    	setContentPane(painel);
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	setLayout(null);
    	
    	id = new JLabel("ID");
    	id.setBounds(25,20,120,30);
    	painel.add(id);
    	
    	fornecedor = new JLabel("FORNECEDOR");
    	fornecedor.setBounds(25,68,125,30);
    	painel.add(fornecedor);
    	
    	produto = new JLabel("PRODUTO");
    	produto.setBounds(25,125,125,30);
    	painel.add(produto);
    	
    	preco = new JLabel("PRE?O");
    	preco.setBounds(25,180,125,30);
    	painel.add(preco);
    	
    	tfid = new JTextField(String.format("%d", autoId));
    	tfid.setEditable(false);
    	tfid.setBounds(55,25,55,20);
    	painel.add(tfid);
    	
    	tffornecedor = new JTextField();
    	tffornecedor.setBounds(130,69,160,30);
    	painel.add(tffornecedor);
    	
    	tfproduto = new JTextField();
    	tfproduto.setBounds(130,120,160,30);
    	painel.add(tfproduto);
    	
    	tfpreco = new JTextField();
    	tfpreco.setBounds(130,180,160,30);
    	painel.add(tfpreco);
    	
    	verResultados = new JTextArea();
    	verResultados.setEditable(false);
    	verResultados.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
    	preencherAreaDeTexto();
   
    	rolagem = new JScrollPane(verResultados);
    	rolagem.setBounds(40,255,600,200);
    	painel.add(rolagem);
    	
    	adicionar = new JButton("Adicionar");
    	buscar = new JButton("Buscar");
    	alterar = new JButton("Alterar");
    	deletar = new JButton("Apagar");
    	adicionar.setBounds(455, 55, 110, 30);
    	buscar.setBounds(455, 100, 110, 30);
    	alterar.setBounds(455, 150, 110, 30);
    	deletar.setBounds(455, 200, 110, 30);
    	
    	
    	adicionar.addActionListener(this);
    	buscar.addActionListener(this);
    	alterar.addActionListener(this);
    	deletar.addActionListener(this);
    	 
    	
    	alterar.setEnabled(false);
    	deletar.setEnabled(false);
    	
    	painel.add(adicionar);
    	painel.add(deletar);
    	painel.add(alterar);
    	painel.add(buscar);
    }
    
    private void adicionar(){
    	if (tffornecedor.getText().length() !=0 && tfproduto.getText().length() !=0 && tfpreco.getText().length() != 0) {
    		OrcamentoProcess.orc.add(new Orcamento(autoId,tffornecedor.getText().toString(),tfproduto.getText().toString(),Double.parseDouble(tfpreco.getText().toString()),false));
    		
    		 autoId++;
				
    	}else {
    		JOptionPane.showMessageDialog(this, "Favor Preencher todos as informa??es");
    	}
    	limparCampos();
    	comprar();
		preencherAreaDeTexto();
		OrcamentoProcess.salvar();
		
    }
    
    private void deletar() {
		int id = Integer.parseInt(tfid.getText());
		int indice = -1;
		for (Orcamento manu : OrcamentoProcess.orc) {
			if (manu.getId() == id) {
				indice = OrcamentoProcess.orc.indexOf(manu);
			}
		}
		 OrcamentoProcess.orc.remove(indice);
		 comprar();
			preencherAreaDeTexto();
			limparCampos();
			
			
			adicionar.setEnabled(true);
			alterar.setEnabled(false);
			deletar.setEnabled(false);
			OrcamentoProcess.salvar();
			tfid.setText(String.format("%d", OrcamentoProcess.orc.size() + 1));
	}
    
    private void  limparCampos() {
		tffornecedor.setText(null);
		tfproduto.setText(null);
		tfpreco.setText(null);
		
	}
    
    private void preencherAreaDeTexto() {
		texto = "";
		for (Orcamento f : OrcamentoProcess.orc) {
			texto += f.toString()+"\n";
		}
		verResultados.setText(texto);
	}
    
    private void alterar() {
    	int id = Integer.parseInt(tfid.getText());
    	int indice = -1;
		
		for(Orcamento f: OrcamentoProcess.orc) {
			if(f.getId() == id) {
				indice = OrcamentoProcess.orc.indexOf(f);
			}
		}
		
		if (tfid.getText().length() !=0 && tffornecedor.getText().length() !=0 && tfproduto.getText().length() !=0 && tfpreco.getText().length() !=0) {
    		OrcamentoProcess.orc.set(indice, new Orcamento(Integer.parseInt(tfid.getText().toString()),tffornecedor.getText().toString(),tfproduto.getText().toString(),Double.parseDouble(tfpreco.getText().toString()),false));
    		comprar();
			preencherAreaDeTexto();
			limparCampos();
			
			
		}else {
			JOptionPane.showMessageDialog(this, "Favor preencher todos os campos.");
		}
		adicionar.setEnabled(true);
		alterar.setEnabled(false);
		deletar.setEnabled(false);
		tfid.setText(String.format("%d", OrcamentoProcess.orc.size() + 1));
		OrcamentoProcess.salvar();
	}
	
    private void buscar() {
    String entrada = JOptionPane.showInputDialog( this,"Digite o id da manuten??o");
	
	boolean num = true;
	if(entrada != null) {
		for (int i = 0; i < entrada.length(); i++) {
			if(!Character.isDigit(entrada.charAt(i))) {
				num = false;	
			}
		}
		
	}else {
		num = false;
	}
	if (num) {
		int id = Integer.parseInt(entrada);
		boolean achou = false;
		
		for (Orcamento oca : OrcamentoProcess.orc) {
			if (oca.getId() == id) {
				achou = true;
				int indice = OrcamentoProcess.orc.indexOf(oca);
				tfid.setText(OrcamentoProcess.orc.get(indice).getId("s"));
				tffornecedor.setText(OrcamentoProcess.orc.get(indice).getFornecedor());
				tfproduto.setText(OrcamentoProcess.orc.get(indice).getProduto());
				tfpreco.setText(OrcamentoProcess.orc.get(indice).getPreco("s").replace(",", "."));
				OrcamentoProcess.salvar();
				adicionar.setEnabled(false);
				alterar.setEnabled(true);
				deletar.setEnabled(true);
				break;
			}
		}
		
		if (!achou) {
			JOptionPane.showMessageDialog(this, "N?o encontrado");
		}
	}
}
	
    public void comprar() {
    	for (Orcamento orcamento : OrcamentoProcess.orc) {
			OrcamentoProcess.comprarPrdutos(orcamento.getProduto());
		}
	 }
   
    
	public static void main(String[] args) {
		OrcamentoProcess.abrir();
		new OrcamentoForm().setVisible(true);
 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == adicionar) {
			adicionar();
		}
		if (e.getSource() == deletar) {
			deletar();
		}
		if(e.getSource() == buscar) {
			buscar();
		}
		if(e.getSource() == alterar) {
			alterar();
		}
		
	}
		
	}


