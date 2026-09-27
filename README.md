# PWeb Final

Web application for library management built with JavaServer Faces (JSF), Spring Framework and PrimeFaces.

The project provides a server-rendered interface for managing library resources and operations such as books, authors, clients, copies, loans, loan requests, matters, sanctions and users.

## Features

- Authentication and access control with Spring Security.
- Management screens for:
  - Books and authors.
  - Clients and copies.
  - Loans and loan requests.
  - Matters and sanctions.
  - Users and roles.
- Form validation and reusable JSF views.
- Reports for books by author and loans by client.
- Spanish and English message bundles.
- PDF generation support through OpenPDF.
- Responsive interface components with PrimeFaces.

## Technology stack

- Java 8
- JavaServer Faces 2.2
- PrimeFaces 10
- Spring Framework 5
- Spring Security
- Maven
- Servlet API 4
- OpenPDF
- Jackson

## Project structure

```text
src/
├── main/java/
│   └── cu/edu/cujae/pweb/
│       ├── bean/       # JSF managed beans and application actions
│       ├── config/     # Spring, security and web configuration
│       └── dto/        # Data transfer objects
├── main/resources/
│   └── i18n/           # Spanish and English messages
└── main/webapp/
    ├── WEB-INF/        # JSF and servlet configuration
    ├── pages/          # XHTML views grouped by feature
    └── resources/      # CSS, JavaScript and images
```

## Requirements

- JDK 8
- Maven 3.6 or newer
- A Java web container compatible with Servlet 4, such as Apache Tomcat
- An environment configured for the application's external services, if required

## Build the application

Clone the repository and build the WAR package:

```bash
git clone https://github.com/jennyanez/pweb-final.git
cd pweb-final
mvn clean package
```

The generated artifact will be available in `target/`.

## Run locally

Deploy the generated WAR file to a compatible servlet container. For Apache Tomcat, copy the WAR file into the container's `webapps/` directory and start the server.

The application context is derived from the WAR filename. For example:

```text
http://localhost:8080/pweb-jsf/
```

Review the project configuration before starting the application if it depends on services that are specific to the original development environment.

## Notes

This repository includes Eclipse project metadata such as `.classpath`, `.project` and `.settings/`. The application is built with Maven, so the `pom.xml` should be treated as the source of truth for dependencies and packaging.

## Project status

Academic web programming project focused on server-side Java development, JSF interfaces and library management workflows.
