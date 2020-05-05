FROM openjdk:13.0.2-jdk
LABEL maintainer="renderForSEO Maintainer <tzry93@gmail.com>"
WORKDIR /
ADD target/render4seo.jar render4seo.jar
EXPOSE 4000
ENTRYPOINT ["java","-jar","render4seo.jar"]