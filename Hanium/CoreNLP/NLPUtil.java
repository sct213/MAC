package poly.util;

import java.util.Iterator;
import java.util.Properties;

import org.apache.log4j.Logger;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class NLPUtil {
	
	public static Iterator<CoreSentence> sentence(String str) {
		
		Properties props = new Properties();
		
		props.setProperty("annotators", "tokenize,ssplit,pos,lemma");
		
		props.setProperty("coref.algorithm", "neural");
		
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		
		CoreDocument doc = new CoreDocument(str);
		
		pipeline.annotate(doc);
		
		Iterator<CoreSentence> it = doc.sentences().iterator();
		
		return it;
	}

	public static void main(String[] args) {
		
		Properties props = new Properties();
		
		props.setProperty("annotators", "tokenize,ssplit,pos,lemma");
		
		props.setProperty("coref.algorithm", "neural");
		
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		
		CoreDocument doc = new CoreDocument("Hello, myname is adam. I have two sisters. I went to California");
		
		pipeline.annotate(doc);
		
		Iterator<CoreSentence> it = doc.sentences().iterator();
		
		
//		while(it.hasNext()) {
//			
//			CoreSentence sent = it.next();
//			
//			System.out.println(sent.text());
//			
//			System.out.println(sent.tokens().get(0).originalText());
//			
//			System.out.println(sent.tokens().get(0).index());
//			
//			System.out.println(sent.lemmas());
//			
//			
//		}
		
		
		
		
	}
}