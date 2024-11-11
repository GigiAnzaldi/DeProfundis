/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.eserciziopreparatorio.server;


import edu.avo.tcpiolibrary.IMessageConsumer;
import edu.avo.tcpiolibrary.MyReader;
import edu.avo.tcpiolibrary.MySender;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
/**
 *
 * @author palma
 */
public class Server {

    public Server(Socket socket){
        try {
            PrintWriter out=new PrintWriter(socket.getOutputStream());
            MySender sender=new MySender(out);
            SenderProtocolManager spm=new SenderProtocolManager(sender, "Fine");
            App app=new App(spm);
            IMessageConsumer rpm=new ReaderProtocolManager(app);
            BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            MyReader reader=new MyReader(in,"Fine",rpm);
            reader.start();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
