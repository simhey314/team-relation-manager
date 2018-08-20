# Team Relation Manager
_Webapp to managing the information of all team members_

## Aufgabenstellung
1. Usecase: Es soll eine Softwarelösung erstellt werden, die Kontakte eines Teams verwaltet.
2. Datenbankmodell:
    1. Mandatory:
        1. Attribute: Vorname, Nachname, Mailadresse
        2. Optional: weitere sinnvolle Attribute/EntitÃ¤ten definieren und ein kleines Datenbankmodell modellieren

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

### Ausblick/ Weiterentwicklung
* REST (API) Schnittstelle implementieren
    * Frontend über Ajax und REST rendern
* Template Engine Freemarker einsetzen

### Eingesetzte Frameworks
* Spring Framework MVC
* Hibernate ORM
* AspectJ

## Projekt Konfiguration
1. MySQL Server: src/main/resources/mysql.properties musss entsprechend des SQL-Servers angepasst werden
    * jdbc.url
    * jdbc.user
    * jdbc.password
2. Log4j2: src/main/resources/log4j2.xml musss ggf. _basePath_ angepasst werden

## Projekt ausführen
### Eclipse/ IDE
Projekt auf einem Java Applicationsserver (Bsp. Tomcat) Server ausführen (Run as: Run on Server)

### WAR File
Mit Maven das Projekt bauen (Maven install) und das WAR file dann auf dem Java Applikations Server (Bsp. Tomcat) ablegen