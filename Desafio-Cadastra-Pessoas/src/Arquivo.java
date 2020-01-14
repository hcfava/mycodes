import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Arquivo {
	public void escreveArquivo(ArrayList<Pessoas> lstPessoas){
		try {
			FileWriter f = new FileWriter("Pessoas.txt");
        
			BufferedWriter bw = new BufferedWriter(f);
        
			for (int i = 0; i < lstPessoas.size(); i++){
				
				String linha = lstPessoas.get(i).salvaCadastro();
            
				bw.write(linha + "\r\n");
			}
        
			bw.close();
		} catch (IOException ex) {
			Logger.getLogger(Arquivo.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	public void leArquivo(ArrayList<Pessoas> lstPessoas) {
		try {
            FileReader f = new FileReader("Pessoas.txt");
            
            BufferedReader br = new BufferedReader(f);
            
            String linha;
            while((linha = br.readLine()) != null){
            	String str[] = linha.split(",");
            	Pessoas p = new Pessoas();
            	p.setCodigo(Integer.valueOf(str[0]));
            	p.setNome(str[1]);
            	p.setIdade(Integer.valueOf(str[2]));
            	p.setEndereco(str[3]);
            	lstPessoas.add(p);
            }
            br.close();
            
        } catch (FileNotFoundException ex){
            System.err.println("ERRO: Arquivo não existe");
        } catch (IOException ex){
            System.err.println("ERRO: " + ex.getMessage());
        }
    }
}
