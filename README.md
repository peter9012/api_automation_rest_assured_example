# REST API Automation Framework Example


We are using REST Assured. It is a Java DSL for simplifying testing of REST based services built on top
of HTTP Builder. It supports POST, GET, PUT, DELETE, OPTIONS, PATCH and HEAD requests and can be used to validate and verify the response of these requests.

## Installation

Download Java from link: 
http://www.oracle.com/technetwork/java/javase/downloads/index-jsp-138363.html

Setup Environmental variables Path

Download Eclipse from link:
https://eclipse.org/downloads/

Install TestNG In Eclipse Guide:
https://www.guru99.com/install-testng-in-eclipse.html

Download Rest Assured Jars :
from below link https://github.com/rest-assured/rest-assured/wiki/Downloads

Add External Library In Eclipse:
There is a"external_jar_file" folder and keep all external jars in the folder.
Subsequently, add all the jar files in the lib folder into your build path by using
```bash
Project => Properties => Java Build Path => Libraries => Add JAR ...
```

## Usage

Right Click and Run As TestNG Test
```java
@Test
public void sumOfCourses() 
{
  JsonPath js = new JsonPath(payload.CoursePrice());
  int count = js.getInt("courses.size()");
  System.out.println(count);
}
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.
