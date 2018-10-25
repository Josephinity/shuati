package LinkedIn;
import java.util.List;
import java.util.Random;
/**
 randomly select a weighted item from a list
 */

class WeightedItem {
    Object obj;
    int weight;
}
public class WeightedRandomSelect {

    public WeightedItem random(List<WeightedItem> items) {
        double totalWeight = 0;
        WeightedItem selected = null;
        Random rand = new Random();

        for(WeightedItem item: items) {
            double r = rand.nextDouble() * (totalWeight + item.weight);
            if(r >= totalWeight) {
                selected = item;
                totalWeight +=item.weight;
            }
        }
        return selected;
    }


    public Integer random(int[] array, int[] freq) {
        int totalFreq = 0;
        Integer selected = null;
        Random rand = new Random();

        for(int i = 0; i < array.length; i++) {
            int r = rand.nextInt() * (totalFreq + freq[i]);
            if(r >= totalFreq) {
                selected = array[i];
                totalFreq += freq[i];
            }
        }
        return selected;
    }


}
