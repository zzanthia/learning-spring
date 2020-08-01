mkdir "%userprofile%/docker/volumes/postgres"
rd /s /q "%userprofile%/docker/volumes/postgres/data"

docker run --rm --name pg-docker -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=dev -d -p 5432:5432 -v %userprofile%/docker/volumes/postgres:/var/lib/postgresql postgres

docker exec -it pg-docker psql -U postgres -d dev -h localhost -f /var/lib/postgresql/schema.sql
docker exec -it pg-docker psql -U postgres -d dev -h localhost -f /var/lib/postgresql/data.sql