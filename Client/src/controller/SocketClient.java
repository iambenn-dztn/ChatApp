package controller;

import view.ChatFrame;
import java.io.*;
import java.net.*;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class SocketClient implements Runnable{
    
    public int port;
    public String serverAddr;
    public Socket socket;
    public ChatFrame ui;
//    public ObjectInputStream In;
//    public ObjectOutputStream Out;
    DatagramSocket Client = new DatagramSocket();
    public History hist;
    
    public SocketClient(ChatFrame frame) throws IOException{
        ui = frame; this.serverAddr = ui.serverAddr; this.port = ui.port;
        socket = new Socket(InetAddress.getByName(serverAddr), port);
        hist = ui.hist;
    }

    @Override
    public void run() {
        boolean keepRunning = true;
        while(keepRunning){
            try {
                Message msg = (Message) In.readObject();
                System.out.println("Incoming : "+msg.toString());
                
                if(msg.type.equals("message")){
                    if(msg.recipient.equals(ui.username)){
                        ui.fieMessages.append("["+msg.sender +" > Me] : " + msg.content + "\n");
                    }
                    else{
                        ui.fieMessages.append("["+ msg.sender +" > "+ msg.recipient +"] : " + msg.content + "\n");
                    }
                                            
                    if(!msg.content.equals(".bye") && !msg.sender.equals(ui.username)){
                        String msgTime = (new Date()).toString();
                        
                        try{
                            hist.addMessage(msg, msgTime);
                            DefaultTableModel table = (DefaultTableModel) ui.historyFrame.jTable1.getModel();
                            table.addRow(new Object[]{msg.sender, msg.content, "Me", msgTime});
                        }
                        catch(Exception ex){}  
                    }
                }
                else if(msg.type.equals("login")){
                    if(msg.content.equals("TRUE")){
                        ui.butLogin.setEnabled(false); ui.butRegister.setEnabled(false);                        
                        ui.butSendMessage.setEnabled(true); ui.butPickFile.setEnabled(true);
                        ui.butHistory.setEnabled(true);
                        ui.fieMessages.append("[SERVER > Me] : Login Successful\n");
                        ui.fieUsername.setEnabled(false); ui.fiePassword.setEnabled(false);
                    }
                    else{
                        ui.fieMessages.append("[SERVER > Me] : Login Failed\n");
                    }
                }
                else if(msg.type.equals("test")){
                    ui.butConnect.setEnabled(false);
                    ui.butLogin.setEnabled(true); ui.butRegister.setEnabled(true);
                    ui.fieUsername.setEnabled(true); ui.fiePassword.setEnabled(true);
                    ui.fieServerAddress.setEditable(false); ui.fieServerPort.setEditable(false);
                }
                else if(msg.type.equals("newuser")){
                    if(!msg.content.equals(ui.username)){
                        boolean exists = false;
                        for(int i = 0; i < ui.model.getSize(); i++){
                            if(ui.model.getElementAt(i).equals(msg.content)){
                                exists = true; break;
                            }
                        }
                        if(!exists){ ui.model.addElement(msg.content); }
                    }
                }
                else if(msg.type.equals("signup")){
                    if(msg.content.equals("TRUE")){
                        ui.butLogin.setEnabled(false); ui.butRegister.setEnabled(false);
                        ui.butSendMessage.setEnabled(true); ui.butPickFile.setEnabled(true);
                        ui.fieMessages.append("[SERVER > Me] : Signup Successful\n");
                    }
                    else{
                        ui.fieMessages.append("[SERVER > Me] : Signup Failed\n");
                    }
                }
                else if(msg.type.equals("signout")){
                    if(msg.content.equals(ui.username)){
                        ui.fieMessages.append("["+ msg.sender +" > Me] : Bye\n");
                        ui.butConnect.setEnabled(true); ui.butSendMessage.setEnabled(false); 
                        ui.fieServerAddress.setEditable(true); ui.fieServerPort.setEditable(true);
                        
                        for(int i = 1; i < ui.model.size(); i++){
                            ui.model.removeElementAt(i);
                        }
                        
                        ui.clientThread.stop();
                    }
                    else{
                        ui.model.removeElement(msg.content);
                        ui.fieMessages.append("["+ msg.sender +" > All] : "+ msg.content +" đã thoát.\n");
                    }
                }
                else if(msg.type.equals("upload_req")){
                    
                    if(JOptionPane.showConfirmDialog(ui, ("Accept '"+msg.content+"' from "+msg.sender+" ?")) == 0){
                        
                        JFileChooser jf = new JFileChooser();
                        jf.setSelectedFile(new File(msg.content));
                        int returnVal = jf.showSaveDialog(ui);
                       
                        String saveTo = jf.getSelectedFile().getPath();
                        if(saveTo != null && returnVal == JFileChooser.APPROVE_OPTION){
                            Download dwn = new Download(saveTo, ui);
                            Thread t = new Thread(dwn);
                            t.start();
                            //send(new Message("upload_res", (""+InetAddress.getLocalHost().getHostAddress()), (""+dwn.port), msg.sender));
                            send(new Message("upload_res", ui.username, (""+dwn.port), msg.sender));
                        }
                        else{
                            send(new Message("upload_res", ui.username, "NO", msg.sender));
                        }
                    }
                    else{
                        send(new Message("upload_res", ui.username, "NO", msg.sender));
                    }
                }
                else if(msg.type.equals("upload_res")){
                    if(!msg.content.equals("NO")){
                        int port  = Integer.parseInt(msg.content);
                        String addr = msg.sender;
                        
                        ui.butPickFile.setEnabled(false); ui.butSendFile.setEnabled(false);
                        Upload upl = new Upload(addr, port, ui.file, ui);
                        Thread t = new Thread(upl);
                        t.start();
                    }
                    else{
                        ui.fieMessages.append("[SERVER > Me] : "+msg.sender+" từ chối nhận file.\n");
                    }
                }
                else{
                    ui.fieMessages.append("[SERVER > Me] : Unknown message type\n");
                }
            }
            catch(Exception ex) {
                keepRunning = false;
                ui.fieMessages.append("[Application > Me] : Connection Failure\n");
                ui.butConnect.setEnabled(true); ui.fieServerAddress.setEditable(true); ui.fieServerPort.setEditable(true);
                ui.butSendMessage.setEnabled(false); ui.butPickFile.setEnabled(false); ui.butPickFile.setEnabled(false);
                
                for(int i = 1; i < ui.model.size(); i++){
                    ui.model.removeElementAt(i);
                }
                
                ui.clientThread.stop();
                
                System.out.println("Exception SocketClient run()");
                ex.printStackTrace();
            }
        }
    }
    
    public void send(Message msg){
        try {
            Out.writeObject(msg);
            Out.flush();
            System.out.println("Outgoing : "+msg.toString());
            
            if(msg.type.equals("message") && !msg.content.equals(".bye")){
                String msgTime = (new Date()).toString();
                try{
                    hist.addMessage(msg, msgTime);               
                    DefaultTableModel table = (DefaultTableModel) ui.historyFrame.jTable1.getModel();
                    table.addRow(new Object[]{"Me", msg.content, msg.recipient, msgTime});
                }
                catch(Exception ex){}
            }
        } 
        catch (IOException ex) {
            System.out.println("Exception SocketClient send()");
        }
    }
    
    public void closeThread(Thread t){
        t = null;
    }
}
