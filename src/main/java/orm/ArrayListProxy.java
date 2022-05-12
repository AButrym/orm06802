package orm;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ArrayListProxy<T> extends ArrayList<T> {
    Object parent;
    Field mappedBy;

    public ArrayListProxy(Object parent, Field mappedBy) {
        this.parent = parent;
        mappedBy.setAccessible(true);
        this.mappedBy = mappedBy;
    }

    @Override
    public boolean add(T t) {
        // set in t the property referenced in "mappedBy" to parent
        mappedBy.set(t, parent);
        return super.add(t);
    }
}
