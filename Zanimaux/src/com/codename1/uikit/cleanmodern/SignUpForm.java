/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.codename1.uikit.cleanmodern;

import com.codename1.components.FloatingHint;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Signup UI
 *
 * @author Shai Almog
 */
public class SignUpForm extends BaseForm {

    public SignUpForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
                
        TextField username = new TextField("", "Username", 20, TextField.ANY);
        TextField email = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        TextField confirmPassword = new TextField("", "Confirm Password", 20, TextField.PASSWORD);
        TextField lieux= new TextField("","Ville",20,TextField.ANY);
        TextField telephone = new TextField("","numéro telephone",20,TextField.ANY);
        Button uploadim = new Button("uploadir imae");
       
  
        
        username.setSingleLineTextArea(false);
        email.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        confirmPassword.setSingleLineTextArea(false);
        Button next = new Button("Next");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");
        
        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                new FloatingHint(confirmPassword),
                createLineSeparator(),
                new FloatingHint(lieux),
                createLineSeparator(),
                new FloatingHint(telephone),
                createLineSeparator()
        );
      
      content.add(uploadim);
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        next.requestFocus();
        String sexe= "femme";
        next.addActionListener(e->{/*new ActivateForm(res).show();*/
            if ((username.getText()!="")&&(telephone.getText()!="")&&(lieux.getText()!="")&&(password.getText()!="")&&(password.getText().equals(confirmPassword.getText())))
            {
                System.out.println("create user blyat");
                ConnectionRequest r = new ConnectionRequest();
            r.setUrl("http://localhost/projet_zanimaux/web/app_dev.php/mobile/user/register?username="+username.getText()+"&email="+email.getText()+"&password="+password.getText()+"&telephone="+telephone.getText()+"&ville="+lieux.getText()+"&image="+uploadim.getText());
            System.out.println(r.getUrl());

            //0 User   1 Artisan
            r.setPost(false);
            r.addResponseListener(resp -> {
                byte[] data= (byte[]) resp.getMetaData();
            try {
                    
                    String decodedData = new String(data,"UTF-8");
                    
                    System.out.println(decodedData);
                } catch (UnsupportedEncodingException ex) {
                    ex.printStackTrace();
                }

                String s=data.toString();
                
                System.out.println("data :"+s);
            });
            NetworkManager.getInstance().addToQueue(r);
            Dialog.show("Welcome", "Welcome to the family son", "cancel", "ok");
            new SignInForm(res).show();
            }
            else {
                Dialog.show("Ooops", "Looks like something is missing", "cancel", "try again");
                System.out.println("you have Vadim in your code");
                System.out.println("username.getText() :"+username.getText());
                System.out.println("lieux.getText() :"+lieux.getText());
                System.out.println("tel.getText() :"+telephone.getText());
                System.out.println("password.getText() :"+password.getText());
                
                
                
                
            }
            
        });
    }
    private Map<String, Object> createListEntry(String name, String date) {
    Map<String, Object> entry = new HashMap<>();
    entry.put("Line1", name);
    entry.put("Line2", date);
    return entry;
}
    }
    

