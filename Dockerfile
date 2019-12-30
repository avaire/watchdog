# Build container

FROM gradle:4.10.2-jdk11-slim AS build

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

# Run container

FROM openjdk:11-jre-slim AS runtime

WORKDIR /opt/wdavaire/

RUN adduser --disabled-password --gecos '' wdavaire; \
    chown wdavaire:wdavaire -R /opt/wdavaire; \
    chmod u+w /opt/wdavaire; \
    chmod 0755 -R /opt/wdavaire

USER wdavaire

COPY --from=build /home/gradle/src/Watchdog.jar /bin/

CMD ["java","-jar","/bin/Watchdog.jar","-env","--use-plugin-index"]
