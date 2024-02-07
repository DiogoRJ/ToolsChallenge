# ToolsChallenge

**Como rodar o projeto:**
- Sugerida a utilização da IDE IntelliJ, pode ser a versão Community;
- Importar como um projeto Maven;
- Baixar dependências;
- Garantir que nas configurações do projeto esteja configurado Java 17;
- Rodar o projeto pela classe main ChallengeApplication

**Observações:**
- Collection do Postman com as requisições está dentro da pasta 'collection'
- Foi usado banco de dados em memória: H2 Database SQL
- URL Swagger: http://localhost:8080/swagger-ui/index.html
- URL H2 Console: http://localhost:8080/h2-console
- H2.url = jdbc:h2:mem:testdb
- H2.username = sa
- H2.password = password


## Tecnologias

*  _Java 17_
*  _Spring Boot 3.2.2_
*  _SwaggerUI_
*  _H2 Database_

---
### Guia passo a passo para configurar o ambiente:

### Requisitos mínimo
* Java 17
* Maven

Abaixo estão os passos básicos para configurar o Java 8 e o Maven em Windows, Linux e macOS.
### Windows:

#### Java 17:
- Baixe o instalador do JDK 17 para Windows no site oficial da Oracle
https://www.azul.com/downloads/?version=java-17-lts&show-old-builds=true#zulu
- Execute o instalador e siga as instruções para concluir a instalação
- Defina a variável de ambiente JAVA_HOME apontando para o diretório de instalação do JDK.
- Link para instrução da definição de Path em variável de ambiente: https://www.java.com/pt-BR/download/help/path_pt-br.html

#### Maven:
Baixe o Apache Maven de https://maven.apache.org/download.cgi
Extraia o arquivo baixado para um diretório de sua escolha
Adicione o diretório bin do Maven ao seu PATH e crie a variável de ambiente MAVEN_HOME.
---

### Linux:

#### Java 17:
- Instale o JDK 17 usando o gerenciador de pacotes da sua distribuição Linux. Por exemplo, no Ubuntu, você pode usar:
```
sudo apt-get install openjdk-17-jdk
```

- Configure a variável de ambiente JAVA_HOME no seu arquivo .bashrc ou .zshrc.
```
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
export PATH=$PATH:$JAVA_HOME/bin
```

- Execute source ~/.bashrc ou source ~/.zshrc para aplicar as alterações.

#### Maven:
Instale o Maven usando o gerenciador de pacotes. No Ubuntu, por exemplo:
sudo apt-get install maven
---

### macOS:
#### Java 17:
- No macOS, você pode instalar o JDK 8 usando o Homebrew:
```
brew tap adoptopenjdk/openjdk
brew install adoptopenjdk17
```
- Execute o seguinte comando para encontrar o caminho do executável do Java 17:
```
which java
```
- Configure a variável de ambiente JAVA_HOME em seu arquivo .bash_profile ou .zshrc.
```
export JAVA_HOME=<caminho_do_java_17>
export PATH=$PATH:$JAVA_HOME/bin
```

- Execute source ~/.bash_profile ou source ~/.zshrc para aplicar as alterações.



#### Maven:
- Instale o Maven usando o Homebrew:
```
brew install maven
```
---

Depois de configurar o Java 8 e o Maven, você pode verificar as instalações executando os seguintes comandos:
```
java -version
mvn -version
```

