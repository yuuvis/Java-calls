import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface {
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Hello Yuuvis");

        mainFrame.setSize(700,700);

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setSize(680, 680);

        GridBagLayout gridbag = new GridBagLayout();
        panel.setLayout(gridbag);

        GridBagConstraints constraints = new GridBagConstraints();

        //Subscription Key Field
        JTextField subscriptionKeyTextField = new JTextField("Input Subscription Key here", 50);
        //Import Area
        JLabel importLabel = new JLabel("Import");
        final String[] importFileName = {""};
        final String[] importFilePath = {""};
        final JLabel importFileNameLabel = new JLabel("");

        JButton importFileSelectButton = new JButton("Choose File");
        importFileSelectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int chosen = chooser.showOpenDialog(null);

                if(chosen == JFileChooser.APPROVE_OPTION){
                    importFileName[0] = chooser.getSelectedFile().getName();
                    importFilePath[0] = chooser.getSelectedFile().getAbsolutePath();
                    importFileNameLabel.setText(importFileName[0]);
                }
            }
        });

        //Retrieve Area
        JLabel retrieveLabel = new JLabel("Retrieve");
        JTextField retrieveObjectIdTextField = new JTextField("Input Object ID here", 36);

        //Update Area
        JLabel updateLabel = new JLabel("Update Content");
        final String[] updateFileName = {""};
        final String[] updateFilePath = {""};
        final JLabel updateFileNameLabel = new JLabel("");

        JButton updateFileSelectButton = new JButton("Choose File");
        updateFileSelectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int chosen = chooser.showOpenDialog(null);
                if(chosen == JFileChooser.APPROVE_OPTION){
                    updateFileName[0] = chooser.getSelectedFile().getName();
                    updateFilePath[0] = chooser.getSelectedFile().getAbsolutePath();
                    updateFileNameLabel.setText(updateFileName[0]);
                }
            }
        });

        JTextField updateObjectIdTextField = new JTextField("Input Object ID here", 36);

        //Search Area
        JLabel searchLabel = new JLabel("Search for Keyword");
        JTextField searchKeywordTextField = new JTextField("Input Keywords here", 36);

        //Delete Area
        JLabel deleteLabel = new JLabel("Delete");
        JTextField deleteObjectIdTextField = new JTextField("Input Object ID here", 36);

        //Result Area
        JTextArea resultTextArea = new JTextArea(500, 50);
        resultTextArea.setLineWrap(true);
        JScrollPane resultScrollPane = new JScrollPane(resultTextArea);

        //Layout
        //Import
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(0, 0,10, 10);
        gridbag.setConstraints(importLabel, constraints);
        panel.add(importLabel);

        constraints.gridy = 1;
        gridbag.setConstraints(importFileSelectButton, constraints);
        panel.add(importFileSelectButton);

        constraints.gridx = 1;
        gridbag.setConstraints(importFileNameLabel, constraints);
        panel.add(importFileNameLabel);

        //Retrieve
        constraints.gridy = 2;
        constraints.gridx = 0;
        gridbag.setConstraints(retrieveLabel, constraints);
        panel.add(retrieveLabel);

        constraints.gridy = 3;
        constraints.gridwidth = 3;
        gridbag.setConstraints(retrieveObjectIdTextField, constraints);
        panel.add(retrieveObjectIdTextField);

        //Update
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        gridbag.setConstraints(updateLabel ,constraints);
        panel.add(updateLabel);

        constraints.gridy = 5;
        gridbag.setConstraints(updateFileSelectButton, constraints);
        panel.add(updateFileSelectButton);

        constraints.gridx = 1;
        gridbag.setConstraints(updateFileNameLabel, constraints);
        panel.add(updateFileNameLabel);

        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 3;
        gridbag.setConstraints(updateObjectIdTextField, constraints);
        panel.add(updateObjectIdTextField);

        //Search
        constraints.gridwidth = 1;
        constraints.gridy = 7;
        gridbag.setConstraints(searchLabel, constraints);
        panel.add(searchLabel);

        constraints.gridwidth = 3;
        constraints.gridy = 8;
        gridbag.setConstraints(searchKeywordTextField, constraints);
        panel.add(searchKeywordTextField);

        //delete
        constraints.gridwidth = 1;
        constraints.gridy = 9;
        gridbag.setConstraints(deleteLabel, constraints);
        panel.add(deleteLabel);

        constraints.gridwidth = 3;
        constraints.gridy = 10;
        gridbag.setConstraints(deleteObjectIdTextField, constraints);
        panel.add(deleteObjectIdTextField);

        /*constraints.gridwidth = 5;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.ipady = 100;
        constraints.ipadx = 400;
        constraints.gridy = 11;
        gridbag.setConstraints(resultScrollPane, constraints);
        panel.add(resultScrollPane);*/




        mainFrame.add(panel);

        mainFrame.setVisible(true);
    }
}
