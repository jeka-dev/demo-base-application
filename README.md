# Projectless Application

This repository shows how JeKa builds, tests, runs and dockerizes an application without a project structure
and with almost no build configuration, using the `base` KBean.

The application is a command-line tool that lists the OpenJDK distributions known by
[foojay-disco](https://foojay.io/today/disco-api-helping-you-to-find-any-openjdk-distribution/), by calling the
[foojay-disco REST API](https://api.foojay.io/swagger-ui/).

## Prerequisites

- [JeKa installed](https://jeka-dev.github.io/jeka/installation/). JeKa downloads the JDK it needs.
- Network access: the application and its tests call the foojay-disco API.
- Docker, for the Docker image.

## Run the app directly from this repository

- Print help: `jeka -r https://github.com/jeka-dev/demo-base-application -p --help`
- Print the available distributions: `jeka -r https://github.com/jeka-dev/demo-base-application -p --distrib`
- Print the Temurin versions: `jeka -r https://github.com/jeka-dev/demo-base-application -p temurin`

## Build commands

Run the tests:
```shell
jeka test
```

Create the jar, in `jeka-output/`:
```shell
jeka pack
```

Run the jar:
```shell
jeka runJar programArgs="temurin" jvmOptions=""
```

Run the application, building the jar if it is missing. It does not check whether the sources changed since the last run:
```shell
jeka -p temurin
```

### Docker

Create the image:
```shell
jeka docker: build
```

Show info about the image:
```shell
jeka docker: info
```

Run it:
```shell
docker run --rm demo-base-application:latest temurin
```

## How it works

- [jeka.properties](jeka.properties): sets the JeKa and Java versions, and makes `base` the default KBean, so
  `jeka test` means `jeka base: test`.
- [jeka-src](jeka-src): holds sources and tests. Dependencies are declared in the code with `@JkDep`,
  as in [Main.java](jeka-src/app/Main.java).
- By convention, the [_dev](jeka-src/_dev) package (and its sub-packages) holds code and dependencies that are
  excluded from the production jar, such as tests.

This layout suits small or medium applications. You can move it to a project structure later, without build
configuration: only the dependencies need to move to a `jeka.project.deps` file.

## Links

- [Build a codebase](https://jeka-dev.github.io/jeka/tutorials/build-base/)
- [base KBean](https://jeka-dev.github.io/jeka/reference/kbeans-base/)
- [docker KBean](https://jeka-dev.github.io/jeka/reference/kbeans-docker/)
- [Source-runnable applications](https://jeka-dev.github.io/jeka/tutorials/source-runnable-apps/)
