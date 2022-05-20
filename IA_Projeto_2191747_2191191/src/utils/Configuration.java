package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.stream.Stream;

public class Configuration {
	protected Properties prop;
	public Configuration(String confname){
		prop = new Properties();
		try (Stream<Path> walkStream = Files.walk(Paths.get("./"))) {
			walkStream.filter(p -> p.toFile().isFile()).forEach(f -> {
				if (f.toString().endsWith(confname)) {
					try {
						FileInputStream fis = new FileInputStream(f.toString());
						prop.load(fis);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					
				}
				if(f.toString().endsWith(confname+".example")){
					System.out.print("Are you shure you copied the config file? if not copy the respective "+confname+".example and rename it to: "+confname+"\n");
				}
			});
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	public String getDefaultDir(){
		return prop.getProperty("app.default_dir");
		
	}
	
}