FILES = $(find . -type f -name '*.java')

all:
	javac -cp ./lib/*: ./src/* -d ./bin/

run:
	java -cp ./lib/*:./bin/ Main
