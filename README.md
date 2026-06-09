# EclipseLink UUID/PostgreSQL Reproduction

This repository contains a focused Spring Boot integration test that reproduces the EclipseLink PostgreSQL UUID-null binding issue tracked as [eclipse-ee4j/eclipselink#2717](https://github.com/eclipse-ee4j/eclipselink/issues/2717).

The test fixture uses:

- EclipseLink `4.0.9` as the Jakarta Persistence provider.
- Spring Boot Test and Spring Data JPA.
- A real PostgreSQL database provisioned by Testcontainers.
- A `Gizmo` entity whose `UUID` field is intentionally left unset before `saveAndFlush(...)`.

The expected defect shape is PostgreSQL rejecting an insert into the `gizmo.gizmo_id` `uuid` column because EclipseLink binds the unset Java `UUID` value as a character-varying expression instead of a PostgreSQL UUID-compatible null.

## Running the reproduction

Run the focused test with Maven:

```bash
mvn test -Dtest=EclipseLinkUuidNullPostgresTest
```

The test starts its own PostgreSQL container, creates the required `gizmo` table, saves a new `Gizmo` without assigning `gizmoId`, and asserts that the exception chain contains PostgreSQL's UUID binding error fragments.
