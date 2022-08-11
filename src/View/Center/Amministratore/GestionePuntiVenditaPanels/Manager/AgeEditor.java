package View.Center.Amministratore.GestionePuntiVenditaPanels.Manager;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgeEditor extends AbstractCellEditor
        implements TableCellEditor,
        ActionListener {
    String currentData;
    JButton button;
    String data;
    JFrame frame;
    ManagerTableModel managerTableModel;
    JTable table;
    protected static final String EDIT = "edit";

    public AgeEditor(JFrame frame, JTable table, ManagerTableModel managerTableModel) {
        this.frame = frame;
        this.table = table;
        this.managerTableModel = managerTableModel;
        button = new JButton();
        button.setActionCommand("somma");
        button.addActionListener(this);
        button.setBorderPainted(false);

    }

    public void actionPerformed(ActionEvent e) {
        if ("somma".equals(e.getActionCommand())) {
            button.setText(managerTableModel.getLista().get(table.convertRowIndexToModel(table.getSelectedRow())).getBirthdate());
            data = ListDialog.showDialog(frame,
                    button,
                    "Somma numeri",
                    "Ciao",
                    null,
                    button.getText(),
                    null);


            button.setText(data);

            //Make the renderer reappear.
            fireEditingStopped();


        } else { //User pressed dialog's "OK" button.

        }
    }

    //Implement the one CellEditor method that AbstractCellEditor doesn't.
    public Object getCellEditorValue() {
        return data;
    }

    //Implement the one method defined by TableCellEditor.
    public Component getTableCellEditorComponent(JTable table,
                                                 Object value,
                                                 boolean isSelected,
                                                 int row,
                                                 int column) {
        currentData = data;
        return button;
    }
}
