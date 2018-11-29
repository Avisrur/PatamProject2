package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class TestServer {


    public static void runClient1(int port) {
        Socket s = null;
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            s = new Socket("127.0.0.1", port);
            s.setSoTimeout(311000);
            out = new PrintWriter(s.getOutputStream());
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out.println("s|-|-|-|-|-L");
            out.println("-7|-|-|-|-|L");
            out.println("|7|-|-|-|-|g");
            out.println("done");
            out.flush();
            String line = in.readLine();
            if (line == null || !line.equals("0,1,1\n0,2,3\n1,2.1"))
                System.out.println("Client 1");
            line = in.readLine();
            if (line == null || !line.equals("done"))
                System.out.println("Your Server does not work according to the right protocol (-10)");
        } catch (SocketTimeoutException e) {
            System.out.println("Your Server takes over 3 seconds to answer (-40)");
        } catch (IOException e) {
            System.out.println(e.toString());
        } finally {
            close(s, out, in);
        }

    }

    public static void runClient2(int port) {
        Socket s = null;
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            s = new Socket("127.0.0.1", port);
            s.setSoTimeout(311000);
            out = new PrintWriter(s.getOutputStream());
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out.println("s|-|-|-|-|-L");
            out.println("-7|-|-|-|-|L");
            out.println("-7|-|-|-|-|L");
            out.println("-7|-|-|-|-|L");
            out.println("-7|-|-|-|-|L");
            out.println("-7|-|-|-|-|L");
            out.println("-7|-|-|-|-|L");
            out.println("-7|-|-|-|-|L");
            out.println("-7|-|-|-|-|L");
            out.println("|7|-|-|-|-|g");
            out.println("done");
            out.flush();
            String line = in.readLine();
            if (line == null || !line.equals("0,1,1\n0,2,3\n1,2.1"))
                System.out.println("Client 2");
            line = in.readLine();
            if (line == null || !line.equals("done"))
                System.out.println("Your Server does not work according to the right protocol (-10)");
        } catch (SocketTimeoutException e) {
            System.out.println("Your Server takes over 3 seconds to answer (-40)");
        } catch (IOException e) {
            System.out.println("Your Server ran into some IOException (-40)");
        } finally {
            close(s, out, in);
        }

    }

    public static void runClient3(int port) {
        Socket s = null;
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            s = new Socket("127.0.0.1", port);
            s.setSoTimeout(311000);
            out = new PrintWriter(s.getOutputStream());
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out.println("s|-L");
            out.println("L|-L");
            out.println("L--g");
            out.println("done");
            out.flush();
            String line = in.readLine();
            if (line == null || !line.equals("0,1,1\n0,2,3\n1,2.1"))
                System.out.println("Client 3");
            line = in.readLine();
            if (line == null || !line.equals("done"))
                System.out.println("Your Server does not work according to the right protocol (-10)");
        } catch (SocketTimeoutException e) {
            System.out.println("Your Server takes over 3 seconds to answer (-40)");
        } catch (IOException e) {
            System.out.println("Your Server ran into some IOException (-40)");
        } finally {
            close(s, out, in);
        }

    }

    private static void close(Socket s, PrintWriter out, BufferedReader in) {
        try {
            in.close();
            out.close();
            s.close();
        } catch (IOException e) {
            System.out.println("Your Server ran into some IOException (-40)");
        }
    }

}
