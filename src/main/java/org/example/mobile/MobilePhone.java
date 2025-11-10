package org.example.mobile;

import java.util.ArrayList;
import java.util.List;

public class MobilePhone {

    private final String myNumber;
    private final ArrayList<Contact> myContacts;


    public MobilePhone(String myNumber) {
        this.myNumber = myNumber;
        this.myContacts = new ArrayList<>();
    }


    public MobilePhone(String myNumber, List<Contact> contacts) {
        this.myNumber = myNumber;
        this.myContacts = new ArrayList<>();
        if (contacts != null) {
            for (Contact c : contacts) {
                if (c != null) {
                    this.myContacts.add(c);
                }
            }
        }
    }

    // Getter'lar
    public String getMyNumber() {
        return myNumber;
    }

    public ArrayList<Contact> getMyContacts() {
        return myContacts;
    }


    public boolean addNewContact(Contact contact) {
        if (contact == null) return false;
        if (findContact(contact.getName()) >= 0) return false;
        myContacts.add(contact);
        return true;
    }


    public boolean updateContact(Contact oldContact, Contact newContact) {
        if (oldContact == null || newContact == null) return false;

        int oldIdx = findContact(oldContact);
        if (oldIdx < 0) return false;

        int clashIdx = findContact(newContact.getName());
        if (clashIdx >= 0 && clashIdx != oldIdx) {
            // başka bir kayıt aynı ismi kullanıyorsa izin verme
            return false;
        }

        myContacts.set(oldIdx, newContact);
        return true;
    }

    // Kayıt varsa sil
    public boolean removeContact(Contact contact) {
        int idx = findContact(contact);
        if (idx < 0) return false;
        myContacts.remove(idx);
        return true;
    }

    // Contact bazlı index
    public int findContact(Contact contact) {
        if (contact == null) return -1;
        return findContact(contact.getName());
    }

    // İsimle index
    public int findContact(String name) {
        if (name == null) return -1;
        for (int i = 0; i < myContacts.size(); i++) {
            if (myContacts.get(i).getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    // İsimle kişi döndür, yoksa null
    public Contact queryContact(String name) {
        int idx = findContact(name);
        return (idx >= 0) ? myContacts.get(idx) : null;
    }

    // Formatlı çıktı
    public void printContacts() {
        System.out.println("Contact List:");
        for (Contact c : myContacts) {
            System.out.println(c.getName() + " -> " + c.getPhoneNumber());
        }
    }
}
