package arquivo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class HandleLeituraArquivo {
    private FileReader leitor;
    private BufferedReader buffer;
    private DadosArquivo dados; 

    public HandleLeituraArquivo(String path) {
        try {
            this.leitor = new FileReader(path);
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Arquivo näo encontrado!");
            //TODO: handle exception
        }
        this.buffer = new BufferedReader(this.leitor);
        this.dados = new DadosArquivo();
    }

    public DadosArquivo lerArquivo() {
        try {
            //orientacao
            String linha = this.buffer.readLine();
            this.handleLinha(linha);
            //numero de vertices
            linha = this.buffer.readLine();
            do {
                if(!linha.isBlank())
                    this.handleLinha(linha);
                linha = this.buffer.readLine();
            } while (linha != null);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Näo foi possivel ler a linha");
        }

		try {
            this.buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Nao foi possivel fechar o arquivo");
        }
        return this.dados;
    }

    public void handleLinha(String linha) {
        linha = linha.replace(" ", "");
        linha = linha.replace("(", "");
        linha = linha.replace(")", "");
        linha = linha.replace(",", "!");
        linha = linha.replace(":", "!");
        linha = linha.replace(" ", "");
        if(linha.contains("orientado")){
            String orientado = linha.split("=")[1];
            this.dados.setOrientado(orientado.equals("sim"));
        }else if(linha.contains("V")){
            String numVertice = linha.split("=")[1];
            this.dados.setNumVertices(Integer.parseInt(numVertice));
        }else{
            String[] valAresta = linha.split("!");
  
            this.dados.addAresta(Integer.parseInt(valAresta[0]), Integer.parseInt(valAresta[1]), Integer.parseInt(valAresta[2]));
        }
    }
}
