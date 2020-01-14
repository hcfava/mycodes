import java.util.ArrayList;
import java.util.Scanner;

public class CadastraPessoas {

	public static void main(String[] args) {
		ArrayList<Pessoas> lstPessoa = new ArrayList<Pessoas>();
		int controle = 1;
		Arquivo a = new Arquivo();
		a.leArquivo(lstPessoa);
		
		
		while(controle != 0) {
			System.out.println("##Escolha uma das opções abaixo##");
			System.out.println("Opção 1 - Cadastra pessoas");
			System.out.println("Opção 2 - Imprime pessoas cadastradas");
			System.out.println("Opção 0 - Sair do programa");
			System.out.println("___________________________");
			System.out.print("Digite aqui sua opção: ");
			Scanner sc = new Scanner(System.in);
			controle = sc.nextInt();
			switch (controle) {
			case 1:
				Pessoas p = new Pessoas();
				System.out.print("Digite o código: ");
				sc = new Scanner(System.in);
				p.setCodigo(sc.nextInt());
				System.out.print("Digite o nome: ");
				sc = new Scanner(System.in);
				p.setNome(sc.nextLine());
				System.out.print("Digite o endereço: ");
				sc = new Scanner(System.in);
				p.setEndereco(sc.nextLine());
				System.out.print("Digite a idade: ");
				sc = new Scanner(System.in);
				p.setIdade(sc.nextInt());
				lstPessoa.add(p);
				a = new Arquivo();
				a.escreveArquivo(lstPessoa);
				break;
				
			case 2:
				if(!lstPessoa.isEmpty()) {
					for (int i = 0; i < lstPessoa.size(); i++) {
						System.out.println(lstPessoa.get(i));
						System.out.println("");
					}
				}else
					System.out.println("Nenhuma pessoa cadastrada");
				break;
				
			case 0:
				break;

			default:
				System.out.println("Opção inválida");
				break;
			}
			System.out.println("");
		}
	}

}
