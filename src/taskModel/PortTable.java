package taskModel;

import javax.swing.JTable;

public class PortTable extends JTable
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public static PortItemTableModel portItemTableModel = new PortItemTableModel();

    public PortTable()
    {
        super(portItemTableModel);
    }

}
