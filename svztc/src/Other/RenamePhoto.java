package Other;

/**
 John likes to travel. He has visited a lot of cities over many years. Whenever he visits a city, he takes a few photos and saves them on his computer. Each photo has a name with an extension ("jpg , "png or "jpeg") and there is a record of the name of the city where the photo was taken and the time and date the photo; for example: "photo. jpg, Warsaw, 2013—09—05 14 : 08: 15".

 John notices that his way of filing photos on his computer has become a mess. He wants to reorganize the photos. First he decides to group the photos by city, then, within each such group, sort the photos by the time they were taken and finally assign consecutive natural numbers to the photos, starting from 1 . Afterwards he intends to rename all the photos. The new name of each photo should begin with the name of the city followed by the number already assigned to that photo. The number of every photo in each group should have the same length (equal to the length of the largest number in this group); thus, John needs to add some leading zeros to the numbers. The new name of the photo should end with the extension, which should remain the same. Your task is to help John by finding the new name of each photo. Each of John's photos has the format: “<<photoname>>.<<extension>>, <<city_name>>, yyyy—mm—dd hh:n-m: ss , where "<<photoname>>", "<<extension>>" and "<<city_name>>" consist only of letters of the English alphabet and supply the name of the photo, the file name extension and the city name, respectively.

 Write a function:

 string solution (string S) ;

 that, given a string representing the list of M photos, returns the string representing the list of the new names of all photos (the order of photos should stay the same).


 For example, given a string:


 photo. jpg, Warsaw, 2013—09—05 14:08:15

 john.png, London, 2015—06—20 15:13:22

 my-Friends.png, Warsaw, 2013—09—05 14:07:13

 Eiffel.ppg, Paris, 2015-07-23 08:03:02

 pisatower.jpg, Paris, 2015—07—22 23:59:59

 BOB.ipg, London, 2015-08-05 00:02:03

 notredame.png, Paris, 2015—09—01 12:00:00

 me.jpg, Warsaw, 2013—09—06 15:40: 22

 a.png, Warsaw, 2016—02—13 13:33:50

 b.jpg, Warsaw, 2016-01-02 15:12:22

 c.jpg, Warsaw, 2016-01-02 14:34:30

 d.jpg, Warsaw, 2016-01-02 15:15:01

 e.png, Warsaw, 2016-01-02 09:49:09

 f.png, Warsaw, 2016-01-02 10:5:32

 g.jpg, Warsaw, 2016-02-29 22:13:11



 your function should return:
 Warsaw02.jpg
 London1.png
 Warsaw01.png
 Paris2.jg
 Paris1.jpg
 London2.jpg
 Paris3.jpg
 Warsaw03.jpg
 Warsaw09.jpg
 Warsaw07.jpg
 Warsaw06.jpg
 Warsaw08.jpg
 Warsaw04.jpg
 Warsaw05.jpg
 Warsaw10.jpg
 as there are ten photos taken in Warsaw (numbered from 01 to 10), two photos in London (numbered from 1 to 2) and three photos in as there are ten photos taken in Warsaw (numbered from 01 to 10), two photos in London (numbered from 1 to 2) and three photos in Paris (numbered from 1 to 3).



 The new names of the photos are returned in the same order as in the given string.



 Assume that:



 • M is an integer within the range [1 .. 100];
 • Each year is an integer within the range [2000..2020];
 • Each line of the input string is of the format
 <<photoname>>.<<extension>> , yyyy—mm—dd hh:n-m:ss" and lines are separated with newline characters;
 • Each photo name (without extension}) and city name consists only of at least 1 and at most 20 letters from the English alphabet;
 • Each name of the city starts with a capital letter and is followed by lower case letters;
 • No two photos share the same date and time;
 • Each extension is "jpg", “png" or “jpeg”
 */


import java.util.*;

class Photo implements Comparable<Photo> {

    String location;
    String type;
    int year;
    int month;
    int day;
    int hour;
    int minute;
    int second;
    int index; //Convert the given string to a list (one photo as an element). This is the index of the current photo

    public Photo(String loc, String t, int y, int m, int d, int h, int mm, int s) {

        location = loc;
        type = t;
        year = y;
        month = m;
        day = d;
        hour = h;
        minute = mm;
        second =s;
    }

    public int compareTo(Photo o) {

        if(year != o.year) return year - o.year;
        if(month != o.month) return month - o.month;
        if(day != o.day) return day - o.day;
        if(hour != o.hour) return hour - o.hour;
        if(minute != o.minute) return minute - o.minute;
        return second - o.second;

    }
}

public class RenamePhoto {

    public String solution(String photos) {

        if(photos.isEmpty()) return "";

        String[] list = photos.split("\n");

        //key: location, value: a list of photos taken at the location
        Map<String, List<Photo>> map = new HashMap<>();

        for(int i = 0; i < list.length; i++) {

            Photo photo = createPhoto(list[i]);
            photo.index = i;

            if(!map.containsKey(photo.location)) {
                map.put(photo.location, new ArrayList<>());
            }

            map.get(photo.location).add(photo);

        }

        for(String loc: map.keySet()){

            List<Photo> photo_by_datetime = map.get(loc);

            Collections.sort(photo_by_datetime);

            int size = photo_by_datetime.size();

            for(int i = 0; i < size; i++) {

                //create new name for current photo
                StringBuilder str = new StringBuilder();

                str.append(photo_by_datetime.get(i).location);
                str.append(padZeros(i, size));
                str.append('.');
                str.append(photo_by_datetime.get(i).type);

                //update list with the new name of this photo
                list[photo_by_datetime.get(i).index] = str.toString();

            }

        }


        StringBuilder ret = new StringBuilder();

        for(String str: list) {   //merge new names into a string

            ret.append(str);
            ret.append('\n');

        }

        return ret.substring(ret.length() - 1);

    }


    private String padZeros(int i, int len) {

        StringBuilder number = new StringBuilder();
        number.append(i);

        i = number.length();

        while(i < len) {
            number.insert(0, '0');
            i++;
        }

        return number.toString();
    }

    //create an instance of photo for each photo name given
    private Photo createPhoto(String photo) {

        int i = 0;

        String type = photo.substring(photo.indexOf('.') + 1, i = photo.indexOf(','));

        String location = photo. substring(i + 1, i = photo.indexOf(i + 1, ','));

        int year = Integer.parseInt(photo.substring(i + 1, i = photo.indexOf(i + 1, '-')));

        int month = Integer.parseInt(photo.substring(i + 1, i = photo.indexOf(i + 1, '-')));

        int day = Integer.parseInt(photo.substring(i + 1, i = photo.indexOf(i + 1, ' ')));

        int hour = Integer.parseInt(photo.substring(i + 1, i = photo.indexOf(i + 1, ':')));

        int minute = Integer.parseInt(photo.substring(i + 1, i = photo.indexOf(i + 1, ':')));

        int second = Integer.parseInt(photo.substring(i + 1));

        return new Photo(location, type, year, month, day, hour, minute, second);

    }



}
