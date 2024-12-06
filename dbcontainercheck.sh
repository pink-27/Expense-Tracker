#!/bin/bash

CONTAINER_NAME="postgresql"

if [[ $(docker ps -a --filter "name=${CONTAINER_NAME}" --format '{{.Names}}') == ${CONTAINER_NAME} ]]; then
    docker container stop ${CONTAINER_NAME} && docker container rm ${CONTAINER_NAME}
    echo "Container ${CONTAINER_NAME} stopped and removed."
else
    echo "Container ${CONTAINER_NAME} does not exist."
fi

