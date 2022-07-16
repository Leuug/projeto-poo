import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.FileReader;


public class MovieList
{
	private ArrayList<Movie> moviesList;


	public MovieList(FileReader file)
	{
		int l = 1;

		this.moviesList = new ArrayList<>();
		try
		{
			BufferedReader reader = new BufferedReader(file);
			String line = "";

			while ((line = reader.readLine()) != null)
			{
				l++;
				
				String[] movieData = line.split("; ");
				Movie newMovie = new Movie(movieData[0].toUpperCase(), movieData[1], movieData[2]);
				this.moviesList.add(newMovie);
			}
		}
		catch (Exception e)
		{
			System.out.println("Problem reading line " + l);
		}
	}


	public void AddMovie(Movie _movie)
	{
		this.moviesList.add(_movie);
	}

	public void RemoveMovie(Movie _movie)
	{
		this.moviesList.remove(_movie);
	}


	@Override
	public String toString()
	{
		String movieListString = "";

		for (Movie movie : moviesList)
		{
			movieListString = movieListString + "Name: " + movie.getName() + "\nSynopsis: " + movie.getSynopsis() + "\nPath: " + movie.getPath() + "\n------------------------------------------------------\n";
		}

		return movieListString;
	}


	public static void main(String[] args) throws FileNotFoundException
	{
		FileReader file = new FileReader(new File("../Images/references.txt"));
		MovieList list = new MovieList(file);
		System.out.println(list.toString());
	}
}
