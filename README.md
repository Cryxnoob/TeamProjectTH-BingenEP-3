# TeamProjectTH-BingenEP-3

Erstelle eine einfache Zugsimulation als Teil der TH-Bingen Informatik Vorlesung.

## Mitarbeiter
Janos, Marcel, Niklas und Benedict

## Sprache
Die Kommentare und die Unterhaltung werden in Deutsch geführt.

## Lizenz
Wir verwenden in diesem Projekt die MIT-Lizenz.

## Code of Conduct
Wir haben den Contributor Covenant Code of Conduct gewählt.

## Anwendung Hello World 
Nachdem der Download der JAR unter Releases vollendet ist, muss Powershell geöffnet werden 
und dort in den Ordner TeamProjectTH-BingenEP-3 navigiert werden.
Jetzt muss folgender Befehl eingegeben werden.
 
**java -jar target/TeamProjectTH-BingenEP-3-1.0-SNAPSHOT-jar-with-dependencies.jar**

Das Programm lässt eine simualtion eines Schienennetzwerks mit Zügen und Segmenten durchlaufen.
Das Netz besteht aus 5 Segmenten auf denen jeweils maximal 3 Züge gleichzeitig platz finden. Jeder
Zug hat einen durch eine CSV Datei zugewiesenen Fahrplan. Diese zugewiesenen Werte sind konfigurierbar.
Die Funktion schaut nun ob der Zug auf dem Segment fahren kann oder dieses Überlastet ist und gibt
somit aus ob der Zug pünktlich oder verspätet ist. Die Ergebnisse werden in einer results.csv Datei
und in der Konsole ausgegeben. Es sind nur die Züge auf einem Segment verspätet die über der
maximalen Anzahl von drei liegen. Die Entscheidung welcher Zug verspätet ist erfolgt zufällig.
