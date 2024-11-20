docker build -t nicolasmonta1807/cloud-config:latest ./cloud-config
docker build -t nicolasmonta1807/eureka-server:latest ./eureka-server
docker build -t nicolasmonta1807/gateway:latest ./gateway
docker build -t nicolasmonta1807/keycloak-server:latest ./keycloak-server
docker build -t nicolasmonta1807/users-microservice:latest ./users-microservice
docker build -t nicolasmonta1807/marketplace-microservice:latest ./marketplace
docker build -t nicolasmonta1807/reviews-microservice:latest ./reviews-microservice

docker push nicolasmonta1807/cloud-config:latest
docker push nicolasmonta1807/eureka-server:latest
docker push nicolasmonta1807/gateway:latest
docker push nicolasmonta1807/keycloak-server:latest
docker push nicolasmonta1807/users-microservice:latest
docker push nicolasmonta1807/marketplace-microservice:latest
docker push nicolasmonta1807/reviews-microservice:latest
