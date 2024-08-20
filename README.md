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

  **Example:** `ACTIVE_PROFILE=prod`

#### Application environment variables for production (User Service Container)

- `PROD_SPRING_DATASOURCE_URL`

  **Description:** URL of the datasource container for production

  **Example:** `PROD_SPRING_DATASOURCE_URL=jdbc:mariadb://db:3306/user_service`

- `PROD_SPRING_DATASOURCE_USERNAME`

  **Description:** Username to be used to get access on the database. _(the default mariadb root username is `root`)_

  **Example:** `PROD_SPRING_DATASOURCE_USERNAME=root`

- `PROD_SPRING_DATASOURCE_PASSWORD`

  **Description:** Password to be used to get access on the database

  **Example:** `PROD_SPRING_DATASOURCE_PASSWORD=Password123`

- `PROD_SPRING_PORT`

  **Description:** Port that the User Service will run inside the container

  **Example:** `PROD_SPRING_PORT=8080`

#### Application environment variables for development

- `DEV_SPRING_DATASOURCE_URL`

  **Description:** URL of the datasource container for development purposes

  **Example:** `DEV_SPRING_DATASOURCE_URL=jdbc:mariadb://localhost:3306/user_service`

- `DEV_SPRING_DATASOURCE_USERNAME`

  **Description:** Username of the database on development environment

  **Example:** `DEV_SPRING_DATASOURCE_USERNAME=root_dev`

- `DEV_SPRING_DATASOURCE_PASSWORD`

  **Description:** Password of the database on development environment

  **Example:** `DEV_SPRING_DATASOURCE_PASSWORD=password_for_dev`

- `DEV_SPRING_PORT`

  **Description:** Port of User Service in your development environment

  **Example:** `DEV_SPRING_PORT=8080`

#### MariaDB Container environment variables

- `MARIADB_ROOT_PASSWORD`

  **Description:** Root password for mariadb container

  **Example:** `MARIADB_ROOT_PASSWORD=root_dev`

- `MARIADB_PORT`

  **Description:** Port that mariadb will run inside the mariadb container

  **Example:** `MARIADB_PORT=3306`

#### Container environment variables

- `DATABASE_CONTAINER_PORT`

  **Description:** Port where the mariadb container will run

  **Example:** `DATABASE_CONTAINER_PORT=3307`

- `SPRING_CONTAINER_PORT`

  **Description:** Port where the User Service container will run

  **Example:** `SPRING_CONTAINER_PORT=8080`

#### Threading and DB Connection Configuration
- `THREAD_CORE_POOL_SIZE`

  **Description:** Number of threads to keep in a pool when idling.

  **Example:** `THREAD_CORE_POOL_SIZE=4`

- `THREAD_MAX_POOL_SIZE`

  **Description:** Maximum number of threads that a pool can handle.

  **Example:** `THREAD_MAX_POOL_SIZE=50`

- `THREAD_QUEUE_CAPACITY`

  **Description:** Number of task to be queued to execute if there are no more threads available.These will depend on the.

  **Example:** `THREAD_QUEUE_CAPACITY=600`

- `THREAD_KEEP_ALIVE_MS`

  **Description:** The amount of time in milliseconds when idle threads will be terminated.

  **Example:** `THREAD_KEEP_ALIVE_MS=60000`

- `DB_MAX_POOL_SIZE`

  **Description:** Sets the maximum number of connections that the pool can accommodate.

  **Example:** `DB_MAX_POOL_SIZE=30`

- `DB_MIN_IDLE_CONNECTIONS`

  **Description:** Defines the minimum number of idle connections that HikariCP attempts to maintain in the pool.

  **Example:** `DB_MIN_IDLE_CONNECTIONS=10`

---

- `DB_CONNECTION_LIFETIME_MS`

  **Description:** Specifies the maximum lifetime of a connection in the pool, in milliseconds.

  **Example:** `DB_CONNECTION_LIFETIME_MS=3600000`

---

- `DB_CONNECTION_TIMEOUT_MS`

  **Description:** Determines the maximum amount of time (in milliseconds) that HikariCP will wait for a connection from the pool before timing out.

  **Example:** `DB_CONNECTION_TIMEOUT_MS=30000`


## Run Locally (Development)

Clone the project

```bash
  git clone https://github.com/jc4balos/user-service.git
```

Go to the project directory

```bash
  cd my-project
```

Set `ACTIVE_PROFILE` to this:

```
ACTIVE_PROFILE=dev

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

1. Set `ACTIVE_PROFILE` into this:

```
ACTIVE_PROFILE=prod

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
