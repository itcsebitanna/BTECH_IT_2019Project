import cryptosystem.dataencapsulation.BlockDecoder;
import cryptosystem.dataencapsulation.BlockEncoder;
import cryptosystem.dataencapsulation.ECB;
import cryptosystem.dataencapsulation.KeyGeneratorD;
import cryptosystem.keyencapsulation.CipherText;
import cryptosystem.keyencapsulation.KeyDecryption;
import cryptosystem.keyencapsulation.KeyEncryption;
import cryptosystem.keyencapsulation.KeyGenerator;
import cryptosystem.keyencapsulation.PublicKey;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
public class GUI extends javax.swing.JFrame {
    public cryptoSysUI() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        tabPanel = new javax.swing.JTabbedPane();
        decTabPanel = new javax.swing.JPanel();
        lDecCipher = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taDecCipher = new javax.swing.JTextArea();
        bDecMsg = new javax.swing.JButton();
        bDecExit = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        taDecMsg = new javax.swing.JTextArea();
        lDecMsg = new javax.swing.JLabel();
        encTabPanel = new javax.swing.JPanel();
        bEncMsg = new javax.swing.JButton();
        lEncMsg = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taEncMsg = new javax.swing.JTextArea();
        bEncExit = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        taEncCipTxt = new javax.swing.JTextArea();
        lEncCipTxt = new javax.swing.JLabel();
        pSecretKey = new javax.swing.JPanel();
        lSecKey = new javax.swing.JLabel();
        pwSecMsg = new javax.swing.JPasswordField();
        bSecKeyExit = new javax.swing.JButton();
        bSecKey = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        lDecCipher.setText("Cipher Text:");

        taDecCipher.setColumns(20);
        taDecCipher.setRows(5);
        jScrollPane2.setViewportView(taDecCipher);

        bDecMsg.setText("Decrypt Ciphertext");
        bDecMsg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bDecMsgMouseClicked(evt);
            }
        });
        bDecMsg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDecMsgActionPerformed(evt);
            }
        });

        bDecExit.setText("Exit");
        bDecExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bDecExitMouseClicked(evt);
            }
        });
        bDecExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDecExitActionPerformed(evt);
            }
        });

        taDecMsg.setEditable(false);
        taDecMsg.setColumns(20);
        taDecMsg.setRows(5);
        jScrollPane4.setViewportView(taDecMsg);

        lDecMsg.setText("Message:");

        javax.swing.GroupLayout decTabPanelLayout = new javax.swing.GroupLayout(decTabPanel);
        decTabPanel.setLayout(decTabPanelLayout);
        decTabPanelLayout.setHorizontalGroup(
            decTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(decTabPanelLayout.createSequentialGroup()
                .addGroup(decTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(decTabPanelLayout.createSequentialGroup()
                        .addGap(266, 266, 266)
                        .addComponent(bDecMsg)
                        .addGap(26, 26, 26)
                        .addComponent(bDecExit))
                    .addGroup(decTabPanelLayout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addGroup(decTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lDecCipher)
                            .addComponent(lDecMsg))
                        .addGap(46, 46, 46)
                        .addGroup(decTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(225, Short.MAX_VALUE))
        );
        decTabPanelLayout.setVerticalGroup(
            decTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(decTabPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(decTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lDecCipher)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(decTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lDecMsg))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addGroup(decTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bDecMsg)
                    .addComponent(bDecExit))
                .addGap(63, 63, 63))
        );

        tabPanel.addTab("Decrypt", decTabPanel);

        bEncMsg.setText("Encrypt Message");
        bEncMsg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bEncMsgMouseClicked(evt);
            }
        });
        bEncMsg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEncMsgActionPerformed(evt);
            }
        });

        lEncMsg.setText("Message:");

        taEncMsg.setColumns(20);
        taEncMsg.setRows(5);
        jScrollPane1.setViewportView(taEncMsg);

        bEncExit.setText("Exit");
        bEncExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bEncExitMouseClicked(evt);
            }
        });
        bEncExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEncExitActionPerformed(evt);
            }
        });

        taEncCipTxt.setEditable(false);
        taEncCipTxt.setColumns(20);
        taEncCipTxt.setRows(5);
        jScrollPane3.setViewportView(taEncCipTxt);

        lEncCipTxt.setText("CipherText:");

        javax.swing.GroupLayout encTabPanelLayout = new javax.swing.GroupLayout(encTabPanel);
        encTabPanel.setLayout(encTabPanelLayout);
        encTabPanelLayout.setHorizontalGroup(
            encTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(encTabPanelLayout.createSequentialGroup()
                .addGroup(encTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(encTabPanelLayout.createSequentialGroup()
                        .addGap(263, 263, 263)
                        .addComponent(bEncMsg)
                        .addGap(44, 44, 44)
                        .addComponent(bEncExit))
                    .addGroup(encTabPanelLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(encTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lEncMsg)
                            .addComponent(lEncCipTxt))
                        .addGap(52, 52, 52)
                        .addGroup(encTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))))
                .addContainerGap(230, Short.MAX_VALUE))
        );
        encTabPanelLayout.setVerticalGroup(
            encTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, encTabPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(encTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lEncMsg))
                .addGap(52, 52, 52)
                .addGroup(encTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lEncCipTxt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(encTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bEncMsg)
                    .addComponent(bEncExit))
                .addGap(57, 57, 57))
        );

        tabPanel.addTab("Encrypt", encTabPanel);

        lSecKey.setText("Secret Message for Key:");

        pwSecMsg.setText("secret key");
        pwSecMsg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pwSecMsgActionPerformed(evt);
            }
        });

        bSecKeyExit.setText("Exit");
        bSecKeyExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bSecKeyExitMouseClicked(evt);
            }
        });
        bSecKeyExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSecKeyExitActionPerformed(evt);
            }
        });

        bSecKey.setText("Update Secret Message");
        bSecKey.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bSecKeyMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pSecretKeyLayout = new javax.swing.GroupLayout(pSecretKey);
        pSecretKey.setLayout(pSecretKeyLayout);
        pSecretKeyLayout.setHorizontalGroup(
            pSecretKeyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pSecretKeyLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(lSecKey, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(pSecretKeyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pSecretKeyLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(bSecKey)
                        .addGap(40, 40, 40)
                        .addComponent(bSecKeyExit))
                    .addComponent(pwSecMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(133, Short.MAX_VALUE))
        );
        pSecretKeyLayout.setVerticalGroup(
            pSecretKeyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pSecretKeyLayout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addGroup(pSecretKeyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pwSecMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lSecKey, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(127, 127, 127)
                .addGroup(pSecretKeyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bSecKey)
                    .addComponent(bSecKeyExit))
                .addContainerGap(209, Short.MAX_VALUE))
        );

        tabPanel.addTab("Secret Key", pSecretKey);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 884, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void bEncMsgActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
    }                                       

    private void bDecMsgActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
    }                                       

    private void bEncExitActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        System.exit(0);
    }                                        

    private void bDecExitActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        System.exit(0);
    }                                        

    private void bDecExitMouseClicked(java.awt.event.MouseEvent evt) {                                      
        // TODO add your handling code here:
        System.exit(0);
    }                                     

    private void bEncExitMouseClicked(java.awt.event.MouseEvent evt) {                                      
        // TODO add your handling code here:
        System.exit(0);
    }                                     

    private void bEncMsgMouseClicked(java.awt.event.MouseEvent evt) {                                     
        // TODO add your handling code here:
        String msg = taEncMsg.getText();
        String pw = Arrays.toString(pwSecMsg.getPassword());
        encryptMessage(msg,pw, taEncCipTxt);
    }                                    

    private void bDecMsgMouseClicked(java.awt.event.MouseEvent evt) {                                     
        // TODO add your handling code here:
        String msg = taDecCipher.getText();
        decryptMessage(msg,taDecMsg);
    }                                    

    private void formWindowOpened(java.awt.event.WindowEvent evt) {                                  
        // TODO add your handling code here:
    }                                 

    private void pwSecMsgActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void bSecKeyExitMouseClicked(java.awt.event.MouseEvent evt) {                                         
        // TODO add your handling code here:
        System.exit(0);
    }                                        

    private void bSecKeyExitActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void bSecKeyMouseClicked(java.awt.event.MouseEvent evt) {                                     
        // TODO add your handling code here:
        key.updateSecretMsg(Arrays.toString(pwSecMsg.getPassword()));
        System.out.println("Secret Message Updated");
    }                                    

    public static void main(String args[]) {
 
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(cryptoSysUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(cryptoSysUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(cryptoSysUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(cryptoSysUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new cryptoSysUI().setVisible(true);
            }
        });      
        
    }

    ECB ecb = new ECB();
    KeyGenerator key = new KeyGenerator();
    String lpw;
    CipherText ctObj = new CipherText();
    // Variables declaration - do not modify                     
    private javax.swing.JButton bDecExit;
    private javax.swing.JButton bDecMsg;
    private javax.swing.JButton bEncExit;
    private javax.swing.JButton bEncMsg;
    private javax.swing.JButton bSecKey;
    private javax.swing.JButton bSecKeyExit;
    private javax.swing.JPanel decTabPanel;
    private javax.swing.JPanel encTabPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lDecCipher;
    private javax.swing.JLabel lDecMsg;
    private javax.swing.JLabel lEncCipTxt;
    private javax.swing.JLabel lEncMsg;
    private javax.swing.JLabel lSecKey;
    private javax.swing.JPanel pSecretKey;
    private javax.swing.JPasswordField pwSecMsg;
    private javax.swing.JTextArea taDecCipher;
    private javax.swing.JTextArea taDecMsg;
    private javax.swing.JTextArea taEncCipTxt;
    private javax.swing.JTextArea taEncMsg;
    private javax.swing.JTabbedPane tabPanel;
    public void encryptMessage(String msg,String pw, javax.swing.JTextArea cipher) {
        PublicKey pk = key.getPublicKey();
        if(!pw.equalsIgnoreCase(key.getKeyMsg()))
        {
            key.updateSecretMsg(pw);
            System.out.println("Secret Message Updated");
        }
        KeyEncryption ke= new KeyEncryption(pw, pk);
        CipherText c = ke.getCipher();
        ctObj = c;
        String cHex = c.toHex();
        if (!"".equals(msg)) {
            List<String> blocks;
            KeyGeneratorD keyGen = new KeyGeneratorD(cHex);
            String[] enckeys = keyGen.generateEKeys();
            //System.out.println(Arrays.toString(enckeys));
            BlockEncoder be = new BlockEncoder(enckeys);
            blocks = ecb.createStringBlocks(msg);
            System.out.println("Plaintext in hexadecimal blocks: " + blocks.toString());
            String cipherText = "";
            for (int i = 0; i < blocks.size(); i++) {
                String temp = be.encodeBlock(blocks.get(i));
                cipherText += temp;
            }
            System.out.println("The ciphertext is: " + cipherText);
            //update cipherText
            cipher.setText(cipherText);
        } 
    }
    public void decryptMessage(String ct, javax.swing.JTextArea msg) {
        BigInteger pk = key.getPrivateKey(); 
        BigInteger p = key.getPublicKey().getP(); 
        KeyDecryption kd= new KeyDecryption(ctObj, pk, p);
        String kHex = kd.getKeyMsg();
        if (!"".equals(ct)) {
            List<String> blocks;
            KeyGeneratorD keyGen = new KeyGeneratorD(kHex);
            String[] deckeys = keyGen.generateDKeys();
            //System.out.println(Arrays.toString(deckeys));
            BlockDecoder bd = new BlockDecoder(deckeys);
            blocks = ecb.createHexBlocks(ct.trim());
            System.out.println("Hexadecimal blocks: " + blocks.toString());
            String plainText = "";
            for (int i = 0; i < blocks.size(); i++) {
                String temp = bd.decodeBlock(blocks.get(i));
                //String temp2 = ecb.hexToString(temp);
                plainText += temp;
            }
            plainText = ecb.hexToString(plainText);
            System.out.println("This is the plaintext: " + plainText);
            msg.setText(plainText.trim());
        } 
    }
}
