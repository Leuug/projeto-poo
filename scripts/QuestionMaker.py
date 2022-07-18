# MÓDULOS IMPORTANTES
import json
import os
import unidecode

# CONTANTES
PATH_TO_FILE = "data/questions.json"

# FUNÇÕES
def standardize(string):
    return unidecode.unidecode(string.strip().lower())

# CARREGANDO PERGUNTAS JÁ EXISTENTES
questions = []
if os.path.exists(PATH_TO_FILE):
    intput_file = open (PATH_TO_FILE, "r")
    questions = json.load(intput_file)
    intput_file.close()

lastId = 0 if 0 == len(questions) else questions[len(questions) - 1]["id"]
nextId = lastId + 1

# LOOP DE LEITURA
active = True
while active:
    newQuestion = {
        "id": nextId,
        "inquiry": input("Qual é o enunciado da questão? ").strip(),
        "type": int(input("Qual é o typo da questão? ").strip()),
        "difficulty": int(input("Qual é a dificuldade da questão? ").strip()),
        "correctAnswer": input("Qual é a resposta correta? ").strip(),
        "movieNames": []
    }

    n = int(input("Quantas referências a filmes essa questão usa? "))
    for i in range(n):
        newQuestion["movieNames"].append(input("Digite o nome do filme número {}: ".format(i)))
    
    questions.append(newQuestion)
    answer = input("Deseja cadastrar mais uma pergunta? [S/n] ").strip().lower()
    active = answer in ["y", "yes", "s", "sim"]
    nextId += 1


# ESCREVENDO TODOS OS FILMES EM UM JSON
output_file = open (PATH_TO_FILE, "w")
json.dump(questions, output_file, indent = 4)