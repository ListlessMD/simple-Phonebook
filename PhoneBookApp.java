import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.HashMap;

public class PhoneBookApp extends JFrame implements ActionListener {

    private LinkedList<Contact> phoneBook;

    private JTextField nameField, phoneField, searchField;
    private JTextArea displayArea;
    private JButton addButton, searchButton, displayButton, deleteButton, updateButton, sortButton;

    public PhoneBookApp() {
        phoneBook = new LinkedList<>();

        setTitle("Phone Book Application");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel inputPanel = new JPanel(new GridLayout(5, 2));

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        // Add phone number input
        inputPanel.add(new JLabel("Phone Number:"));
        phoneField = new JTextField();
        inputPanel.add(phoneField);

        // Add buttons for contact actions
        addButton = new JButton("Add Contact");
        addButton.addActionListener(this);
        inputPanel.add(addButton);

        // Search section
        inputPanel.add(new JLabel("Search by Name:"));
        searchField = new JTextField();
        inputPanel.add(searchField);

        searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        inputPanel.add(searchButton);

        // Add display area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // Add buttons for other features
        displayButton = new JButton("Display All Contacts");
        displayButton.addActionListener(this);
        deleteButton = new JButton("Delete Contact");
        deleteButton.addActionListener(this);
        updateButton = new JButton("Update Contact");
        updateButton.addActionListener(this);
        sortButton = new JButton("Sort Contacts");
        sortButton.addActionListener(this);

        // Add panels to the frame
        JPanel actionPanel = new JPanel(new GridLayout(1, 4));
        actionPanel.add(displayButton);
        actionPanel.add(deleteButton);
        actionPanel.add(updateButton);
        actionPanel.add(sortButton);
        add(inputPanel, BorderLayout.NORTH);
        add(actionPanel, BorderLayout.SOUTH);
    }

    // Method to handle actions when buttons are clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            // Add contact logic
            String name = nameField.getText();
            String phone = phoneField.getText();
            if (!name.isEmpty() && !phone.isEmpty()) {
                phoneBook.add(new Contact(name, phone));
                displayArea.setText("Contact Added: " + name + " -> " + phone);
                nameField.setText("");
                phoneField.setText("");
            } else {
                displayArea.setText("Please enter both name and phone number.");
            }
        } else if (e.getSource() == searchButton) {
            // Search contact logic
            String name = searchField.getText();
            Contact found = searchContactByName(name);
            if (found != null) {
                displayArea.setText("Found: " + found.getName() + " -> " + found.getPhone());
            } else {
                displayArea.setText("Contact not found.");
            }
        } else if (e.getSource() == displayButton) {
            // Display all contacts
            displayAllContacts();
        } else if (e.getSource() == deleteButton) {
            // Delete contact logic
            String name = searchField.getText();
            Contact found = searchContactByName(name);
            if (found != null) {
                phoneBook.remove(found);
                displayArea.setText("Deleted Contact: " + name);
            } else {
                displayArea.setText("Contact not found.");
            }
        } else if (e.getSource() == updateButton) {
            // Update contact logic
            String name = searchField.getText();
            String newPhone = phoneField.getText();
            Contact found = searchContactByName(name);
            if (found != null && !newPhone.isEmpty()) {
                found.setPhone(newPhone);
                displayArea.setText("Updated Contact: " + name + " -> " + newPhone);
            } else {
                displayArea.setText("Contact not found or phone number is empty.");
            }
        } else if (e.getSource() == sortButton) {
            // Sort contacts by name
            sortContacts();
        }
    }

    // Method to display all contacts
    private void displayAllContacts() {
        if (phoneBook.isEmpty()) {
            displayArea.setText("No contacts available.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Contact contact : phoneBook) {
                sb.append(contact.getName()).append(" -> ").append(contact.getPhone()).append("\n");
            }
            displayArea.setText(sb.toString());
        }
    }

    // Method to sort contacts by name using insertion sort (suitable for linked lists)
    private void sortContacts() {
        if (phoneBook.isEmpty()) {
            displayArea.setText("No contacts to sort.");
        } else {
            // Perform insertion sort on the linked list
            for (int i = 1; i < phoneBook.size(); i++) {
                Contact key = phoneBook.get(i);
                int j = i - 1;
                while (j >= 0 && phoneBook.get(j).getName().compareTo(key.getName()) > 0) {
                    phoneBook.set(j + 1, phoneBook.get(j));
                    j--;
                }
                phoneBook.set(j + 1, key);
            }

            // Display sorted contacts
            displayAllContacts();
        }
    }

    // Helper method to search contact by name
    private Contact searchContactByName(String name) {
        for (Contact contact : phoneBook) {
            if (contact.getName().equalsIgnoreCase(name)) {
                return contact;
            }
        }
        return null;
    }

    // Main method to run the application
    public static void main(String[] args) {
        PhoneBookApp app = new PhoneBookApp();
        app.setVisible(true);
    }
}

// Contact class to store name and phone number
class Contact {
    private String name;
    private String phone;

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }
}
