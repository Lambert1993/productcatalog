package com.ecommerce.productcatalogservice.service;

import com.ecommerce.productcatalogservice.dto.GroqPitchResponseDto;
import com.ecommerce.productcatalogservice.dto.ProductDto;
import com.ecommerce.productcatalogservice.dto.ProductPitchDto;
import com.ecommerce.productcatalogservice.model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.restclient.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class ProductPitchService implements IProductPitchService {

    public static final String API_URL="https://api.groq.com/openai/v1/chat/completions";
    public static final String LLM_MODEL="openai/gpt-oss-20b";
    public static final String SYSTEM_PROMPT="you are a tag line writer, you need to write tagline to the products I provide";

    @Value("${groq.api.key}")
    private String apiKey;
    private RestTemplate restTemplate;

    public ProductPitchService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate=restTemplateBuilder.build();
    }

    @Override
    public String generatePitchForProductId(Product product) {
        try{

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(apiKey);
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, Object> jsonMap = Map.of(
                    "model", LLM_MODEL,
                    "messages", List.of(
                            Map.of(
                                    "role", "system",
                                    "content", SYSTEM_PROMPT
                            ),
                            Map.of(
                                    "role", "user",
                                    "content", "product:mascara;price:$9;name:ICatch"
                            ),
                            Map.of(
                                    "role", "user",
                                    "content", buildUserPrompt(product)
                            )
                    )
            );

            HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(jsonMap,headers);

           ResponseEntity<GroqPitchResponseDto> response =  restTemplate.postForEntity(API_URL,httpEntity, GroqPitchResponseDto.class);

           if(response.getStatusCode().is2xxSuccessful())
                return response.getBody().getChoices().get(0).getMessage().getContent();

           else
               return "Always Surprises you with a smile!!!!";

        }catch(Exception e){
            System.out.println(e.getMessage());
            return "Always Surprises you!!!!";
        }
    }

    public String buildUserPrompt(Product dto){

        return """
                Build a TagLine with following specs:
                1. It should be oneline.
                2. There can be emojji, if possible.
                3. It should be catchy.
                
                Product:
                ProductName: %s,
                ProductCategory: %s,
                ProductPrice: %s
                """.formatted(dto.getName(),dto.getCategory().getName(),dto.getPrice());

    }


}
