docker stop offer-service
docker rm offer-service
docker run --name offer-service -d -p 8081:8081 offer-service