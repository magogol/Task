import java.io.*;
import java.util.Base64;

public interface Data {
    void writeData(String data);
    String readData();
}
class DataFile implements Data {
    private String name;

    public DataFile(String name) {
        this.name = name;
    }

    @Override
    public void writeData(String data) {
        File file = new File(name);
        try (OutputStream fos = new FileOutputStream(file)) {
            fos.write(data.getBytes(), 0, data.length());
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public String readData() {
        char[] buffer = null;
        File file = new File(name);
        try (FileReader reader = new FileReader(file)) {
            buffer = new char[(int) file.length()];
            reader.read(buffer);
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        return new String(buffer);
    }
}
class Source implements Data {
    private Data dat;
    Source(Data source) {
        this.dat = source;
    }
    @Override
    public void writeData(String data) {
        dat.writeData(data);
    }
    @Override
    public String readData() {
        return dat.readData();
    }
}

class Encryption extends Source {
    public Encryption(Data d) {
        super(d);
    }
    private String encode(String data) {
        byte[] result = data.getBytes();
        for (int i = 0; i < result.length; i++) {
            result[i] += (byte) 1;
        }
        return Base64.getEncoder().encodeToString(result);
    }
    private String decode(String data) {
        byte[] result = Base64.getDecoder().decode(data);
        for (int i = 0; i < result.length; i++) {
            result[i] -= (byte) 1;
        }
        return new String(result);
    }
    @Override
    public void writeData(String data) {
        super.writeData(encode(data));
    }
    @Override
    public String readData() {
        return decode(super.readData());
    }
}