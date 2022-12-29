#!/bin/bash
 mvn package -Dmaven.test.skip=true
docker-compose up -f docker-compose-dev.yml --build -d