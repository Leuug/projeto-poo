// PACOTE AO QUAL ESSA CLASSE PERTENCE
package cinewise;

// IMPORTANDO PACOTES ÚTEIS DO JAVA
import java.lang.Math.*;

// IMPORTANDO PACOTES PARA A MANIPULAÇÃO DE ARQUIVOS JSON
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;


// ENUNS
/**
 * Esse enum contem os tipos de questões possíveis.
 */
enum QuestionType {
	MUTIPLE_CHOISE,
	TRUE_OR_FALSE,
	ESSAY
}


/**
 * Encapsula uma pergunta e contem dados relevantes para exibir essa pergunta
 *  para o usuário e  para avaliar se a resposta do usuário está correta.
 * @author Bernardo Maia Coelho (12542481) e {...}
 * @see cinewise.QuestionList
 */
public class Question
{
	public int id;
	public QuestionType type;
	public int difficulty;
	public String inquiry;
	public String correctAnswer;
	public MovieList movieList;


	public Question(MovieList MovieSource, int id, QuestionType type, int difficulty, String inquiry, String correctAnswer, String [] movieNames)
	{
		this.id = id;
		this.type = type;
		this.difficulty = difficulty;
		this.inquiry = inquiry;
		this.correctAnswer = CineUtils.standardize(correctAnswer);
		this.movieList = new MovieList();

		for (String movieName : movieNames)
			this.movieList.AddMovie(MovieSource.getMovie(movieName));
	}


	public Question(MovieList MovieSource, JSONObject jsonObject)
	{
		this (
			MovieSource,
			Math.toIntExact((long) jsonObject.get("id")),
			QuestionType.values()[Math.toIntExact((long) jsonObject.get("type"))],
			Math.toIntExact((long) jsonObject.get("difficulty")),
			(String) jsonObject.get("inquiry"),
			(String) jsonObject.get("answer"),
			CineUtils.jsonToArray((JSONArray) jsonObject.get("movieNames"))
		);
	}


	/**
	 * Avalia se determinada resposta está correta.
	 * @param answer A resposta dada pelo usuário.
	 * @return true se a resposta estiver correta e false caso contrário.
	 * @throws Exception Caso o tipo da pergunta não seja conhecido.
	 */
	public boolean evaluate (String answer) throws Exception
	{
		// PADRONIZANDO A RESPOSTA
		answer = CineUtils.standardize(answer);
		
		// CHECANDO SE A RESPOSTA ESTÁ CORRETA DE ACORDO COM O TIPO DA PERGUNTA
		switch (this.type)
		{
			case MUTIPLE_CHOISE:
				return this.correctAnswer.equals(answer);
			default: 
				throw new Exception ("The question is of an unknown type.");
		}
		
	}

	
	/**
	 * Avalia se determinada resposta está correta.
	 * @param answer A resposta dada pelo usuário.
	 * @return true se a resposta estiver correta e false caso contrário.
	 * @throws Exception Caso o tipo da pergunta não seja conhecido.
	 */
	public boolean evaluate (int answer) throws Exception
	{
		return this.evaluate(Integer.toString(answer));
	}

}
