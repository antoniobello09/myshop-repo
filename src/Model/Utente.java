package Model;

import View.Nameable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

public class Utente implements Nameable{

    private int idUtente;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String birthdate;
    private String telephone;
    private String address;
    private String job;


    public Utente() {
        this.idUtente = 0;
        this.username = "";
        this.password = "";
        this.name = "";
        this.surname = "";
        this.email = "";
        this.birthdate = "";
        this.telephone = "";
        this.address = "";
        this.job = "";
    }

    public Utente(int idUtente, String username, String password, String name, String surname, String email, String birthdate, String telephone, String address, String job) {
        this.idUtente = idUtente;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthdate = birthdate;
        this.telephone = telephone;
        this.address = address;
        this.job = job;
    }

    public Utente(int idUtente, String username, String password, String email) {
        this.idUtente = idUtente;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Utente(String username, String password, String name, String surname, String email, String birthdate, String telephone, String address, String job) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthdate = birthdate;
        this.telephone = telephone;
        this.address = address;
        this.job = job;
    }

    public Utente(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }


    public String toString() {
        return name + " " + surname + " " + email + " " + birthdate;
    }

    @Override
    public String getNome() {
        return null;
    }
}