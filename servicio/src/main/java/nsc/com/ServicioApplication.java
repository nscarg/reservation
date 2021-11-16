package nsc.com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;


	
@SpringBootApplication
public class ServicioApplication implements CommandLineRunner{
	
	@Autowired
    private AWS_Properties yaml; 	
	
	public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ServicioApplication.class);
        app.run();
    }

     public void run(String... args) throws Exception {
    	System.out.println("velez");
        System.out.println("name: " + yaml.getPuerto());
        
        Prueba pru = new Prueba(yaml.getPuerto());
        	   System.out.println("x"+pru.leerMarca());
     }   

	
	 
}
