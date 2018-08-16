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
    1. Anlegen (Änderung: muss auf die Übersichtsseite -> bei keinem Eintrag, kann auch kein neuer angelegt werden)
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
