package cn.jtduan.util.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class Application {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Application.class);

		app.addListeners(new MyApplicationReadyEventListener());
		app.run(args);
	}
}
