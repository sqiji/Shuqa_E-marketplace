# Shuqa - E-Marketplace

## What is Shuqa? 
Shuqa is a local online marketplace connecting buyers and sellers within communities. Users can browse listings without an account, but registration is needed to post and manage items. It's designed for local commerce and community interaction.

## Why Shuqa?
This app fills a crucial gap by providing a local online marketplace where bank cards and online payments are not common. It offers a simple and accessible platform for local buying and selling, catering to the specific needs of these communities.

## The Meaning Behind "Shuqa"
"Shuqa" in Chaldean Neo-Aramaic — a language with deep roots in the ancient Chaldean civilization — means "Bazaar" (market).

## Deployment
The Shuqa application is deployed to **Azure Cloud**, ensuring scalability, availability, and robust infrastructure support. Azure also provides integrated monitoring and resource scaling to meet user demand.

## Hardware and Software Technologies

| Milestone         | Version |
|-------------------|---------|
| Spring Boot       | 3.2     |
| Java              | 17      |
| Thymeleaf         | 3.2     |
| Bootstrap         | 5       |
| HTML and CSS      | HTML5, CSS3 |
| MongoDB           | Atlas Cloud Database |
| VS Code           | 1.95    |
| Figma (UI/UX Design) | -    |
| smtp.gmail.com | - |
| Operating System  | Windows 11 |

## Logical Solution Design
This project employs the **Model-View-Controller (MVC)** architectural pattern, separating concerns into three layers: presentation, business logic, and data access. 

- **Front-end**: Built using **Bootstrap** and **Thymeleaf** templates for responsive UI design.
- **Business Layer**: Developed with **Spring Boot** and **Java** for handling application logic and managing user sessions.
- **Data Access Layer**: Communicates with **MongoDB** to perform CRUD operations.

![Logical Solution Design](/Documents/Logical%20Solution%20Design.png)

## Physical Solution Design
The web application is hosted on **Azure**. Users access the website securely over HTTPS (port 443).

- **Initial Azure Configuration**:
  - 1 vCPU
  - 1.75 GB RAM
  - 10 GB storage
- **Database**: MongoDB (Atlas Cluster or Azure Cosmos DB for MongoDB API)

![Physical Solution Design](/Documents/Physical%20Solution.jpg)

## General Technical Approach
The initial focus is on building a functional marketplace platform. Future enhancements include:
- Adding payment integrations
- User feedback and review system
- Admin panel for managing listings

The web application is built using **Spring Boot**, **Bootstrap**, and **Thymeleaf** for rapid and scalable development.

## Key Technical Design Decisions
- **IDE**: VS Code for lightweight Java development and Git integration.
- **Backend Framework**: Spring Boot for simplicity, dependency injection, and production-ready support.
- **Programming Language**: Java 17 for its platform independence, performance, and compatibility with Spring.
- **Frontend Framework**: Bootstrap for mobile-first responsive design.
- **Template Engine**: Thymeleaf for server-side rendering and seamless Spring Boot integration.

## Schema (ER Diagram)
The application uses MongoDB with two main collections:
- **Users**: Stores user credentials and profile information.
- **Products**: Stores product listings created by users.

Relationship: **One-to-Many** (One user → many products).

![ER Diagram](/Documents/ERDiagram.png)

## Flowchart / Process Flow
The application flow includes:
- Accessing the homepage
- Navigating via the navbar
- Registration and login validation
- Posting, editing, and deleting products (authenticated users only)

![Flowchart](/Documents/Flowchart.jpg)

## Sitemap
The sitemap outlines navigation routes between the homepage, login, registration, products listing, and other key areas.

![Sitemap](/Documents/Sitemappng.jpg)

## User Interface Diagrams
Wireframes were designed in **Figma** to plan and validate the user experience before implementation.

![Wireframe](/Documents/wireframe.jpg)

## UML Diagrams
The system's object-oriented structure is represented using a UML Class Diagram.

![UML Diagram](/Documents/UML.jpg)

## Non-Functional Requirements (NFRs)
- **Security**: 
  - User authentication via username/password.
  - Passwords encrypted with **BCrypt** hashing.
- **Data Integrity**: 
  - Daily database backups.
- **Scalability**: 
  - Azure resource scaling based on demand.
- **Responsiveness**: 
  - Mobile-first design using Bootstrap.
- **Monitoring**:
  - Spring Boot Actuator for system health checks.
  - Azure Monitor and Log Analytics for real-time monitoring and logging.

## Operational Support Design
- **Spring Boot Actuator**: Health and performance monitoring.
- **Azure Monitor**: Cloud-level monitoring.
- **Azure Log Analytics**: Centralized logs.
- **Logback (Spring Boot)**: Application-level logging.

## DevOps Practices
- **Version Control**: Managed via GitHub.
- **Continuous Monitoring**: Enabled with Azure Monitor.
- **Backup Strategy**: Daily backups of database instances.
- **Testing and Validation**: Manual functional testing using structured test cases and traceability matrices.

## Technical Issues and Risk Log
| Risk / Issue | Management Approach |
|--------------|----------------------|
| Data Security | Encrypted password storage and user authentication |
| Scam & Fraud Prevention | Listing validations, user reporting mechanisms (planned) |
| Image Handling Performance | Limit file size and types for uploads |
| Project Management | Git-based version control and regular testing |

## Functional Testing and Validation
A structured testing approach was followed:
- **Test Cases**:
  - Covered navigation, form validations, login, registration, and product management.
- **Traceability Matrix**:
  - Mapped test cases to user stories to ensure complete requirement coverage.

[Download Test Cases (Excel)](/Documents/Test%20Case.xls)  
[Download Traceability Matrix (Excel)](/Documents/Traceability%20Matrix.xls)

## New Technologies Learned
During the Shuqa project, I learned:
- **MongoDB**: Implemented flexible NoSQL database structures.
- **Azure Cloud Services**: Deployed and monitored a cloud-based web application.
- **Spring Boot Actuator**: Implemented application health monitoring.

These technologies were selected to build a modern, scalable, and maintainable web application.

---

# Conclusion
Shuqa is a full-stack web application addressing real-world community needs. It reflects best practices in software design, security, cloud deployment, and operational monitoring, while providing a solid foundation for future enhancements.

---
