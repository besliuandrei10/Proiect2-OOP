package simulation.Cities;

import database.Child;
import enums.Cities;

import java.util.Comparator;
import java.util.LinkedList;

public class City {

    private Cities name;
    private LinkedList<Child> childList;
    private Double averageScore;

    /**
     *
     * @param name
     */
    public City(Cities name) {
        this.name = name;
        this.childList = new LinkedList<>();
    }

    static class IDComparator implements Comparator<Child> {

        @Override
        public int compare(Child o1, Child o2) {
            return (-1) * o1.getId().compareTo(o2.getId());
        }
    }

    /**
     *
     * @return
     */
    public Double getAverageScore() {
        return averageScore;
    }

    /**
     *
     * @return
     */
    public Cities getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(Cities name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public LinkedList<Child> getChildList() {
        return childList;
    }

    /**
     *
     * @param child
     */
    public void addToChildList(Child child) {
        childList.add(child);
    }

    /**
     *
     */
    public void calculateAverageScore() {
        Double sum = 0.0;
        int count = childList.size();

        for (Child child : childList) {
            sum += child.getAverageScore();
        }

        averageScore = sum / count;
        this.childList.sort(new IDComparator());
    }
}
