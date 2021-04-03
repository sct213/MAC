package poly.service;

import java.util.List;
import java.util.Set;

import poly.dto.ChatDTO;

public interface IChatService {
	
	public Set<String> getRoomList() throws Exception;
	
	public List<ChatDTO> insertChat(ChatDTO pDTO) throws Exception;
	
	public List<ChatDTO> getChat(ChatDTO pDTO) throws Exception;
}
