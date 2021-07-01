package collector;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import codefx.Article;

public class lab {

	public static void main(String[] args) {
		/*
		 * Article is POJO-Model Class
		 * readAll() - return all distinct value of Article Object.
		 */
		Set<Article> articles = Article.readAll();
        var count = articles.stream()
        							.count();
        System.out.println(count);
        
        final var minYear =
        				articles.stream()
        						.map(Article::getInceptionYear)
        						//.min(Comparator.naturalOrder()) --> here we are using Stream API 
        						.collect(Collectors.minBy(Comparator.naturalOrder()))   // Using collector API
        						.get();
        
        System.out.println("minYear  "+minYear);
        
        /*
         * Summary Statistics : Collector work only on INT Stream.
         */
        var intSummaryStatistics = 
        							articles.stream()
        									.mapToInt(Article::getInceptionYear)
        									.summaryStatistics();
        
        System.out.println(intSummaryStatistics);
        
        /*
         * 1960 Article to print
         */
        
        var article1982 = 
        		articles.stream()
        			.filter(s -> s.getInceptionYear() == 1982)
        			.map(Article::getTitle)
                    .collect(Collectors.joining(", "));
        
        System.out.println(article1982);
        
        List.of(article1982).forEach(System.out::print);
 
	}

}
