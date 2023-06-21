docker build -t libraryv
docker stop libraryv
docker stop libraryV
docker rm libraryv
docker rm libraryV
@REM docker run -d -e MYSQL_ROOT_PASSWORD=pass123 -p 3306:3306 --restart=unless-stopped -v C:\Docker\mysql --name libraryv_mysql mysql:8.0.29
docker run -d -p 8080:8080 --name=libraryV -e SPRING_PROFILES_ACTIVE=dev --network libraryv-network --restart unless-stopped libraryv
