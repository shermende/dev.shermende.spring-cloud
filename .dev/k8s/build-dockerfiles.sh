#!/bin/bash
mvn clean package -DskipTests
docker build -f env-authorization/src/main/docker/Dockerfile -t env-authorization:0.0.1 ./env-authorization
docker build -f app-reference/src/main/docker/Dockerfile -t app-reference:0.0.1 ./app-reference
docker build -f app-game/src/main/docker/Dockerfile -t app-game:0.0.1 ./app-game
docker build -f env-sba/src/main/docker/Dockerfile -t env-sba:0.0.1 ./env-sba
