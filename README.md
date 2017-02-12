pandur
======

Database Abstraction for java.

Usage
-----

This is the intended first usage:

``` java
DataSource dataSource = ...;
Pandur pandur = new Pandur(dataSource, User.class);
User user = pandur.findById(User.class, 123);
```

Links
-----

 - [Info](https://rynr.github.io/pandur/)
 - [Github](https://github.com/rynr/pandur)
 - [Bugs](https://github.com/rynr/pandur/issues)
 - [![Build Status](https://travis-ci.org/rynr/pandur.svg?branch=master)](https://travis-ci.org/rynr/pandur)


