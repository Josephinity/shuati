package LinkedList;

/**
 * merge two sorted linked lists
 *
 * What would you do if you were behind the schedule
 *
 * You don't know about thing about a the technology you need to work on. You've got a bug to solve. How would you start?
 *
 * Obviously, this question is to identify how you would solve the problem which has not enough data to start.
 * The interviewer expects you to start asking questions about the technology and the bug itself to resolve ambiguity.
 * I would start from the following:
 1) When speaking about technology, does it mean I have application written in that technology?
 If yes, is this application desktop or web? In which part of the application is the bug? ETC.
 2) When said "solve a bug", it meant to fix it or to develop a test-case to find it and document? ETC.
 Every next question is based on what the interviewer already replied to you.
 Usually after 10-12 questions you asked you will be stopped because it would be clear how smart you are in getting data
 you need for work. :)

 Why do you wanna join facebook?


 */
public class MergeSortedLinkedLists {
    public double sqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }
        double left = 1.0;
        double right = x;
        for (int i = 0; i < 1000; i++) {
            double middle = left + (right - left) / 2;
            if (middle * middle == x) {
                return middle;
            } else if (middle * middle < x) {
                left = middle;
            } else {
                right = middle;
            }
        }
        if (right * right < x) {
            return right;
        }
        return left;
    }
}


