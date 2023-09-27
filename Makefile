#IMAGE_TAG=latest
include .env

compile:
	mvn compile

validate:
	mvn validate

build: validate compile

dev:
	mvn run

dockerImage:
	docker build -t droidzed/spring-mailer-app:$(IMAGE_TAG) .

compose:
	docker compose up -d

decompose:
	docker compose down

dockerUp: dockerImage compose