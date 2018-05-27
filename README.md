# vitaCore

[![CircleCI](https://circleci.com/gh/jklancic/resume-core/tree/master.svg?style=svg)](https://circleci.com/gh/jklancic/resume-core/tree/master)

Java API back-end server supporting multi-tenant resume functionality.

## Getting started

Running the server is as simple as running a gradle task.

### Prerequisites

Running the server requires an installed instance of at least Java 8 running locally.

### Running

To run the server, first the jar file needs to be built. Please go to the directory, where the cloned repository is placed and run the following command:

`./gradlew shadowJar`

Once the jar is built, make sure there is also a configuration file for the server. The repository already has one placed in the root directory called [configuration.yml](./configuration.yml), which can be used for running local instances. There is also the option to create custom configuration file by copying and changing the following mandatory configurations:

```
# Database settings.
database:

  # the name of your JDBC driver
  driverClass: com.mysql.cj.jdbc.Driver
  # the username
  user: root
  # the password
  password: password

  # the JDBC URL
  url: jdbc:mysql://localhost:3306/resume

# Server settings.
server:
#  softNofileLimit: 1000
#  hardNofileLimit: 1000
  applicationConnectors:
    - type: http
      port: 8080
  adminConnectors:
    - type: http
      port: 8081

  rootPath: /api/

# JWT settings
jwtSecret: secret
```

Once the jar and configuration file are ready, the application server can be run with this command:

`java -jar ./vitaCore.jar server myConfig.yml`


## Running the tests

Similar to running the application, we take advantage of gradle command:

`./gradlew test`

## Built With

* [Dropwizard 1.3.2](https://www.dropwizard.io/1.3.2/docs/)
* [java-jwt 3.3.0](https://github.com/auth0/java-jwt)
* [dagger 2.16](https://github.com/google/dagger)

## Authors

* Jernej Klancic
