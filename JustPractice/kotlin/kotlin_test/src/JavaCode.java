import java.util.Collection;
import java.util.Iterator;

/**
 * Created by MewX on 7/24/2016.
 */

public class JavaCode {
    public String toJSON(Collection<Integer> collection) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator<Integer> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            sb.append(element);
            if (iterator.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}