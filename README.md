# User Service

A microservice that handles user basic information such as follows:

- Basic User Data
- Log In Credentials
- Change Password

## Environment Variables

To run this project, you will need to add the following environment variables to your .env file

- `ACTIVE_PROFILE`

  **Description:** profile being used by User Service.

  **Usage:** set the value to "dev" for development and "prod" for production ready version

  **Example:** `prod`

#### Application environment variables for production (User Service Container)

- `PROD_SPRING_DATASOURCE_URL`

  **Description:** URL of the datasource container for production

  **Example:** `jdbc:mariadb://db:3306/user_service`

- `PROD_SPRING_DATASOURCE_USERNAME`

  **Description:** Username to be used to get access on the database. _(the default mariadb root username is `root`)_

  **Example:** `root`

- `PROD_SPRING_DATASOURCE_PASSWORD`

  **Description:** Password to be used to get access on the database

  **Example:** `Password123`

- `PROD_SPRING_PORT`

  **Description:** Port that the User Service will run inside the container

  **Example:** `8080`

#### Application environment variables for development

- `DEV_SPRING_DATASOURCE_URL`

  **Description:** URL of the datasource container for development purposes

  **Example:** `jdbc:mariadb://localhost:3306/user_service`

- `DEV_SPRING_DATASOURCE_USERNAME`

  **Description:** Username of the database on development environment

  **Example:** `root_dev`

- `DEV_SPRING_DATASOURCE_PASSWORD`

  **Description:** Password of the database on development environment

  **Example:** `password_for_dev`

- `DEV_SPRING_PORT`

  **Description:** Port of User Service in your development environment

  **Example:** `8080`

#### MariaDB Container environment variables

- `MARIADB_ROOT_PASSWORD`

  **Description:** Root password for mariadb container

  **Example:** `root_dev`

- `MARIADB_PORT`

  **Description:** Port that mariadb will run inside the mariadb container

  **Example:** `3306`

#### Container environment variables

- `DATABASE_CONTAINER_PORT`

  **Description:** Port where the mariadb container will run

  **Example:** `3307`

- `SPRING_CONTAINER_PORT`

  **Description:** Port where the User Service container will run

  **Example:** `8080`

## Run Locally (Development)

Clone the project

```bash
  git clone https://github.com/jc4balos/user-service.git
```

Go to the project directory

```bash
  cd my-project
```

Set `src/main/resources/application.properties` to this:

```
spring.profiles.active=dev

```

Clean and compile via maven

```
  mvn clean compile
```

Start and configure your MariaDB development server.
See https://mariadb.com/kb/en/getting-installing-and-upgrading-mariadb/ for more info.

Run the Spring application

## Deployment along with mariadb container

Follow this steps to deploy the project on your server

1. Set application.properties into this:

```
spring.profiles.active=prod

```

2. Run this on project directory

```
mvn clean package -Dmaven.test.skip=true

```

3. Run this to create and deploy containers

```
sudo docker compose -f docker-compose.yaml up --build

```

## Deployment with User Service container only

Follow this steps to deploy the project on your server

1. Set application.properties into this:

```
spring.profiles.active=prod

```

2. Run this on project directory

```
mvn clean package -Dmaven.test.skip=true

```

3. Run this to create and deploy containers

```
sudo docker compose -f docker-compose-service-only.yaml up --build

```

# API Endpoints

Go to https://voltesiv.postman.co/workspace/954c2fe7-c7cc-4b73-9ea5-8fda4e20efd7 to see test API Endpoints.

Stay tuned for futher updates!
