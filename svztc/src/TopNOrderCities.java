/**

 */
import java.util.*;

class CityOrder {
    private String city;
    int orderCount;

    public CityOrder(String city) {
        this.city = city;
        orderCount = 1;
    }
}
public class TopNOrderCities {

    private PriorityQueue<CityOrder> topN = new PriorityQueue<>((o1, o2) -> (o1.orderCount - o2.orderCount));   //keep top n cities with most orders in here
    private Set<CityOrder> minOrderInTopNCities = new HashSet<>();
    private Map<String, CityOrder> cityOrderMap = new HashMap<>();

    public TopNOrderCities(int n, String[] orders) {
        for(String city: orders) {
            CityOrder cityOrder;

            if(!cityOrderMap.containsKey(city)) {
                cityOrder = new CityOrder(city);
                cityOrderMap.put(city, cityOrder);

                if(topN.size() < n) {
                    topN.add(cityOrder);
                    cityOrderMap.put(city, cityOrder);
                } else if(topN.peek().orderCount == 1) {
                    minOrderInTopNCities.add(cityOrder);
                }
            } else {
                cityOrder = cityOrderMap.get(city);
            }

            CityOrder min = topN.peek();
            if(cityOrder.orderCount == min.orderCount) {
                minOrderInTopNCities.add(cityOrder);
            } else if(cityOrder.orderCount == min.orderCount + 1) {

                if(minOrderInTopNCities.contains(cityOrder)) {
                    minOrderInTopNCities.remove(cityOrder);
                    topN.add(cityOrder);
                }
                CityOrder temp;
                if(topN.size() == n + 1) {
                     temp = topN.poll();
                     if(topN.peek().orderCount == cityOrder.orderCount) {
                         minOrderInTopNCities = new HashSet<>();
                     } else {
                         minOrderInTopNCities.add(temp);
                     }
                }
                if(topN.peek().orderCount == cityOrder.orderCount) {
                    minOrderInTopNCities = new HashSet<>();
                }

                min = topN.poll();
                if(cityOrder.orderCount == topN.peek().orderCount && minOrderInTopNCities.contains(cityOrder)) {
                    minOrderInTopNCities.remove(cityOrder);
                    topN.add(cityOrder);

                }
            }
        }
    }


}
