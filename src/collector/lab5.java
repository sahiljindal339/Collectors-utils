package collector;


import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import codefx.Article;
import collectors_utilits.Collectors_utility;

public class lab5 {

	public static void main(String[] args) {

		Set<Article> articles = Article.readAll();
		
		//Article with Most Authors
		var articleWithMostAuthors=
		articles.stream()
				.max(
					Comparator.comparing(article -> article.getAuthors().size())	
				).get();
		
		System.out.println(articleWithMostAuthors);
		
		
		
		//Article with Most Authors Per Year
		
        Collector<Article, ?, Article> optionalCollector =
				Collectors.filtering(
								article -> article.getInceptionYear() > 1900 ,
								Collectors.collectingAndThen(
															Collectors.maxBy(
																		Comparator.comparing(article -> ((Article) article).getAuthors().size())	
																		) ,
															Optional :: get
															)
						); 
		
		var articleWithMostAuthorsPerYear=
		articles.stream()
				.collect(
						Collectors.groupingBy(
									Article::getInceptionYear,
									optionalCollector
								)
			);
		
		System.out.println(articleWithMostAuthorsPerYear);
		
		
		/*
		 * Implement  same logic with Stream To handle Empty Optional Cases.
		 */
		Collector<Article, ?, Stream<Article>> streamCollector =
				Collectors.filtering(
								article -> article.getInceptionYear() > 1900 ,
								Collectors.collectingAndThen(
															Collectors.maxBy(
																		Comparator.comparing(article -> ((Article) article).getAuthors().size())	
																		) ,
															Optional :: stream
															)
						); 
		
		/*
		 * Moving common Code
		 */                		
                
         Function<Map<Integer, Stream<Article>>, Map<Integer, Article>> emptyStreamValuesRemover = Collectors_utility.removeEmptyStreams();

		
		var articleWithMostAuthorsPerYearStream=
				articles.stream()
						.collect(
				                Collectors.collectingAndThen(
				                					Collectors.groupingBy(
				                							Article::getInceptionYear,
				                							streamCollector
				                							),
				                						emptyStreamValuesRemover
				                						)
				                );
						
						
		
		System.out.println(articleWithMostAuthorsPerYearStream);
	}
}
