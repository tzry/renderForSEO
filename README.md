# renderForSEO
As is known to all, a single-page application (SPA) is crawler-unfriendly.
This project is to render pages for SEO crawlers

#dependency
Chrome (Remote) is required for the project to render the page, we can use the following docker to run the headless chrome.

docker run -d -p 4444:4444 -v /dev/shm:/dev/shm selenium/standalone-chrome:3.141.59-20200409

#easiest way to run the project (docker-compose)
A docker-compose.yml file has been provided for easily running the project.
Please use maven3 to build the project into a jar file before using the docker-compose file.

#Configuration
## in src/main/java/resources/application-prod.yml
You can configure the property config.driver.num to manage the number of chrome sessions that the project maintains.
To improve the performance, the project will maintain a pool of driver sessions, and drop the requests if all the sessions are busy.

## in src/main/java/resources/ehcache.xml
You can configure the cache configuration for the pages. For the details, please refer to https://www.ehcache.org/generated/2.10.4/html/ehc-all/

#Donations
If you favor this project, please star the project. Your support is my best encouragement. You can also use the PayPal link (https://paypal.me/tzry93) to donate to the author.

