# User Service

A microservice that handles user basic information such as follows:

- Basic User Data
- Log In Credentials
- Change Password

## Environment Variables

To run this project, you will need to add the following environment variables to your .env file

#### Application environment variables for production (User Service Container)

- `SPRING_DATASOURCE_URL`

  **Description:** URL of the datasource container for production

  **Example:** `jdbc:mariadb://db:3306/user_service`

- `SPRING_DATASOURCE_USERNAME`

  **Description:** Username to be used to get access on the database. _(the default mariadb root username is `root`)_

  **Example:** `root`

- `SPRING_DATASOURCE_PASSWORD`

  **Description:** Password to be used to get access on the database

  **Example:** `Password123`

- `SPRING_PORT`

  **Description:** Port that the User Service will run inside the container

  **Example:** `8080`

#### Application environment variables for development

- `SPRING_DATASOURCE_URL_DEV`

  **Description:** URL of the datasource container for development purposes

  **Example:** `jdbc:mariadb://localhost:3306/user_service`

- `SPRING_DATASOURCE_USERNAME_DEV`

  **Description:** Username of the database on development environment

  **Example:** `root_dev`

- `SPRING_DATASOURCE_PASSWORD_DEV`

  **Description:** Password of the database on development environment

  **Example:** `password_for_dev`

- `SPRING_PORT_DEV`

  **Description:** Port of User Service in your development environment

  **Example:** `8080`

#### MariaDB Container environment variables

- `MARIADB_ROOT_PASSWORD`

  **Description:** Root password for mariadb container

  **Example:** `root_dev`

- `MARIADB_PORT=3306`

  **Description:** Port that mariadb will run inside the mariadb container

  **Example:** `3306`

#### Container environment variables

- `DATABASE_CONTAINER_PORT`

  **Description:** Port where the mariadb container will run

  **Example:** `3307`

- `SPRING_CONTAINER_PORT=8082`

  **Description:** Port where the User Service container will run

  **Example:** `8080`

## Run Locally

Clone the project

```bash
  git clone https://github.com/jc4balos/user-service.git
```

Go to the project directory

```bash
  cd my-project
```

Clean and compile via maven

```
  mvn clean compile
```

Start and configure your MariaDB development server.
See https://mariadb.com/kb/en/getting-installing-and-upgrading-mariadb/ for more info.

Run the Spring application

## Deployment

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

Stay tuned for futher updates!
