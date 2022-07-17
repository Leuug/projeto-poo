package cinewise;


// IMPORTANDO PACOTES ÚTEIS DO JAVA
import java.text.Normalizer;
import java.util.*;

// IMPORTANDO PACOTES PARA A MANIPULAÇÃO DE ARQUIVOS JSON
import org.json.simple.JSONArray;


public class CineUtils 
{
    /**
	 * Padroniza uma string, para facilitar comparações entre strings.
	 * @param input Uma string qualquer.
	 * @return uma string padronizada.
	 */
	public static String standardize(String input)
	{
		return removeAccents(input).toLowerCase().strip();
	}

    /**
     * Converte um JSONArray para um array de strings.
     * @param jsonArray O JSONArray que será convertido.
     * @return O array de strings.
     */
    public static String [] jsonToArray (JSONArray jsonArray)
    {
        // INICIALIZANDO UMA LISTA  
        List<String> list = new ArrayList<String>();

        // PASSANDO OS ELEMENTOS DO JSONARRAY PARA A LISTA
        int size = jsonArray.size();
        for (int i = 0; i < size; i++)
            list.add((String) jsonArray.get(i));
  
        // PASSANDO OS ITENS DA LISTA PARA UM ARRAY
        size = list.size();
        String[] stringArray = list.toArray(new String[size]);
        
        return stringArray;
    }


    /**
	 * Remove acentos de uma string.
	 * Baseado em https://www.baeldung.com/java-remove-accents-from-text
	 * @param input Uma string qualquer.
	 * @return String sem caracteres assentuados.
	 */
	private static String removeAccents(String input) 
	{
		// TRATANDO ERROS
		if (null == input) return null;
		
		/* OBS: O código abaixo primeiro normaliza a string. Ou seja, ele
		 * 	divide caracteres acentuados em dois, o caracter "normal" e um
		 * 	caracter que indica a acentuação. Em seguinda, esses caracteres
		 * 	indicadores de acentuação são removidos.
		 */
		return Normalizer.normalize(input, Normalizer.Form.NFKD)
			.replaceAll("\\p{M}", "");
	}
}
