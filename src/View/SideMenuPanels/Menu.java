package View.SideMenuPanels;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Menu {

    List<JButton> pulsanti = new ArrayList<JButton>();

    public List<JButton> getPulsanti() {
        return pulsanti;
    }

}
