

# Shuqa - E-marketplace

## What is Shuqa? 
Shuqa is a local online marketplace connecting buyers and sellers within communities. Users can browse listings without an account, but registration is needed to post and manage items. It's designed for local commerce and community interaction.

## Why Shuqa?
This app fills a crucial gap by providing a local online marketplace where bank cards and online payments are not common. It offers a simple and accessible platform for local buying and selling, catering to the specific needs of these communities.

## The name of Shuqa?
Shuqa in Chaldean Neo-Aramaic, a language with deep roots in the ancient Chaldean civilization, means Bazar.

### Hardwate and Software Technologies


|Category          |Technology      |Verion/Tool|
| ----- | ----- | ----- |
|Backend Framework | Spring Boot    | 3.2     |
|Language          | Java           | 17      |
|Frontend Engine   | Thymeleaf      | 3.2     |
|CSS Framework     | Bootstrap      | 5       |
|Web Standerds     | HTML5 and CSS3   | Standard |
|Database          | MongoDB        | Atlas Cloud (NoSQL) |
|IDE               | VS Code        | 1.95    |
|UI/UX Design      | Figma          | Wirefranes |
|Communicaton      | SMTP           | Gmail API Integration |
| Operating System | Windows 11     | -  |       


## Logical Solution Design:
This project employs the **Model-View-Controller (MVC)** architectural pattern, separating concerns into three layers: presentation, business logic, and data access. 

- **Front-end**: Built using **Bootstrap** and **Thymeleaf** templates for responsive UI design.
- **Business Layer**: Developed with **Spring Boot** and **Java** for handling application logic and managing user sessions.
- **Data Access Layer**: Communicates with **MongoDB** to perform CRUD operations.

![Screen1](/Documents/Logical%20Solution%20Design.png)


## Physical Solution Design:


![Physical Solutoin](/Documents/Physical%20Solution.png)

## General Technical Approach:
The initial focus is on building a functional marketplace platform. Future enhancements include:
- Adding payment integrations
- User feedback and review system
- Admin panel for managing listings

The web application is built using **Spring Boot**, **Bootstrap**, and **Thymeleaf** for rapid and scalable development.

## Key Technical Design Decisions:
- **IDE**: VS Code for lightweight Java development and Git integration.
- **Backend Framework**: Spring Boot for simplicity, dependency injection, and production-ready support.
- **Programming Language**: Java 17 for its platform independence, performance, and compatibility with Spring.
- **Frontend Framework**: Bootstrap for mobile-first responsive design.
- **Template Engine**: Thymeleaf for server-side rendering and seamless Spring Boot integration.
- **Gmail**: for sending the reset password link to user.

## Schema ER Diagram:
The application uses MongoDB with two main collections:
- **Users**: Stores user credentials and profile information.
- **Products**: Stores product listings created by users.

Relationship: **One-to-Many** (One user → many products).

![Screen2](/Documents/ERDiagram.png)

## Flow Charts/Process Flows:
The application flow includes:
- Accessing the homepage
- Navigating via the navbar
- Registration and login validation
- Posting, editing, and deleting products (authenticated users only)
- Implemented an SMTP-based automated password recovery workflow.

[Flowchart](/Documents/FlowChart.pdf).

## Sitemap Diagram:
The sitemap outlines navigation routes between the homepage, login, registration, products listing, and other key areas.

[Sitemap](/Documents/SiteMap.png).

## User Interface Diagrams:
Wireframes were designed in **Figma** to plan and validate the user experience before implementation. <br>
[Wireframe](/Documents/Wireframe.pdf).

## UML Diagrams:
The system's object-oriented structure is represented using a UML Class Diagram. <br>
[UML](/Documents/UML.pdf).

## Non-Functional Requirements (NFRs)
- **Security**: 
  - User authentication via username/password.
  - Passwords encrypted with **BCrypt** hashing.
- **Data Integrity**: 
  - Daily database backups.
- **Responsiveness**: 
  - Mobile-first design using Bootstrap

## Operational Support Design:
- **Spring Boot Actuator**: Health and performance monitoring.
- **Azure Monitor**: Cloud-level monitoring.
- **Azure Log Analytics**: Centralized logs.
- **Logback (Spring Boot)**: Application-level logging.

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

[Test Cases (Excel)](/Documents/TestCase.xls)  
[Traceability Matrix (Excel)](/Documents/TraceabilityMatrix.xls)

## New Technologies Learned
During the Shuqa project, I gained hands-on experience in:
- **MongoDB & NoSQL Design**: Implemented flexible document-oriented structures to manage variable product metadata and optimized data retrieval using Spring Data MongoDB.
- **Spring Security 6**: Developed a robust authentication layer utilizing BCrypt password hashing and role-based access logic to protect user data.
- **Automated Communication (SMTP)**: Integrated JavaMailSender to handle automated system emails, specifically for secure password reset workflows via the Gmail API.
- **File I/O & Mapping**: Engineered a system to manage local multipart file uploads, linking physical storage paths to database entries for dynamic image rendering.
- **Thymeleaf Layout Dialect**: Mastered server-side template fragmenting to create a modular, reusable, and maintainable frontend architecture.

These technologies were selected to build a secure, data-driven, and community-focused web application that bridges the gap between ancient cultural roots and modern commerce.
---

# Conclusion
Shuqa is a full-stack web application addressing real-world community needs. It reflects best practices in software design, security, cloud deployment, and operational monitoring, while providing a solid foundation for future enhancements.

# Other Documents
- [Project Proposal](/Documents/OtherDoc/ProjectProposal.pdf)
- [Project Requirements](/Documents/OtherDoc/ProjectRequirements.pdf)
- [Project Architecture & Design](/Documents/OtherDoc/ProjectArchitecture&Design.pdf)
- [Sprint Back Log](/Documents/OtherDoc/SprintBackLog.xls)
-[Test Cases](/Documents/OtherDoc/TestCase.xls)
- [Traceability Matrix](/Documents/OtherDoc/TraceabilityMatrix.xls)

## Demo of web-application:
[Video](https://youtu.be/Zrg2Buge7x0?si=BrY6etBNwIJEZ6e6)

---
**Author: Soran Qiji\
Capstone project**
