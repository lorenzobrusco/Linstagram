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

import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.ngram.EdgeNGramFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Fields;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

@Entity
@Indexed
@Table(name="hashtag")


@AnalyzerDef(name = "edgeNgram",
tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
filters = {
        @TokenFilterDef(factory = LowerCaseFilterFactory.class), // Lowercase all characters
        @TokenFilterDef(
                factory = EdgeNGramFilterFactory.class, // Generate prefix tokens
                params = {
                        @Parameter(name = "minGramSize", value = "1"),
                        @Parameter(name = "maxGramSize", value = "10")
                }
        )
})

public class Hashtag {

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name = "hashtag", unique = true, nullable = false)

	@Fields({
		  @Field(name = "hashtag", index = Index.YES, store = Store.NO),
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
