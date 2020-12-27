# punk_api
This api allows you to make an account, select info about available beers,
save your favorite beers and send an email with it. Powers by https://punkapi.com/
## Run

* Download or clone repository and run it in IntelliJ IDEA
* Go to  ```meal/src/main/resources/application.properties```
and in ```spring.datasource.url``` connect with your MySql database,
in ```spring.datasource.username and spring.datasource.password```
enter your username and password to database. Next in ```spring.mail.username and spring.mail.password``` enter valid
gmail email and password if you want api be able to send emails

# Register

* To make an account use client like Postman, go to:
```
http://localhost:{your_default_port}/registration
```
   and send a body in post request like example below:
```
{
    "login": "seba123",
    "email": "seba123@email.com",
    "password": "password123"
}

```
## Api map:

* Select page number with five items (get request):
```
http://localhost:{your_default_port}/{num}
```
* Save beer to favorite by beer id (get request):
```
http://localhost:{your_default_port}/save/{beerId}
```
* Delete beer from favorite by beer id (get request):
```
http://localhost:{your_default_port}/delete/{beerId}
```
* List your favorite beers (get request):
```
http://localhost:{your_default_port}/favourite
```
* Send an email with your favorite beers (get request):
```
http://localhost:{your_default_port}/sendMail
```
