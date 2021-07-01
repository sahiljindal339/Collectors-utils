package collector;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import codefx.Article;
import codefx.Author;

public class lab3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set<Article> articles = Article.readAll();
		
		Map<Author, Long> noOfArticlebyAuthor=
		articles.stream()
				.flatMap(article -> article.getAuthors().stream())
				.collect(
						Collectors.groupingBy(
												Function.identity(),
												Collectors.counting()
											)
						);
		
		Map.Entry<Author, Long> maxNoOfArticleByAuthor=
		noOfArticlebyAuthor.entrySet()
							.stream()
							.max(Comparator.comparing
									(
											entry -> entry.getValue()
									)
									).get();
		
		System.out.println(maxNoOfArticleByAuthor);

		
	}

}
