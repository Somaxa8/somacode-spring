#!/usr/bin/env bash

docker stop somacode
docker rm -f somacode
docker rmi somacode

cd /root/somacode/project
bash -x gradlew buildDocker --no-daemon --stacktrace -Dprod -Pprofile=prod -x test -Dkotlin.compiler.execution.strategy=in-process -Dkotlin.incremental=false

docker logs -f somacode