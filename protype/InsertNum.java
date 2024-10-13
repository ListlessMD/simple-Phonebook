import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.TreeMap;

public class InsertNum extends JFrame implements ActionListener {

    private JPanel InsertPannel;
    private JLabel INStitle;
    private JButton backButton;
    private JTextField textField1; // For First Name
    private JTextField textField2; // For Phone Number
    private JLabel PhNum;
    private JLabel FName;
    private JButton saveButton;
    private JButton diplayButton;
    private JButton sortButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton searchButton;
    private JTextField searchtextfield;
    private JPanel displaybox;
    private JTextArea displayArea;

    // HashMap to store phone numbers against names
    private HashMap<String, String> contactsMap;

    public InsertNum() {
        contactsMap = new HashMap<>();

        setContentPane(InsertPannel);
        setTitle("Insert New Number");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 700);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);

        displayArea = new JTextArea(); // Initialize displayArea
        displayArea.setEditable(false);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Returns user to home panel
                HomeGUI Homeepannel = new HomeGUI();
                Homeepannel.show(); // Displays home panel

                dispose(); // Closes this JFrame
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textField1.getText().trim();
                String phoneNumber = textField2.getText().trim();

                // Check if fields are not empty
                if (!name.isEmpty() && !phoneNumber.isEmpty()) {
                    // Store in HashMap
                    contactsMap.put(name, phoneNumber);
                    JOptionPane.showMessageDialog(null, "Contact saved!");

                    // Clear text fields after saving
                    textField1.setText("");
                    textField2.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in both fields.");
                }
            }
        });
        System.out.println(contactsMap);
        diplayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayArea.setText(""); // Clear the text area
                if (contactsMap.isEmpty()) {
                    displayArea.setText("No contacts found.");
                } else {
                    for (String name : contactsMap.keySet()) {
                        String phoneNumber = contactsMap.get(name);
                        displayArea.append(name + ": " + phoneNumber + "\n");
                    }
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String nameToUpdate = JOptionPane.showInputDialog(null, "Enter the name of the contact to update:");
                        if (contactsMap.containsKey(nameToUpdate)) {
                            String newPhoneNumber = JOptionPane.showInputDialog(null, "Enter the new phone number:");
                            if (!newPhoneNumber.isEmpty()) {
                                contactsMap.put(nameToUpdate, newPhoneNumber);
                                JOptionPane.showMessageDialog(null, "Contact updated successfully!");
                            } else {
                                JOptionPane.showMessageDialog(null, "Please enter a valid phone number.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Contact not found.");
                        }
                    }
                });

            }
        });
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        TreeMap<String, String> sortedContacts = new TreeMap<>(contactsMap);
                        contactsMap.clear();
                        contactsMap.putAll(sortedContacts);
                        JOptionPane.showMessageDialog(null, "Contacts sorted alphabetically!");
                    }
                });

            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String nameToDelete = JOptionPane.showInputDialog(null, "Enter the name of the contact to delete:");
                        if (contactsMap.containsKey(nameToDelete)) {
                            contactsMap.remove(nameToDelete);
                            JOptionPane.showMessageDialog(null, "Contact deleted successfully!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Contact not found.");
                        }
                    }
                });


            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String nameToSearch = JOptionPane.showInputDialog(null, "Enter the name of the contact to search for:");
                        if (contactsMap.containsKey(nameToSearch)) {
                            String phoneNumber = contactsMap.get(nameToSearch);
                            JOptionPane.showMessageDialog(null, "Phone number: " + phoneNumber);
                        } else {
                            JOptionPane.showMessageDialog(null, "Contact not found.");
                        }
                    }
                });


            }
        });
    }

    private void displayContacts() {
        // Create a StringBuilder to hold the contact details
        StringBuilder contactsList = new StringBuilder("Contacts:\n");

        // Loop through the HashMap and append the entries to the StringBuilder
        for (String key : contactsMap.keySet()) {
            contactsList.append(key).append(": ").append(contactsMap.get(key)).append("\n");
        }

        // Show the contacts in a dialog
        JOptionPane.showMessageDialog(this, contactsList.toString(), "Contact List", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new InsertNum();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}