package exApiGdrive.quickstart;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URLConnection;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

public class DriveQuickstart {

	private static final Logger logger = LoggerFactory.getLogger(DriveQuickstart.class.getName());

	private static final String APPLICATION_NAME = "Google Drive API Java Quickstart";

	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	// Directory to store user credentials for this application.
	private static final java.io.File CREDENTIALS_FOLDER //
			= new java.io.File(System.getProperty("user.home"), "credentials");

	private static final String CLIENT_SECRET_FILE_NAME = "client_secret.json";

	//
	// Global instance of the scopes required by this quickstart. If modifying these
	// scopes, delete your previously saved credentials/ folder.
	//
	private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);

	private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {

		java.io.File clientSecretFilePath = new java.io.File(CREDENTIALS_FOLDER, CLIENT_SECRET_FILE_NAME);

		if (!clientSecretFilePath.exists()) {
			throw new FileNotFoundException("Please copy " + CLIENT_SECRET_FILE_NAME //
					+ " to folder: " + CREDENTIALS_FOLDER.getAbsolutePath());
		}

		// Load client secrets.
		InputStream in = new FileInputStream(clientSecretFilePath);

		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				clientSecrets, SCOPES).setDataStoreFactory(new FileDataStoreFactory(CREDENTIALS_FOLDER))
						.setAccessType("offline").build();

		return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
	}

	/**
	 * affichage de tous les dossiers d'un dossier parent
	 * 
	 * @param idFolder
	 * @return List<String>
	 * @throws IOException
	 * @throws GeneralSecurityException
	 */
	public static List<String> displayListFolder(String idFolder) throws IOException, GeneralSecurityException {
		Drive service = getService();
		FileList result = service.files().list().setQ("'" + idFolder + "' in parents and trashed = false")
				.setSpaces("drive").setFields("nextPageToken, files(id, name, parents,mimeType)").setPageToken(null)
				.execute();

		List<File> files = result.getFiles();
		List<String> folderStr = new ArrayList<String>();
		List<File> folderFile = new ArrayList<File>();
		if (files == null || files.isEmpty()) {
			System.out.println("No files found.");
			return null;
		} else {
			System.out.println("Folders:");
			int folderNumber = 0;
			for (File file : files) {
				if (file.getMimeType().equals("application/vnd.google-apps.folder")) {
					System.out.printf("%s = > %s (%s)\n", file.getMimeType(), file.getName(), file.getId());
					folderStr.add(file.getId());
					folderFile.add(file);
					folderNumber++;
				}
			}
			System.out.println("nombre de dossier " + folderNumber);
		}

		return folderStr;

	}

	/**
	 * affichage de tous les fichiers dans un dossier
	 * 
	 * @param idFolder
	 * @return List<com.google.api.services.drive.model.File>
	 * @throws IOException
	 * @throws GeneralSecurityException
	 */
	public static List<File> displayListFile(String idFolder) throws IOException, GeneralSecurityException {
		Drive service = getService();
		FileList result = service.files().list().setQ("'" + idFolder + "' in parents and trashed = false")
				.setSpaces("drive").setFields("nextPageToken, files(id, name, parents,mimeType)").setPageToken(null)
				.execute();

		List<File> files = result.getFiles();
		List<String> folderStr = new ArrayList<String>();
		List<File> folderFile = new ArrayList<File>();
		if (files == null || files.isEmpty()) {
			System.out.println("No files found.");
			return null;
		} else {
			System.out.println("File:");
			int fileNumber = 0;
			for (File file : files) {
				if (!file.getMimeType().equals("application/vnd.google-apps.folder")) {
					System.out.printf("%s = > %s (%s)\n", file.getMimeType(), file.getName(), file.getId());
					folderStr.add(file.getId());
					folderFile.add(file);
					fileNumber++;
				}
			}
			System.out.println("nombre de dossier " + fileNumber);
		}

		return folderFile;

	}


	/**
	 * televersement du pc jusqu'au drive
	 * 
	 * 
	 * 
	 * @param idFolder id du dossier sur gDrive "root" pour mettre sur la racine
	 * @param source   chemin absolu du fichier sur l'hote
	 * @throws GeneralSecurityException
	 * @throws IOException
	 */
	public static void UploadFile(String idFolder, String source) throws IOException, GeneralSecurityException {
		Drive driveService = getService();
//		idFolder = "1BYN8jwM46pWyHYOE6TsQEBNU0acrg58q";
//		String mimeType = "image/jpeg";
//		source = "C:\\Users\\tberezia\\OneDrive - Capgemini\\Pictures\\photo.jpg";

		File fileMetadata = new File();
		java.io.File filePath = new java.io.File(source);
		String fileName = filePath.getName();

		fileMetadata.setName(fileName);
		if (idFolder != null || !idFolder.isEmpty()) {
			fileMetadata.setParents(Collections.singletonList(idFolder));
		}

		logger.info(filePath.getParent() + filePath.getName());
		String mimeType = URLConnection.guessContentTypeFromName(filePath.getName());
		logger.info(mimeType + " = " + filePath.getName());
		FileContent mediaContent = new FileContent(mimeType, filePath);
		File file;

		try {
			file = driveService.files().create(fileMetadata, mediaContent).setFields("id").execute();
			logger.info("File ID: " + file.getId() + " File mimeType: " + file.getMimeType());
			logger.info(mimeType);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * telechargement du drive juqu'au pc
	 * 
	 * TODO gerer le nom du fichier TODO penser à stocker dans une base de données
	 * avec ByteArrayOutputStream()
	 * 
	 * fileid = id du fichier du drive target = la cible sur l'hote
	 * 
	 * @param driveService
	 * @throws GeneralSecurityException
	 * @throws IOException
	 */
	public static void CopyFileInHost(String fileId, String target) throws IOException, GeneralSecurityException {
		Drive driveService = getService();
//		 fileId = "19jQ2xtkSQjgsXuMSmfNlF0PjE0SwGjPk";
//		 target = "C:\\Users\\tberezia\\Downloads\\photo.jpg";
//		 OutputStream outputStream = new ByteArrayOutputStream();
		String path = "C:\\Users\\tberezia\\Downloads\\" + target;
		System.out.println(path);
		OutputStream outputStream = new FileOutputStream(path);
		try {
			driveService.files().get(fileId).executeMediaAndDownloadTo(outputStream);
			outputStream.flush();
			outputStream.close();
			System.out.println("ça passe");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static Drive getService() throws IOException, GeneralSecurityException {
		System.out.println("CREDENTIALS_FOLDER: " + CREDENTIALS_FOLDER.getAbsolutePath());
		logger.info("demmarrage de la connexion Gdrive");
		// 1: Create CREDENTIALS_FOLDER
		if (!CREDENTIALS_FOLDER.exists()) {
			CREDENTIALS_FOLDER.mkdirs();

			System.out.println("Created Folder: " + CREDENTIALS_FOLDER.getAbsolutePath());
			System.out.println("Copy file " + CLIENT_SECRET_FILE_NAME + " into folder above.. and rerun this class!!");
			return null;
		}

		// 2: Build a new authorized API client service.
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

		// 3: Read client_secret.json file & create Credential object.
		Credential credential = getCredentials(HTTP_TRANSPORT);

		// 5: Create Google Drive Service.

		Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential) //
				.setApplicationName(APPLICATION_NAME).build();

		return service;
	}

	public static void main(String... args) throws IOException, GeneralSecurityException {

//		displayFiles(service);
//		displayAll(service);
//		File fileReturn = updateFiles(service);
//		printFile(service,"1rGN04LAH1_xld1Tghya9xPwPnl9fWatC" );
//		displayListFolder(service,"root");
//		displayListFile(service, "1BYN8jwM46pWyHYOE6TsQEBNU0acrg58q");
//		downloadFile(service,new File());
//		String idFolder = "1BYN8jwM46pWyHYOE6TsQEBNU0acrg58q";
//		UploadFile("root","C:\\Users\\tberezia\\OneDrive - Capgemini\\Pictures\\photo.jpg");
//		CopyFileInHost(service );
	}

}
