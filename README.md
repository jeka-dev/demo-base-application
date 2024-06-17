# Build-less application

This repository demonstrates the ability of JeKa to build, dockerize, and publish applications with zero build configurations.

The application is a simple command line displaying managed OpenJDK distributions managed by [foojay-disco](https://foojay.io/today/disco-api-helping-you-to-find-any-openjdk-distribution/).

The code and dependencies that is excluded from the production jar is located under *[_dev](jeka-src/_dev)* package, 

Dependencies are declared inside Java code using [specific annotations](jeka-src/app/Main.java).



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