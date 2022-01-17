package database;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

public class Database {
    private static final Database DATABASE = new Database();

    private Long numberOfYears;
    private Double santaBudget;

    private LinkedList<Child> childList = new LinkedList<>();
    private ArrayList<Gift> giftList = new ArrayList<>();
    private ArrayList<AnnualChange> changes = new ArrayList<>();

    /**
     * @return
     */
    public ArrayList<AnnualChange> getChanges() {
        return changes;
    }

    /**
     * @param changes
     */
    public void setChanges(final ArrayList<AnnualChange> changes) {
        this.changes = changes;
    }

    /**
     * @param change
     */
    public void addChange(final AnnualChange change) {
        this.changes.add(change);
    }

    /**
     * @return
     */
    public Long getNumberOfYears() {
        return numberOfYears;
    }

    /**
     * @param numberOfYears
     */
    public void setNumberOfYears(final Long numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    /**
     * @return
     */
    public ArrayList<Gift> getGiftList() {
        return giftList;
    }

    /**
     * @return
     */
    public Double getSantaBudget() {
        return santaBudget;
    }

    /**
     * @param santaBudget
     */
    public void setSantaBudget(final Double santaBudget) {
        this.santaBudget = santaBudget;
    }

    /**
     * @return
     */
    public LinkedList<Child> getChildList() {
        return childList;
    }

    /**
     * @param child
     */
    public void addToChildList(final Child child) {
        this.childList.add(child);
    }

    /**
     * @param child
     */
    public void removeFromChildList(final Child child) {
        this.childList.remove(child);
    }

    /**
     * @param gift
     */
    public void addToGiftsList(final Gift gift) {
        this.giftList.add(gift);
    }

    /**
     * @param id
     * @return
     */
    public Child childByID(final Long id) {
        for (Child child : this.childList) {
            if (child.getId().equals(id)) {
                return child;
            }
        }
        return null;
    }

    /**
     *
     */
    public void clearDatabase() {
        this.childList.clear();
        this.giftList.clear();
        this.numberOfYears = 0L;
        this.santaBudget = 0.0;
        this.changes.clear();
    }

    public Database() { }

    /**
     * @return
     */
    public static Database getInstance() {
        return DATABASE;
    }

    public void sortChildList() {
        this.childList.sort(new Comparator<Child>() {
            @Override
            public int compare(Child o1, Child o2) {
                return o1.getId().compareTo(o2.getId());
            }
        });
    }
}
