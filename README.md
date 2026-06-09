# eclipselink-uuid-test

This repository contains a focused Spring Boot integration test that reproduces an EclipseLink bug involving PostgreSQL `uuid` columns and unset `java.util.UUID` entity fields.

The project was created to demonstrate the behavior reported in [eclipse-ee4j/eclipselink#2717](https://github.com/eclipse-ee4j/eclipselink/issues/2717). In the reproduction, EclipseLink `4.0.9` binds an unset `UUID` field as a `character varying` value when inserting into a PostgreSQL `uuid` column, which PostgreSQL rejects.

## What the test does

The test fixture:

- starts a real PostgreSQL database with Testcontainers;
- creates a minimal `gizmo` table with an integer identity primary key and a nullable PostgreSQL `uuid` column;
- configures Spring Data JPA to use EclipseLink instead of Hibernate;
- saves a new `Gizmo` entity without setting its `gizmoId` field; and
- flushes the insert so the PostgreSQL type error is raised during the test.

The expected failure includes an error indicating that column `gizmo_id` is of type `uuid` but the expression is of type `character varying`.

## Running the reproduction

Run the focused Maven test:

```bash
mvn test -Dtest=EclipseLinkUuidNullPostgresTest
```

The test requires Docker or another Testcontainers-compatible container runtime so it can start PostgreSQL automatically. No local PostgreSQL service or pre-existing schema is required.
