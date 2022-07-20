import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        // fazer conexão e buscar os top 250 filmes e imagens da nasa

        // https://api.mocki.io/v2/549a5d8b/MostPopularTVs
        // https://api.mocki.io/v2/549a5d8b/Top250TVs
        // https://api.mocki.io/v2/549a5d8b/Top250Movies
        // https://api.mocki.io/v2/549a5d8b/MostPopularMovies

        // String url = "https://api.mocki.io/v2/549a5d8b";
        // ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();
        String url = "https://api.mocki.io/v2/549a5d8b/NASA-APOD";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);
    
        // exibir e manipular os dados

        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();

        for (int i = 0; i < 3; i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "./saida/" + conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();
            
        }

    }
}
