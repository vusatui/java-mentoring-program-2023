import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vusatui.extramile.algorithms.BinarySearch;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
class BinarySearchTest {

    @Test
    void testSuccessfulSearch() {
        List<Integer> src = new ArrayList<>() {
            {
                add(1);
                add(2);
                add(3);
                add(4);
                add(5);
            }
        };

        Optional<Integer> result = BinarySearch.search(src, 4);

        assertTrue(result.isPresent());
    }

    @Test
    void testUnsuccessfulSearch() {
        List<Integer> src = new ArrayList<>() {
            {
                add(1);
                add(2);
                add(3);
                add(4);
                add(5);
            }
        };

        Optional<Integer> result = BinarySearch.search(src, 10);

        assertFalse(result.isPresent());
    }
}
