
public class Pessoas {
	private int codigo;
	private int idade;
	private String endereco;
	private String nome;
		

	public void setIdade(int idade) {
		this.idade = idade;
	}

		public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	@Override
	public String toString(){
		String ret;
		ret = "Código: " + this.codigo + "\nNome: " +this.nome + "\nIdade: " + this.idade + "\nEndereço: " + this.endereco;
		return ret;
	}
	public String salvaCadastro() {
		String ret = this.codigo + "," + this.nome + "," + this.idade + "," + this.endereco;
		return ret;
	}
}
