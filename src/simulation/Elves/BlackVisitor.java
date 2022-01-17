package simulation.Elves;

import database.Child;
import simulation.Visitor;

public class BlackVisitor implements Visitor {

    /**
     *
     * @param child
     */
    @Override
    public void visit(Child child) {
        double newBudget;
        newBudget = child.getAllocatedBudget() - (child.getAllocatedBudget() * 30 / 100);
        child.setAllocatedBudget(newBudget);
    }
}
