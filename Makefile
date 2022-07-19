# CONFIGURAÇÕES
JC := javac
JR := java

SRCDIR := src
BINDIR := bin
LIBDIR := lib


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
	JAVACPSEPARATOR:=;
else
	PROGRAM:=bin/$(EXE)
	MKDIR:=mkdir -p
	RM:=rm -rfv
	MAKE:=make
	CLEAR:=clear
	NEWLINE:=@echo 
	SHELL:=/bin/bash
	JAVACPSEPARATOR:=:
endif


# BUSCANDO ARQUIVOS E DEFININDO ESPECTATIVAS
JARFILES := $(wildcard $(LIBDIR)/*.jar)
ESCAPE_SPACE = $(subst _,,_ _)
CP := $(BINDIR)$(subst $(ESCAPE_SPACE),,$(foreach jarfile,$(JARFILES),$(JAVACPSEPARATOR)$(strip $(jarfile))))

JAVAFILES := $(wildcard $(SRCDIR)/*.java) $(wildcard $(SRCDIR)/*/*.java)
CLASSFILES := $(subst $(SRCDIR),$(BINDIR),$(subst .java,.class,$(JAVAFILES)))


# FUNÇÕES PARA MANIPULAÇÃO DO NOME DE CLASSES
getClassName=$(notdir $(basename $(1)))
getJavaFile=$(filter %/$(call getClassName,$(1)).java,$(JAVAFILES))


# CONFIGURANDO TARGETS
.PHONY: clean
.IGNORE: clean


# TARGET PADRÃO
default: all


# TARGETS IMPORTANTES
all: $(BINDIR) $(CLASSFILES)

run: $(CLASSFILES)
	$(JR) -cp $(CP) $(focus)

ifeq ($(OS),Windows_NT)
clean:
	$(RM) $(subst /,\\,$(wildcard $(BINDIR)/*))
else
clean:
	$(RM) $(wildcard $(BINDIR)/*)
endif

fresh: clean all


# TARGETS PRÁTICOS
clear: clean
	$(CLEAR)


rerun:
	$(make) clean
	$(make) all
	$(CLEAR)
	$(MAKE) run


# COMPILANDO E CRIANDO ELEMENTOS
$(CLASSFILES): $(call getJavaFile,$@)
	$(JC) -cp $(CP) -sourcepath $(SRCDIR) -d $(BINDIR) $(call getJavaFile,$@)

$(BINDIR):
	$(MKDIR) $(BINDIR)


# TARGETS PARA SCRIPTS
question:
	python3 scripts/QuestionMaker.py

# TARGETS PARA UTILIDADES SECUNDÁRIAS
debian-java-setup:
	sudo apt update && sudo apt upgrade -y
	sudo apt-get install openjdk-17-jre -y
	sudo apt-get install openjdk-17-jdk -y

debian-python-setup:
	sudo apt-get install python3-pip
	pip3 install Unidecode

debian-setup: debian-java-setup debian-python-setup
