package kr.bigskypark.whatsnew.collector.common.config;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XmlMapperConfiguration {

  @Bean
  public XmlMapper xmlMapper() {
    return XmlMapper.builder().build();
  }
}
