FROM tomcat:9.0.88-jdk17-openjdk-slim
ADD ./target/supermercado /usr/local/tomcat/webapps/supermercado
RUN mkdir -p /usr/local/tomcat/webapps/supermercado
CMD ["catalina.sh", "run"]