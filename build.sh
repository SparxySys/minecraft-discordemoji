#!/bin/bash
docker run -d=false --mount type=bind,source="$(pwd)",target=/mnt sparxy/fabric-docker:latest