package self.ref;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Scope;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class HttpClient {

	private CloseableHttpClient httpClient;

	private RestTemplate restTemplate;

	public HttpClient() {		
		this.httpClient = HttpClientBuilder.create().setConnectionManager(new PoolingHttpClientConnectionManager()).build();

		this.restTemplate = new RestTemplateBuilder()
				.requestFactory(() -> new HttpComponentsClientHttpRequestFactory(this.httpClient)).build();

		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl("http://localhost").pathSegment("api");
		this.restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(uriComponentsBuilder));
	}

}
