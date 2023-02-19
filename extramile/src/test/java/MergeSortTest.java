import static org.junit.jupiter.api.Assertions.assertTrue;

import com.vusatui.extramile.algorithms.MergeSort;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import utils.TestUtils;

@Testable
class MergeSortTest {

    @Test
    void testMergeSort() {
        int[] src = {1, 10, 40, 30, 50 , 2, 3, 4, 6};

        MergeSort.sort(src);

        assertTrue(TestUtils.isSorted(src));
    }
}
