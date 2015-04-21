# JavaEE_DWWS
Repositorio para o Trabalho 1 no Desenvolvimento Java Web

Este projeto Utiliza gerenciamento de dependências com Maven, de modo que não é necessário distribuir os arquivos JAR dos frameworks utilizados;

# Database creation

1   Create a database schema named gametime;

2   Create a database user named gametime with password gametime;

3   Give user gametime full permission for the schema gametime.

# Datasource configuration in WildFly

    <datasource jta="true" jndi-name="java:jboss/datasources/GameTime" pool-name="GameTimePool" enabled="true" use-java-context="true">
      <connection-url>jdbc:mysql://localhost:3306/gametime</connection-url>
      <driver>mysql</driver>
      <security>
        <user-name>gametime</user-name>
        <password>gametime</password>
      </security>
    </datasource>
