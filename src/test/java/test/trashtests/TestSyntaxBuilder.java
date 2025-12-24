package test.trashtests;

import com.soundcheck.listeners.error.ErrorHandler;
import com.soundcheck.processor.ParserImpl;
import com.soundcheck.processor.ReadFileImpl;
import com.soundcheck.processor.contracts.ReadFile;
import com.soundcheck.raag.models.Raag;

import java.io.IOException;

public class TestSyntaxBuilder {
  public static void main(String[] args) throws IOException {
    ReadFile reader = new ReadFileImpl();
    ErrorHandler.isCallFromClient();
    String content = reader.readFile(
        "C:\\Users\\Aniruddha Sarkar\\Documents\\github\\SoundCheck\\src\\test\\raag_files\\error_files_test\\mkm\\mkm1.raag"
    );
    Raag raag = new ParserImpl().parse(content);
    System.out.println(ErrorHandler.hasErrorOccurred());
  }
}
