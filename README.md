# JavaEE_DWWS
Repositorio para o Trabalho 1 no Desenvolvimento Java Web


  \<datasource jta="true" jndi-name="java:jboss/datasources/Casa" pool-name="CasaPool" enabled="true"  use-java-context="true" use-ccm="true">
        <connection-url>jdbc:mysql://localhost:3306/casa</connection-url>
        <driver>mysql</driver>
        <security>
            <user-name>casa</user-name>
            <password>casa</password>
        </security>
    </datasource>

  <datasource jta="true" jndi-name="java:jboss/datasources/GameTime" pool-name="GameTimePool" enabled="true" use-java-context="true">
  <connection-url>jdbc:mysql://localhost:3306/gametime</connection-url>
  <driver>mysql</driver>
  <security>
  <user-name>gametime</user-name>
  <password>gametime</password>
  </security>
  </datasource>
