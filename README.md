# AMRIT - BeneficiaryID-Generation-API Service
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)  [![DeepWiki](https://img.shields.io/badge/DeepWiki-PSMRI%2FBeneficiaryID--Generation--API-blue)](https://deepwiki.com/PSMRI/BeneficiaryID-Generation-API)


This service is used to generate unique beneficiary registration Id for new beneficiaries.

## Building From Source
This microservice is built on Java, Spring boot framework and MySQL DB.

### Prerequisites 
Find the detailed list of software dependencies [here] (https://piramal-swasthya.gitbook.io/amrit/developer-guide/development-environment-setup/software-dependencies) .
* JDK 17 (LTS)
* Maven
* Wildfly (or any compatible app server)
* Redis
* MySQL Database

## Commit Message Validation

This repository enforces commit message format using a Git `commit-msg` hook and GitHub workflow.

### Commit Format

All commits must follow this format:

type(scope): message

### Enable Git Hooks

After cloning the repository, run the following command once to activate the hooks:

`git config core.hooksPath .git-hooks`

This enables the `commit-msg` hook located in the `.git-hooks` directory.

### Validation

Commit messages are validated in two ways:

1. Local Git hook using Commitlint via `npx`
2. GitHub workflow that checks commit messages during pull requests

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

## Filing Issues

If you encounter any issues, bugs, or have feature requests, please file them in the [main AMRIT repository](https://github.com/PSMRI/AMRIT/issues). Centralizing all feedback helps us streamline improvements and address concerns efficiently.  

## Join Our Community

We’d love to have you join our community discussions and get real-time support!  
Join our [Discord server](https://discord.gg/FVQWsf5ENS) to connect with contributors, ask questions, and stay updated.  
