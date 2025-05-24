# Use official Tomcat image as the base
FROM tomcat:9.0-jdk11

# Remove default webapps to keep the container clean
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the WAR file into the Tomcat webapps directory
# Adjust the name if your WAR is not named ROOT.war
COPY target/demo-app-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Expose the default Tomcat port
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
