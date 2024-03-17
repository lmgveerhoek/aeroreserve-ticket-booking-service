# syntax=docker/dockerfile:1.4
FROM --platform=$BUILDPLATFORM maven:3.8.5-eclipse-temurin-17 AS builder
WORKDIR /workdir/server
COPY pom.xml /workdir/server/pom.xml
RUN mvn dependency:go-offline

# COPY .env /workdir/server/.env

COPY src src
RUN mvn install

RUN <<EOF
useradd -s /bin/bash -m vscode
groupadd docker
usermod -aG docker vscode
EOF

CMD ["mvn", "package", "exec:java"]