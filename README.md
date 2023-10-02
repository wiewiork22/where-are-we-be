# Employee Map Dashboard application

## Developers
1. a
2. b
3. c
4. d
5. f
6. e

## Tools

* IDE https://www.jetbrains.com/idea/
* Docker https://www.docker.com/products/docker-desktop/
* Git https://git-scm.com/downloads
* Postman https://www.postman.com/

#### Java

- Java 17 (Windows)
    - Java 17 JDK download: [https://adoptium.net/](https://adoptium.net/)
    - Environment variables needed (Windows)
        - “JAVA_HOME” - https://confluence.atlassian.com/doc/setting-the-java_home-variable-in-windows-8895.html
        - "Path" – add path to your JDK bin file (e.g. C:\Program Files\Eclipse
          Adoptium\jdk-17.0.4.101-hotspot\bin)
- Java 17 (MacOS)
    - JAVA 17 JDK download: console command: "brew install --cask temurin"
    - Environment variables applying
        - e.g. export JAVA_HOME=`/usr/libexec/java_home -v 17`

# Database

```bash
# launch and init empty db
# should have Docker Desktop running
docker-compose up -d
```

```bash
# recreate db / cleanup
# Mac
docker-compose down -v && docker-compose up -d
# Windows
docker-compose down -v; docker-compose up -d
```

## Starting service

Run directly via IDEA (Shift + F10) or

```bash
./gradlew bootRun
```
