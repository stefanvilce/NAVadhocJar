package no.nav.adhoct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdhocTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdhocTestApplication.class, args);
	}
	
	
	/*
	 * NU mai pot folosi astea. Nu imi mai trebuie pentru ca nu folosesc deloc redirectarea de la http la https
	 * 
	 * 
	// Let's configure additional connector to enable support for both HTTP and HTTPS
	 * https://www.javadevjournal.com/spring-boot/how-to-enable-http-https-in-spring-boot/
	 * 
		@Bean
		public ServletWebServerFactory servletContainer() {
			TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
			tomcat.addAdditionalTomcatConnectors(createStandardConnector());
			return tomcat;
		}

		private Connector createStandardConnector() {
			Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
			connector.setPort(8104);
			return connector;
		}
	 */
	
	/*
	@Bean
	public ServletWebServerFactory servletContainer() {
	    TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
	        @Override
	        protected void postProcessContext(Context context) {
	            SecurityConstraint securityConstraint = new SecurityConstraint();
	            securityConstraint.setUserConstraint("CONFIDENTIAL");
	            SecurityCollection collection = new SecurityCollection();
	            collection.addPattern("/*");
	            securityConstraint.addCollection(collection);
	            context.addConstraint(securityConstraint);
	        }
	    };
	    tomcat.addAdditionalTomcatConnectors(redirectConnector());
	    return tomcat;
	}

	private Connector redirectConnector() {
	    Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
	    System.out.println("Schema: " + connector.getScheme());
	    //connector.setScheme("https");
	    //connector.setPort(8081);
	    //connector.setSecure(false);
	    //connector.setRedirectPort(8100);
	    
	    connector.setPort(8101);
	    connector.setRedirectPort(8100);
	    return connector;
	}
	*/
	
	
	
}
