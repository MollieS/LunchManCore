# LunchManCore

[![Build Status](https://travis-ci.org/MollieS/LunchManCore.svg?branch=master)](https://travis-ci.org/MollieS/LunchManCore) [![Coverage Status](https://coveralls.io/repos/github/MollieS/LunchManCore/badge.svg?branch=master)](https://coveralls.io/github/MollieS/LunchManCore?branch=master)

### Clone

```shell
$ cd <folder where you want to store the project>

$ git clone https://github.com/MollieS/LunchManCore.git

$ git clone git@github.com:MollieS/LunchManCore.git

$ cd LunchManCore/
```

This project has a Gradle Wrapper embedded, so you can run the project and tests without having Gradle on your path.

### Compile
```shell
$ ./gradlew build
```


### Test Program
To see the results in the command line:
```shell
$ ./gradlew cleanTest test
```
Or in your browser:
```shell
$ open reports/tests/index.html
```

### Copy JAR

If you are also planning to work with the web enabled LunchMan, we have created
a simple shell script that will copy the built gradle JAR to the cloned
LunchMan repo. You may need to `chmod` this file after cloning it in order to
do so. 

```shell
$ ./copyJar.sh
```
