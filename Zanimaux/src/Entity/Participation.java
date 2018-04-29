/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;



import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author houss
 */

public class Participation  {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   
    private int id;
    private int id_user;
    private int id_evenement;

    public Participation() {
    }
    
    

    public Participation( int id_user, int id_evenement) {
       
        this.id_user = id_user;
        this.id_evenement = id_evenement;
    }

    public Participation(int id, int id_user, int id_evenement) {
        this.id = id;
        this.id_user = id_user;
        this.id_evenement = id_evenement;
    }

   
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId_user() {
        return id_user;
    }

    public int getId_evenement() {
        return id_evenement;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    @Override
    public String toString() {
        return "Participation{" + "id=" + id + ", id_user=" + id_user + ", id_evenement=" + id_evenement + '}';
    }

   

  

   
    
}
