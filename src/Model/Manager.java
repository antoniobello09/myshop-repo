package Model;

import View.Nameable;

import java.util.ArrayList;
import java.util.Iterator;

public class Manager extends Utente implements Cloneable, Nameable {

    public Manager(String username, String password, String name, String surname, String email, String birthdate, String telephone, String address, String job){
        super(username, password, name, surname, email, birthdate, telephone, address, job);
    }

    public Manager(){
    }

    public static ArrayList<Manager> cloneList(ArrayList<Manager> lista){
        ArrayList<Manager> newList = new ArrayList<>();
        Iterator<Manager> it = lista.iterator();
        while(it.hasNext()){

            newList.add((Manager) it.next().clone());
        }
        return newList;
    }

    @Override
    public String getNome(){
        return getUsername();
    }

    @Override
    protected Object clone() {
        return super.clone();
    }
}
