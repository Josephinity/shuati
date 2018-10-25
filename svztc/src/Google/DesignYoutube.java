package Google;

/**

 major components: database model, video/image storage, web server, cache, scalability,recommendation,security

 DB: mySQL

    user model:
        single table: email, name, registration data, profile information
        or
        two tables: table I. authentication data, table II. additional profile information

    video:
        meta data (title, description, size, etc.), video file, comments, view counts, like counts and so on

    author-video: relation will be another table to map user id to video id.

    user-like-video relationship: video - liked user


 Video & image storage

    There are 4X more image than videos on Youtube. TCP vs UDP

    use CDN (Content delivery network), a globally distributed network of proxy servers deployed in multiple data centers.
    The biggest benefit using CDN is that CDN replicates content in multiple places so that there’s a better chance of content being closer to the user, with fewer hops, and content will run over a more friendly network.

    Popular vs Long-tail vids
        host popular videos in CDN and less popular videos are stored in our own servers by location

        Popular videos are viewed by a huge number of audiences in different locations, which is what CND is good at.
        It replicates the content in multiple places so that it’s more likely to serve the video from a close and friendly network.
        Long-tailed videos are usually consumed by a particular group of people and if you can predict in advance,
        it’s possible to store those content efficiently.

 Scale the DB

    I. db sharding: at some point, you’ll have to partition the database and settle on a sharding approach.

        shard rule: you can split the database by users’ location and when a request comes, you’ll route the request to the corresponding database.

        pros:
            reduces index size, which generally improves search performance

        cons:
            heavier reliance on the interconnect between servers
            Increased latency when querying, especially where more than one shard must be searched

    II. we can prioritize traffic by splitting the data into two clusters: a video cluster and a general cluster.


 Cache

    server cache + frontend cache

    not very helpful for long-tailed videos

 Security

    view hacking - block high frequency requests from a same IP. restrict view count per IP.
                    However there are services to mask or proxy user IP

                Take into consideration engagement metrics like share count, comment count, view time to identify a view hacking


 Web server
    To scale the web server, you can simply have multiple replicas and build a load-balancer on top of them.

    The server is mainly responsible for handling user requests and return response.
    It should have few heavy logics and everything else should be built into separate servers.
    For instance, recommendation should be a separate component to let Python server fetches data from.


 */


public class DesignYoutube {
}
