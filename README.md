# Crawler
Task for admission on Java developer position to one of the companies.

This is simple web crawler that collects information about given domain.

## Statement of work:
Develop service that collecting information about links on pages of given site. As initial parameter domain is given. Service should scan site by reading main page and navigating across html links to inner pages. On each page of site external links should be counted.
As result of service work should be a table with next fields:
1. Page URL
2. Nesting level
3. Number of external links
4. Overall domain information
Results should be saved to database. All not specified details could be realized at your choice.

## Realization information
Project tested to work in WildFly 8.2.0/9.0.0 Java application containers.

Maven have two profiles:
- datasource-use-h2 (used by default) - use H2 as storage. In this case Crawler do not need any additional database services to be started.
- datasource-use-mysql - use MySQL as storage.
