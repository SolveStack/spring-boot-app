# Building and running the application

## Docker

```bash
docker run --rm --name web -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven maven:3.3-jdk-8 mvn clean install
docker-compose up
```

## Locally

### Option 1
``mvn clean install``\
``mvn spring-boot:run``

### Option 2
``mvn package spring-boot:repackage``\
``java -Dspring.profiles.active=default -jar target/spring-boot-app-0.0.1-SNAPSHOT.war``

# Accessing the application
http://localhost:8081/springbootapp/employees
