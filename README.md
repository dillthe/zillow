# Zillow Clone

Zillow Clone is a web application that mimics the functionalities of the Zillow real estate platform. This application allows users to search for properties, view property details, and manage property listings.

## Features

- **Property Search**: Users can search for properties based on various criteria such as location, price range, and property type.
- **Property Details**: Detailed view of each property including photos, price, description, and other relevant information.
- **User Authentication**: Users can register, login, and manage their profiles.
- **Property Management**: Logged-in users can add, edit, and delete property listings.

## Tech Stack

### Backend
- **Java**: A robust and widely-used programming language suitable for building scalable backend services.
- **Spring Boot**: A Java-based framework that simplifies the development of RESTful web services and microservices.
- **MySQL**: A reliable and efficient relational database management system for storing application data.

## Installation and Running

### Prerequisites

- Java Development Kit (JDK) installed
- MySQL installed and running

## Usage

1. **Register and Login**: Create a new account or login with an existing account.
2. **Search Properties**: Use the search bar to find properties based on location, price, and other criteria.
3. **View Property Details**: Click on a property to view detailed information.
4. **Manage favorite Listings**: If logged in, use the property management features to add or delete property listings to favorites.

## Project Structure
  - `src/main/java/`: Java source files
    - `com/zillowc/`: Main package
      - `web/`: REST controllers
      - `service/`: Business logic
      - `repository/`: Database access, Entity definitions
  - `src/main/resources/`: Application configuration files

## Why These Technologies?

- **Java**: Chosen for its robustness and scalability, making it ideal for backend services.
- **Spring Boot**: Selected for its ability to simplify the development of RESTful web services and microservices, providing a robust and scalable backend.
- **MySQL**: Used for its reliability and efficiency in managing relational data, making it suitable for handling the application’s data storage needs.

## Challenges Faced

1. **Integrating External APIs**: Faced difficulties in fetching information using external APIs. Successfully implemented an API to fetch detailed listing information. The Listing file continued to have errors, but I am trying to resolve it now with the help of a mentor.
2. **Using AWS for Server and Database**: Set up a server and accessed the database using AWS. Implementing this on an EC2 server for the first time was challenging, but following tutorials and applying them gradually led to a good understanding.
3. **Implementing Login with Token Authentication**: Implemented user login and token-based authentication, ensuring secure access to the application.
