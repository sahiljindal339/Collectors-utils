package collectors_utilits;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class Collectors_utility<K, V> {

	public static <T,U> Collector<T, ?, Map<U, Long>> groupingbyCounting(Function<T,U> classifier) {
		return Collectors.groupingBy(
																	classifier,
																	Collectors.counting()
																	);
	}

	public static <K, V extends Comparable<? super V>> Function<Map<K, V>, Entry<K, V>> maxByEntryValue() {
		return maxBy(Map.Entry.<K, V>comparingByValue());
	}

	public static <K, V extends Comparable<? super V>> Function<Map<K, V>, Entry<K, V>> maxBy(Comparator<? super Entry<K, V>> comparator) {
		return map -> map.entrySet()
		.stream()
		.max(comparator)
		.get();
	}
	
	public static <K,V >Collector<Map.Entry<K,V>, ? , ?> toNaturalMap(){
		return Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue);		
	}
	
	public static <T> Collector<T, ?, Map<T, Long>>
    groupingBySelfAndCounting() {
        return groupingbyCounting(Function.identity());
    }
	
	Function<Entry<K, Stream<V>>, Stream<Entry<K, V>>> emptyStreamsRemover =
    		entry -> entry.getValue().map(value -> Map.entry(entry.getKey(), value));
    		
    
    public static <K, V> Function<Map<K, Stream<V>>, Map<K, V>>
    	    removeEmptyStreams() {
    	        return map -> map
    	                .entrySet().stream()
    	                .flatMap(
    	                        entry -> entry.getValue().map(e -> Map.entry(entry.getKey(), e))
    	                )
    	                .collect(toNaturalMap());
    	    }

}
