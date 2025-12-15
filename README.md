# Simple Game Character Registry

This project is a straightforward Java console application designed as a registration and management system for game characters. It demonstrates fundamental CRUD (Create, Read, Update, Delete) operations for entities using Hibernate as the JPA provider and PostgreSQL as the relational database.

It was developed to deepen the understanding of Object-Relational Mapping (ORM) concepts, database interaction, and transaction management within an interactive application context.

## Technologies Used

*   **Java 21:** The primary programming language for the application.
*   **Maven:** Build automation tool for managing project dependencies and building the application.
*   **JPA (Java Persistence API):** Specification for persisting Java objects into a relational database.
*   **Hibernate:** The concrete implementation of the JPA standard used for database interaction.
*   **PostgreSQL:** A powerful open-source relational database used for storing character data.
*   **SLF4J Simple:** A basic logging framework used to display Hibernate's generated SQL statements and application messages.

## Features

The application provides an interactive console menu with the following functionalities:

1.  **Create and Save New Character:** Input and persist a new game character's name, class, health, attack (ATK), and defense (DEF).
2.  **List All Characters:** Display all game characters currently stored in the database.
3.  **List a Specific Character:** Retrieve and display details of a single character by their unique ID.
4.  **Update a Specific Character:** Modify the name, class, health, ATK, or DEF of an existing character.
5.  **Delete a Specific Character:** Remove a character from the database by their ID, with a confirmation prompt.
6.  **Exit Application:** Gracefully shut down the application and release database resources.

## Prerequisites

Before running this project, ensure you have the following software installed:

*   **Java Development Kit (JDK) 21 or higher**
*   **Apache Maven** (usually bundled with IDEs or installable separately)
*   **PostgreSQL Database Server:** A running PostgreSQL instance is required.
    *   Ensure you have a database (e.g., `sandboxtest`) and a user with appropriate permissions set up.

## Setup and Execution

1.  **Clone the Repository:**
    ```bash
    git clone https://github.com/boeani05/SimpleGameCharacterRegistry.git
    cd SimpleGameCharacterRegistry
    ```
    
2.  **Configure `hibernate.cfg.xml`:**
    Navigate to `src/main/resources/hibernate.cfg.xml` and update the database connection details:
    ```xml
    <!-- ... -->
    <property name="hibernate.connection.url">jdbc:postgresql://hostname:port/nameOfDatabank</property>
    <property name="hibernate.connection.username">username</property>
    <property name="hibernate.connection.password">password</property>
    <!-- ... -->
    <property name="hibernate.hbm2ddl.auto">create-drop</property> <!-- Recommended for initial setup/testing -->
    ```
    *   **Important:** Adjust the `url`, `username`, and `password` to match your PostgreSQL setup.
    *   `hibernate.hbm2ddl.auto` set to `create-drop` will automatically create the necessary table (`game_character`) on application start and drop it on shutdown. You can change this to `update` to preserve data across runs after the initial setup.

3.  **Build the Project:**
    Open your terminal in the project's root directory and run:
    ```bash
    mvn clean install
    ```
    This command compiles the Java code, runs tests, and packages the application.

4.  **Run the Application:**
    After a successful build, you can run the application from the target directory:
    ```bash
    java -jar target/SimpleGameCharacterRegistry-1.0-SNAPSHOT.jar
    ```
    Alternatively, you can run it directly from your IDE (e.g., IntelliJ IDEA, Eclipse) by executing the `main` method in `org.bernhard.user.GameApp`.
