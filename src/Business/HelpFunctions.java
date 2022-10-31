package Business;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

public class HelpFunctions {

    public static final int YEARS_BOUND = 100;
    private static String[] years = new String[YEARS_BOUND+1];
    private static String[] monthsN = {"--","01","02","03","04","05","06","07","08","09","10","11","12"};
    private static String[] days = {"--","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
    private static String[] months = {"--","GENNAIO", "FEBBRAIO", "MARZO", "APRILE", "MAGGIO", "GIUGNO", "LUGLIO", "AGOSTO", "SETTEMBRE", "OTTOBRE", "NOVEMBRE", "DICEMBRE"};


    public static void setDaysComboBox(JComboBox comboBox, String month, String year){
        comboBox.removeAllItems();
        switch (month) {
            case "FEBBRAIO":
                if (Integer.parseInt(year) % 4 == 0) {
                    for(int i=0; i < 30;i++)
                        comboBox.addItem(days[i]);
                } else {
                    for(int i=0; i < 29;i++)
                        comboBox.addItem(days[i]);
                }
                break;
            case "APRILE":
            case "GIUGNO":
            case "NOVEMBRE":
            case "SETTEMBRE":
                for(int i=0; i < 31;i++)
                    comboBox.addItem(days[i]);
                break;
            default:
                for(int i=0; i < 32;i++)
                    comboBox.addItem(days[i]);
        }
    }

    public static JComboBox setYearsComboBox(){
        years[0] = "--";
        for(int i = 1; i < YEARS_BOUND+1; i++){
            int thisYear = Calendar.getInstance().get(Calendar.YEAR);
            int startYear = thisYear-16;
            years[i] = Integer.toString(startYear - i);
        }
        return new JComboBox<>(years);
    }

    public static JComboBox setMonthsComboBox(){
        return new JComboBox<>(months);
    }

    public static String convertMonth(int index){
        return monthsN[index];
    }
/*
    public static String convertMonth2(String name){
        int index = -1;
        for(int i=0;i<months.length;i++){
            if(months[i].equals(name)){
                index = i;
            }
        }
        return monthsN[index];
    }

    public static String convertMonth3(String number){
        int index = -1;
        for(int i=0;i<monthsN.length;i++){
            if(monthsN[i].equals(number)){
                index = i;
            }
        }
        return months[index];
    }
*/
    public static JComboBox<String> getFullComboBox(ArrayList<String> lista){
        JComboBox<String> comboBox = new JComboBox<>();
        Iterator<String> iterator = lista.iterator();
        while(iterator.hasNext()){
            comboBox.addItem(iterator.next());
        }
        return comboBox;
    }

    public static JComboBox<String> getFullComboBox(JComboBox<String> comboBox, ArrayList<String> lista){
        for (String s : lista) {
            comboBox.addItem(s);
        }
        return comboBox;
    }



}
