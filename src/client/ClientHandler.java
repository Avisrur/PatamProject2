package client;

import com.sun.org.apache.xml.internal.serializer.OutputPropertiesFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public interface ClientHandler {
    void handle(PrintWriter outTC,String level);
}
