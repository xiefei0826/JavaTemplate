#!/bin/bash
docker build -t activemq:latest   ./dockerfile/activemq/

docker build -t maven:latest ./dockerfile/maven/

docker build -t redis:latest ./dockerfile/redis/