docker-compose up -d delivery-db 
cd auth-ms
./gradlew build
cd ../customer-ms
./mvnw package
cd ../courier-ms
./gradlew build
cd ../delivery-ms
./gradlew build
cd ..
docker-compose up -d
