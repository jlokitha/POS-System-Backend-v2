﻿# POS System Backend - v2

## Overview
A backend service for a Point of Sale (POS) system designed to manage customer, item, and order data efficiently. This service facilitates the creation, retrieval, updating, and deletion of records, providing robust capabilities for handling orders and monitoring application health.

## Technologies Used
The following technologies were used in the development of the Backend Server:

- **Java 17**
- **Spring Framework**
- **Hibernate ORM**
- **Spring Web MVC**
- **Spring Data JPA**
- **MySQL**
- **Maven**
- **Tomcat**

## API endpoints
- **Customer Management:**
    - Create new customer records.
    - Read and retrieve customer details.
    - Update existing customer information.
    - Delete customer records as needed.


- **Item Management:**
    - Add new items to the inventory.
    - Retrieve item details.
    - Update item information and pricing.
    - Remove items from the inventory.


- **Order Handling:**
    - Save new orders with customer and item details.
    - Retrieve order history and status.


- **Health Check:**
    - Verify the application's health status.

## Benefits

- **Efficient Management:** Streamlines customer and item management with CRUD operations, improving operational efficiency.
- **Robust Order Handling:** Facilitates accurate and reliable order processing, enhancing sales and inventory tracking.
- **Reliable Data Storage:** Uses Spring and JPA for stable data management and persistence.
- **Scalable Architecture:** Built on spring, Java, and Tomcat, allowing for easy scaling as business needs grow.

## Installation
To install and run the System, follow these steps:

1. Clone the repository:

   ```bash
   git clone https://github.com/jlokitha/POS-System-Backend-v2.git

2. Navigate to the project directory:

   ```bash
   cd POS-System-Backend-v2

3. Open the project in IntelliJ IDEA:

   ```bash
   idea .
## Setup and Configuration
- **Configure Tomcat:**
    - Go to `Run` > `Edit Configurations`.
    - Click the `+` button and select `Tomcat Server` > `Local`.
    - Set the Tomcat Home directory to your [Tomcat](https://tomcat.apache.org/download-90.cgi) installation path.
    - Apply and save the configuration.


- **Update Database Credentials:**
    - Open the `src/main/java/lk/ijse/possystembackendv2/config/WebAppRootConfig.java` file.
    - Update the MySQL username and password:

      ```bash
      @Bean
      public DataSource dataSource() {
        DriverManagerDataSource dmds = new DriverManagerDataSource();
        dmds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dmds.setUrl("jdbc:mysql://localhost:3306/pos_system?createDatabaseIfNotExist=true");
        dmds.setUsername("Your Username");
        dmds.setPassword("Your Password");
        return dmds;
      }

- **Run Tomcat:**
    - In IntelliJ IDEA, select the Tomcat configuration you created.
    - Click the `Run` button (green play icon) to start Tomcat.
    - The application should be deployed and accessible from your browser.


## API Documentation
You can find the API documentation for this project at [API Documentation](https://documenter.getpostman.com/view/35384124/2sAXxV4pTW).

## License
This project is licensed under the MIT License - see the [MIT License](LICENSE) file for details.

##
<div align="center">
<a href="https://github.com/jlokitha" target="_blank"><img src = "https://img.shields.io/badge/GitHub-000000?style=for-the-badge&logo=github&logoColor=white"></a>
<a href="https://git-scm.com/" target="_blank"><img src = "https://img.shields.io/badge/Git-000000?style=for-the-badge&logo=git&logoColor=white"></a>
<a href="https://spring.io/projects/spring-framework" target="_blank"><img src = "https://img.shields.io/badge/Spring_Framework-100000?style=for-the-badge&logo=spring&logoColor=white"></a>
<a href="https://spring.io/projects/spring-data-jpa" target="_blank"><img src = "https://img.shields.io/badge/Spring_Data_JPA-100000?style=for-the-badge&logo=spring&logoColor=white"></a>
<a href="https://hibernate.org/orm/" target="_blank"><img src = "https://img.shields.io/badge/Hibernate-100000?style=for-the-badge&logo=Hibernate&logoColor=white"></a>
<a href="https://www.mysql.com/downloads/" target="_blank"><img src = "https://img.shields.io/badge/MySQL-000000?style=for-the-badge&logo=mysql&logoColor=white"></a>
<a href="https://maven.apache.org/download.cgi" target="_blank"><img src = "https://img.shields.io/badge/Maven-000000?style=for-the-badge&logo=apachemaven&logoColor=white"></a>
<a href="https://tomcat.apache.org/download-90.cgi" target="_blank"><img src = "https://img.shields.io/badge/Tomcat-000000?style=for-the-badge&logo=apachetomcat&logoColor=white"></a>
<a href="https://www.postman.com/downloads/" target="_blank"><img src = "https://img.shields.io/badge/Postman-000000?style=for-the-badge&logo=Postman&logoColor=white"></a>
<a href="https://www.jetbrains.com/idea/download/?section=windows" target="_blank"><img src = "https://img.shields.io/badge/intellij-000000?style=for-the-badge&logo=intellijidea&logoColor=white"></a>
</div> <br>
<p align="center">
  &copy; 2024 Janindu Lokitha
</p>
