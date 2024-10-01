-- ------------------------------------------------------------------------
-- Data & Persistency
-- Opdracht S6: Views
--
-- (c) 2020 Hogeschool Utrecht
-- Tijmen Muller (tijmen.muller@hu.nl)
-- André Donk (andre.donk@hu.nl)
-- ------------------------------------------------------------------------


-- S6.1.
--
-- 1. Maak een view met de naam "deelnemers" waarmee je de volgende gegevens uit de tabellen inschrijvingen en uitvoering combineert:
--    inschrijvingen.cursist, inschrijvingen.cursus, inschrijvingen.begindatum, uitvoeringen.docent, uitvoeringen.locatie
-- 2. Gebruik de view in een query waarbij je de "deelnemers" view combineert met de "personeels" view (behandeld in de les):
--     CREATE OR REPLACE VIEW personeel AS
-- 	     SELECT mnr, voorl, naam as medewerker, afd, functie
--       FROM medewerkers;
-- 3. Is de view "deelnemers" updatable ? Waarom ?

-- 1)
create or replace view deelnemers as
select
    i.cursist,
    i.cursus,
    i.begindatum,
    u.docent,
    u.locatie
from
    inschrijvingen i
        join
    uitvoeringen u on i.cursus = u.cursus
        and i.begindatum = u.begindatum

-- 2)
create or replace view personeel as
select mnr, voorl, naam as medewerker, afd, functie
from medewerkers


select
    d.cursist,
    p1.voorl as cursist_voorl,
    p1.medewerker as cursist_naam,
    d.cursus,
    d.begindatum,
    p2.voorl as docent_voorl,
    p2.medewerker as docent_naam,
    d.locatie
from
    deelnemers d
        join
    personeel p1 on d.cursist = p1.mnr
        join
    personeel p2 on d.docent = p2.mnr

-- 3)
-- Nee, de view "deelnemers" is niet updatable. Want de view is gebaseerd op een join tussen twee tabellen, inschrijvingen en uitvoeringne, waardoor de
-- de updatebaarheid wordt beperkt.


-- S6.2.
--
-- 1. Maak een view met de naam "dagcursussen". Deze view dient de gegevens op te halen:
--      code, omschrijving en type uit de tabel curssussen met als voorwaarde dat de lengte = 1. Toon aan dat de view werkt.
-- 2. Maak een tweede view met de naam "daguitvoeringen".
--    Deze view dient de uitvoeringsgegevens op te halen voor de "dagcurssussen" (gebruik ook de view "dagcursussen"). Toon aan dat de view werkt
-- 3. Verwijder de views en laat zien wat de verschillen zijn bij DROP view <viewnaam> CASCADE en bij DROP view <viewnaam> RESTRICT

-- 1)
create or replace view dagcursussen as
select
    code,
    omschrijving,
    type
from
    cursussen c
where
    lengte = 1;

-- test
select * from dagcursussen;

-- 2)
create or replace view daguitvoeringen as
select
    u.cursus,
    u.begindatum,
    u.locatie,
    u.docent
from
    uitvoeringen u
        join
    dagcursussen d on u.cursus = d.code

-- test
select * from daguitvoeringen

-- 3)
drop view dagcursussen cascade

-- Verwijdert de view samen met all afhankelijke objecten.

drop view dagcurssen restrict

-- Voorkomt het verwijderen van de view als er afhankelijke object zijn. Je krijgt ook een foutmelding als je het probeert.




