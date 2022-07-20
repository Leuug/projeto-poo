package cinewise;


import java.util.*;

public class CineSession {
    // private int difficulty;
    private MovieList movieList;
    private QuestionList questionList;
    private Question currentQuestion;
    private int score;


    /**
     * Esse construtor assume os nomes dos arquivos json que contem dados
     *  acerca das perguntas e dos filmes.
     */
    public CineSession (int difficulty, String srcpath) throws Exception
    {
        // this.difficulty = difficulty;
        this.movieList = new MovieList(srcpath + "/movies.json");
        this.questionList = new QuestionList(movieList, srcpath + "/questions.json");
        this.questionList.SelectDificulty(difficulty);
        this.currentQuestion = null;
        this.nextQuestion ();
        this.score = 0;
    }


    /**
     * Esse construtor assume que os arquivos com dados acerca dos filmes e
     *  das questões estão armazenados na pasta "data".
     */
    public CineSession (int difficulty) throws Exception
    {
        this (difficulty, "data");
    }


    /**
     * Passa para a próxima questão.
     */
    public void nextQuestion () throws Exception
    {
        if (0 == this.questionList.getSize()) 
            this.currentQuestion = null;

        this.currentQuestion = this.questionList.getRandQuestion();
        if (this.currentQuestion.getHitHard())
        {    
            System.out.println("Forte de mais\n\n");
            this.questionList.RemoveQuestion(this.currentQuestion);
        }
    }

    public Question getCurrentQuestion ()
    {
        return this.currentQuestion;
    }

    /**
     * Checa se está na hora de terminar o jogo. É importante checar se esse é
     *  o caso antes de interagir com a próxima pergunta, pois pode ser que não
     *  haja uma próxima pergunta.
     * @return true se estiver na hora de terminar a sessão e false caso
     *  contrário.
     */
    public boolean isItTheEnd ()
    {
        if (0 == this.questionList.getSize()) 
            this.currentQuestion = null;
        return 0 == this.questionList.getSize() && 
            null == this.currentQuestion;
    }


    /**
     * Busca o enunciado da resposta atual.
     * @return Uma string com um enunciado.
     * @throws Exception Caso não haja pergunta atual.
     */
    public String getInquiry () throws Exception
    {
        if (null == this.currentQuestion) 
            throw new Exception("Current question is a null.");
        
        return this.currentQuestion.inquiry;
    }


    /**
     * @return Uma string contendo a resposta esperada.
     * @throws Exception Caso não haja pergunta atual.
     */
    public String getCorrectAnswer () throws Exception
    {
        if (null == this.currentQuestion) 
            throw new Exception("Current question is a null.");
        
        return this.currentQuestion.correctAnswer;
    }


    /**
     * Avalia se a resposta do usuário está correta.
     * @return true se a resposta estiver correta e false caso contrário.
     * @throws Exception Caso não haja pergunta atual.
     */
    public boolean evaluate (String answer) throws Exception
    {
        if (null == this.currentQuestion) 
            throw new Exception("Current question is a null.");
        
        return this.currentQuestion.evaluate(answer);
    }


    /**
     * Avalia se a resposta do usuário está correta.
     * @return true se a resposta estiver correta e false caso contrário.
     * @throws Exception Caso não haja pergunta atual.
     */
    public boolean evaluate (int answer) throws Exception
    {
        if (null == this.currentQuestion) 
            throw new Exception("Current question is a null.");
        
        return this.currentQuestion.evaluate(answer);
    }


    /**
     * @return O score.
     */
    public int getScore()
    {
        return this.score;
    }


    /**
     * Adiciona um valor ao score.
     * @param increment Valor a ser adicionado ao score.
     */
    public void addToScore (int increment)
    {
        this.score += increment;
    }

    /**
     * Retorna um filme em determinado indicie da lista de filmes da pergunta
     *  atual.
     * @param index Indicie do filme a ser retornado.
     * @return Um filme ou null.
     */
    public Movie getMovie (int index)
    {
        return this.currentQuestion.movieList.getMovie(index);
    }


    /**
     * Retorna a lista de filmes da questão atual.
     * @return Uma lista de filmes ou null.
     */
    public MovieList getMovieList ()
    {
        return this.currentQuestion.movieList;
    }

    
    /**
     * Busca o primeiro filme da lista de filmes da pergunta atual.
     * @return Um filme ou null.
     */
    public Movie getMovie ()
    {
        return this.currentQuestion.movieList.getMovie(0);
    }

    /**
     * Busca o caminho para a imagem do poster do primeiro filme da lista
     *  de filmes da pergunta atual.
     * @return O caminho para a imagem do poster do filme.
     * @throws Exception Caso não haja pergunta atual.
     */ 
    public String getMoviePath() throws Exception
	{
		if (null == this.currentQuestion) 
            throw new Exception("Current question is a null.");
        
        // return this.getMovie().getPath();
        return this.currentQuestion.currentMovie.getPath();
	}


    /**
     * Retorna o nome do primeiro filme da lista de filmes da pergunta atual.
     * @return O nome do filme.
     * @throws Exception Caso não haja pergunta atual.
     */ 
    public String getMovieName() throws Exception
	{
		if (null == this.currentQuestion) 
            throw new Exception("Current question is a null.");
        
        // return this.getMovie().getName();
        return this.currentQuestion.currentMovie.getName();
	}


    /**
     * Retorna a sinopse do primeiro filme da lista de filmes da pergunta atual.
     * @return A string sinopse.
     * @throws Exception Caso não haja pergunta atual.
     */ 
    public String getSynopsis() throws Exception
	{
		if (null == this.currentQuestion) 
            throw new Exception("Current question is a null.");
        
        // return this.getMovie().getSynopsis();
        return this.currentQuestion.currentMovie.getSynopsis();
	}

    /**
     * Uma lista de respostas erradas contendo uma resposta certa.
     * @param choiseFactor
     * @return
     */
    public String [] genMutipleChoises (int choiseFactor) throws Exception
    {
        return this.currentQuestion
            .genMutipleChoises(this.movieList, choiseFactor);
    }

    public static void main(String[] args) throws Exception
    {
        // try
        // {
        CineSession cs = new CineSession(10, "data");
        System.out.println(Arrays.toString(cs.genMutipleChoises(4)));
        System.out.println(cs.getCorrectAnswer());

        // }
        // catch (Exception e)
        // {
        //     System.out.println("An error has occurred.");
        //     System.out.println(e);
        // }
    }
}

