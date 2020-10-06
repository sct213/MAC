// lemma!!!!!
	List<String> lemmaList = new ArrayList<>();
	
	String lemma = (pMap.get("newsLemmas")).toString();
	
	lemma.replaceAll("\\(", "");
	lemma.replaceAll("\\(", "");
	lemma.replaceAll(")", "");
	lemma.replaceAll("[", "");
	lemma.replaceAll("#", "");
	lemma.replaceAll(";", "");
	lemma.replaceAll(",", "");
	lemma.replaceAll(".", "");
	lemma.replaceAll("-", "");
	
	String[] lemmas = lemma.split(" ");
	
	for(int i = 0; i<lemmas.length; i++) {
			
			lemmas[i] = lemmas[i].replaceAll("\\(", "");
			lemmas[i] = lemmas[i].replaceAll(")", "");
			lemmas[i] = lemmas[i].replaceAll("[", "");
			lemmas[i] = lemmas[i].replaceAll("#", "");
			lemmas[i] = lemmas[i].replaceAll(";", "");
			lemmas[i] = lemmas[i].replaceAll(",", "");
			lemmas[i] = lemmas[i].replaceAll(".", "");
			lemmas[i] = lemmas[i].replaceAll("-", "");
			
			lemmaList.add(lemmas[i]);
			log.info(lemmaList.get(i));
	}
	
	obj.put("lemmas", lemmaList);
	
	log.info("------------------------------");
	
		
			while(newsContents.hasNext()) {
				CoreSentence nlpContents = newsContents.next();
				
				String stringNlp = nlpContents.toString();
				
				String stringLemma = stringNlp;
				
				stringNlp += "#";
				// 문장을 끊기 좋게 일부로 넣은 것 
				pList.add(stringNlp);
				
				lemmaTestList = NLPUtil.lemma(stringNlp);
				
				String lemmaString = lemmaTestList.toString();
				
			}
			
			Iterator<String> itSentence = originalSentence.iterator();
					
					while(itSentence.hasNext()) {
						
					}
					// String으로 형변환한 service.newsContents를 String sent에 대입 
					String sent = originalSentence.toString();
					
					List<String> originalSentenceList = new ArrayList<>();
					// String sent를 # 기준으로 나누어 String[]에 대입
					String[] splitSent = sent.split("#");
					
					for(int i = 0; i<=splitSent.length-1; i++) {
						
						originalSentenceList.add(splitSent[i].substring(1));
						
						log.info(originalSentenceList.get(i));
					}