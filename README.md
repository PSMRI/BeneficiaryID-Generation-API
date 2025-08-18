# AMRIT - BeneficiaryID-Generation-API Service
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)  ![branch parameter](https://github.com/PSMRI/HWC-API/actions/workflows/sast-and-package.yml/badge.svg)

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

## Setting Up Commit Hooks

This project uses Git hooks to enforce consistent code quality and commit message standards. Even though this is a Java project, the hooks are powered by Node.js. Follow these steps to set up the hooks locally:

### Prerequisites
- Node.js (v14 or later)
- npm (comes with Node.js)

### Setup Steps

1. **Install Node.js and npm**
   - Download and install from [nodejs.org](https://nodejs.org/)
   - Verify installation with:
     ```
     node --version
     npm --version
     ```

2. **Install dependencies**
   - From the project root directory, run:
     ```
     npm ci
     ```
   - This will install all required dependencies including Husky and commitlint

3. **Verify hooks installation**
   - The hooks should be automatically installed by Husky
   - You can verify by checking if the `.husky` directory contains executable hooks

### Commit Message Convention

This project follows the [Conventional Commits](https://www.conventionalcommits.org/) specification:
- Format: `type(scope): subject`
- Example: `feat(bengen): add unique ID generation algorithm`

Types include:
- `feat`: A new feature
- `fix`: A bug fix
- `docs`: Documentation changes
- `style`: Code style changes (formatting, etc.)
- `refactor`: Code changes that neither fix bugs nor add features
- `perf`: Performance improvements
- `test`: Adding or fixing tests
- `build`: Changes to build process or tools
- `ci`: Changes to CI configuration
- `chore`: Other changes (e.g., maintenance tasks, dependencies)

Your commit messages will be automatically validated when you commit, ensuring project consistency.

### Using Commitizen

For an easier commit process, you can use Commitizen:
```
npm run commit
```
This will guide you through creating a properly formatted commit message.

## Usage
All features have been exposed as REST endpoints. Refer to the SWAGGER API specification for details.

## Filing Issues

If you encounter any issues, bugs, or have feature requests, please file them in the [main AMRIT repository](https://github.com/PSMRI/AMRIT/issues). Centralizing all feedback helps us streamline improvements and address concerns efficiently.  

## Join Our Community

We'd love to have you join our community discussions and get real-time support!  
Join our [Discord server](https://discord.gg/FVQWsf5ENS) to connect with contributors, ask questions, and stay updated.  
