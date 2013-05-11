## The Maven Wrapper

> fork of [bdemers/maven-wrapper](https://github.com/bdemers/maven-wrapper)

The goodness of [gradlew](http://www.gradle.org/docs/current/userguide/gradle_wrapper.html) ported to be used with Maven.

For thus not familiar with gradlew, it's a wrapper allowing to build Gradle-based projects without the need to install Gradle beforehand.
All that is needed is to add number of files to the root of the project. After that anyone who gets the sources (presumably through version control system) can build it with something similar to `./gradlew build` (or `gradlew.bat build` on Windows).

The mvnw (Maven version of gradlew) consists of

    mvnw # for *nix
    mvnw.bat # for windows
    .maven/wrapper/
        maven-wrapper.jar
        maven-wrapper.properties # change value of "distributionUrl" whenever you want to switch to a new version of Maven

It can be easily integrated into existing project with (run inside project root directory):

    curl -Ls https://github.com/shyiko/mvnw/raw/master/bundle.tgz | tar xvz

or

    wget --content-disposition -O - https://github.com/shyiko/mvnw/raw/master/bundle.tgz | tar xvz

Another way would be manually to download and extract [bundle.tgz](https://github.com/shyiko/mvnw/raw/master/bundle.tgz).

Once mvnw is installed, use it just like you would use mvn, e.g. `./mvnw clean install`, `./mvnw package -DskipTests=true -s settings.xml`. It will automatically download and install Maven if needed.

