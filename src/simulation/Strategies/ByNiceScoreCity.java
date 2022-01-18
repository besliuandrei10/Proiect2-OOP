package simulation.Strategies;

import database.Child;
import database.Database;
import enums.Cities;
import simulation.Cities.City;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ByNiceScoreCity implements GiftStrategy {

    private final LinkedHashMap<Cities, City> cityMap = new LinkedHashMap<>();

    static class CityComparator implements Comparator<Map.Entry<Cities, City>> {

        @Override
        public int compare(final Map.Entry<Cities, City> o1, final Map.Entry<Cities, City> o2) {
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
    public void executeStrategy() {

        List<Map.Entry<Cities, City>> sortedCities = createCityMap();

        for (Map.Entry<Cities, City> entry : sortedCities) {
            GiftDistributor giftAssigner = new GiftDistributor();
            giftAssigner.distributeGifts(entry.getValue().getChildList());
        }

    }
}
