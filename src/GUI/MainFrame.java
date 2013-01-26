/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import service.ftp.domain.FTPMediaFile;
import service.filesystem.TypeX;
import service.ftp.FTPFileManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import control.*;
import java.util.*;

/**
 *
 * @author kavan
 */
public class MainFrame extends JFrame {

        private Container contentPane;
        private BoxLayout boxLayout;
        private JPanel sp;//searchPanel
        private JPanel cp; // categoryPanel
        private JPanel bp; // bottomPanel
        private TreeMap<String,FTPMediaFile> moviesMap;
        private FTPFileManager setup;
        private FTPMediaFile[] fold;
       
    public MainFrame(){
        
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setTitle("SolxiomMediaCenter DEMO");
        super.setSize(800,600);
        this.setMainFrame();
        this.setVisible(true);
    }

    private void setMainFrame(){
        
        this.setSearchPanel();
        this.setBottomPanel();


        cp = new JPanel();
        cp.setBackground(Color.darkGray);
        cp.setOpaque(true);
        this.setCategoryPanel();

        
        
      
   
   
        

        contentPane = super.getContentPane();
        boxLayout = new BoxLayout(contentPane, BoxLayout.Y_AXIS);
        contentPane.setLayout(boxLayout);
        contentPane.add(sp);
        contentPane.add(cp);
     
        contentPane.add(bp);
       //contentPane.add(new JButton("H 3333"));
    }
    private void setSearchPanel(){
        sp = new JPanel();
        JTextField jt = new JTextField("");
        jt.setMaximumSize(new Dimension(400,25));
        jt.setMinimumSize(new Dimension(400,25));
        sp.setSize(100,super.getWidth());
        
        sp.setLayout(new BoxLayout(sp,BoxLayout.X_AXIS));
       
        sp.add(Box.createRigidArea(new Dimension(0,50)));
        sp.add(jt);
        sp.add(new JButton("search"));
     
    }
    private void setCategoryPanel(){

        setup = new FTPFileManager();
        fold = setup.listCategories();

        this.cp.removeAll();
        this.cp.updateUI();

        JPanel innerCp = new JPanel();
        JScrollPane scroll = new JScrollPane(innerCp);
        scroll.setVerticalScrollBarPolicy(scroll.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(scroll.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(null);
        cp.setLayout(new GridLayout(1,1));
        innerCp.setLayout(new GridLayout(2,3));

        for(FTPMediaFile cat: fold){
            if(cat.isMediaType(service.filesystem.TypeX.CATEGORY_DIR))
            {
                    JLabel cb = new JLabel( "");
                   
               
                    int h =305;
                    int w= 235;
                    cb.setMinimumSize(new Dimension(w,h));
                    cb.setPreferredSize(new Dimension(w,h));
                    cb.setMaximumSize(new Dimension(w,h));
                    //cb.addActionListener(this);
                    Icon icon = new ImageIcon("files/img/"+cat.getName().toLowerCase()+".png");
                    
               
                    cb.setIcon(icon);
                    cb.setBackground(null);
                    cb.setBorder(null);
                    cb.addMouseListener(new MouseActions());
                    cb.setName(cat.getName());
                 
                    
                    innerCp.add(cb);
                    innerCp.add(Box.createRigidArea(new Dimension(20,0)));
            }
             cp.add(scroll);
             this.cp.updateUI();
        }
     
    }
   
    public void setMoviesPanel(){

        JPanel north = new JPanel();
        JPanel south = new JPanel();
        JPanel listP = new JPanel();
        JPanel iconP = new JPanel();
        JScrollPane listSc = new JScrollPane(listP);
        listSc.setVerticalScrollBarPolicy(listSc.VERTICAL_SCROLLBAR_ALWAYS);
        listSc.getVerticalScrollBar().setPreferredSize(new Dimension(10,100));
        listSc.getVerticalScrollBar().setBackground(Color.white);
        listSc.getVerticalScrollBar().setBorder(null);
        //listSc.setBorder(null);
        //listSc.getVerticalScrollBar().
        listP.setLayout(new BoxLayout(listP,BoxLayout.Y_AXIS));
        listP.setBackground(Color.white);
        listP.setOpaque(true);

        iconP.setBackground(Color.white);

        //north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        //south.setLayout(new GridLayout(1,2));
        south.setLayout(new BoxLayout(south, BoxLayout.X_AXIS));
        north.setLayout(new BorderLayout());


        //cp.setMaximumSize(new Dimension(super.getWidth(),super.getHeight()));
             //cp = new JPanel();
          this.cp.removeAll();
          cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));
          JLabel backBt =new JLabel("");
          backBt.setIcon(new ImageIcon("files/img/backButton.png"));
          backBt.setName("backButton");
          backBt.addMouseListener(new MouseActions());
          //
          JPanel backBtP = new JPanel();
          backBtP.setLayout(new BorderLayout());
          backBtP.add(Box.createRigidArea(new Dimension(10,10)),BorderLayout.WEST);
          backBtP.add(backBt,BorderLayout.CENTER);
          backBtP.add(Box.createRigidArea(new Dimension(0,10)),BorderLayout.SOUTH);
          north.add(backBtP, BorderLayout.WEST);
          
          cp.add(north);

          if(fold != null)
          {
              moviesMap = new TreeMap<String,FTPMediaFile>();
              TreeSet set;
              for(FTPMediaFile md: fold)// finding the movies folder
                    if(md.getName().equalsIgnoreCase("movies") && md.getFTPFile().isDirectory())
                        fold = md.listFTPMediaFiles();
              for(FTPMediaFile md :fold)               
                  moviesMap.put(md.getMediaName(), md);
              
              
              for(String key :moviesMap.keySet())
              {
                  
//&& md.isDirContainsType(TypeX.VIDEO)
                  FTPMediaFile md = moviesMap.get(key);
                  
                     if((md.isMediaType(TypeX.MEDIA_DIR)
                           || md.isMediaType(TypeX.VIDEO)))
                                 
                    {

                            JPanel movP = new JPanel();
                            JPanel optionP = new JPanel();
                            optionP.setLayout(new GridLayout(1,0));

                            movP.setMinimumSize(new Dimension(listP.getWidth(),50));
                            movP.setBackground(Color.white);
                            movP.setLayout(new BorderLayout());
                            JLabel filmIcon = new JLabel("");
                            filmIcon.setIcon(new ImageIcon("files/img/filmIcon2.png"));
                            JLabel infoIcon = new JLabel("");
                            infoIcon.setIcon(new ImageIcon("files/img/info2.png"));
                            infoIcon.setName("info2");
                            JLabel playIcon = new JLabel("");
                            playIcon.setIcon(new ImageIcon("files/img/play.png"));
                            playIcon.setName("play");

                            playIcon.addMouseListener(new MouseActions(md));
                            infoIcon.addMouseListener(new MouseActions());

                            JLabel movLab = new JLabel(md.getMediaName() );
                            movLab.setName("movLab");
                            
                            movLab.setFont(new Font("Times New Roman", Font.PLAIN, 18));
                            movLab.setForeground(Color.red);

                            optionP.add(infoIcon);
                            optionP.add(Box.createRigidArea(new Dimension(10,0)));
                            optionP.add(playIcon);
                            optionP.add(Box.createRigidArea(new Dimension(10,0)));
                            optionP.setOpaque(false);


                            movP.add(filmIcon, BorderLayout.WEST);
                            movP.add(movLab, BorderLayout.CENTER);
                            movP.add(optionP, BorderLayout.EAST);
                            movP.setName("movP");
                            movP.addMouseListener(new MouseActions());

                            listP.add(Box.createRigidArea(new Dimension(0,8)));
                            listP.add(movP);
                    }
              }
             south.add(Box.createRigidArea(new Dimension(10,0)));
             south.add(listSc);
             south.add(iconP);
             south.add(Box.createRigidArea(new Dimension(10,0)));

          }
          cp.add(south);
          this.cp.updateUI();
       

    }

    private void setBottomPanel(){
        bp = new JPanel();
        bp.setLayout(new BorderLayout());
        bp.setMaximumSize(new Dimension(super.getWidth(),100));
        bp.setMinimumSize(new Dimension(super.getWidth(),25));
        bp.add(Box.createRigidArea(new Dimension(0,30)));
    }

   class Actions implements ActionListener{

       public void actionPerformed(ActionEvent e){

           MainFrame.this.setCategoryPanel();
       }
   }
   

    class MouseActions implements MouseListener{
        service.filesystem.MediaFile file;
        MouseActions(){
            file =null;
        }
        MouseActions(Object ob){
            try{
                file = (service.filesystem.MediaFile)ob;
            }
            catch(Exception ex)
            {
               file = null;
            }

        }
             public void mousePressed(MouseEvent e) {
                if(e.getComponent().getName().equalsIgnoreCase("movP"))
                {

                }
                else{
                      try{
                         JLabel jb = (JLabel)e.getComponent();
                         jb.setIcon(new ImageIcon("files/img/"+jb.getName().toLowerCase()+"Pressed.png"));
                        }
                         catch(Exception ex)
                         {

                         }
                 }
                
                
        }

        public void mouseReleased(MouseEvent e) {
            if(e.getComponent().getName().equalsIgnoreCase("movP"))
            {
                JPanel panel = (JPanel) e.getComponent();
             
            }
            else
            {
               try{
                    JLabel jb = (JLabel)e.getComponent();
                    jb.setIcon(new ImageIcon("files/img/"+jb.getName().toLowerCase()+"MouseOn.png"));
                }
                 catch(Exception ex)
                 {

                 }
            }
        }

        public void mouseEntered(MouseEvent e) {
                //JOptionPane.showMessageDialog(new JFrame("media dialog"), "MouseOn!!");
                if(e.getComponent().getName().equalsIgnoreCase("movP"))
                {
                    JPanel panel = (JPanel) e.getComponent();
                    panel.setBackground(new Color(245,245,220));
                    panel.setForeground(Color.GREEN);
                   
                    //panel.setToolTipText(e.getComponent().getParent().toString());
                }
                else
                {
                    try{
                            JLabel jb = (JLabel)e.getComponent();
                            jb.setIcon(new ImageIcon("files/img/"+jb.getName().toLowerCase()+"MouseOn.png"));
                            if(e.getComponent().getName().equalsIgnoreCase("info2") ||
                                    e.getComponent().getName().equalsIgnoreCase("play") )
                            {
                                JPanel panel = (JPanel) e.getComponent().getParent().getParent();
                                panel.setBackground(new Color(245,245,220));
                                panel.setForeground(Color.GREEN);

                            }
                     }
                     catch(Exception ex)
                     {

                     }
                }

        }

        public void mouseExited(MouseEvent e) {
            if(e.getComponent().getName().equalsIgnoreCase("movP"))
            {
                JPanel panel = (JPanel) e.getComponent();
                panel.setBackground(Color.WHITE);
                panel.setForeground(Color.RED);
            }
            else{
                  try{
                            JLabel jb = (JLabel)e.getComponent();
                            jb.setIcon(new ImageIcon("files/img/"+jb.getName().toLowerCase()+".png"));
                            if(e.getComponent().getName().equalsIgnoreCase("info2") ||
                                        e.getComponent().getName().equalsIgnoreCase("play") )
                                {
                                    JPanel panel = (JPanel) e.getComponent().getParent().getParent();
                                     panel.setBackground(Color.WHITE);
                                    panel.setForeground(Color.RED);
                                }
                     }
                     catch(Exception ex)
                     {

                     }
            }

                //JOptionPane.showMessageDialog(new JFrame(), MainFrame.this.getHeight());
        }

        public void mouseClicked(MouseEvent e) {
            
            if(e.getComponent().getName().equalsIgnoreCase("movP"))
            {

            }
            else
            {
               try{
                        JLabel jb = (JLabel)e.getComponent();
                         if(jb.getName().equalsIgnoreCase("movies"))
                                MainFrame.this.setMoviesPanel();
                         else if(jb.getName().equalsIgnoreCase("backButton"))
                                MainFrame.this.setCategoryPanel();
                         else if(jb.getName().equalsIgnoreCase("play"))
                         {
                               if(file != null)
                               {
                                   //if(file.isMediaType(TypeX.MEDIA_DIR))
                                   if(file.isDirectory() && (file.isDirEmpty() || !file.isDirContainsType(TypeX.VIDEO)))
                                   {
                                       JPanel px = (JPanel)jb.getParent();
                                       px.removeAll();
                                       px.setLayout(new BorderLayout());
                                       px.setBackground(Color.pink);
                                       px.setOpaque(true);
                                       px.add(Box.createRigidArea(new Dimension(10,0)),BorderLayout.WEST);
                                       px.add(new JLabel("Empty Media Directory!!"),BorderLayout.CENTER);
                                       px.add(Box.createRigidArea(new Dimension(20,0)),BorderLayout.EAST);
                                       px.updateUI();
                                   }
                                   else
                                          new control.CMD_Controller().openMediaFile(file);
                               }
                         }
                 }
                 catch(Exception ex)
                 {

                 }
            }
        }
    }

}


