package SystemDesign;

/**

 front-end X
 back-end *
    Data model -schema/read/writes trade-offs *

    2 objects in model - users and feeds

    for users,store userID, name, registration date and so on..

    for feeds, feedID, type, content, metadata and so on.. support image/vids

        Relational Database - user-feed relation and friend relation

            user-feed:   user_feed table that maps userID to feedID

            friend relation:    adjacency list is a common approach.



    Feed ranking

    Feed publishing - scalability



 */
public class NewsFeed {


}
