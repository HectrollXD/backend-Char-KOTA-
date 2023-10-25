package mx.com.hexlink.charkota.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/v1/test")
public class TestController {
	@GetMapping
	public String getHello(){
		return "Hello World";
	}
}
