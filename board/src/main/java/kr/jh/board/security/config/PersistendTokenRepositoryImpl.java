package kr.jh.board.security.config;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import kr.jh.board.security.domain.PersistentLogins;
import kr.jh.board.security.repository.TokenRepository;

public class PersistendTokenRepositoryImpl implements PersistentTokenRepository{

	private static final Logger log = LoggerFactory.getLogger(PersistendTokenRepositoryImpl.class);
	
	@Autowired
	TokenRepository tokenRepository;
	
	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		// TODO Auto-generated method stub
		PersistentLogins newToken = new PersistentLogins();
		newToken.setUsername(token.getUsername());
		newToken.setSeries(token.getSeries());
		newToken.setToken(token.getTokenValue());
		newToken.setLastUsed(token.getDate());
		tokenRepository.save(newToken);
		
	}

	@Override
	public void updateToken(String series, String tokenValue, Date lastUsed) {
		// TODO Auto-generated method stub
		PersistentLogins token = tokenRepository.findBySeries(series);
		token.setToken(tokenValue);
		token.setLastUsed(lastUsed);
		tokenRepository.save(token);
	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		// TODO Auto-generated method stub
		PersistentLogins token = tokenRepository.findBySeries(seriesId);
		
		PersistentRememberMeToken persistentRememberMeToken = 
				new PersistentRememberMeToken(token.getUsername(), seriesId, token.getToken(), token.getLastUsed());
		
		return persistentRememberMeToken;
	}

	@Override
	public void removeUserTokens(String username) {
		// TODO Auto-generated method stub
		tokenRepository.deleteByUsername(username);
	}

	
}
