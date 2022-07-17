#!/bin/bash

# build jar file
mvn clean
mvn package

# ensure that docker-compose stopped
docker-compose stop

# add environment variables
export DB_USER='postgres'
export DB_PASSWORD='postgres'

# start new deployment
docker-compose up --build -d