package exApiGdrive.quickstart;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.List;

import com.google.api.services.drive.model.File;

public class Main {

	public static void main(String[] args) {

			try {
				List<String> listFolder = DriveQuickstart.displayListFolder("root");
				
				for (String folder : listFolder) {
					if(folder.equals("1BYN8jwM46pWyHYOE6TsQEBNU0acrg58q")) {
						
						List<File> listfile = DriveQuickstart.displayListFile(folder.toString());
						System.out.println("j'ai trouvé "+ listfile.size() + " fichier" + (listfile.size()>1 ? "s":"") );
						
						for(File file : listfile) {
							DriveQuickstart.CopyFileInHost(file.getId(), file.getName());
						}
					}
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (GeneralSecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
