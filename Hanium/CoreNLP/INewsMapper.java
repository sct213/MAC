package poly.persistance.mapper;

import java.util.Iterator;

import config.Mapper;
import edu.stanford.nlp.pipeline.CoreSentence;
import poly.dto.NewsDTO;

@Mapper("NewsMapper")
public interface INewsMapper {

	int InsertNewsInfo(NewsDTO nDTO)throws Exception;

	NewsDTO getNewsInfoFromDB(NewsDTO nDTO);
	
}
