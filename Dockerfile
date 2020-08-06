FROM openjdk:11-jre-stretch
ENV TZ=America/Bogota
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ENV APP_FILE demo-0.0.1-SNAPSHOT.jar
ENV APP_HOME /usr/apps
EXPOSE 8080
COPY build/libs/$APP_FILE $APP_HOME/
WORKDIR $APP_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -jar $APP_FILE"]

