# AMRIT - BeneficiaryID-Generation-API Service
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)  ![branch parameter](https://github.com/PSMRI/HWC-API/actions/workflows/sast-and-package.yml/badge.svg)

This service is used to generate unique beneficiary registration Id for new beneficiaries.

## Building From Source
This microservice is built on Java, Spring boot framework and MySQL DB.

### Prerequisites 
* JDK 1.8
* Maven
* Wildfly (or any compatible app server)
* Redis
* MySQL Database

### Installation and setup

Please follow these steps:

1. Clone the repository to your local machine.
2. Install the dependencies and build the module:
    - Run the command `mvn clean install`.
3. You can copy `bengen_example.properties` to `bengen_local.properties` and edit the file accordingly. The file is under `src/main/environment` folder.
4. Run the development server:
    - Start the Redis server.
    - Run the command `mvn spring-boot:run -DENV_VAR=local`.
5. Open your browser and access `http://localhost:8080/swagger-ui.html#!/` to view the Swagger API documentation.


## Usage
All features have been exposed as REST endpoints. Refer to the SWAGGER API specification for details.
