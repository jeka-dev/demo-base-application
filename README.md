# Projectless Application

This repository demonstrates JeKa's ability to build, dockerize, and publish applications with zero build configurations or project structure.

The application is a simple command line tool displaying OpenJDK distributions managed by [foojay-disco](https://foojay.io/today/disco-api-helping-you-to-find-any-openjdk-distribution/), by requesting the [foojay-disco REST API](https://api.foojay.io/swagger-ui/).

Here, we don't create a project structure but rely solely on the JeKa scripting engine. Sources and tests are all located in the *jeka-src* directory, which allows declaring dependencies directly in Java code.

By convention, the *[_dev](jeka-src/_dev)* package (and its sub-packages) contains code and dependencies that are excluded from the production JAR.

This structure is suitable for small to medium-sized applications but can be trivially moved to a project structure later on.

**Note:**  
This application can be easily moved into a plain project structure without requiring build configuration (only dependencies need to be externalized in a `dependency.txt` file).

## JeKa commands

Execute tests
```shell
jeka test
```

Create jar 
```shell
jeka pack
```

Run jar
```shell
jeka runJar programArgs="temurin" jvmOptions=""
```

Run jar
This will directly run the built application, without checking if sources have changed since last run
```shell
jeka -p
```

### Docker

Create image
```shell
jeka docker: build
```
Show info about image
```shell
jeka docker: info
```
