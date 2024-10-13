This is a group project done by 6 people 
the main purpose of this project is to make a phone book that inserts, sorts,displays,deletes,searches, and updates contacts
every explanation per code is indicated by <--- besides repetitive code that has been explained prior

Imports 
1. Import java.swing.*; <---is a development tool used for GUI creation.
2. Import java.awt.*; <--- is used for creating user interfaces and painting graphics and 
images.
3. Import java.util.*; - provide functionality for commonly used cases which are usually
encountered like scanners.

Class 
Phone book class <--- extends JFRAMES meaning its a type of window.
 -Implements ActionListener, which allows the app to respond to button clicks.


Attribute of PhoneBookApp
- phoneBook: A LinkedList to store Contact objects (name and phone number).
- nameField, phoneField, searchField: Input fields for the user to enter names, phone numbers, 
or search queries.
- displayArea: A text area to display information about contacts.
- Buttons: These trigger actions like adding, searching, displaying, deleting, updating, and 
sorting contacts.

Method actionPerformed(ActionEvent e) <---( this line prettymuch means [ do an action { action is an event action}]
• This part of the code handles the logic of the buttons (till where it ends)
<--- Add Contact button: Takes input , creates a new Contact, and adds it to the phoneBook.
<--- Search Contact button: Searches for a contact by name.
<--- Display All Contacts button: Show all the contacts in the list.
- Delete Contact button: Removes a contact by name.
<--- Update Contact button: Updates a contact's phone number.
<--- Sort Contacts button: Sorts the phonebook alphabetically.

<--- setTitle("Phone Book Application"); -To set a specified title/name of a window/JFrame 
 setSize(800, 800); - To set the width followed by the high of said window/JFrame
 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); <--- to set the defult closeoperation/x button/close window button to exit as its closed 
 setLayout(new BorderLayout());
 setLocationRelativeTo(null); - to make the window/JFrame be centerd as it pops up
 setResizable(false) <--- this makes it so the size of the window/JFrame cannot be alterd

// Add phone number input
 inputPanel.add(new JLabel("Phone Number:")); <--- lable on the JFrame/ preset text that is unchangeble
 phoneField = new JTextField();
 
 // Add buttons for contact actions
 addButton = new JButton("Add Contact");
 addButton.addActionListener(this); <--- It registers an event listener for the button (addButton), so that when the button is clicked, a specific action will be done
 inputPanel.add(addButton)

  inputPanel.add(new JLabel("Search by Name:")); <-- Adds a label to the panel with the text "Search by Name:" to guide the user.
 searchField = new JTextField(); <- Creates a text field (searchField) where the user can type the name they want to search for.
 inputPanel.add(searchField);< - Adds the text field to the panel.
 searchButton = new JButton("Search"); <- Creates a button labeled "Search".
 searchButton.addActionListener(this); 
 inputPanel.add(searchButton); <---

 displayArea = new JTextArea();
 displayArea.setEditable(false); <---Disables editing, making the text area read-only.
 add(new JScrollPane(displayArea), BorderLayout.CENTER); -Adds the displayArea to the main GUI window, wrapped in a JScrollPane to enable scrolling. The BorderLayout.CENTER ensures that the text area occupies the central space of the window.

 // Add panels to the frame
 JPanel actionPanel = new JPanel(new GridLayout(1, 4)); <---Creates a panel (actionPanel) with a 1-row, 4-column grid layout for arranging buttons.
 actionPanel.add(displayButton); 
 actionPanel.add(deleteButton); 
 actionPanel.add(updateButton);
 actionPanel.add(sortButton); <- Adds four buttons (display, delete, update, sort) to the actionPanel.
 add(inputPanel, BorderLayout.NORTH); <- Adds the inputPanel  to the top (north) of the frame.
 add(actionPanel, BorderLayout.SOUTH); <- Adds the inputPanel  to the botton (south) of the frame.
 }

 // Method to handle actions when buttons are clicked
 public void actionPerformed(ActionEvent e) {  
 if (e.getSource() == addButton) { <---- Begins handling a click on the "Search" button .
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
 } else if (e.getSource() == searchButton) <--- Checks if the "Add" button was clicked.
Retrieves text from nameField and phoneField.
If both fields are not empty, it adds a new contact (name and phone) to the phoneBook.
Displays a success message in displayArea and clears the input fields.
If any field is empty, it prompts the user to enter both name and phone number.

// Search contact logic <----- Search Contact Logic:
Gets the name from searchField.
Searches for the contact using searchContactByName(name).
If found, displays the contact's name and phone. Otherwise, it shows "Contact not found."

 String name = searchField.getText();
 Contact found = searchContactByName(name);
 if (found != null) {
 displayArea.setText("Found: " + found.getName() + " -> " + found.getPhone());
 } else {
 displayArea.setText("Contact not found.");
 }
 } else if (e.getSource() == displayButton) {




 // Display all contacts <----If no contacts are available, it displays "No contacts available."
Otherwise, it lists all contacts in the displayArea.
 displayAllContacts();
 } else if (e.getSource() == deleteButton) {




 
 // Delete contact logic <------ Searches for the contact by name and, if found, deletes it from the phone book. Otherwise, it shows "Contact not found."
 String name = searchField.getText();
 Contact found = searchContactByName(name);
 if (found != null) {
 phoneBook.remove(found);
 displayArea.setText("Deleted Contact: " + name);
 } else {
 displayArea.setText("Contact not found.");
 }
 } else if (e.getSource() == updateButton) {





 
 // Update contact logic <----- Searches for the contact by name and updates the phone number if found and if the new phone number is not empty.
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




 
 // Sort contacts by name <------ Uses insertion sort to sort contacts by name and displays the sorted list.
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
 sb.append(contact.getName()).append(" -> 
").append(contact.getPhone()).append("\n");
 }
 displayArea.setText(sb.toString());
 }
 }

 
 // Method to sort contacts by name using insertion sort (suitable for linked lists)
 private void sortContacts() {
 if (phoneBook.isEmpty()) {
 displayArea.setText("No contacts to sort.");
 } else {

 
 // Perform insertion sort on the linked list <---- to sort a list 
 for (int i = 1; i < phoneBook.size(); i++) {
 Contact key = phoneBook.get(i);
 int j = i - 1;
 while (j >= 0 && phoneBook.get(j).getName().compareTo(key.getName()) > 0) {
 phoneBook.set(j + 1, phoneBook.get(j));
 j--;
 }
 phoneBook.set(j + 1, key);
 }

 
 // Display sorted contacts <--- displaying a sorted list
 displayAllContacts();
 }
 }

 
 // Helper method to search contact by name  <------- Searches the phone book for a contact matching the given name, ignoring case.
 private Contact searchContactByName(String name) {
 for (Contact contact : phoneBook) {
 if (contact.getName().equalsIgnoreCase(name)) {
 return contact;
 }
 }
 return null}



  // Main method to run the application <------Creates an instance of PhoneBookApp and makes it visible (app.setVisible(true)), starting the application.

 public static void main(String[] args) {
 PhoneBookApp app = new PhoneBookApp();
 app.setVisible(true);
 }
}




// Contact class to store name and phone number <------- Stores a contact's name and phone.
Provides getters (getName, getPhone) and a setter (setPhone) to access and modify the contact's details.
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

This code runs the phone book application and defines a Contact class to manage individual contact entries.
