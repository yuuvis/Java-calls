import okhttp3.*;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;

public class UserFormInterface {
    private JTextField retrieveOidTextField;
    private JButton updateChooseFileButton;
    private JTextField updateOidTextField;
    private JTextField keywordTextField;
    private JButton sendButtonUpdate;
    private JButton sendButtonRetrieve;
    private JButton sendButtonSearch;
    private JButton sendButtonDelete;
    private JButton importChooseFileButton;
    private JButton sendButtonImport;
    private JLabel importLabel;
    private JLabel updateLabel;
    private JTextField deleteOidTextField;
    private JTextField versionNurmberTextField;
    private JLabel importFileNameLabel;
    private JScrollPane resultPanel;
    private JLabel deleteSuccessLabel;
    private JLabel searchSuccessLabel;
    private JLabel updateSuccessLabel;
    private JLabel getSuccessLabel;
    private JLabel importSuccessLabel;
    private JButton objectIdCopyButton;
    private JScrollPane resultScrollPane;
    private JLabel updateFileNameLabel;
    private JTextField inputYourSubscriptionKeyTextField;
    private JPanel mainPanel;
    private JPanel operationsPanel;
    private JLabel keyLabel;
    private JTextArea resultTextArea;
    private final String[] importFileName = {""};
    private final String[] importFilePath = {""};
    private final String[] updateFileName = {""};
    private final String[] updateFilePath = {""};
    private final String[] keywords = {""};
    private final String[] key = {""};

    private ApiCaller apiCaller = null;
    private final String[] lastStatus = {""};
    private String lastResponseString = null;

    public UserFormInterface() {
        importChooseFileButton.addActionListener(new ActionListener() {
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
        updateChooseFileButton.addActionListener(new ActionListener() {
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
        sendButtonImport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                key[0] = inputYourSubscriptionKeyTextField.getText();
                if(apiCaller == null | lastStatus.equals("401")){
                    //rebuild API caller with new subscription key
                    apiCaller = new ApiCaller(key[0]);
                }
                if(importFilePath[0].equals("")){
                    resultTextArea.setText("No file selected! Please select a File to update your document with.");
                } else {
                    try {
                        Response lastResponse = apiCaller.importFile(importFileName[0], importFilePath[0]);
                        lastStatus[0] = String.valueOf(lastResponse.code());
                        importSuccessLabel.setText(lastStatus[0]);
                        lastResponseString = lastResponse.body().string();
                        resultTextArea.setText(lastResponseString);
                        //System.out.println(lastResponseString);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });
        sendButtonRetrieve.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                key[0] = inputYourSubscriptionKeyTextField.getText();
                String objectId = retrieveOidTextField.getText();
                if(apiCaller == null | lastStatus.equals("401")){
                    //rebuild API caller with new subscription key
                    apiCaller = new ApiCaller(key[0]);
                }
                if(objectId.length() != 36){
                    resultTextArea.setText("Object ID invalid!");
                } else {
                    try {
                        Response lastResponse = apiCaller.getFileByObjectId(objectId);
                        lastStatus[0] = String.valueOf(lastResponse.code());
                        getSuccessLabel.setText(lastStatus[0]);
                        if(lastStatus[0].equals("200")){
                            String contentType = lastResponse.header("Content-Type");
                            if(contentType.equals("text/html")){
                                lastResponseString = lastResponse.body().string();
                                resultTextArea.setText("Displaying the Content of the object with ID: "+objectId+"\n\n"+lastResponseString);
                            }else{
                                resultTextArea.setText("Content of the object with ID: "+objectId+" successfully retrieved, but cannot be displayed due to content type "+contentType);
                            }
                        }


                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });
        sendButtonUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                key[0] = inputYourSubscriptionKeyTextField.getText();
                String objectId = updateOidTextField.getText();
                if(apiCaller == null | lastStatus.equals("401")){
                    //rebuild API caller with new subscription key
                    apiCaller = new ApiCaller(key[0]);
                }
                if(updateFilePath[0].equals("")){
                    resultTextArea.setText("No file selected! Please select a File to update your document with.");
                }else if(objectId.length() != 36){
                    resultTextArea.setText("Object ID invalid!");
                }
                else {
                    try {
                        Response lastResponse = apiCaller.updateObjectContentByObjectId(objectId, updateFilePath[0]);
                        lastStatus[0] = String.valueOf(lastResponse.code());
                        updateSuccessLabel.setText(lastStatus[0]);
                        lastResponseString = lastResponse.body().string();
                        if(lastStatus[0].equals("200")){
                            resultTextArea.setText("Successfully updated object with ID: "+objectId+"\n\n"+lastResponseString);
                        } else {
                            resultTextArea.setText("Something went wrong when trying to update: "+objectId+"\n\n"+lastResponseString);

                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });
        sendButtonSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                key[0] = inputYourSubscriptionKeyTextField.getText();
                String keywords = keywordTextField.getText();
                if(apiCaller == null | lastStatus.equals("401")){
                    //rebuild API caller with new subscription key
                    apiCaller = new ApiCaller(key[0]);
                }
                try {
                    Response lastResponse = apiCaller.fullTextSearchForKeyword(keywords);
                    lastStatus[0] = String.valueOf(lastResponse.code());
                    searchSuccessLabel.setText(lastStatus[0]);
                    lastResponseString = lastResponse.body().string();
                    resultTextArea.setText("Displaying One Search Result for keywords: "+keywords +"\n\n"+lastResponseString);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        sendButtonDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                key[0] = inputYourSubscriptionKeyTextField.getText();
                String objectId = updateOidTextField.getText();
                String versionNr = versionNurmberTextField.getText();
                if(apiCaller == null | lastStatus.equals("401")){
                    //rebuild API caller with new subscription key
                    apiCaller = new ApiCaller(key[0]);
                }
                if(objectId.length() != 36){
                    resultTextArea.setText("Object ID invalid!");
                } else {
                    try {
                        Response lastResponse = apiCaller.deleteObjectById(objectId, versionNr);
                        lastStatus[0] = String.valueOf(lastResponse.code());
                        deleteSuccessLabel.setText(lastStatus[0]);
                        lastResponseString = lastResponse.body().string();
                        if(lastStatus[0].equals("200")){
                            resultTextArea.setText("Successfully deleted version "+versionNr+" of object with ID: "+ objectId);
                        }else{
                            resultTextArea.setText("Something went wrong with the deletion of object with ID: "+objectId);
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        objectIdCopyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String objectId = apiCaller.copyObjectIdFromResponse(lastResponseString);
                StringSelection objectIdSelection = new StringSelection(objectId);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(objectIdSelection, objectIdSelection);
                resultTextArea.append("\nCopied enaio:objectId to clipboard!");
            }
        });
        inputYourSubscriptionKeyTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                inputYourSubscriptionKeyTextField.select(0, inputYourSubscriptionKeyTextField.getText().length());
            }

            @Override
            public void focusLost(FocusEvent e) {
                inputYourSubscriptionKeyTextField.select(0, 0);

            }
        });
        retrieveOidTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                retrieveOidTextField.select(0, retrieveOidTextField.getText().length());
            }

            @Override
            public void focusLost(FocusEvent e) {
                retrieveOidTextField.select(0, 0);

            }
        });
        updateOidTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                updateOidTextField.select(0, updateOidTextField.getText().length());
            }

            @Override
            public void focusLost(FocusEvent e) {
                updateOidTextField.select(0, 0);

            }
        });
        keywordTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                keywordTextField.select(0, keywordTextField.getText().length());
            }

            @Override
            public void focusLost(FocusEvent e) {
                keywordTextField.select(0, 0);

            }
        });
        deleteOidTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                deleteOidTextField.select(0, deleteOidTextField.getText().length());
            }

            @Override
            public void focusLost(FocusEvent e) {
                deleteOidTextField.select(0, 0);

            }
        });
        versionNurmberTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                versionNurmberTextField.select(0, versionNurmberTextField.getText().length());
            }

            @Override
            public void focusLost(FocusEvent e) {
                versionNurmberTextField.select(0, 0);

            }
        });

    }

    public static void main(String[] args) {
        UserFormInterface ufi = new UserFormInterface();
        JFrame mainFrame = new JFrame("Hello yuuvis®");
        mainFrame.setSize(700, 800);
        mainFrame.setContentPane(ufi.mainPanel);

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

       // mainFrame.pack();
        mainFrame.setVisible(true);
    }

    private void createUIComponents() {
        resultTextArea = new JTextArea();
        resultScrollPane = new JScrollPane();
        resultScrollPane.add(resultTextArea);
    }
}
