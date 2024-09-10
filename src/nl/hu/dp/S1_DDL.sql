-- ------------------------------------------------------------------------
-- Data & Persistency
-- Opdracht S1: Data Definition Language
--
-- (c) 2020 Hogeschool Utrecht
-- Tijmen Muller (tijmen.muller@hu.nl)
-- André Donk (andre.donk@hu.nl)
--
--
-- Opdracht: schrijf SQL-queries om onderstaande resultaten op te vragen,
-- aan te maken, verwijderen of aan te passen in de database van de
-- bedrijfscasus.
--
-- Codeer je uitwerking onder de regel 'DROP VIEW ...' (bij een SELECT)
-- of boven de regel 'ON CONFLICT DO NOTHING;' (bij een INSERT)
-- Je kunt deze eigen query selecteren en los uitvoeren, en wijzigen tot
-- je tevreden bent.
--
-- Vervolgens kun je je uitwerkingen testen door de testregels
-- (met [TEST] erachter) te activeren (haal hiervoor de commentaartekens
-- weg) en vervolgens het hele bestand uit te voeren. Hiervoor moet je de
-- testsuite in de database hebben geladen (bedrijf_postgresql_test.sql).
-- NB: niet alle opdrachten hebben testregels.
--
-- Lever je werk pas in op Canvas als alle tests slagen. Draai daarna
-- alle wijzigingen in de database terug met de queries helemaal onderaan.
-- ------------------------------------------------------------------------


-- S1.1. Geslacht
--
-- Voeg een kolom `geslacht` toe aan de medewerkerstabel.
ADD TABLE medewerkers
ADD COLUMN geslacht CHAR(1);

-- Voeg ook een beperkingsregel `m_geslacht_chk` toe aan deze kolom,
-- die ervoor zorgt dat alleen 'M' of 'V' als geldige waarde wordt
-- geaccepteerd. Test deze regel en neem de gegooide foutmelding op als
-- commentaar in de uitwerking.
ALTER TABLE medewerkers
ADD CONSTRAINT m_geslacht_chk
CHECK ( geslacht IN ('M', 'V') );




-- S1.2. Nieuwe afdeling
--
-- Het bedrijf krijgt een nieuwe onderzoeksafdeling 'ONDERZOEK' in Zwolle.
-- Om de onderzoeksafdeling op te zetten en daarna te leiden wordt de
-- nieuwe medewerker A DONK aangenomen. Hij krijgt medewerkersnummer 8000
-- en valt direct onder de directeur.
-- Voeg de nieuwe afdeling en de nieuwe medewerker toe aan de database.

-- Voeg de nieuwe afdeling 'ONDERZOEK' toe
INSERT INTO afdelingen (anr, aname, locatie)
VALUES (50, 'ONDERZOEK', 'Zwolle');

-- Voeg de nieuwe medewerker 'A DONK' toe
INSERT INTO medewerkers (mnr, naam, voorl, functie, chef, gbdatum, maandsal, comm, afd)
VALUES (7490, 'DONK', 'A', 'LEIDINGGEVENDE', 7432, '1980-05-01', 2280.00, NULL, 40)

-- S1.3. Verbetering op afdelingentabel
--
-- We gaan een aantal verbeteringen doorvoeren aan de tabel `afdelingen`:
--   a) Maak een sequence die afdelingsnummers genereert. Denk aan de beperking
--      dat afdelingsnummers veelvouden van 10 zijn.
--   b) Voeg een aantal afdelingen toe aan de tabel, maak daarbij gebruik van
--      de nieuwe sequence.
--   c) Op enig moment gaat het mis. De betreffende kolommen zijn te klein voor
--      nummers van 3 cijfers. Los dit probleem op.

-- a)
CREATE SEQUENCE afdeling_nr_seq
    START WITH 10
    INCREMENT BY 10;

-- b)
INSERT INTO afdelingen (anr, naam, locatie, hoofd)
VALUES (nextval('afdeling_nr_seq'), 'FINANCE', 'AMSTERDAM', 7698);

INSERT INTO afdelingen (anr, naam, locatie, hoofd)
VALUES (nextval('afdeling_nr_seq'), 'HR', 'UTRECHT', 7452);

INSERT INTO afdelingen (anr, naam, locatie, hoofd)
VALUES (nextval('afdeling_nr_seq'), 'IT', 'DEN HAAG', 7952);

-- c)
ALTER TABLE afdelingen
ALTER COLUMN anr TYPE INTERGER;

-- S1.4. Adressen
--
-- Maak een tabel `adressen`, waarin de adressen van de medewerkers worden
-- opgeslagen (inclusief adreshistorie). De tabel bestaat uit onderstaande
-- kolommen. Voeg minimaal één rij met adresgegevens van A DONK toe.
--
--    postcode      PK, bestaande uit 6 karakters (4 cijfers en 2 letters)
--    huisnummer    PK
--    ingangsdatum  PK
--    einddatum     moet na de ingangsdatum liggen
--    telefoon      10 cijfers, uniek
--    med_mnr       FK, verplicht

-- Tabel 'adressen' maken met de vereiste kolommen
CREATE TABLE adressen (
    postcode CHAR(6) NOT NULL,          -- Postcode moet bestaan uit 4 cijfers en 2 letters
    huisnummer INT NOT NULL,            -- Huisnummer
    ingangsdatum DATE NOT NULL,         -- Ingangsdatum, primary key
    einddatum DATE,                     -- Einddatum, moet na de inganggsdatum om geldig te zijn
    telefoon CHAR(10) UNIQUE NOT NULL,  -- Telefoonnummer moet minimaal 10 cijfers, uniek
    med_mnr INT NOT NULL,               -- Verwijzing naar de tabel medewerker, foreign key

    -- Primary Keys instellen voor postcode, huisnummer en ingangsdatum
    CONSTRAINT pk_adres PRIMARY KEY (postcode, huisnummer, ingangsdatum),

    -- Ervoor zorgen dat de einddatum wordt gecheckt of het wel of niet NA de inggangsdatum ligt
    CONSTRAINT chk_einddatum CHECK ( einddatum IS NULL OR einddatum > adressen.ingangsdatum ),

    -- Foreign Key 'med_mnr' verwijzen naar de medewerkers tabel
    CONSTRAINT fk_medewerker FOREIGN KEY (med_mnr) REFERENCES medewerkers (mnr)
);

-- Rij met adresgegevens voor A DONK toevoegen in de adressen tabel
INSERT INTO adressen (postcode, huisnummer, ingangsdatum, einddatum, telefoon, med_mnr)
VALUES ('1012RJ', 147, '2024-10-09', '2026-10-09', '0205226161', 7490)

-- S1.5. Commissie
--
-- De commissie van een medewerker (kolom `comm`) moet een bedrag bevatten als de medewerker een functie als
-- 'VERKOPER' heeft, anders moet de commissie NULL zijn. Schrijf hiervoor een beperkingsregel. Gebruik onderstaande
-- 'illegale' INSERTs om je beperkingsregel te controleren.

-- Deze insert is illegaal omdat de functie geen 'VERKOPER' is, maar 'TRAINER', en comm niet NULL
-- Verwachte foutmelding: CHECK constraint "comm_functie_chk" failed
INSERT INTO medewerkers (mnr, naam, voorl, functie, chef, gbdatum, maandsal, comm)
VALUES (8001, 'MULLER', 'TJ', 'TRAINER', 7566, '1982-08-18', 2000, 500);

-- Deze insert is illegaal omdat de functie 'VERKOPER' is, maar comm is NULL
-- Verwachte foutmeldingL CHECK constraint "comm_functe_chk" failed
INSERT INTO medewerkers (mnr, naam, voorl, functie, chef, gbdatum, maandsal, comm)
VALUES (8002, 'JANSEN', 'M', 'VERKOPER', 7698, '1981-07-17', 1000, NULL);

-- Beperkingsregel toe voegen om de waarde van 'comm' te controleren
ALTER TABLE medewerkers
ADD CONSTRAINT comm_functie_chk
CHECK (
    (functie = 'VERKOPER' AND comm IS NOT NULL) OR
    (functie <> 'VERKOPER' AND comm IS NULL)
    )

-- -------------------------[ HU TESTRAAMWERK ]--------------------------------
-- Met onderstaande query kun je je code testen. Zie bovenaan dit bestand
-- voor uitleg.

SELECT * FROM test_exists('S1.1', 1) AS resultaat
UNION
SELECT * FROM test_exists('S1.2', 1) AS resultaat
UNION
SELECT 'S1.3 wordt niet getest: geen test mogelijk.' AS resultaat
UNION
SELECT * FROM test_exists('S1.4', 6) AS resultaat
UNION
SELECT 'S1.5 wordt niet getest: handmatige test beschikbaar.' AS resultaat
ORDER BY resultaat;


-- Draai alle wijzigingen terug om conflicten in komende opdrachten te voorkomen.
DROP TABLE IF EXISTS adressen;
UPDATE medewerkers SET afd = NULL WHERE mnr < 7369 OR mnr > 7934;
UPDATE afdelingen SET hoofd = NULL WHERE anr > 40;
DELETE FROM afdelingen WHERE anr > 40;
DELETE FROM medewerkers WHERE mnr < 7369 OR mnr > 7934;
ALTER TABLE medewerkers DROP CONSTRAINT IF EXISTS m_geslacht_chk;
ALTER TABLE medewerkers DROP COLUMN IF EXISTS geslacht;