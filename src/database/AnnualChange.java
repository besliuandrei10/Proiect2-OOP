package database;

import enums.CityStrategyEnum;

import java.util.ArrayList;

public class AnnualChange {

    public AnnualChange() { }

    private Double newSantaBudget;
    private ArrayList<Gift> newGifts = new ArrayList<>();
    private ArrayList<Child> newChildren = new ArrayList<>();
    private ArrayList<ChildUpdate> childrenUpdates = new ArrayList<>();

    private CityStrategyEnum strategy;

    /**
     *
     * @return
     */
    public CityStrategyEnum getStrategy() {
        return strategy;
    }

    /**
     *
     * @param strategy
     */
    public void setStrategy(CityStrategyEnum strategy) {
        this.strategy = strategy;
    }

    /**
     *
     * @return
     */
    public final Double getNewSantaBudget() {
        return newSantaBudget;
    }

    /**
     *
     * @param newSantaBudget
     */
    public final void setNewSantaBudget(final Double newSantaBudget) {
        this.newSantaBudget = newSantaBudget;
    }

    /**
     *
     * @return
     */
    public ArrayList<Gift> getNewGifts() {
        return newGifts;
    }

    /**
     * @param newGifts
     */
    public void setNewGifts(final ArrayList<Gift> newGifts) {
        this.newGifts = newGifts;
    }

    /**
     * @param gift
     */
    public void addNewGifts(final Gift gift) {
        this.newGifts.add(gift);
    }

    /**
     * @return
     */
    public ArrayList<Child> getNewChildren() {
        return newChildren;
    }

    /**
     *
     * @param child
     */
    public void addNewChildren(final Child child) {
        this.newChildren.add(child);
    }

    /**
     *
     * @param newChildren
     */
    public void setNewChildren(final ArrayList<Child> newChildren) {
        this.newChildren = newChildren;
    }

    /**
     *
     * @return
     */
    public ArrayList<ChildUpdate> getChildrenUpdates() {
        return childrenUpdates;
    }

    /**
     *
     * @param childrenUpdates
     */
    public void setChildrenUpdates(final ArrayList<ChildUpdate> childrenUpdates) {
        this.childrenUpdates = childrenUpdates;
    }

    /**
     *
     * @param update
     */
    public void addChildrenUpdates(final ChildUpdate update) {
        this.childrenUpdates.add(update);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "AnnualChange{"
                +
                "newSantaBudget=" + newSantaBudget
                +
                ", newGifts=" + newGifts
                +
                ", newChildren=" + newChildren
                +
                ", childrenUpdates=" + childrenUpdates
                +
                '}';
    }
}
