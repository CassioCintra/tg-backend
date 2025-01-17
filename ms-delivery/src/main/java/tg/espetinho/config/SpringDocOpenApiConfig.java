package tg.espetinho.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocOpenApiConfig {
   @Bean
   public OpenAPI openAPI(){
      return new OpenAPI()
              .info(new Info()
                      .title("API Gerenciamento de Pedidos")
                      .description("API do Espetinho do Gaúcho para a gestão de pedidos")
                      .version("v1")
                      .contact(new Contact()
                              .name("Cássio Cintra")
                              .email("cassiocr2012@hotmail.com")
                              .url("https://www.linkedin.com/in/cassio-ccr/")
                      )
              );
   }
}
