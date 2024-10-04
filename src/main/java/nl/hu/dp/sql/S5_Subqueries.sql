-- ------------------------------------------------------------------------
-- Data & Persistency
-- Opdracht S5: Subqueries
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
-- NB: Gebruik in elke vraag van deze opdracht een subquery.
--
-- Codeer je uitwerking onder de regel 'DROP VIEW ...' (bij een SELECT)
-- of boven de regel 'ON CONFLICT DO NOTHING;' (bij een INSERT)
-- Je kunt deze eigen query selecteren en los uitvoeren, en wijzigen tot
-- je tevreden bent.

-- Vervolgens kun je je uitwerkingen testen door de testregels
-- (met [TEST] erachter) te activeren (haal hiervoor de commentaartekens
-- weg) en vervolgens het hele bestand uit te voeren. Hiervoor moet je de
-- testsuite in de database hebben geladen (bedrijf_postgresql_test.sql).
-- NB: niet alle opdrachten hebben testregels.
--
-- Lever je werk pas in op Canvas als alle tests slagen.
-- ------------------------------------------------------------------------


-- S5.1.
-- Welke medewerkers hebben zowel de Java als de XML cursus
-- gevolgd? Geef hun personeelsnummers.
-- DROP VIEW IF EXISTS s5_1; CREATE OR REPLACE VIEW s5_1 AS                                                     -- [TEST]

select
    i.cursist as medewerker_nummer,
    i.cursus as cursus_code
from
    inschrijvingen i
        join
    medewerkers m on i.cursist = m.mnr
where
    i.cursus like 'JAV' or i.cursus like 'XML'
group by
    i.cursist, i.cursus

-- S5.2.
-- Geef de nummers van alle medewerkers die niet aan de afdeling 'OPLEIDINGEN'
-- zijn verbonden.
-- DROP VIEW IF EXISTS s5_2; CREATE OR REPLACE VIEW s5_2 AS                                                     -- [TEST]

select
    m.afd as afdeling,
    m.mnr as medewerker_nummer
from
    medewerkers m
        join
    afdelingen a on m.afd = a.anr
where
    m.afd not in (30)
group by
    m.afd, m.mnr

-- S5.3.
-- Geef de nummers van alle medewerkers die de Java-cursus niet hebben
-- gevolgd.
-- DROP VIEW IF EXISTS s5_3; CREATE OR REPLACE VIEW s5_3 AS                                                     -- [TEST]

select
    i.cursus as cursus,
    i.cursist as medewerkers_nummer
from
    inschrijvingen i
        join
    medewerkers m on i.cursist = m.mnr
where
    i.cursus not in ('JAV')
group by
    i.cursus, i.cursist

-- S5.4.
-- a. Welke medewerkers hebben ondergeschikten? Geef hun naam.
-- DROP VIEW IF EXISTS s5_4a; CREATE OR REPLACE VIEW s5_4a AS                                                   -- [TEST]

-- b. En welke medewerkers hebben geen ondergeschikten? Geef wederom de naam.
-- DROP VIEW IF EXISTS s5_4b; CREATE OR REPLACE VIEW s5_4b AS                                                   -- [TEST]

-- a)
select
    naam as medewerker_naam
from
    medewerkers
where
    functie = 'MANAGER';

-- b)
select
    naam as medewerker_naam
from
    medewerkers
where
    functie <> 'MANAGER';


-- S5.5.
-- Geef cursuscode en begindatum van alle uitvoeringen van programmeercursussen
-- ('BLD') in 2020.
-- DROP VIEW IF EXISTS s5_5; CREATE OR REPLACE VIEW s5_5 AS                                                     -- [TEST]

select
    u.cursus as cursus_code,
    u.begindatum as begindatum
from
    uitvoeringen u
        join
    cursussen c on u.cursus = c.code
where
    c.type = 'BLD' and extract(year from u.begindatum) = 2020
group by
    u.cursus, u.begindatum


-- S5.6.
-- Geef van alle cursusuitvoeringen: de cursuscode, de begindatum en het
-- aantal inschrijvingen (`aantal_inschrijvingen`). Sorteer op begindatum.
-- DROP VIEW IF EXISTS s5_6; CREATE OR REPLACE VIEW s5_6 AS                                                     -- [TEST]

select
    u.cursus as cursus_code,
    u.begindatum as begindatum,
    count (*) as aantal_inschrijvingen
from
    uitvoeringen u
        join
    inschrijvingen i on u.cursus = i.cursus
group by
    u.cursus, u.begindatum
order by
    u.begindatum

-- S5.7.
-- Geef voorletter(s) en achternaam van alle trainers die ooit tijdens een
-- algemene ('ALG') cursus hun eigen chef als cursist hebben gehad.
-- DROP VIEW IF EXISTS s5_7; CREATE OR REPLACE VIEW s5_7 AS                                                     -- [TEST]

select
    m.voorl as voorletter_s,
    m.naam as achternaam
from
    medewerkers m
        join
    uitvoeringen u on m.mnr = u.docent
        join
    cursussen c on u.cursus = c.code
        join
    inschrijvingen i on u.cursus = i.cursus
        join
    medewerkers chef on  i.cursist = chef.mnr
where
    m.functie = 'TRAINER'
  and c.type = 'ALG'
  and chef.mnr = m.mnr

-- S5.8.
-- Geef de naam van de medewerkers die nog nooit een cursus hebben gegeven.
-- DROP VIEW IF EXISTS s5_8; CREATE OR REPLACE VIEW s5_8 AS                                                     -- [TEST]

select
    m.naam as medewerker_naam
from
    medewerkers m
left join
    uitvoeringen u on m.mnr = u.docent
where
    u.docent is null;



-- -------------------------[ HU TESTRAAMWERK ]--------------------------------
-- Met onderstaande query kun je je code testen. Zie bovenaan dit bestand
-- voor uitleg.

SELECT * FROM test_select('S5.1') AS resultaat
UNION
SELECT * FROM test_select('S5.2') AS resultaat
UNION
SELECT * FROM test_select('S5.3') AS resultaat
UNION
SELECT * FROM test_select('S5.4a') AS resultaat
UNION
SELECT * FROM test_select('S5.4b') AS resultaat
UNION
SELECT * FROM test_select('S5.5') AS resultaat
UNION
SELECT * FROM test_select('S5.6') AS resultaat
UNION
SELECT * FROM test_select('S5.7') AS resultaat
UNION
SELECT * FROM test_select('S5.8') AS resultaat
ORDER BY resultaat;