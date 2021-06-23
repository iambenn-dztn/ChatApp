package view;

import controller.History;
import controller.Message;
import controller.SocketClient;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;

public class ChatFrame extends javax.swing.JFrame {

    public SocketClient client;
    public int port;
    public String serverAddr, username, password;
    public Thread clientThread;
    public DefaultListModel model;
    public File file;
    public String historyFile = "./History.xml";
    public HistoryFrame historyFrame;
    public History hist;

    public ChatFrame() {
        initComponents();
        this.setTitle("Chat Client");
        model.addElement("All");
        lisOnline.setSelectedIndex(0);
        hist = new History(historyFile);

        this.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    client.send(new Message("message", username, ".bye", "SERVER"));
                    clientThread.stop();
                } catch (Exception ex) {
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
    }

    public boolean isWin32() {
        return System.getProperty("os.name").startsWith("Windows");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        fieServerAddress = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        fieServerPort = new javax.swing.JTextField();
        butConnect = new javax.swing.JButton();
        fieUsername = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        butRegister = new javax.swing.JButton();
        fiePassword = new javax.swing.JPasswordField();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        fieMessages = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        lisOnline = new javax.swing.JList();
        jLabel5 = new javax.swing.JLabel();
        fieInput = new javax.swing.JTextField();
        butSendMessage = new javax.swing.JButton();
        butLogin = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        butPickFile = new javax.swing.JButton();
        butSendFile = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        labFilePath = new javax.swing.JLabel();
        butHistory = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Host Address : ");

        fieServerAddress.setText("localhost");

        jLabel2.setText("Host Port : ");

        fieServerPort.setText("13000");

        butConnect.setText("Connect");
        butConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butConnectActionPerformed(evt);
            }
        });

        fieUsername.setText("zeltrax");
        fieUsername.setEnabled(false);

        jLabel3.setText("Password :");

        jLabel4.setText("Username :");

        butRegister.setText("Register");
        butRegister.setEnabled(false);
        butRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butRegisterActionPerformed(evt);
            }
        });

        fiePassword.setText("1");
        fiePassword.setEnabled(false);
        fiePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fiePasswordActionPerformed(evt);
            }
        });

        fieMessages.setColumns(20);
        fieMessages.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        fieMessages.setRows(5);
        jScrollPane1.setViewportView(fieMessages);

        lisOnline.setModel((model = new DefaultListModel()));
        lisOnline.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lisOnlineMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(lisOnline);

        jLabel5.setText("Message : ");

        butSendMessage.setText("Send Message ");
        butSendMessage.setEnabled(false);
        butSendMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butSendMessageActionPerformed(evt);
            }
        });

        butLogin.setText("Login");
        butLogin.setEnabled(false);
        butLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butLoginActionPerformed(evt);
            }
        });

        butPickFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/attack_icon.png"))); // NOI18N
        butPickFile.setEnabled(false);
        butPickFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butPickFileActionPerformed(evt);
            }
        });

        butSendFile.setText("Send");
        butSendFile.setEnabled(false);
        butSendFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butSendFileActionPerformed(evt);
            }
        });

        jLabel6.setText("File :");

        butHistory.setText("History");
        butHistory.setEnabled(false);
        butHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butHistoryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fieUsername)
                            .addComponent(fieServerAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fieServerPort, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                            .addComponent(fiePassword))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(butConnect, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(butLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(butRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labFilePath)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(butPickFile, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(butSendFile, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieInput)
                        .addGap(18, 18, 18)
                        .addComponent(butHistory)
                        .addGap(18, 18, 18)
                        .addComponent(butSendMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(fieServerAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(fieServerPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(butConnect))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(butRegister)
                    .addComponent(fiePassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(butLogin))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butSendMessage)
                    .addComponent(jLabel5)
                    .addComponent(fieInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(butHistory))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labFilePath)
                    .addComponent(butSendFile, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(butPickFile)
                    .addComponent(jLabel6))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void butConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butConnectActionPerformed
        serverAddr = fieServerAddress.getText();
        port = Integer.parseInt(fieServerPort.getText());

        if (!serverAddr.isEmpty() && !fieServerPort.getText().isEmpty()) {
            try {
                client = new SocketClient(this);
                clientThread = new Thread(client);
                clientThread.start();
                client.send(new Message("test", "testUser", "testContent", "SERVER"));
            } catch (Exception ex) {
                fieMessages.append("[Application > Me] : Server not found\n");
            }
        }
    }//GEN-LAST:event_butConnectActionPerformed

    private String toSha256(String s) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(s.getBytes());
        byte[] digest = md.digest();
        StringBuilder hexString = new StringBuilder();

        for (int i = 0; i < digest.length; i++) {
            hexString.append(Integer.toHexString(0xFF & digest[i]));
        }
        return hexString.toString();
    }

    private void butLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butLoginActionPerformed
        username = fieUsername.getText();
        password = fiePassword.getText();

        if (!username.isEmpty() && !password.isEmpty()) {
            try {
                password = toSha256(password);
                client.send(new Message("login", username, password, "SERVER"));
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ChatFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_butLoginActionPerformed

    private void butSendMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butSendMessageActionPerformed
        String msg = fieInput.getText();
        String target = lisOnline.getSelectedValue().toString();

        if (!msg.isEmpty() && !target.isEmpty()) {
            fieInput.setText("");
            client.send(new Message("message", username, msg, target));
        }
    }//GEN-LAST:event_butSendMessageActionPerformed

    private void butRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butRegisterActionPerformed
        username = fieUsername.getText();
        password = fiePassword.getText();

        if (!username.isEmpty() && !password.isEmpty()) {
            try {
                password = toSha256(password);
                client.send(new Message("signup", username, password, "SERVER"));
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ChatFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_butRegisterActionPerformed

    private void butPickFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butPickFileActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showDialog(this, "Select File");
        file = fileChooser.getSelectedFile();

        if (file != null) {
            if (!file.getName().isEmpty()) {
                butSendFile.setEnabled(true);
                String str;

                if (labFilePath.getText().length() > 30) {
                    String t = file.getPath();
                    str = t.substring(0, 20) + " [...] " + t.substring(t.length() - 20, t.length());
                } else {
                    str = file.getPath();
                }
                labFilePath.setText(str);
            }
        }
    }//GEN-LAST:event_butPickFileActionPerformed

    private void butSendFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butSendFileActionPerformed
        long size = file.length();
        if (size < 120 * 1024 * 1024) {
            client.send(new Message("upload_req", username, file.getName(), lisOnline.getSelectedValue().toString()));
        } else {
            fieMessages.append("[Application > Me] : File is size too large\n");
        }
    }//GEN-LAST:event_butSendFileActionPerformed

    private void fiePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fiePasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fiePasswordActionPerformed

    private void butHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butHistoryActionPerformed
        // TODO add your handling code here:
        historyFrame = new HistoryFrame(hist);
        historyFrame.setLocation(this.getLocation());
        historyFrame.setVisible(true);
    }//GEN-LAST:event_butHistoryActionPerformed

    private void lisOnlineMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lisOnlineMouseReleased
        // TODO add your handling code here:
        if (evt.isPopupTrigger()) {
            String[] options = {"abc", "def", "ghi", "jkl"};
            //Integer[] options = {1, 3, 5, 7, 9, 11};
            //Double[] options = {3.141, 1.618};
            //Character[] options = {'a', 'b', 'c', 'd'};
            int x = JOptionPane.showOptionDialog(null, "Returns the position of your choice on the array",
                    "Click a button",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            System.out.println(x);
        }
    }//GEN-LAST:event_lisOnlineMouseReleased

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            System.out.println("Look & Feel exception");
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChatFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton butConnect;
    public javax.swing.JButton butHistory;
    public javax.swing.JButton butLogin;
    public javax.swing.JButton butPickFile;
    public javax.swing.JButton butRegister;
    public javax.swing.JButton butSendFile;
    public javax.swing.JButton butSendMessage;
    public javax.swing.JTextField fieInput;
    public javax.swing.JTextArea fieMessages;
    public javax.swing.JPasswordField fiePassword;
    public javax.swing.JTextField fieServerAddress;
    public javax.swing.JTextField fieServerPort;
    public javax.swing.JTextField fieUsername;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    public javax.swing.JLabel labFilePath;
    public javax.swing.JList lisOnline;
    // End of variables declaration//GEN-END:variables
}
