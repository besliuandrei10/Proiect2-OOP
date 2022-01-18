package simulation.Elves;

import common.Constants;
import database.Child;
import simulation.Visitor;

public class BlackVisitor implements Visitor {

    /**
     *
     * @param child
     */
    @Override
    public void visit(final Child child) {
        double newBudget;

        // Urasc ca a trebuit sa pun constantele acolo dar checkstyle-ul imi forteaza mana.

        newBudget = child.getAllocatedBudget()
                - (child.getAllocatedBudget() * Constants.THIRTY / Constants.ONE_HUNDRED);
        child.setAllocatedBudget(newBudget);
    }
}
