# ECM2414 - Paired Programming Software Development CA

# Maven Info:

## Maven Version:

Maven Version: 3.9.1.16

## Info

groupId: SWD-CA-SRC
modelVersion: 4.0.0
maven.compiler.release: 25

## Dependencies

### JUnit

groupId: org.junit.jupiter
artifactId: junit-jupiter
JUnit Version: 5.14.4
scope: test

## Build

testSourceDirectory: ${project.basedir}/src/tests/java

# Project Dependencies

JUnit
Maven

### Cards.jar Packages

java.io.\*
java.nio.file.Files
java.util.ArrayList
java.util.List
java.util.Scanner
java.util.concurrent.atomic.AtomicInteger

### Testing Dependencies

Junit
Maven
cardgame
org.junit.jupiter.api.Assertions.assertEquals
java.util.Scanner
java.io.\*

## Intended way to Compile & Test:

- I installed a fresh install of both Maven and Java through HomeBrew.
- Versions 3.10.0 and 27.0.1.0.3 respectively, they were the most up-to-date.

- Open your terminal of choice.
- Set the Current Directory to `.../Software Development Paired Programming`.

- run `mvn clean` - it should display `BUILD SUCCESS`, it should display similarly for other commands.
- run `mvn validate`, again it should pass.
- run `mvn compile`, should pass.
- run `mvn test`, it should pass with flying colours.

That's the process to run the tests. If you have any issues I recommend to try our alternative method as described below or find support articles from Java, Maven and JUnit.

Run tests: mvn test
Run game: java -jar cards.jar

# A note:

This project was developed on IntelliJ IDEA and has utilised the following plugins to enable JUnit and Maven functionality, this project should run outside of IntelliJ of course. However, in the case it doesn't we reccomend attemting to use IntelliJ to open and run the Project.

IntelliJ IDEA version: 2026.2.3
Maven Plugin: 262.10968.63
JUnit Plugin: 262.10968.63

In addition this project was developed entirely on MacOS, if you have issues running the project on a different chip architecture and or operating system, it will likely be a technical issue with the OS, chip architecture, the dependencies, Java, etc. It should run flawlessly on any machine.

## Alternative way to run testing suite: (Method used within our development)

- Open IntelliJ IDEA
- Ensure the correct JDK, Maven and JUnit versions are installed, along with IntelliJ IDEA's Plugins.
- Open our project
- On the side menu there is an 'm' logo, it's maven
- Open the maven menu.
- click the '>' symbols to open the `Paired Programming Software Development CA [CardGame]`
- do the same for `Lifecycle`
- Now double click each of the following items in order to run our test suite
- Clean
- Validate
- Compile
- Test
- After you have run test you will see that all of our testing suite should have passed and there will be a breakdown within the terminal of how many tests were run, what passed, etc...

# Github repo

https://github.com/DRGN-3455/ECM2414---Paired-Programming-Software-Development-CA
