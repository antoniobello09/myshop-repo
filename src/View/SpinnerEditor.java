package View;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

public class SpinnerEditor extends DefaultCellEditor{

        private JSpinner spinner;

        public SpinnerEditor()
        {
            super(new JFormattedTextField());
            spinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
            spinner.setBorder( null );
        }

        public Component getTableCellEditorComponent(
                JTable table, Object value, boolean isSelected, int row, int column)
        {
            spinner.setValue( value );
            return spinner;
        }

        public Object getCellEditorValue()
        {
            return spinner.getValue();
        }
    }

