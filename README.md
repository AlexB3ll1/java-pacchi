# Java OOP — registro e gestione pacchi

**Tipologia:** esercitazione di preparazione all'esame  
**Tecnologia:** Java (progetto Eclipse con `module-info.java`)

## Obiettivo

Modellare un centro di gestione pacchi, con registrazione delle spedizioni, controllo dei posti disponibili, consegna, calcolo dei costi e salvataggio di un report testuale.

## Struttura dei sorgenti

```text
simulazione11/
├── src/
│   ├── module-info.java
│   └── simulazione11/
│       ├── Pacco.java
│       ├── TipoPacco.java
│       ├── RegistroPacchi.java
│       ├── RegistroPacchiUrbano.java
│       └── PaccoException.java
├── .project
└── .classpath
```

## Funzionalità presenti

- **Pacco:** rappresenta codice, destinatario, peso, tipologia e stato di consegna, con validazione dei dati di creazione.
- **TipoPacco:** definisce i valori `STANDARD` ed `EXPRESS` e una verifica della tipologia.
- **RegistroPacchi (astratta):** mantiene centro, posti disponibili per tipo e lista dei pacchi; permette inserimento, marcatura della consegna, ricerca per tipo e calcolo del peso totale non consegnato.
- **RegistroPacchiUrbano:** applica una formula di costo in base al peso e al tipo e ne calcola il totale.
- **PaccoException:** eccezione personalizzata per operazioni non valide.
- **Report:** il metodo `salvaReport(String nomeFile)` crea un file di testo riepilogativo con numero di pacchi, consegnati, non consegnati e peso ancora da consegnare.

### Regole di costo presenti nell'esercizio

- `STANDARD`: `peso × 2`.
- `EXPRESS`: `peso × 4 + 5`.

Sono **formule illustrative implementate nel codice**, non tariffe di un corriere reale.

## Concetti applicati

Incapsulamento, ereditarietà, classi astratte, overriding, polimorfismo, eccezioni personalizzate, `List`, `LinkedList`, `Map`, confronto `equals/hashCode` e I/O su file.

## Compilazione

Richiede un JDK con supporto ai moduli Java (Java 9 o successivo). Dalla cartella `simulazione11/`, con una shell Bash:

```bash
mkdir -p out
javac -d out src/module-info.java src/simulazione11/*.java
```

Anche qui manca una classe `main`: **è un esercizio sulle classi**, non un programma console pronto da avviare. Per provarlo è necessario un piccolo programma utilizzatore o un test.

Si può importare il progetto originale in Eclipse.

## Limiti e possibili miglioramenti

- Assenza di una classe di avvio e di test automatici.
- La mappa dei posti disponibili viene modificata dal registro; per un uso robusto sarebbe utile copiarla o controllarne meglio l'uso esterno.
- La scrittura del report usa un `PrintWriter` senza `try-with-resources`; la gestione delle risorse e degli errori di scrittura può essere migliorata.
- I file creati dal report dipendono dal percorso passato dall'utilizzatore.

## Stato

Sorgenti Java compilati con successo durante la revisione; test funzionali completi non eseguiti.
