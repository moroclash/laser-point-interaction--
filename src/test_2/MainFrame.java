/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_2;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

/**
 *
 * @author moroclash
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    private String graypath,path,name;
    private Mat mat;
    private VideoCapture vedio ;
    private MatOfByte MB;
    private Thread t;
    public MainFrame() {
        initComponents();
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
        jButton4.setEnabled(true);
        jButton5.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("chose image");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("GrayScale");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("points");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1018, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jButton4.setText("Vedio");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Stope");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("DejaVu Serif", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(131, 8, 11));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(161, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      JFileChooser choser = new JFileChooser();

        // Get array of available formats
        String[] suffices = ImageIO.getReaderFileSuffixes();

        // Add a file filter for each one
        FileNameExtensionFilter filter ;

        choser. setAcceptAllFileFilterUsed(false);
        for (String suffice : suffices) {
            filter = new FileNameExtensionFilter(suffice + " files", suffice);
            choser.addChoosableFileFilter(filter);
        }
        File s=choser.getSelectedFile();
    
      choser.setDialogTitle("Image Choser");
      choser.setCurrentDirectory(new File("."));
      choser.setMultiSelectionEnabled(false);
      if(choser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION)
      {
          path = choser.getSelectedFile().getParent();
          name = choser.getSelectedFile().getName();
          System.err.println(path);
          System.err.println(name);
          Thread kt;
           kt = new Thread()
           {
               @Override
               public void run()
               {
                   try {
                       repaint();
                       Image img = ImageIO.read(choser.getSelectedFile());
                       Graphics g = jPanel1.getGraphics();
                       g.drawImage(img, 0, 0, jPanel1.getWidth(), jPanel1.getHeight(),null);
                       g.drawImage(img, 0, 0, jPanel1.getWidth(), jPanel1.getHeight(),null);
                       jButton2.setEnabled(true);
                   } catch (IOException ex) {
                       System.out.println("erro yad");
                   }
               }
           };
          kt.start();
      }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Mat mat = new Mat();
        mat = Imgcodecs.imread(path+"/"+name);
        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BGR2GRAY);
        graypath = path+"/GrayScale_"+name;
        Imgcodecs.imwrite(graypath, mat);
        Thread t = new Thread()
          {
              public void run()
              {
                  try {
                      repaint();
                      Image img = ImageIO.read(new File(path+"/GrayScale_"+name));
                      Graphics g = jPanel1.getGraphics();
                      g.drawImage(img, 0, 0, jPanel1.getWidth(), jPanel1.getHeight(),null);
                      jButton3.setEnabled(true);
                      jButton4.setEnabled(true);
                  } catch (IOException ex) {
                      System.out.println("erro yad 2");
                  }
              }
          };
          t.start();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Mat ma = new Mat();
        ma = Imgcodecs.imread(path+"/"+name);
        ma = CoreCode.pointsimg(ma);
        Imgcodecs.imwrite(graypath, ma);
        Thread t = new Thread()
          {
              public synchronized void run()
              {
                  try {
                      repaint();
                      Image img = ImageIO.read(new File(graypath));
                      Graphics g = jPanel1.getGraphics();
                      g.drawImage(img, 0, 0, jPanel1.getWidth(), jPanel1.getHeight(),null);
                  } catch (IOException ex) {
                      System.out.println("erro yad 2");
                  }
              }
          };
          t.start();
    }//GEN-LAST:event_jButton3ActionPerformed

    
    private static int  chose = 1,it=1;
    private static double wid,hghi;;
    private static boolean ch = false;
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        vedio = new VideoCapture(0);
        mat = new Mat();
        MB = new MatOfByte();
        jButton5.setEnabled(true);
        jButton4.setEnabled(false);
        jButton1.setEnabled(false);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
        Mat mat2 = new Mat();
        Mat mat1 = new Mat();
        wid=1;
        hghi=1;
        boolean f = false;
        t = new Thread()
        {
            public void run()
            {
                while(true)
                {
                    try {
                        vedio.grab();
                        vedio.retrieve(mat1);
                        vedio.retrieve(mat2);
                        mat = CoreCode.points(mat1,mat2,chose,jPanel1.getWidth(),jPanel1.getHeight());
                        Core.flip(mat, mat, 2);
                        Imgcodecs.imencode(".jpg", mat, MB);
                        Image img = ImageIO.read(new ByteArrayInputStream(MB.toArray()));
                        BufferedImage buf = (BufferedImage) img ;
                        Graphics2D g = (Graphics2D) jPanel1.getGraphics();
                        g.scale(wid, hghi);
                        g.drawImage(img ,0, 0, jPanel1.getWidth(),jPanel1.getHeight(), 0, 0, buf.getWidth(), buf.getHeight(), null);
                        if(CoreCode.isCcircel())
                            {
                                   wid=1.6;
                                   hghi=1.6;
                                   ch = true;
                                   CoreCode.left=false;
                                   CoreCode.right=false;
                                   CoreCode.booton=false;
                            } 
                        if(CoreCode.isleft())
                        {
                            
                            jLabel1.setText("left");
                            System.exit(0);
                            
                        }
                         
                         else if(CoreCode.isRight())
                        {
                                jLabel1.setText("snaping shoot "+it);
                                Imgcodecs.imwrite("shoot number "+it+".jpg", mat);
                                it++;
                                CoreCode.setRight(false);
                                ch = false;
                        }
                         else if(CoreCode.isUp())
                        {
                            CoreCode.up=false;
                            if(ch)
                            {
                                   wid=1;
                                   hghi=1;
                                   ch = false;
                                   CoreCode.setCcircel(false);
                            }
                            else
                            {
                            jLabel1.setText("Up");
                            chose = 2;
                            }
                        }
                         else if(CoreCode.isBooton())
                        {
                            jLabel1.setText("down");
                            chose = 1;
                        }
                    } catch (Exception ex) {
                        System.out.println("Error zoraar Vedio");
                    }  
                }
            }
        };
        t.start();  
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
            vedio.release();
            jButton1.setEnabled(true);
            jButton2.setEnabled(false);
            jButton3.setEnabled(false);
            jButton5.setEnabled(false);
            jButton4.setEnabled(true);
            jPanel1.repaint();
    }//GEN-LAST:event_jButton5ActionPerformed

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
