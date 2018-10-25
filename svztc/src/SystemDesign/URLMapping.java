package SystemDesign;

/**
 Design a url shortener.
 Assume our website has 1 billions of subsites that each is linked with a long url.
 It is given to you that all URLs are stored in database and every URL has an associated integer id.
 For instance, 'www.yelp.com/japanese-tapas-restaurant-cupertino?page=2'
 convert to - 'www.tinyurl.com/abc3hh'

 */

/**
 shorten url charset {
    ['A'-'Z'] -> 26
    ['a'-'z'] -> 26
    ['0'-'9'] -> 10
 = 62
 }

 a server can handle 2000 requests/per sec
 * */
public class URLMapping {

    static char[] map = new char[62];
    static {
        for(int i = 0; i < 26; i++) {
            map[i] = (char)('A' + i);
            map[i + 26] = (char)('a' + i);
        }
        for(int i = 0; i < 10; i++) {
            map[i + 52] = (char)('0' + i);
        }
    }
    public static void main(String[] args) {
        String url = shortenID(0);
        System.out.println(url);
        System.out.println(getURLID(url));
    }

    public static String shortenID(int id) {
        if(id == 0) return "A";
        StringBuilder url = new StringBuilder();
        while(id > 0) {
            url.append(map[id % 62]);
            id /= 62;
        }
        return url.reverse().toString();
    }

    public static int getURLID(String url) {
        int id = 0;
        for(char c: url.toCharArray()) {
            if(c >= 'A' && c <= 'Z') {
                id = id * 62 + c - 'A';
            } else if(c >= 'a' && c <= 'z') {
                id = id * 62 + c - 'a' + 26;
            } else {
                id *= id * 62 + c - '0' + 52;
            }
        }
        return id;
    }

}
