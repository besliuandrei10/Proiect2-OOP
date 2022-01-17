package simulation.Strategies;

import database.Child;
import database.Database;
import database.Gift;
import enums.Category;
import enums.Cities;
import simulation.Cities.City;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class byNiceScoreCity implements GiftStrategy {

    private final LinkedHashMap<Cities, City> cityMap = new LinkedHashMap<>();
    private List<Map.Entry<Cities, City>> sortedMap;

    static class CityComparator implements Comparator<Map.Entry<Cities, City>> {

        @Override
        public int compare(Map.Entry<Cities, City> o1, Map.Entry<Cities, City> o2) {
            if (o1.getValue().getAverageScore().equals(o2.getValue().getAverageScore())) {
                return o1.getValue().getName().toString().compareTo(
                        o2.getValue().getName().toString());
            }
            return (-1) * o1.getValue().getAverageScore().compareTo(
                    o2.getValue().getAverageScore());
        }
    }

    /**
     *
     * @return
     */
    private List<Map.Entry<Cities, City>> createCityMap() {
        // populate with values
        for (Child child : Database.getInstance().getChildList()) {
            if (cityMap.containsKey(child.getCity())) {
                cityMap.get(child.getCity()).addToChildList(child);
            } else {
                City newCity = new City(child.getCity());
                newCity.addToChildList(child);
                cityMap.put(child.getCity(), newCity);
            }
        }

        // calculate average scores

        for (Map.Entry<Cities, City> entry : cityMap.entrySet()) {
            entry.getValue().calculateAverageScore();
        }

        // sort
        List<Map.Entry<Cities, City>> sortedMap =
                new ArrayList<Map.Entry<Cities, City>>(cityMap.entrySet());
        sortedMap.sort(new CityComparator());
        return sortedMap;

    }

    /**
     *
     */
    @Override
    public void distributeGifts() {

        sortedMap = createCityMap();

        for (Map.Entry<Cities, City> entry : sortedMap) {
            for (Child child : entry.getValue().getChildList()) {
                Double remainingBudget = child.getAllocatedBudget();

                for (Category category : child.getGiftsPreferences()) {
                    Gift selectedGift = null;
                    Double selectedGiftPrice = Double.MAX_VALUE;

                    for (Gift gift : Database.getInstance().getGiftList()) {
                        if (gift.getQuantity() > 0) {
                            if (gift.getCategory().equals(category)) {
                                if (selectedGiftPrice > gift.getPrice()) {
                                    selectedGift = gift;
                                    selectedGiftPrice = gift.getPrice();
                                }
                            }
                        }
                    }
                    if (remainingBudget - selectedGiftPrice > 0 && selectedGift != null) {
                        child.addToReceivedGifts(selectedGift);
                        remainingBudget -= selectedGiftPrice;
                        selectedGift.setQuantity(selectedGift.getQuantity() - 1);
                    }
                }
            }
        }

    }
}
