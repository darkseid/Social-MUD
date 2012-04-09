SocialMUD[![Build Status](https://secure.travis-ci.org/darkseid/Social-MUD.png?branch=master)](http://travis-ci.org/darkseid/Social-MUD)
========

1. TODO - Description of the project

2. Installation instructions:

2.1 Install Redis:

On Linux/ MacOS:

Download it at: http://redis.googlecode.com/files/redis-2.4.8.tar.gz

Execute this commands on the terminal:
  tar -zxvf redis-2.4.8.tar.gz 
  cd redis-2.4.8
  make
  sudo make install

2.2 Install Maven

//TODO maven installation instructions

3. Running the project:

3.1 Run Redis server:

Execute this command on the terminal:
  redis-server

3.2 Running the application:

On the application root folder, and execute this command on the terminal:
  mvn jetty:run

3.3 Accessing the application

On your web browser, enter the url:
  http://localhost:8080/game/index.do
