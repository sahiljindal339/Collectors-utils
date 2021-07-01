package collector;

import java.util.Comparator;
import java.util.stream.Collectors;

import codefx.Article;

public class lab2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * create HashMap
		 */
		var articles = Article.readAll();
		var numberofArticlePerYear = articles.stream()
							.collect(
									Collectors.groupingBy(
											Article::getInceptionYear,
											Collectors.counting()
											)
									);
		var maxNumberofArticlePerYear = numberofArticlePerYear.entrySet()
																.stream()
																.max(Comparator.comparing
																		(
																				entry -> entry.getValue()
																		)
																).get();
		
		System.out.println(maxNumberofArticlePerYear);
		
		
		//Note: it is possible More than One Max.
		
		var allMaxesNumberofArticlePerYear = numberofArticlePerYear.entrySet()
																	.stream()
																	.collect(
																				Collectors.groupingBy(entry -> entry.getValue())
																			)
																	.entrySet()
																	.stream()
																	.max(Comparator.comparing
																			(
																				entry -> entry.getKey()
																			)
																	).get();
		System.out.println(allMaxesNumberofArticlePerYear);
		
		
	}

}
