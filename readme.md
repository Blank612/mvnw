# mvnw

Like [gradlew](http://www.gradle.org/docs/current/userguide/gradle_wrapper.html) but for Maven.

This fork:
- is JDK 6+ compatible  
(provided Maven is [<= 3.2.5](https://maven.apache.org/docs/history.html) (by default `.mvn/wrapper/maven-wrapper.properties` points to 3.3.9));
- used by [mysql-binlog-connector-java](https://github.com/shyiko/mysql-binlog-connector-java), [ktlint](https://github.com/shyiko/ktlint), ...
 (which means you can count on it being maintained);
- supports [default jvm/command line options](https://maven.apache.org/docs/3.3.1/release-notes.html) (Maven 3.3.1+)

## Installation

> (within project (root) directory)

```sh
curl -sL https://github.com/shyiko/mvnw/releases/download/0.1.0/mvnw.tar.gz | tar xvz

# Maven version can be changed with
(MAVEN_VERSION=3.2.5 && 
  sed -iEe "s/[0-9]\+[.][0-9]\+[.][0-9]\+/${MAVEN_VERSION}/g" .mvn/wrapper/maven-wrapper.properties)
```

> If you don't have curl installed - replace `curl -sL` with `wget -qO-`.

You'll get the following files (all of which are meant to be committed to VCS):

    mvnw # shell script to be used on Linux/Mac OS X
    mvnw.bat # batch file for Windows
    .mvn/
    .mvn/jvm.config # default jvm options (omitted by default) (example: -Xmx512m)
    .mvn/maven.config # default command line options (omitted by default) (example: -s settings.xml)
    .mvn/wrapper/maven-wrapper.jar
    .mvn/wrapper/maven-wrapper.properties # contains mvnw config options, such as "distributionUrl"

## Usage

Instead of `mvn ...` use `./mvnw ...` (e.g. `./mvnw clean install`).
It will automatically download and install Maven (if needed) (the exact version is specified in `.mvn/wrapper/maven-wrapper.properties`).

## License

[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0)
