package cn.jtduan.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@ServletComponentScan
@EnableWebSocket
public class Application {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Application.class);
		SpringApplication.run(Application.class, args);
	}
}
