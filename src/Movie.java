public class Movie
{
	private String movieName;
	private String movieSynopsis;
	private String moviePath;


	public Movie(String _movieName, String _movieSynopsis, String _moviePath)
	{
		this.movieName = _movieName;
		this.movieSynopsis = _movieSynopsis;
		this.moviePath = _moviePath;
	}


	public String getName()
	{
		return this.movieName;
	}

	public String getSynopsis()
	{
		return this.movieSynopsis;
	}

	public String getPath()
	{
		return this.moviePath;
	}

	
}
