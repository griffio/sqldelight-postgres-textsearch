# SqlDelight 2.1.x Postgresql Text Search support 

https://github.com/cashapp/sqldelight

Snapshot version: 2.1.0-SNAPSHOT

Support `TSVECTOR` type, `GIST` and `GIN` operations

The pg_trgm module provides GiST and GIN index operator classes that allow you to create an index over a text column for the purpose of very fast similarity searches

```sql
CREATE EXTENSION pg_trgm;
```

String is the input and output type for the table API for `TSVECTOR` columns

Example supported https://www.postgresql.org/docs/current/textsearch-tables.html

```sql
CREATE TABLE PgWeb (
  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  title TEXT,
  body TEXT,
  last_mod_date TIMESTAMPTZ
);
```

Add `TSVECTOR` column: to keep this column automatically up to date with its source data, use a stored generated column.
This example is a concatenation of title and body, using coalesce to ensure that one field will still be indexed when the other is NULL:

```sql
ALTER TABLE pgweb
ADD COLUMN textsearchable_index_col TSVECTOR
GENERATED ALWAYS AS (to_tsvector('english', coalesce(title, '') || ' ' || coalesce(body, ''))) STORED;
```

## Data 

```
Title: Neutrinos in the Sun
Body: Neutrinos are subatomic particles produced in the core of the sun during nuclear fusion reactions.
      Billions of neutrinos pass through the sun and earth every second.
      Detecting solar neutrinos has helped reveal processes inside the sun. 
Search: 'billion':20 'core':12 'detect':31 'earth':28 'everi':29 'fusion':18 'help':35 'insid':38 'neutrino':1,5,22,33 
        'nuclear':17 'particl':8 'pass':23 'process':37 'produc':9 'reaction':19 'reveal':36 'second':30 'solar':32 
        'subatom':7 'sun':4,15,26,40
```

## Functions Supported 

Use custom dialect for additional functions https://github.com/griffio/sqldelight-custom-dialect

```
 to_tsquery Text
 to_tsvector TsVector
 ts_rank REAL or TEXT (can only return one type)
 websearch_to_tsquery Text
```

*Not supported*

* TSVector column type
  * MERGED https://github.com/cashapp/sqldelight/issues/5082
* Gist Index and tsvector operator
  * MERGED https://github.com/cashapp/sqldelight/issues/5059 
* RegEx operators
  * AWAITING MERGE https://github.com/cashapp/sqldelight/issues/5137

----

```shell
createdb textsearches
./gradlew build
./gradlew flywayMigrate
```

Flyway db migrations
https://documentation.red-gate.com/fd/gradle-task-184127407.html
