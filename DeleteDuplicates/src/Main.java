import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Main {

	// folder is the directory
	public static void listFilesForFolder(final File folder) {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) { // if there is a another directory, go into it
				listFilesForFolder(fileEntry);
			} else {
				System.out.println(fileEntry.getName());
			}
		}
	}

	public static boolean CompareFiles(File x, File y) {
		try {
			FileInputStream xs = new FileInputStream(x);
			FileInputStream ys = new FileInputStream(y);
			System.out.println("Compare: " + x + " vs " + y);
			boolean result = true;
			while (result == true) {
				int xb = xs.read();
				int yb = ys.read();
				if (xs.read() != ys.read()) {
					result = false;
					xs.close();
					ys.close();
					break;
				}
				if (xb == -1)
					xs.close();
					ys.close();
					break;
			}
			return result;
		} catch (FileNotFoundException e) {
			return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public static void main(String[] args) {
		deleteFilesOfOrdner(args[0]);
	}

		
	

	public static void deleteFilesOfOrdner(String directoryPath) {
		File dir = new File(directoryPath);

		File[] fileList = dir.listFiles();
		for (int x = 0; x < fileList.length; x++) {
			for (int y = x + 1; y < fileList.length; y++) {
				if (fileList[x].length() == fileList[y].length()) {
					if (CompareFiles(fileList[x], fileList[y])) {
						if (fileList[x].getName().length() > fileList[y].getName().length()) {
							if (fileList[x].delete()) {
								System.out.println("Deleted the file: " + fileList[x].getName());
							} else {
								System.out.println("Failed to delete the file.");
							}
							// System.out.println(fileList[y]);
						} else {
							if (fileList[y].delete()) {
								System.out.println("Deleted the file: " + fileList[y].getName());
							} else {
								System.out.println("Failed to delete the file.");
							}
						}
					}
				}
			}
		}
		System.out.print("!!!CHECKING FINISHED!!!");
	}
}
