package modelo;

import java.util.Objects;

public class Orcamento {
private int id;
private String fornecedor;
private String produto;
private Double preco;
private boolean maisbarato;
public Orcamento(int id, String fornecedor,String produto, Double preco, boolean maisbarato) {
	this.id = id;
	this.fornecedor = fornecedor;
	this.produto = produto;
	this.preco = preco;
	this.maisbarato = maisbarato;
}


public Orcamento(String linha) {
	this.id = Integer.parseInt(linha.split(";")[0]);
	this.fornecedor = linha.split(";")[1];
	this.produto = linha.split(";")[2];
	this.preco = Double.parseDouble(linha.split(";")[3]);
	this.maisbarato = Boolean.parseBoolean(linha.split(";")[4]);
}


public int getId() {
	return id;
}

public String getId(String s) {
	return String.format("%d", id);
}

public void setId(int id) {
	this.id = id;
}
public String getFornecedor() {
	return fornecedor;
}
public void setFornecedor(String fornecedor) {
	this.fornecedor = fornecedor;
}

public String getProduto() {
	return fornecedor;
}
public void setProduto(String produto) {
	this.produto = produto;
}
public String getPreco(String s) {
	return String.format("%.0f", preco);
}

public Double getPreco() {
	return preco;
}
public void setPreco(Double preco) {
	this.preco = preco;
}
public boolean isMaisbarato() {
	return maisbarato;
}
public void setMaisbarato(boolean maisbarato) {
	this.maisbarato = maisbarato;
}

@Override
public int hashCode() {
	return Objects.hash(fornecedor, id, maisbarato, preco);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Orcamento other = (Orcamento) obj;
	return Objects.equals(fornecedor, other.fornecedor) && id == other.id && maisbarato == other.maisbarato
			&& Objects.equals(preco, other.preco);
}

@Override
public String toString() {
	return "Orcamento [id=" + id + ", fornecedor=" + fornecedor + ", preco=" + preco + ", maisbarato=" + maisbarato
			+ "]";
}

}
