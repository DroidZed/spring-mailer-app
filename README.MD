## **_Spring Mailer App_**

This app is my open source implementation of an SMTP server using Spring Boot and GraphQL.

Feel free to fork the project and use it as you like.

The credentials for the app are currently stored in a `.env` file for security reasons.
You need to provide your own `.env` file or setup the environment on your deployment machine.

This project can also be used as a microservice, part of a bigger backend.

The included `tests/` directory includes all the necessary unit tests needed to qualify the app.

### Steps to installation:

1. Clone the repo:

```sh
git clone github.com/DroidZed/spring-mailer-app.git
```

2. Create your environment variables:

```properties
SMTP_HOST=
SMTP_PORT=
SMTP_USRNAME=
SMTP_PASSWORD=
TO= # Your domain's email
SERVER_PORT=
IMAGE_TAG= # docker image tag
```

3. build it:

```sh
make build
```

4. Test the code:

```sh
make test
```

5. Run the app:

```sh
mvn run
```

I have included the necessary files for Docker and Jenkins 😄

Happy coding!
