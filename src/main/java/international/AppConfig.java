package international;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import international.web.ExcelDownloadView;

// Spring IoC 컨테이너가 탐색할 패키지 설정
// => 지정한 패키지 및 그 하위 패키지를 모두 뒤져서
// @Component 애노테이션이 붙은 클래스를 찾아 객체를 생성한다.
//
@ComponentScan(value = "international")
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {

  static Logger logger = LogManager.getLogger(AppConfig.class);

  public AppConfig() {
    logger.debug("AppConfig 객체 생성!");
  }

  @Bean
  public MultipartResolver multipartResolver() {
    // 스프링 방식으로 파일 업로드를 처리하고 싶다면,
    // 이 메서드를 통해 MultipartResolver 구현체를 등록해야 한다.
    // 그래야 request handler가 MultipartFile 객체를 받을 수 있다.
    CommonsMultipartResolver mr = new CommonsMultipartResolver();
    mr.setMaxUploadSize(10000000);
    mr.setMaxInMemorySize(2000000);
    mr.setMaxUploadSizePerFile(5000000);
    return mr;
  }
  
  
  @Bean
  public InternalResourceViewResolver internalResourceViewResolver() {
	  InternalResourceViewResolver rv = new InternalResourceViewResolver();
//	  rv.setPrefix("/WEB-INF/");
//	  rv.setSuffix(".jsp");
	  rv.setOrder(1);
	  return rv;
  }
  @Bean
  public BeanNameViewResolver beanNameViewResolver() {
	  BeanNameViewResolver vr = new BeanNameViewResolver();
	  vr.setOrder(0);
	  return vr;
  }
  
  @Bean
  public ExcelDownloadView excelDownloadView() {
	  ExcelDownloadView ex = new ExcelDownloadView();
	  return ex;
  }
  
//  <beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
//  <beans:property name="prefix" value="/WEB-INF/views/" />
//  <beans:property name="suffix" value=".jsp" />
//  <beans:property name="order" value="1"></beans:property>
//</beans:bean>
}


