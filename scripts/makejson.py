# MÓDULOS IMPORTANTES
import json

# ABRINDO OS ARQUIVOS
intput_file = open ("../data/references.txt", "r")
output_file = open ("../data/movies.json", "w")

# CRIANDO VARIÁVEIS RELEVANTES
movies = []
counter = 0

# LOOP DE LEITURA
for line in intput_file:
    # CHECANDO SE A LINHA ESTÁ VAZIA
    if not len(line.strip()):
        break

    # QUEBRANDO A LINHA EM PEDAÇOS E VERIFICANDO SE HÁ PEDAÇOS O SUFICIENTE
    pieces = line.replace("\n","").split(";")
    if 3 != len (pieces):
        print ("Line {} could not be read.".format(counter))
        continue
    
    # INSERINDO OS DADOS EM UM DICINÁRIO
    movie = {
        "id": counter,
        "name": pieces[0].strip(),
        "img": pieces[2].strip().replace("./images/",""),
        "synopsis": pieces[1].strip(),
    }

    # INSERINDO O FILME NA LISTA DE FILMES
    movies.append(movie)

    counter += 1


# ESCREVENDO TODOS OS FILMES EM UM JSON
json.dump(movies, output_file, indent = 4)