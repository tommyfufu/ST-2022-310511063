import com.sun.jdi.connect.Connector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collection;
import java.util.PriorityQueue;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PriorityQueueTest {
    static Stream<Arguments> streamProvider()
    {
        int[] arr1 = {1,3,5,4,2};
        int[] arr2 = {5,4,3,2,1};
        int[] arr3 = {1,3,5,2,4};
        int[] arr4 = {2,4,1,3,5};
        int[] arr5 = {5,3,4,1,2};
        int[] correct_array = {1,2,3,4,5};
        return Stream.of(
                Arguments.of(arr1,correct_array),
                Arguments.of(arr2,correct_array),
                Arguments.of(arr3,correct_array),
                Arguments.of(arr4,correct_array),
                Arguments.of(arr5,correct_array)
        );

    }
    @ParameterizedTest(name = "#{index} - Test with Argument={0},{1}")
    @MethodSource("streamProvider")
    public void PriorityQueue_RunTest(int[] random_array, int[] correct_array){
        PriorityQueue<Integer> test = new PriorityQueue<Integer>();
        int index = 0; //for cnt index
        Integer s;  // for PriorityQueue.poll();
        int[] result = new int[random_array.length];

        //random array add to PQ
        for(index=0;index<random_array.length;index++)
        {
            test.add(random_array[index]);
        }
        for(index=0;index<random_array.length;index++)
        {
            result[index] = test.poll();
        }
        //get PQ result
        assertArrayEquals(correct_array,result);
    }
    @Test
    public void whenExceptionThrow_thenInitialCapacityNotGreaterThanone(){
        Exception exception = assertThrows(
                IllegalArgumentException.class,() -> {
                    PriorityQueue<Integer> test = new PriorityQueue<Integer>(0);
        });
        }
    @Test
    public void whenExceptionThrow_thenOfferEisNull() {
        Exception exception = assertThrows(
                NullPointerException.class, () -> {
            PriorityQueue<Integer> test = new PriorityQueue<Integer>();
            test.offer(null);
        });
    }
    @Test
    public void whenExceptionThrow_thenAddNull() {
        Exception exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    PriorityQueue<Integer> test = new PriorityQueue<Integer>(0);
                    test.add(null);
                });
    }
}
