package se.thematrix.examples;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.crypto.Cipher;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;

public class DecryptObjectExample {

	public static void main(String[] args) throws Exception {
		SecretKey key = (SecretKey)readFromFile("secretkey.dat");
		
		SealedObject sealedObject = (SealedObject)readFromFile("sealed.dat");
		
		String algorithmName  = sealedObject.getAlgorithm();
		Cipher cipher = Cipher.getInstance(algorithmName);
		cipher.init(Cipher.DECRYPT_MODE, key);
		
		String text  = (String)sealedObject.getObject(cipher);
		System.out.println(String.format("Text: %1s", text));
	}

	private static Object readFromFile(String filename) throws Exception {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		Object object = null;
		try {
			fis = new FileInputStream(new File(filename));
			ois = new ObjectInputStream(fis);
			object = ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ois != null) {
				ois.close();
			}
			if (fis != null) {
				fis.close();
			}
		}
		return object;
	}
}
