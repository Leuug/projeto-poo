/**
 * @author Raphael Zoega Cali Gomes (11800729)
 * @author Bernardo Maia Coelho (12542481).
 */


// PACOTE AO QUAL ESSA CLASSE PERTENCE
package cinewise;

// IMPORTANDO PACOTES ÚTEIS DO JAVA
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.FileReader;
import java.util.Random;

// IMPORTANDO PACOTES PARA A MANIPULAÇÃO DE ARQUIVOS JSON
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

/**
 * Representa uma lista de filmes. Essa classe serve como lista de instâncias
 * da classe 'Movie'.
 * 
 * @author Bernardo Maia Coelho (12542481) e {...}
 * @see cinewise.Movie
 */
public class MovieList {
	private ArrayList<Movie> moviesList;
	private Random random;

	public MovieList() {
		this.random = new Random();
		this.moviesList = new ArrayList<>();
	}

	public MovieList(FileReader file) {
		this.random = new Random();

		// INICIALIZANDO O CONTADOR DE LINHAS
		int l = 1;

		// INICIALIZANDO A LISTA DE FILMES
		this.moviesList = new ArrayList<>();

		// TENTATIVA DE LEITURA
		try {
			// INICIALIZANDO O LEITOR BUFFERIZADO E A LINHA
			BufferedReader reader = new BufferedReader(file);
			String line = "";

			// LOOP DE LEITURA ATÉ O FIM DO ARQUIVO
			while ((line = reader.readLine()) != null) {
				l++;

				String[] movieData = line.split("; ");
				Movie newMovie = new Movie(movieData[0], movieData[1], movieData[2]);
				this.moviesList.add(newMovie);
			}
		} catch (Exception e) // Tratando qualquer erro na leitura.
		{
			System.out.println("Problem reading line " + l);
		}
	}

	/**
	 * @param filepath O caminho para um json contendo informações acerca de
	 *                 filmes. Vale destacar que esse caminho começa no diretório do
	 *                 projeto,
	 *                 na pasta onde o Makefile está presente. Dessa forma,
	 *                 idealmente, a
	 *                 string filepath começa com "data/" e termina com ".json".
	 */
	public MovieList(String filepath) throws Exception {
		this.random = new Random();

		// INICIALIZANDO O LEITOR BUFFERIZADO
		FileReader fr = new FileReader(filepath);
		BufferedReader reader = new BufferedReader(fr);

		// LENDO O ARQUIVO JSON
		JSONParser parser = new JSONParser();
		JSONArray jsonArray = (JSONArray) parser.parse(reader);
		int arrLen = (int) jsonArray.size();

		// INICIALIZANDO A LISTA DE FILMES
		this.moviesList = new ArrayList<Movie>();

		// LOOP DE CONVERSÃO DE OBJETO JSON PARA INSTÂNCIA DA CLASSE MOVIE
		for (int i = 0; i < arrLen; i++) {
			JSONObject jsonObject = (JSONObject) jsonArray.get(i);
			Movie newMovie = new Movie(jsonObject);
			this.moviesList.add(newMovie);
		}
	}

	/**
	 * @return um filme aleatório da lista.
	 */
	public Movie getRandMovie() {
		int index = this.random.nextInt(this.moviesList.size());
		return this.moviesList.get(index);
	}

	public MovieList setMovie(int index, Movie element) {
		this.moviesList.set(index, element);
		return this;
	}

	public String[] getNames() {
		int size = this.size();
		String[] names = new String[size];
		for (int i = 0; i < size; i++)
			names[i] = this.moviesList.get(i).getName();
		return names;
	}

	public int size() {
		return this.moviesList.size();
	}

	public ArrayList<Movie> getList() {
		return this.moviesList;
	}

	/**
	 * Adiciona um filme à lista de filmes.
	 * 
	 * @param _movie O objeto filme a ser adicionado.
	 */
	public void AddMovie(Movie _movie) {
		this.moviesList.add(_movie);
	}

	/**
	 * Remove um objeto filme da lista.
	 * 
	 * @param _movie O objeto filme a ser removido.
	 * @return Uma referência para a lista de filmes. Esse valor de retorno
	 *         apenas existe para facilitar o encadeamento.
	 */
	public MovieList RemoveMovie(Movie _movie) {
		this.moviesList.remove(_movie);
		return this;
	}

	public MovieList getRandList(int size) {
		if (this.size() < size)
			return null;

		MovieList moviePool = this.copy();
		MovieList newList = new MovieList();

		for (int i = 0; i < size; i++) {
			Movie movie = moviePool.getRandMovie();
			moviePool.RemoveMovie(movie);
			newList.AddMovie(movie);
		}

		return newList;
	}

	public int find(Movie movie) {
		for (int i = 0; i < this.size(); i++)
			if (this.getMovie(i).equals(movie))
				return i;
		return -1;
	}

	public MovieList copy() {
		MovieList newList = new MovieList();
		for (Movie movie : this.moviesList)
			newList.AddMovie(movie);
		return newList;
	}

	/**
	 * Recupera um filme por meio do id.
	 * 
	 * @param id O id a ser buscado.
	 * @return null se o filme não for encontrado, caso contrario uma
	 *         referência para o objeto movie.
	 */
	public Movie getMovie(String name) {
		for (Movie movie : this.moviesList)
			if (CineUtils.standardize(name)
					.equals(CineUtils.standardize(movie.getName())))
				return movie;
		return null;
	}

	/**
	 * Busca um filme na lista por meio de um índicie.
	 * 
	 * @param index O índicie do filme a ser buscado.
	 * @return Um filme ou null.
	 */
	public Movie getMovie(int index) {
		if (0 > index || index >= this.moviesList.size())
			return null;
		return this.moviesList.get(index);
	}

	/**
	 * @return O primeiro filme da lista.
	 */
	public Movie getMovie() {
		return this.getMovie(0);
	}

	@Override
	public String toString() {
		// TRATANDO ERROS
		if (null == moviesList)
			return "null\n";

		// CONSTANTES
		String separator = "\n------------------------------------------------------\n";

		// INICIALIZANDO VARIAVEIS
		String movieListString = "";

		// CONCATENANDO A STRING DE TODOS OS FILMES DA LISTA
		for (Movie movie : this.moviesList)
			movieListString += movie.toString() + separator;

		return movieListString;
	}

	/**
	 * Útil para a etapa de testes.
	 */
	public static void main(String[] args) throws FileNotFoundException {
		FileReader file = new FileReader(new File("../Images/references.txt"));
		MovieList list = new MovieList(file);
		System.out.println(list.toString());
	}
}
