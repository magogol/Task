import java.io.*;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class FileData {
    public static File Inp(String input) throws IOException {
        File inputFile = new File(input);
        if(!inputFile.exists()) {
            inputFile.createNewFile();
            PrintWriter pwin = new PrintWriter(inputFile);
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter content of file: ");
            while (scan.hasNextLine()) {
                String s = scan.nextLine();
                pwin.println(s);
            }
        }
        return inputFile;
    }
    public static File Outp(String output) throws IOException {
        File outputFile = new File(output);
        if (!outputFile.exists())
            outputFile.createNewFile();
        return outputFile;
    }
    public static void Zip(String file, String zipfile) throws IOException {
        FileOutputStream fos = new FileOutputStream(zipfile);
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        File fileToZip = new File(file);
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
        zipOut.putNextEntry(zipEntry);
        final byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        zipOut.close();
        fis.close();
        fos.close();
    }
    public static void UnZip(String zipfile, String unzipfile) throws IOException {
        File destDir = new File(unzipfile);
        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(zipfile));
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
            File newFile = newFile(destDir, zipEntry);
            if (zipEntry.isDirectory()) {
                if (!newFile.isDirectory() && !newFile.mkdirs()) {
                    throw new IOException("Failed to create directory " + newFile);
                }
            } else {
                File parent = newFile.getParentFile();
                if (!parent.isDirectory() && !parent.mkdirs()) {
                    throw new IOException("Failed to create directory " + parent);
                }
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
            }
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
    }
    public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }

        return destFile;
    }
}
