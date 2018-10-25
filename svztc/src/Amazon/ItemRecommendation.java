package Amazon;
import java.util.*;
/**
 Early on I prefer to post the interview questions with a simplified description or only present the algorithmic part of it. Because when our students get back to us with the interview experience sharing, it's hard for them to memorize the full description. Therefore often times only the algorithm behind the question is given.
 But somehow a user of this site @concernedCoder gets unhappy with it. So from today on, we will try to recover the original version of the question as much as possible.
 We assure you all of the questions we posted are real  questions that you have a good chance to come across during a coding interview. Anyone who has experience in a coding interview should be able to see that.

 Here is the full description of a recent Amazon OA question. The reason why we are able to provide the full description is that this is an online assessment. But for an on-site interviews it's almost impossible to recover the problem perfectly.

 Title: Item Recommendations
 Amazon wants to recommend items to a customer who has just made a purchase. Amazon's recommendation algorithm is based on item 'connection'.
 Two items are 'strongly connected' if a single customer bought both of them. Two items are 'weakly connected' if they are both strongly and weakly connected to some other third item.

 Your  task is to determine the number of the items strongly and weakly connected to a given item.

 You are provided the item id represented as a string, as well as the list of customer purchases represented as an array of strings. Each element in the array consists of a customer id(represented as a string) and an item id (also represented as a string). The two ids are separated by a colon. For example, if they element in the array is "ABJA:Z42G" then that means customer ABJA purchased item Z42G.
 Your output consists of an array, where the first element in the array represents the number of items strongly connected to the provided item id and the second element represents the number of items weakly connected to the provided item id.
 For example, if you were provided with the following input:
 determineRecommendation("ABC",["first:ABC", "first:HIJ", "sec:HIJ", "sec:KLM", "third:NOP", "fourth:ABC", "fourth:QRS", "first"DEF", "fifth:KLM", "fifth:TUV"])

 You would return the following array:
 [3, 2]
 since ABC is strongly connected to 3 items: DEF, HIJ and QRS and is weakly connected to 2 items: KLM and TUV
 */

class Item {

    String label;
    Set<Customer> customers;

    public Item(String s) {
        customers = new HashSet<>();
        label = s;
    }
}

class Customer {

    String id;
    Set<Item> items;

    public Customer(String s) {
        items = new HashSet<>();
        id = s;
    }

}

public class ItemRecommendation {

    public int[] linkageCount(String obj, String[] purchases) {

        Map<String, Item> items = new HashMap<>();
        Map<String, Customer> customers = new HashMap<>();

        for(String purchase: purchases) {
            String[] pair = purchase.split(":");
            String customer = pair[0], item = pair[1];
            if(!customers.containsKey(customer)) {
                customers.put(customer, new Customer(customer));
            }
            if(!items.containsKey(item)) {
                items.put(item, new Item(item));
            }
            customers.get(customer).items.add(items.get(item));
            items.get(item).customers.add(customers.get(customer));
        }

        Set<String> strongly_connected = new HashSet<>();
        Set<String> weakly_connected = new HashSet<>();

        for(Customer customer: items.get(obj).customers) {
            for(Item item: customer.items) {
                strongly_connected.add(item.label);
            }
        }

        for(String item: strongly_connected) {
            for(Customer customer: items.get(item).customers) {
                for(Item i: customer.items) {
                    if(!strongly_connected.contains(i.label)) {
                        weakly_connected.add(i.label);
                        dfsFindWeakConnetions(items, customers, strongly_connected, weakly_connected, i.label);
                    }
                }
            }
        }
        return new int[] {strongly_connected.size(), weakly_connected.size()};  //result
    }

    private void dfsFindWeakConnetions(Map<String, Item> items, Map<String, Customer> customers     //DFS to search for strongly connected items
            , Set<String> strongly_connected, Set<String> weakly_connected, String item) {
        for(Customer customer: items.get(item).customers) {
            for(Item i: customer.items) {
                if(!strongly_connected.contains(i.label) && !weakly_connected.contains(i.label)) {
                    weakly_connected.add(i.label);
                    dfsFindWeakConnetions(items, customers, strongly_connected, weakly_connected, i.label);
                }
            }
        }
    }
}
