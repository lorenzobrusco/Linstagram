package it.unical.linstagram.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.lucene.analysis.core.KeywordTokenizerFactory;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.core.StopFilterFactory;
import org.apache.lucene.analysis.miscellaneous.WordDelimiterFilterFactory;
import org.apache.lucene.analysis.ngram.EdgeNGramFilterFactory;
import org.apache.lucene.analysis.pattern.PatternReplaceFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.search.annotations.*;




@Entity
@Indexed
@Table(name="hashtag")

@AnalyzerDefs({

	@AnalyzerDef(name = "autocompleteEdgeAnalyzer",

			// Split input into tokens according to tokenizer
			tokenizer = @TokenizerDef(factory = KeywordTokenizerFactory.class),

			filters = {
					// Normalize token text to lowercase, as the user is unlikely to
					// care about casing when searching for matches
					@TokenFilterDef(factory = PatternReplaceFilterFactory.class, params = {
							@Parameter(name = "pattern",value = "([^a-zA-Z0-9\\.])"),
							@Parameter(name = "replacement", value = " "),
							@Parameter(name = "replace", value = "all") }),
					@TokenFilterDef(factory = LowerCaseFilterFactory.class),
					@TokenFilterDef(factory = StopFilterFactory.class),
					// Index partial words starting at the front, so we can provide
					// Autocomplete functionality
					@TokenFilterDef(factory = EdgeNGramFilterFactory.class, params = {
							@Parameter(name = "minGramSize", value = "3"),
							@Parameter(name = "maxGramSize", value = "50") }) }),
	@AnalyzerDef(name = "standardAnalyzer",

	//Split input into tokens according to tokenizer
	tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),

	filters = {
			// Normalize token text to lowercase, as the user is unlikely to
			// care about casing when searching for matches
			@TokenFilterDef(factory = WordDelimiterFilterFactory.class),
			@TokenFilterDef(factory = LowerCaseFilterFactory.class),
			@TokenFilterDef(factory = PatternReplaceFilterFactory.class, params = {
					@Parameter(name = "pattern", value = "([^a-zA-Z0-9\\.])"),
					@Parameter(name = "replacement", value = " "),
					@Parameter(name = "replace", value = "all") }),
			
	}), // Def
	@AnalyzerDef(name = "autocompleteAnalyzer",

	// Split input into tokens according to tokenizer
	tokenizer = @TokenizerDef(factory = KeywordTokenizerFactory.class),

	filters = {
			// Normalize token text to lowercase, as the user is unlikely to
			// care about casing when searching for matches
			@TokenFilterDef(factory = WordDelimiterFilterFactory.class),
			@TokenFilterDef(factory = LowerCaseFilterFactory.class),
			@TokenFilterDef(factory = StopFilterFactory.class),
			// Index partial words starting at the front, so we can provide
			// Autocomplete functionality
			@TokenFilterDef(factory = EdgeNGramFilterFactory.class, params = {
					@Parameter(name = "minGramSize", value = "1"),
					@Parameter(name = "maxGramSize", value = "50") }) })
})



public class Hashtag {

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name = "hashtag", unique = true, nullable = false)
//	@Fields({
//		  @Field(name = "hashtag", index = Index.YES, store = Store.YES,
//		analyze = Analyze.YES, analyzer = @Analyzer(definition = "standardAnalyzer")),
//		  @Field(name = "edgeNGramHashtag", index = Index.YES, store = Store.NO,
//		analyze = Analyze.YES, analyzer = @Analyzer(definition = "autocompleteEdgeAnalyzer")),
//		})
	
	@Fields({
		  @Field(name = "hashtag", index = Index.YES, store = Store.YES),
		  @Field(name = "edgeNGramHashtag", index = Index.YES, store = Store.NO,
		analyze = Analyze.YES, analyzer = @Analyzer(definition = "autocompleteAnalyzer")),
		})
	private String hashtag;

	@Column(name="counter")
	private int count;

	@ManyToMany(mappedBy="hashtags")
	@Cascade(value=CascadeType.ALL)
	private Set<Post> posts = new HashSet<Post>();

	public Hashtag() {
	}

	public Hashtag(String hashtag) {
		this.hashtag = hashtag;
		this.count = 1;
	}

	public Hashtag(String hashtag, int count) {
		this.hashtag = hashtag;
		this.count = count;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
