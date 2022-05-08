package showSolution;

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
					//System.out.println(f + " found!");
					try {
						FileInputStream fis = new FileInputStream(f.toString());
						prop.load(fis);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					
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
