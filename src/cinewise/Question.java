// PACOTE AO QUAL ESSA CLASSE PERTENCE
package cinewise;

// IMPORTANDO PACOTES ÚTEIS DO JAVA
import java.lang.Math.*;
import java.util.ArrayList;
import java.util.Random;

// IMPORTANDO PACOTES PARA A MANIPULAÇÃO DE ARQUIVOS JSON
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

/**
 * Encapsula uma pergunta e contem dados relevantes para exibir essa pergunta
 * para o usuário e para avaliar se a resposta do usuário está correta.
 * 
 * @author Bernardo Maia Coelho (12542481) e {...}
 * @see cinewise.QuestionList
 */
public class Question {
	public int id;
	public int type;
	public int difficulty;
	public String inquiry;
	public String correctAnswer;

	private Random random;
	public MovieList movieList;
	public JSONObject moreData;

	public Movie currentMovie;

	public static final int GUESS_NAME_MUTIPLE_CHOISE = 1;
	public static final int GUESS_NAME_ESSAY = 2;

	public Question(MovieList movieSource, JSONObject jsonObject) {
		this.random = new Random();

		this.currentMovie = null;

		this.id = Math.toIntExact((long) jsonObject.get("id"));
		this.type = Math.toIntExact((long) jsonObject.get("type"));
		this.difficulty = Math.toIntExact((long) jsonObject.get("difficulty"));
		this.inquiry = (String) jsonObject.get("inquiry");
		this.correctAnswer = CineUtils.standardize((String) jsonObject.get("answer"));

		// System.out.println("TYPE " + this.type + "\n");
		// System.out.println(movieSource);

		// INDENTIFICANDO O TIPO E CARREGANDO DADOS
		/*
		 * OBS: Tipos com valor positivo são considerados genéricos e sua lista
		 * de filmes é apenas uma cópia da lista principal.
		 */
		if (0 > this.type) {
			String[] movieNames = CineUtils.jsonToArray(
					(JSONArray) jsonObject.get("movieNames"));

			this.movieList = new MovieList();
			for (String movieName : movieNames)
				this.movieList.AddMovie(movieSource.getMovie(movieName));
		} else {
			this.movieList = movieSource.copy();
			// System.out.println(this.movieList);
		}

		this.moreData = (JSONObject) jsonObject.get("more");
	}

	public String[] genMutipleChoises(MovieList movieSource, int choiseFactor) {
		switch (this.type) {
			case GUESS_NAME_MUTIPLE_CHOISE: {
				MovieList randList = movieSource.getRandList(choiseFactor);
				int index = randList.find(this.currentMovie);
				if (0 > index)
					index = this.random.nextInt(randList.size());
				randList.setMovie(index, this.currentMovie);
				this.correctAnswer = this.currentMovie.getName();
				return randList.getNames();
			}
			default:
				return null;
		}
	}

	/**
	 * Avalia se determinada resposta está correta.
	 * 
	 * @param answer A resposta dada pelo usuário.
	 * @return true se a resposta estiver correta e false caso contrário.
	 * @throws Exception Caso o tipo da pergunta não seja conhecido.
	 */
	public boolean evaluate(String answer) throws Exception {
		// PADRONIZANDO A RESPOSTA
		answer = CineUtils.standardize(answer);
		
		// CHECANDO SE A RESPOSTA ESTA CORRETA DE ACORDO COM O TIPO DA PERGUNTA
		return this.correctAnswer.equals(answer);
	}

	/**
	 * Use essa função para bater em uma questão com muita força. Se a questão
	 * sobreviver essa agressão gratuita, significa que ela ainda pode ser
	 * usada novamente.
	 * 
	 * @return true se a função tiver morrido e false se ela ainda estiver
	 *         viva.
	 */
	public boolean getHitHard() throws Exception {
		// * OBS: Questões genéricas são usadas até a lista de filmes acabar. */
		if (0 < this.type)

		{
			System.out.println("tchau!!!!!!!!!!!!!!\n");
			return this.nextMovie();
		} else // Questões específicas são de uso único.
		{
			System.out.println("Oi!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
			return true;
		}
	}

	public boolean nextMovie() throws Exception {
		if (0 >= this.movieList.size())
			throw new Exception("Movie list is unexpectedly empty.");

		System.out.println("Moving On.\n");
		this.currentMovie = this.movieList.getRandMovie();
		this.movieList.RemoveMovie(currentMovie);

		return 0 >= this.movieList.size();
	}

	/**
	 * Avalia se determinada resposta está correta.
	 * 
	 * @param answer A resposta dada pelo usuário.
	 * @return true se a resposta estiver correta e false caso contrário.
	 * @throws Exception Caso o tipo da pergunta não seja conhecido.
	 */
	public boolean evaluate(int answer) throws Exception {
		return this.evaluate(Integer.toString(answer));
	}

}
