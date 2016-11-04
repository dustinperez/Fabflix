# Fabflix
Fabflix is a Netflix like website for buying movies, done as a project with two teammates (Carolyn Jung and Minh Phan) for CS122B at UC Irvine. It was my first attempt at a larger sized web application. It supports user authentication, including user account logins and captcha's to prevent bot access. User's can query for movies using a simple type-ahead input field that includes autocomplete search suggestions via AJAX requests. 

## Database ETL Process, XML Parsing, and Stored Procedures
The MySQL database was populated from XML files, using a custom SAX XML parser to extract, transform, and load the data into the RDBMS. All XML files were initially validated using DTD files to ensure clean data was used, although this requirement was later relaxed and the system was advanced to attempt to also gather data from potentially unsanitized sources. 

We also wrote a stored procedure in SQL to efficiently scale the process of adding extracted information from the files into multiple tables atomically.

We used connection pooling via the JDBC to scale the number of concurrent read requests that each datastore could serve concurrently.

## Server and Database Configuration
The Fabflix website was hosted on a small cluster of AWS EC2 instances. Each EC2 instance runs its own Apache Tomcat server to serve requests forwarded to them via an Apache HTTP server acting as a load balancer. Once a user has a session, their subsequent requests are always sent to the same server to maintain consistency. The application supported both HTTP and HTTPS, forwarding all HTTP requests to the more secure HTTPS server to protect user privacy. The datastore for this project is MySQL, which is replicated on each server for redundancy in a master-slave configuration.

## Application Stack
The web application uses Java servlets as provided by Apache Tomcat and defined in the JEE specification. All content is served from java server pages (JSP), using the JSP expression language to avoid the use of scriptlets. For connectivity to the datastore, JDBC is used. The application also makes use of jQuery to make AJAX calls and to provide tooltips.

## Then and Now
Fabflix was my first larger sized web application and my first time using JEE. Looking back on it, there are a lot of sections that I would have handled differently. However, it was a really great learning experience, both in terms of working with data ETL processes from potentially disparate sources and in building a scalable web application. I also learned a great deal about DevOps and the cloud.
