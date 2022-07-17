#!/bin/bash

# ensure that docker-compose stopped
docker-compose stop

# ensure that the old application won't be deployed again
mvn clean