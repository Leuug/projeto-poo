// PACOTE AO QUAL ESSA CLASSE PERTENCE
package cinewise;

// IMPORTANDO PACOTES PARA A MANIPULAÇÃO DE ARQUIVOS JSON
import org.json.simple.JSONObject;

/**
 * Representa um filme e contem informações relevantes acerca desse filme,
 * incluindo o caminho para a imagem do poster desse filme.
 * 
 * @author Bernardo Maia Coelho (12542481) e {...}
 * @see cinewise.MovieList
 */
public class Movie {
	private int id;
	private String movieName;
	private String movieSynopsis;
	private String moviePath;

	public Movie(String _movieName, String _movieSynopsis, String _moviePath) {
		this.movieName = _movieName;
		this.movieSynopsis = _movieSynopsis;
		this.moviePath = _moviePath;
	}

	public Movie(JSONObject jsonObject) {
		this(
				(String) jsonObject.get("name"),
				(String) jsonObject.get("synopsis"),
				"img/" + (String) jsonObject.get("img"));

		this.id = Math.toIntExact((long) jsonObject.get("id"));
	}

	/**
	 * @return O nome do filme.
	 */
	public String getName() {
		return this.movieName;
	}

	/**
	 * @return O id do filme.
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * @return A sinópse do filme.
	 */
	public String getSynopsis() {
		return this.movieSynopsis;
	}

	/**
	 * @return O caminho (path) para uma imagem jpg do poster do filme. Vale
	 *         destacar que esse caminho começa na pasta do projeto, ou seja,
	 *         idealmente, toda string caminho começa com "img/" e termina com
	 *         ".jpg".
	 */
	public String getPath() {
		return this.moviePath;
	}

	@Override
	public String toString() {
		return "Name: " + this.getName() + "\n" +
				"Synopsis: " + this.getSynopsis() + "\n" +
				"Path: " + this.getPath();
	}
}
