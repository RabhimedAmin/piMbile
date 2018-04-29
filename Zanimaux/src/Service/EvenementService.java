/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Evenement;
import Entity.User;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import static jdk.nashorn.internal.codegen.OptimisticTypesPersistence.store;

/**
 *
 * @author houss
 */
public class EvenementService {

    public void AjouterEvenement(Evenement e) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/projet_zanimaux/web/app_dev.php/mobile/evenement/new"
                + "/" + e.getId_membre().getId()
                + "/" + e.getNom()
                + "/" + e.getLieu()
                + "/" + e.getDescription()
                + "/" + e.getNbr_max_participant()
                + "/" + e.getImage();
        con.setUrl(Url);

        System.out.println("tt");

        con.addResponseListener((event) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
            /*  if (str.trim().equalsIgnoreCase("OK")) {
                f2.setTitle(tlogin.getText());
             f2.show();
            }
            else{
            Dialog.show("error", "login ou pwd invalid", "ok", null);
          }
             */
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public ArrayList<Evenement> afficherAll() {
        ArrayList<Evenement> listEvenement = new ArrayList<>();

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/projet_zanimaux/web/app_dev.php/mobile/evenement/afficher");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> event = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    // System.out.println(event);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) event.get("root");
                    for (Map<String, Object> obj : list) {
                        Evenement e = new Evenement();

                        LinkedHashMap<String, Object> objUser = (LinkedHashMap<String, Object>) obj.get("idMembre");

                        User u = new User();

                        u.setId((int) Float.parseFloat(objUser.get("id").toString()));
                        u.setUsername(objUser.get("username").toString());

                        e.setId_membre((User) u);

                        float id = Float.parseFloat(obj.get("id").toString());
                        e.setId((int) id);

                        e.setNom(obj.get("nom").toString());
                        e.setLieu(obj.get("lieu").toString());
                        e.setDescription(obj.get("description").toString());

                        float nbrmax = Float.parseFloat(obj.get("nbrMaxParticipant").toString());
                        e.setNbr_max_participant((int) nbrmax);

                        float nbr = Float.parseFloat(obj.get("nbrParticipant").toString());
                        e.setNbr_participant((int) nbr);

                        e.setImage((String) obj.get("image").toString());

                        LinkedHashMap<String,Object> date = (LinkedHashMap<String,Object>) obj.get("date"); 
                        
                        double t = (double) date.get("timestamp");
                        long x = (long) (t * 1000L);
                         e.setDate(new Date(x));

                        listEvenement.add(e);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvenement;
    }

}
