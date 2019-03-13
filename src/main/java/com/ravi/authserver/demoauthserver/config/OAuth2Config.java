package com.ravi.authserver.demoauthserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
	
	private String clientId ="myownclientid";
	private String clientSecret = "$2a$10$fJx7RZiRarUSyoXchm37lOPgkmnF4YRUiCvo09t6pTzTqH1HUvYka";//"my-secret";
	
	private String privateKey ="-----BEGIN RSA PRIVATE KEY-----\r\n" + 
			"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\r\n" + 
			"-----END RSA PRIVATE KEY-----\r\n" + 
			"";
	private String publicKey ="ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAACAQDMyKoZXrJCoQyS7xHE/a43XXBoauEuJzTwpa6/UPeZ28ZHygcumIzYL5XnLpfdU1IUiTvdJd/czXVpErfgGWnhlk0IfzwY6fbSNlUmIFQJGrI5PIFlMDl7njYhxZU/mcKW5cER4ti0PiX6DoW9Y5VU3hTmGI3DkAZjDzwdZ2vqFdF2u82LyDz+HdwTxvCg4rQM596Ytg67b4NHqz3YWKfDCdktryOX/dg6Yr8S+Ls5BGwqavzjwlZHhWMQ1UTfpw1yuH8VBi6dP0hV4WXBoNn23QSaCcUUiuGc5AdoVxXr+6VR2tbMLP8RGEndQ3AheH9q0ndJhCH5oIMuMdtfsBh0KgUXoL5TkndvW6HyN+Zzn9HCGlMkOxygq0k+GxraNLAxoZBk3JeK6bW0TrpuJehQmr2fUnNe8zpyRvFj6L03+nAwbwrTH7RXIMlXdQjUPwn0Ke9O9bF8RS2J329axpV12m4cnb2whnlU3JdKWrZsDJoUFa4ktEUEt4T29GDclMP6N0QuvG92xhJCX6kdvOxvmW9hF0DsSy5Q39us2RLWlmtkN9Z2xmMmG856153QOnNKnZUvwRdXl3zDtWIzI+a3SuBrJcLXoXxT45yEM7+fEphthGUe3m/W8ebTxcLz/NMTIZCicFEOlFe7gzh/Ys8zM6xJ8A9PzlT4PhQJnMSzQw== z003yz1d@md1yw7dc\r\n" + 
			"";

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;
	
	@Bean
	public JwtAccessTokenConverter tokenEnhancer() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey(privateKey);
		converter.setVerifierKey(publicKey);
		return converter;
	}
	
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(tokenEnhancer());
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception{
		endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
		.accessTokenConverter(tokenEnhancer());
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer client) throws Exception{
		client.inMemory().withClient(clientId).secret(clientSecret).scopes("read","write")
		.authorizedGrantTypes("password","refresh_token").accessTokenValiditySeconds(120)
		.refreshTokenValiditySeconds(120);
	}
}
