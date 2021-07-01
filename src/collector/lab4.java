package collector;


import static collectors_utilits.Collectors_utility.groupingbyCounting;
import static collectors_utilits.Collectors_utility.maxByEntryValue;

import java.util.Set;
import java.util.stream.Collectors;

import codefx.Article;


public class lab4 {

	public static void main(String[] args) {
		Set<Article> articles = Article.readAll();
		
														
		var numberofArticlePerYear = articles.stream()
				.collect(
						Collectors.collectingAndThen
							(
								groupingbyCounting(Article::getInceptionYear),
								maxByEntryValue()
								)
						);
		
		System.out.println(numberofArticlePerYear);
	}	
}
