# Team Relation Manager
_Webapp to managing the information of all team members_

## Aufgabenstellung
1. Usecase: Es soll eine Softwarelösung erstellt werden, die Kontakte eines Teams verwaltet.
2. Datenbankmodell:
    1. Mandatory:
        1. Attribute: Vorname, Nachname, Mailadresse
        2. Optional: weitere sinnvolle Attribute/Entitäten definieren und ein kleines Datenbankmodell modellieren

### Dazu ist eine Webapplikation erstellen
1. Einstiegsseite:
    1. Übersicht bisher angelegter Kontakte (readonly)
    2. Auswählen eines Datensatzes und Weiterleitung zur Detailseite
2. Detailseite zum Datensatz:
    1. Anlegen
    2. Bearbeiten
    3. Löschen
    4. Optional:
        1. Validierung auf sinnvolle Mailadresse
4. Abgrenzung:
    1. keine Login-Funktion im ersten Schritt nötig

### Technologie
* Multitier Architektur
* Die Datenbank ist frei wählbar
* ein Teil der Umsetzung soll Javabasiert erfolgen
* setzen Sie bitte sinnvoll Komponenten/Frameworks ihrer Wahl ein
* denken Sie an Logging und Fehlerbehandlung

## Umsetzung
### Anpassung der Anforderung
1. Anlegen von neuen Datensätzen muss auf die Übersichtsseite, da sonst bei keinem vorhanden Eintrag, dann auch kein neuer angelegt werden kann

### Erweiterung
1. Beziehung Angestellter <-> Team hinzugefügt
    * Team kann viele Angestellte besitzen
    * Angestellter kann in einem Team sein
2. Suche nach Angestellten Vorname und Nachname hinzugefügt
3. Validierung hinzugefügt (Serverseitig):
    * Angestellter
        * Vorname, Nachname (min 1 Zeichen, no blanks)
        * Email (gültiges Format/ regex html5)
    * Team
        * Name (min 1 Zeichen, no blanks)
4. Clientseitige (HTNL5) Validierung nicht hinzugefügt, um die Serverseitige einfacher zu zeigen.

### Ausblick/ Weiterentwicklung
* REST (API) Schnittstelle implementieren
    * Frontend über Ajax und REST rendern
* Template Engine Freemarker einsetzen
* Unit test implementieren
* Aufteilung des HTML Renderings (Wiederverwendung/ Anpassungen global einmalig durchführen)
    * head
    * body
        * header
        * content
        * java script include
* i18n Unterstützung für alle Literale im HTML/ view
* Team Zuweisung vom Mitarbeiter entfernen => Implementierung verbessern
    * Spring mvc jsp form:select ist nicht geeignet (selbst mit null option $(null}) => validation error beim Pfad team.id
    * Workarounds entfernen
* Team Name unique machen
	 * Validierung komplexer/ spezieller Behandlung im Controller notwendig
	 * Mögliche Umsetzung:
	     * Eigener Validator der eine DB Abfrage auf vorhandene Namen ausführt
	     * @Column(unique= true)
* Security
     * XSS: bei Ausgabe von Benutzereingaben diese escapen (HTML) => http://owasp.github.io/owasp-java-encoder/

### Eingesetzte Frameworks
* Spring Framework MVC
* Hibernate ORM
* AspectJ

## Projekt Setup
1. MySQL Schema anlegen:
    * db-design/01-db-create-sql auf dem MySQL Server ausführen
2. MySQL Server properties im Projekt
    * Vorlage src/main/resources/empty_mysql.properties zu src/main/resources/mysql.properties kopieren 
    * Properties in der mysql.properties entsprechend des DB Servers setzen
    * jdbc.url
    * jdbc.user
    * jdbc.password
    * jdbc.driver
3. Log4j2: src/main/resources/log4j2.xml musss ggf. _basePath_ angepasst werden

## Projekt ausführen
### Eclipse/ IDE
Projekt auf einem Java Applicationsserver (Bsp. Tomcat) Server ausführen (Run as: Run on Server)

* Web-Frontend: http://localhost:8080/team-relation-manager

### WAR File
Mit Maven das Projekt bauen (Maven install) und das WAR file dann auf dem Java Applikations Server (Bsp. Tomcat) ablegen

## Bugs
* keine bekannten

## Releases
* v1.0.1
    * Fehlerbehebung:
        * Email Validierung: das aktuelle Regex Pattern sorgt für eine Pflichteingabe
        * Valdierungs-Fehlermeldungen werden nicht aus der messages.properties resource verwendet => standard Meldungen werden ausgegeben
    * Feature:
        * String Eingaben werden getrimmt
* v1.0.0
    * Release Version
        * voll funktionierende Anwendung
* v0.9
    * erste lauffähige Version der Webanwendung
