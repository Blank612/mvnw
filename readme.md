# mvnw

Like [gradlew](http://www.gradle.org/docs/current/userguide/gradle_wrapper.html) but for Maven (Java 6+).

## Adding `mvnw` to the project

```sh
# within project (root) directory:
curl -Ls https://github.com/shyiko/mvnw/raw/master/bundle.tgz | tar xvz
# or
wget --content-disposition -q -O - https://github.com/shyiko/mvnw/raw/master/bundle.tgz \
  | tar xvz
```

Another way would be to download and extract [bundle.tgz](https://github.com/shyiko/mvnw/raw/master/bundle.tgz).

You should see following files (all of which are meant to be committed to VCS):

    mvnw # shell script to be used on Linux/Mac OS X
    mvnw.bat # batch file for Windows
    .mvn/
      jvm.config # default jvm options* (omitted)
      maven.config # default command line options* (omitted)
      wrapper/
        maven-wrapper.jar
        maven-wrapper.properties # contains mvnw configuration options, such as "distributionUrl"

\* see https://maven.apache.org/docs/3.3.1/release-notes.html

## Usage

Use it just like you would use mvn, e.g. `./mvnw clean install`, `./mvnw package -DskipTests=true -s settings.xml`.
It will automatically download and install Maven if needed.

