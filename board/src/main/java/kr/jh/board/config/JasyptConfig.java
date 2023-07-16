package kr.jh.board.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {

	@Bean(name = "jasyptStringEncryptor")
	public StringEncryptor stringEncryptor() {
		String key = "daom";
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setPassword(key);					// 암호화 키
		config.setAlgorithm("PBEWithMD5AndDES");	// 암호화 키 알고리즘
		config.setKeyObtentionIterations("1000");	// 반복 해싱 횟수
		config.setPoolSize("5");
		config.setProviderName("SunJCE");
		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
		config.setStringOutputType("base64");
		encryptor.setConfig(config);
		return encryptor;
	}
	
	
}
