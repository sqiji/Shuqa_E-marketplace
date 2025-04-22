

# Shuqa - E-marketplace

## What is Shuqa? 
Shuqa is a local online marketplace connecting buyers and sellers within communities. Users can browse listings without an account, but registration is needed to post and manage items. It's designed for local commerce and community interaction.

## Why Shuqa?
This app fills a crucial gap by providing a local online marketplace where bank cards and online payments are not common. It offers a simple and accessible platform for local buying and selling, catering to the specific needs of these communities.

## The name of Shuqa?
Shuqa in Chaldean Neo-Aramaic, a language with deep roots in the ancient Chaldean civilization, means Bazar.

### Hardwate and Software Technoogies

|Milestone|Version|
|--|--|
|Spring Boot | 3.2
|Java | 17.
|Thymeleaf | 3.2
|Bootstrap | 5
|HTML and CSS | 5 - 3
|MongoDB | --
|VS code | 1.95
|Figma for design | --
|Computer Windows OS | 11.


## Logical Solution Design:
This outlines the logical system design for the project, employing the Model-View-Controller (MVC) architectural pattern. The design separates the application into three interconnected layers: the presentation layer for user interaction, the business layer containing application logic, and the data access layer for database communication. The front-end user interface is built using Bootstrap for CSS frameworks and styling, and Thymeleaf for HTML templating. Controllers in the business layer act as intermediaries, handling HTTP requests and responses between the presentation and business layers. The business layer, developed with Spring Boot and Java, manages application logic and interactions with the database through the data access layer. Finally, the data access layer is responsible for performing CRUD operations on the MongoDB database used for data storage.

![Screen1](/Documents/Logical%20Solution%20Design.png)


## Physical Solution Design:
This document outlines the physical solution design for a web application hosted on Azure. Users can access the website via any browser on computers or mobile devices using HTTPS on port 443. The initial Azure hosting configuration includes 1 vCPU, 1.75 GB of RAM, and 10 GB of remote storage, which can be scaled based on application size and user traffic. The application's data will be stored in a Mongo database.

![Physical Solutin](/Documents/Physical%20Solution.jpg)

## General Technical Approach:
The general technical approach for developing this website involves future feature implementation, including adding a payment method for expansion and a user feedback section for improvement. While admin controls for deleting posts or users are currently out of scope, the initial development will utilize MongoDB for database support. The web application will be built using the Spring Boot and Bootstrap frameworks, with Thymeleaf as the Java HTML template engine.

## Key Technical Design Decisions:
The VS Code is used as the IDE due to its Java support and ease of use. Spring Boot is chosen as the backend framework for its simplicity, dependency injection, configuration ease, and modern web application support. Java is the chosen programming language because it's platform-independent (runs on JVM), supports Spring, and offers extensive libraries. Thymeleaf will be the server-side Java template engine, allowing for directly viewable HTML templates and seamless integration with Spring Boot. Bootstrap is selected as the front-end framework for its mobile-first approach, ensuring the website's responsiveness across various screen sizes.

## Schema ER Diagram:
The application will use MongoDB as its database, which will consist of two collections: "Products" to store all user-created product posts, and "Users" to hold information about registered users. The relationship between users and products is one-to-many, meaning each user can create multiple product posts.

![Screen2](/Documents/ERDiagram.png)

## Flow Charts/Process Flows:
The website workflow starts with the user accessing the URL, which opens the homepage. The navigation bar allows users to visit other pages, including login and registration. Users can also access the products page to view all listed items. The flowchart indicates that registration and login are required to access core functionalities like creating, updating, and deleting posts. The system includes checks for valid usernames and passwords during login, as well as validation of user-entered information when creating or updating posts.

Click to display [flowchart](/Documents/Flowchart.jpg).

## Sitemap Diagram:
The sitemap diagram illustrates user navigation within the website, primarily through the navigation bar. Additionally, some pages will contain direct links to related pages, such as a link from the login page to the registration page for unregistered users. Upon entering the website's URL, users will first land on the homepage, and from there, they will utilize the navigation bar to explore other sections of the site. Click to display [Sitemap](/Documents/Sitemappng.jpg).

## User Interface Diagrams:
User interface (wireframe) was designed using figma. Click to display [the wireframe](/Documents/wireframe.jpg).

## UML Diagrams:
Below is the UML diagram for my product. Click to display [the UML](/Documents/UML.jpg).

### NFR’s (Security Design, etc.):
The primary non-functional requirement for this web application is security. Only authorized users will be able to access features like creating, updating, and deleting posts, with user authentication being verified during login using username and password checks. To enhance security, user passwords will be encrypted using the BCrypt hash algorithm. Additionally, a daily database backup will be performed after midnight to mitigate data loss, limiting potential data loss to a maximum of 24 hours.

## Operational Support Design:
The application's health and performance will be monitored using several tools. Spring Boot Actuator, a built-in module, provides production-ready features and actuator endpoints for application monitoring. Additionally, Azure Monitor will be utilized to collect data across all application layers, enabling performance checks and issue identification. Azure Monitor Logs, a centralized logging service, will gather and analyze data from Azure, with Log Analytics Workspace used for storage and querying. For application-level logging within the Spring Boot framework, Logback, a Java logging framework, will be employed.

## Technical Issue and Risk Log:
- Data Security – User credentials are protected through password hashing and secure authentication practices.
- Scam & Fraud Prevention – Listing validations and user-based filters help prevent spam and fake products.
- Image Handling Performance - Large image uploads are mitigated by limiting file size and accepted formats.
- Project Management Approach - Regular testing and iteration to ensure quality, and Version control maintained via Git for safe collaboration and rollback support.
