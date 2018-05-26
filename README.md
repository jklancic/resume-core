# vitaCore

[![CircleCI](https://circleci.com/gh/jklancic/resume-core/tree/master.svg?style=svg)](https://circleci.com/gh/jklancic/resume-core/tree/master)

Java API back-end server supporting multi-tenant resume functionality.

## Getting started

Running the server is as simple as running a gradle task.

### Prerequisites

Running the server requires an installed instance of at least Java 8 running locally.

### Running

To run the server, go to the project directory in the console. Once inside the directory, please run the following command:

`./gradlew install`

## Running the tests

Similar to running the application, we take advantage of gradle command:

`./gradlew test`

## Built With

* [Dropwizard 1.3.2](https://www.dropwizard.io/1.3.2/docs/)
* [java-jwt 3.3.0](https://github.com/auth0/java-jwt)
* [dagger 2.16](https://github.com/google/dagger)

## Authors

* Jernej Klancic
