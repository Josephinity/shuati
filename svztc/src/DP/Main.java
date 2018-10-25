package DP;

/*

I recently [graduated from/study at] the ms of Computer Science at ?? school.
I have always been interested in building up applications by my own. So during school and my internships,
 There are a couple of software developing projects [examples.....] by far and I'm very happy with the outcome.
 One of the projects on [....] involving [technologies]  is currently in production and has served 100,000 users.

 At school I've got pretty good grades.So I though it'd be great if I could get some experience in the industry.
 A place like [facebook???]will be aaaaaawesome to get the experiences and I will do my best to the team if given the opportunity.
* */

/**
 Intro:
 * I graduated from/study at the ms of Computer Science at ?? school. I have always been interested in building up applications
 * by my own, and programming is such an essential skill that could apply in literally any area,(like when I was working on chemistry experiments, we use matlab to analyze the
 * data. .... That's when I start off getting involved with programming stuff.)
 * So I made the big decision to swap into Computer Science.
 *
 *
 * I've been working a couple of software developing projects(examples) by far and I'm very happy with the outcome.
 * Also I've got pretty good grades at school.
 * So I though it'd be great if I can get some experience with the industry. A place like facebook
 * will be aaaaaawesome to get the experiences and I will do my best to the team if given the opportunity.
 *

 Why FB?
 First of all Facebook has a great name of welcoming fresh young talents like myself. Just like the company,
 I am fast-paced, passionate and innovative and always sniffing for something new.
 You can tell from my career change that I'm not lacking courage with new things.
 As a student, I'm eager to explore on the latest software techs in practice. Facebook is definitely pioneer
 in the area. The things that interest me most is how facebook integrated so many tools flawless to get the
 tremendous system working. Cuz I know that the persistence is done using MySQL, Memcached, Hadoop's HBase;
 offline processing using Hadoop and Hive; Data feeds and logging using Scribe and stored in HDFS. All these
 techs I've heard but some never get the chance to dive into. My skill set overlaps with facebook's need on Hadoop, Redis and MySQL.
 And I've built web applications full-stack, although comparing with facebook system it's too simple.
 Therefore all of the tools and architectures that facebook uses I'm super excited to learn about.


 Why Google?
 First of all G has a great name of welcoming fresh young talents like myself. Just like the company,
 I am fast-paced, passionate and innovative and always sniffing for something new.
 As a CS grad, I'm eager to explore on the latest software techs in practice. G is definitely pioneer
 in the area. The things that interest me most is how G integrated so many tools flawless to get the
 tremendous system working. Cuz I know that Google relies heavily on in-house distributed tools GFS, BigTable and MapReduce,
 which I'm sure to get the hang of fairly quickly because of the intern exp at Yahoo working on XXXXX....
 Also a great part of the build system and daily work flow is based on python. Data monitoring and collection is also with python
 which is exactly what I have been doing during school and the internship.

 Java(Gmail)
 CPP(google.com)
 PageRank & Data Analytics (search)


 Project 1
 Scale:
 2G of data, 2,000,000,000
 50 chars/ n-gram: 2,000,000,000/50 = 40,000,000 n-grams,
 from 200,000 comments or reviews
 crawled from where?

 Tools:
 Java
 Crawl from web and write to local file.(Jsoup - extract comment, .net - request urls,.. )
 Hadoop's Map Reducer
 Write data to local file

 How:
 https://dzone.com/articles/word-count-hello-word-program-in-mapreduce


 Project 2
 Scale:
 2000char/url -> 10kb/url
 1,000,000,000,000tb total data-> 100, 000,000 urls
 in two months -> 100,000,000/(3600*24*60) = 19 writes/sec
 19*10k = 200kb writes/sec
 server capacity 1000-10,000 * 10k
 2 servers needed to support writes

 were there a lot of reads?

 Tools:
 Express.js, Node.js
 Dockerize https://github.com/jwilder/dockerize
 Nginx https://www.nginx.com/resources/glossary/reverse-proxy-server/
 Redis with MongoDB https://www.sitepoint.com/caching-a-mongodb-database-with-redis/


 How:
 why migrate to Cassandra: https://academy.datastax.com/planet-cassandra/mongodb-to-cassandra-migration
 https://www.fullcontact.com/blog/mongo-to-cassandra-migration/


 AngularJS Controller:
 <div ng-app="myApp" ng-controller="myCtrl">

 First Name: <input type="text" ng-model="firstName"><br>
 Last Name: <input type="text" ng-model="lastName"><br>
 <br>
 Full Name: {{firstName + " " + lastName}}

 </div>

 <script>
 var app = angular.module('myApp', []);
 app.controller('myCtrl', function($scope) {
 $scope.firstName = "John";
 $scope.lastName = "Doe";
 });
 </script>



 */



/*
MongoDB
Scale:  2G/instance, up to 1000 nodes with Goal, because of 16mb limit of namespaces, supports 24,000 namespaces.
 no more than 16000 data files. This means that a single MMAPv1 database has a maximum size of 32TB (64byte key)


MySQL
 up to 1,073,741,824 rows, < 1TB, 1000 col,


 Cassandra
 No down time, linear cost as data scale


 SQL vs NoSQL
 relational vs distributed
 table based vs document based/ key value pairs/wide column stores
  SQL databases are scaled by increasing the horse-power of the hardware CPU RAM SSD. NoSQL databases are scaled by increasing the databases servers in the pool of resources to reduce the load.
  sql can do complex queries while NoSQL cannot
  sql is no good for hierarchical data storage, NoSQL is good
  SQL databases are best fit for heavy duty transactional type applications, as it is more stable and promises the atomicity as well as integrity of the data. While you can use NoSQL for transactions purpose, it is still not comparable and sable enough in high load and for complex transactional applications.



  Mongo vs Cassandra
  cross platform vs single platform
  non-durable writes vs durable writes
  document based (store document as json objects) vs wide-col store

  Cassandra
  No single point of failure ensures 100% availability.
Operational simplicity for lowest total cost of ownership.
Best-in-class scalability of NoSQL platforms.


Mongo
strong consistency, expressive query language and secondary indexes. As a result, developers can build highly functional applications faster than NoSQL databases.
MongoDB provides the data model flexibility, elastic scalability, and high performance and availability of NoSQL databases. \


As an on-trend feature that just came out 2015, facebook live opens up a new form to share the life with friends. And the user
gets to choose which group of people to share broadcast with and decide how long the record stays on facebook.
And tech wise, I think it's really cool that fb is able to support this real-time feature on such a large scale - over 1b of users
 streaming on the fly and interactively. Some accounts have millions of followers this means when a public figure starts
 a live broadcast, you'll need to handle the potential of a million people watching at the same time. That is a lot of requests
 to handle. I heard that to solve this problem, facebook add another layer of live stream servers behind its origin servers.
 To bring down latency, facebook uses RTMP which pushes audio and video chunks to user through TCP connection therefore making the
 stream faster(using RTMP, most other streaming were HTTP(HLS) (cheaper and easier to scale) based) and stable(bec of TCP).
Comparing to Periscope: No individual mobile app. less features - periscope 360.
Tech: think about migrating to HLS.

Interface:
Mobile apps.
 More interactive features based on facebook connections(birthdays, presents...).
broadcast from Go Pro(IOT support).
You are able to sketch on screen streaming on twitter

 */
import java.util.*;
public class Main {
    public static void main(String[] args) {

        System.out.println(new HashMap().get(5));
    }



}

