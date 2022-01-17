package simulation;

import database.Child;

public interface Visitor {
    void visit(Child child);
}
