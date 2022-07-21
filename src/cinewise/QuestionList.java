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
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

/**
 * Representa uma lista de questões. Essa classe serve como lista de instâncias
 * da classe 'Question'.
 * 
 * @author Bernardo Maia Coelho (12542481) e {...}
 * @see cinewise.Question
 */
public class QuestionList {
	private Random random;
	private MovieList movieList;
	private ArrayList<Question> questionList;

	public QuestionList() {
		this.random = new Random();
		this.movieList = new MovieList();
		this.questionList = new ArrayList<Question>();
	}

	public QuestionList(MovieList movieList) {
		this.random = new Random();
		this.movieList = movieList;
		this.questionList = new ArrayList<Question>();
	}

	/**
	 * @param filepath O caminho para um json contendo informações acerca das
	 *                 perguntas. Vale destacar que esse caminho começa no diretório
	 *                 do
	 *                 projeto, na pasta onde o Makefile está presente. Dessa forma,
	 *                 idealmente, a string filepath começa com "data/" e termina
	 *                 com ".json".
	 */
	public QuestionList(MovieList movieList, String filepath) throws Exception {
		this(movieList);

		// INICIALIZANDO O LEITOR BUFFERIZADO
		FileReader fr = new FileReader(filepath);
		BufferedReader reader = new BufferedReader(fr);

		// LENDO O ARQUIVO JSON
		JSONParser parser = new JSONParser();
		JSONArray jsonArray = (JSONArray) parser.parse(reader);
		int arrLen = (int) jsonArray.size();

		// INICIALIZANDO VALORES
		this.movieList = movieList;
		this.questionList = new ArrayList<Question>();

		// LOOP DE CONVERSÃO DE OBJETO JSON PARA INSTÂNCIA DA CLASSE QUESTION
		for (int i = 0; i < arrLen; i++) {
			JSONObject jsonObject = (JSONObject) jsonArray.get(i);
			Question newQuestion = new Question(this.movieList, jsonObject);
			this.questionList.add(newQuestion);
		}
	}

	/**
	 * Remove todas as perguntas cuja dificuldade seja maior que determinado
	 * valor
	 * 
	 * @param difficulty A valor de dificuldade a ser usado como parâmetro.
	 */
	public void SelectDificulty(int difficulty) {
		for (Question question : this.questionList)
			if (question.difficulty > difficulty)
				this.RemoveQuestion(question);
	}

	/**
	 * Remove um objeto pergunta da lista.
	 * 
	 * @param question O objeto pergunta a ser removido.
	 */
	public void RemoveQuestion(Question question) {
		this.questionList.remove(question);
	}

	/**
	 * @return uma pergunta aleatória da lista.
	 */
	public Question getRandQuestion() {
		if (this.questionList.size() <= 0) {
			System.out.println("O tamanho é zero!!!\n");
			return null;
		}

		return this.questionList
				.get(this.random.nextInt(this.questionList.size()));
	}

	public int getSize() {
		if (null == this.questionList)
			return 0;
		return this.questionList.size();
	}
}
