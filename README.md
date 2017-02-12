pandur
======

Database Abstraction for Java.

Usage
-----

Given a Bean like this:

``` java
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "users")
public class User {

  @Id
  private Long id;

  @Column()
  private String email;

  @Column(name = "password")
  private String passwordEncrypted;

  // getters and setters
}
```

Then it should be possible to retrieve a `User`-Object by calling:

``` java
DataSource dataSource = ...;
Pandur pandur = new Pandur(dataSource, User.class);
User user = pandur.findById(User.class, 123);
```

Current restrictions
--------------------

This is the first POC. I only can query a list of Objects. The properties need
to be `Long` or `String`.  
Please check the [Issues](https://github.com/rynr/pandur/issues) for the next
steps.

Links
-----

 - [Info](https://rynr.github.io/pandur/)
 - [Github](https://github.com/rynr/pandur)
 - [Bugs](https://github.com/rynr/pandur/issues)
 - [![Build Status](https://travis-ci.org/rynr/pandur.svg?branch=master)](https://travis-ci.org/rynr/pandur)


