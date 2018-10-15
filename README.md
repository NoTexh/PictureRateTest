# PictureRate

Das Projekt wird im Rahmen der Vorlesung Webprogrammierung der DHBW Karlsruhe entwickelt

## Technologien

-   Als Applikation-Server kommt Tomcat in der Version 9.0.12 zum Einsatz : 
        https://tomcat.apache.org/download-90.cgi
    Dazu wird unter Binary Distributions Core: die .zip-Datei benötigt.

-   Als Datenbank-Server wird MySQL in der Community Version verwendet : 
        https://dev.mysql.com/downloads/mysql/
    Es wird sowohl der Server, die MySQL Workbench als auch der JConnector benötigt.
    PS: Für eine Test-Datenbank wird ein entsprechendes Skript unter WebPages -> res -> picturetable.csv zu finden sein. Diese einfach mit     Hilfe von MySQL Workbench in MySQL importieren

-   NetBeans 9.0 wird als IDE verwendet, dort die Binaries herunterladen : 
        https://netbeans.apache.org/download/nb90/nb90.html
    Dazu soll der Tomcat-Server in die IDE integriert werden. 
        1. Dafür unter Tools -> Servers einen neuen Tomcat Server hinzufügen
        2. Den Pfad zu dem Tomcat-Ordner angeben
        3. Innerhalb des Tomcat-Ordners die Datei conf -> tomcat-users.xml wie folgt anpassen:
        <role rolename="manager-gui"/>
        <role rolename="manager-script"/>
        <role rolename="manager-jmx"/>
        <role rolename="manager-status"/>
        <user username="admin" password="admin" roles="manager-gui, manager-jmx, manager-script, manager-status"/>
        4. Als Benutzernamen und Passwort admin verwenden
        5. <Resource name="jdbc/db" auth="Container" type="javax.sql.DataSource"
              maxActive="100" maxIdle="30" maxWait="10000" username="root" password="root"
              driverClassName="com.mysql.cj.jdbc.Driver" url="jdbc:mysql://localhost:3306/picturerate?serverTimezone=Europe/Berlin" />

## Build-Prozess

Um den Code zu testen muss zuerst der Tomcat-Server gestartet werden. Dies kann unter Services -> Servers und dann mit Rechtsklick auf den Apache Tomcatserver gemacht werden.
Anschließend muss der Code durch klicken auf das grüne Dreieck ausgeführt werden.
