# IshaDonorSystem

######## Installation and Setuo ############
Install MySQL
Create tables using sql  SQL Script/idsdb.sql

Update Database credentials in IshaDonationSystem/src/main/resources/application.properties

######## Deployment ############
cd IshaDonationSystem/

./gradlew clean build

java -jar build/libs/IshaDonationSystem-0.0.1-SNAPSHOT.jar

######## Testing and Validation ###########

log into app at http://localhost:8080