# Build-less application

This repository demonstrates the ability of JeKa to build, dockerize, and publish applications with zero build configurations.

Here, we don't create project structure but rely only on the JeKa scripting engine : sources and tests are all located in 
the *jeka-src* directory, which allows to declare dependencies inside java code.

By convention, *[_dev](jeka-src/_dev)*  package (and its sub-package) contains the code and dependencies that are excluded from the production jar.

This application is a simple command line displaying managed OpenJDK distributions managed by [foojay-disco](https://foojay.io/today/disco-api-helping-you-to-find-any-openjdk-distribution/), 
by requesting the [foojay-disco rest api](https://api.foojay.io/swagger-ui/).

This structure is suitable for small-medium sized application but may be trivially moved to a project structure later on.

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
jeka runJar programArgs="" jvmOptions=""
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
Run image
```shell
jeka docker: run
```
