# CONFIGURAÇÕES
JC := javac
JR := java
JCFLAGS := 

SRCDIR := src
BINDIR := bin


# ARGUMENTOS
focus:=

ifeq ($(focus),)
	focus := Main
endif


# INDENTIFICANDO O SISTEMA OPERACIONAL
ifeq ($(OS),Windows_NT)
	MKDIR:=mkdir
	RM:=del /s /q
	MAKE:=mingw32-make
	CLEAR:=cls
	NEWLINE:=@echo.
else
	PROGRAM:=bin/$(EXE)
	MKDIR:=mkdir -p
	RM:=rm -rfv
	MAKE:=make
	CLEAR:=clear
	NEWLINE:=@echo 
	SHELL:=/bin/bash
endif


# BUSCANDO ARQUIVOS E DEFININDO ESPECTATIVAS
JAVAFILES := $(wildcard $(SRCDIR)/*.java)
CLASSFILES := $(subst $(SRCDIR),$(BINDIR),$(subst .java,.class,$(JAVAFILES)))


# FUNÇÕES PARA MANIPULAÇÃO DO NOME DE CLASSES
getClassName=$(notdir $(basename $(1)))
getJavaFile=$(filter %/$(call getClassName,$(1)).java,$(JAVAFILES))


# CONFIGURANDO TARGETS
.PHONY: clean


# TARGET PADRÃO
default: all


# TARGETS IMPORTANTES
all: $(BINDIR) $(CLASSFILES)

run: $(CLASSFILES)
	$(JR) -cp $(BINDIR) $(focus)

clean:
	@$(RM) $(wildcard $(BINDIR)/*.class)

fresh: clean all


# COMPILANDO E CRIANDO ELEMENTOS
$(CLASSFILES): $(call getJavaFile,$@)
	$(JC) -sourcepath $(SRCDIR) -d $(BINDIR) $(call getJavaFile,$@)

$(BINDIR):
	$(MKDIR) $(BINDIR)


# TARGETS PARA UTILIDADES SECUNDÁRIAS
debian-setup:
	sudo apt update && sudo apt upgrade -y
	sudo apt-get install openjdk-17-jre -y
	sudo apt-get install openjdk-17-jdk -y
