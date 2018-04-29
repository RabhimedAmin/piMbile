/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import Entity.Evenement;
import Entity.Participation;
import Entity.User;
import Service.EvenementService;
import Service.ParticipationService;
import Service.UserService;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

/**
 *
 * @author houss
 */
public class MesEvenement extends BaseForm {
    
   public MesEvenement(Resources res) {
        
        super("Mes Evenement", BoxLayout.y());
        System.out.println("form d'affichage begin \n");
       header(res);
        affichagemesevent(res);
        
       }
    private void header(Resources res)
    {
         Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Mes Evenement");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("news-item.jpg"), spacer1, "15 Likes  ", "85 Comments", "Integer ut placerat purued non dignissim neque. ");
        addTab(swipe, res.getImage("dog.jpg"), spacer2, "100 Likes  ", "66 Comments", "Dogs are cute: story at 11");
                
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("All", barGroup);
        all.setUIID("SelectBar");
        RadioButton featured = RadioButton.createToggle("Featured", barGroup);
        featured.setUIID("SelectBar");
        RadioButton popular = RadioButton.createToggle("Popular", barGroup);
        popular.setUIID("SelectBar");
        RadioButton myFavorite = RadioButton.createToggle("My Favorites", barGroup);
        myFavorite.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(4, all, featured, popular, myFavorite),
                FlowLayout.encloseBottom(arrow)
        ));
        
        all.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(all, arrow);
        });
        
        all.addActionListener(ev-> {
             removeAll();
    header(res);
        affichagemesevent(res); 
    });
        
         
         
         popular.addActionListener(ev-> {
             removeAll();
                headerpopular(res);
                affichagepopulair(res);

        
    });
        
        bindButtonSelection(all, arrow);
        bindButtonSelection(featured, arrow);
        bindButtonSelection(popular, arrow);
        bindButtonSelection(myFavorite, arrow);
        
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        
    }
    
    private void headerpopular(Resources res)
    {
         Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Newsfeed");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("news-item.jpg"), spacer1, "15 Likes  ", "85 Comments", "Integer ut placerat purued non dignissim neque. ");
        addTab(swipe, res.getImage("dog.jpg"), spacer2, "100 Likes  ", "66 Comments", "Dogs are cute: story at 11");
                
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("All", barGroup);
        all.setUIID("SelectBar");
        RadioButton featured = RadioButton.createToggle("Featured", barGroup);
        featured.setUIID("SelectBar");
        RadioButton popular = RadioButton.createToggle("Popular", barGroup);
        popular.setUIID("SelectBar");
        RadioButton myFavorite = RadioButton.createToggle("My Favorites", barGroup);
        myFavorite.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(4, all, featured, popular, myFavorite),
                FlowLayout.encloseBottom(arrow)
        ));
        
        popular.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(popular, arrow);
        });
        
        all.addActionListener(ev-> {
             removeAll();
    header(res);
        affichagemesevent(res); 
    });
        
         
         
         popular.addActionListener(ev-> {
             removeAll();
                headerpopular(res);
                affichagepopulair(res);
    });
        
        bindButtonSelection(all, arrow);
        bindButtonSelection(featured, arrow);
        bindButtonSelection(popular, arrow);
        bindButtonSelection(myFavorite, arrow);
        
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        
    }
    
    private void affichagepopulair(Resources res)
    {
         EvenementService es=new EvenementService();
        Evenement E=new Evenement();
        
        for (int i=0;i<es.afficherAll().size();i++)
        {
            if (es.afficherAll().get(i).getNbr_participant()>5)
            {
            System.out.println("Affichage de l'evenement : "+(i+1)+"\n");
       addButton(res.getImage("news-item-1.jpg"), es.afficherAll().get(i));
       show();
            }

        }
    }
    
     private void affichagemesevent(Resources res)
    {
         EvenementService es=new EvenementService();
        Evenement E=new Evenement();
       
        for (int i=0;i<es.afficherAll().size();i++)
        {
            if (es.afficherAll().get(i).getId_membre().getId()==1)
            {
            System.out.println("Affichage de l'evenement : "+(i+1)+"\n");
            addButton(res.getImage("news-item-1.jpg"), es.afficherAll().get(i));
            show();
            }

        }
    }
    
    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
        
        
    }
    
    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            new SpanLabel(text, "LargeWhiteText"),
                            FlowLayout.encloseIn(likes, comments),
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
    
   private void addButton(Image img,Evenement e) {
       int height = Display.getInstance().convertToPixels(14f);
       int width = Display.getInstance().convertToPixels(20f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       //cnt.setLeadComponent(image);
      
       TextArea ttitre = new TextArea(e.getNom());
       ttitre.setUIID("NewsTopLine");
       ttitre.setEditable(false);
       
        TextArea tlieu = new TextArea(e.getLieu());
       tlieu.setUIID("NewsTopLine");
       tlieu.setEditable(false);
       
        TextArea tdate = new TextArea(String.valueOf(e.getDate()));
       tdate.setUIID("NewsTopLine");
       tdate.setEditable(false);
       
        TextArea tdescription = new TextArea(e.getDescription());
       tdescription.setUIID("NewsTopLine");
       tdescription.setEditable(false);

      
       Label participants = new Label(String.valueOf(e.getNbr_participant() )+ " Participants  ", "NewsBottomLine");
      
      // AimerEvenementService aes=new AimerEvenementService();
       
       
        Label like = new Label(  "50 likes ");
        Style heartStyle = new Style(like.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        like.setIcon(heartImage);
        like.setTextPosition(RIGHT);
       
        Button btnaimaer=new Button();
      
        /*
       AimerEvenement ae=new AimerEvenement(1,e.getId());
              
             
              
              if (aes.findparticipation(ae))
              {
                  btnaimaer.setText("J'aime pas");
              }
              else
              {
                  btnaimaer.setText("J'aime");
              }
              btnaimaer.addActionListener(ev->{
                  if (btnaimaer.getText().toUpperCase().equalsIgnoreCase("J'AIME") )
                  {
                      aes.aimer(ae);
                     System.out.println("J'aime");
                      btnaimaer.setText("J'aime pas");
                     
                     
                  }
                  else 
                  {
                     
                      aes.aimerpas(ae);
                      System.out.println("J'aime pas");
                       btnaimaer.setText("J'aime");
                  }
              
              
              });
        
        
        */
        Participation p=new Participation(UserService.currentUser.getId(),e.getId());
              ParticipationService ps=new ParticipationService();
             
               Button btnparticiper=new Button();
              if (ps.findparticipation(p))
              {
                  btnparticiper.setText("Abondonner");
              }
              else
              {
                  btnparticiper.setText("Participer");
              }
              btnparticiper.addActionListener(ev->{
                  if (btnparticiper.getText().toUpperCase().equalsIgnoreCase("PARTICIPER") )
                  {
                       btnparticiper.setText("Abondonner");
                      ps.participer(p);
                     System.out.println("Participer");
                     
                     
                          System.out.println(p.toString());
                     
                  }
                  else 
                  {
                       btnparticiper.setText("Participer");
                      ps.abondonner(p);
                      System.out.println("Abondonner");
                     
                  }
              
              
              });
        
        
       
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ttitre,tlieu,tdate,tdescription
                    
               ));
       Container cn=   BoxLayout.encloseX(like,btnaimaer,participants,btnparticiper);
       add(cnt);
       add(cn);
       image.addActionListener(ev -> ToastBar.showMessage(e.getNom(), FontImage.MATERIAL_INFO));
   }
    
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }


}
