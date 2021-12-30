package phonebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class LinkedList {

    node head;

    public void removenode(String d) {
        // Store head node 
        node temp = head, prev = null;

        // If head node itself holds the key to be deleted 
        if (temp != null && temp.data.fullName().equals(d)) {
            head = temp.next; // Changed head 
            return;
        }
        // Search for the key to be deleted, keep track of the 
        // previous node as we need to change temp.next 
        while (temp != null && !temp.data.fullName().equals(d)) {
            prev = temp;
            temp = temp.next;
        }
        // If key was not present in linked list 
        // Unlink the node from linked list 
        prev.next = temp.next;

    }

    public void searchnode(String d, LinkedList l) {
        l.clear();

        node temp = head;
        while (temp != null) {
            System.out.println("Enters in while loop of searchnode method in linkedlist");
            String p = d.toLowerCase();
            String f = temp.data.fullName().toLowerCase();

            if (f.contains(p) || temp.data.getPhone1().contains(d) || temp.data.getPhone2().contains(d)) {
                l.addSorted(temp.data);
            }
            temp = temp.next;
        }
    }

    public boolean searchnodeedit(String d) {

        boolean flag = false;

        node temp = head;
        while (temp != null) {
            System.out.println("Enters in while loop of searchnodeedit method in linkedlist");
            String p = d;

            String f = temp.data.fullName();
            if (p.matches(f)) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        return flag;
    }

    public void returnNode(String d, Contacts c) {

        node temp = head;
        while (temp != null) {
            System.out.println("Enters in while loop of returnnode method in linkedlist");
            String p = d;
            String f = temp.data.fullName();
            if (p.equals(f)) {
                c.setFirstname(temp.data.getFirstname());
                c.setLastname(temp.data.getLastname());
                c.setPhone1(temp.data.getPhone1());
                c.setPhone2(temp.data.getPhone2());
                c.setEmail(temp.data.getEmail());
                c.setOrganization(temp.data.getOrganization());
                c.setTitle(temp.data.getTitle());
                c.setAddress(temp.data.getAddress());
                c.setImage(temp.data.getImage());
                break;
            }
            temp = temp.next;
        }
    }

    public void readlinkedlist() throws FileNotFoundException {
        File file = new File("src\\data\\output.txt");
        Scanner s1 = new Scanner(file);
        System.out.println("in read method ");
        String p = "";
        //PrintWriter input = new PrintWriter(new FileOutputStream("src\\data\\output.txt", true));
        String s[] = null, f[] = null;
        while (s1.hasNext()) {
            p = s1.nextLine();
            s = p.split("-");
            System.out.println("Split S ");
        }
        for (String item : s) {
            System.out.println("Split s " + item);
        }

        for (int i = 0; i < s.length; i++) {
            f = s[i].split(",");
            for (int j = 0; j < f.length; j += 9) {
                String fn, ln, e, addr, org, title, p1, p2, image;
                fn = f[j];
                ln = f[j + 1];
                e = f[j + 2];
                addr = f[j + 3];
                title = f[j + 4];
                org = f[j + 5];
                p1 = f[j + 6];
                p2 = f[j + 7];
                image = f[j + 8];
                addSorted(new Contacts(fn, ln, p1, p2, e, addr, org, title, image));
            }
            System.out.println("Split f " + f[i]);
        }
        System.out.println(f.length);
        for (int i = 0; i < f.length; i++) {
            System.out.println(f[i]);
        }

    }

    public void writelinkedlist() throws FileNotFoundException {
        PrintWriter output = new PrintWriter(new FileOutputStream("src\\data\\output.txt", true));
        System.out.println("In the writelinkedlist method****");
        node temp = head;
        while (temp != null) {
            String p = temp.data.toString();
            output.write(p);
            temp = temp.next;
        }
        output.close();
    }

    public void removeDuplicates() {
        /*Another reference to head*/
        node curr = head;

        /* Traverse list till the last node */
        while (curr != null) {
            node temp = curr;
            /*Compare current node with the next node and 
            keep on deleting them until it matches the current 
            node data */
            while (temp != null && temp.data.fullName().equals(curr.data.fullName())) {
                temp = temp.next;
            }
            /*Set current node next to the next different 
            element denoted by temp*/
            curr.next = temp;
            curr = curr.next;
        }
    }

    public void clear() {
        head = null;
    }

    public int length() {
        if (head == null) {
            return 0;
        } else {
            int count = 0;
            node temp = head;
            while (temp.next != null) {
                count++;
                temp = temp.next;
            }
            return count + 1;
        }
    }

    public void print() {
        node temp = head;
        while (temp != null) {
            System.out.print(temp.data.toString() + " ");
            temp = temp.next;

        }
        System.out.println();
    }

    public void addSorted(Contacts i) {
        node n = new node(i);

        if (head == null) {
            head = n;
        } else {
            node prev = head;
            node temp = head;
            String a1 = temp.data.getFirstname();
            a1 = a1.toLowerCase();
            char a = a1.charAt(0);
            //97-122 a-z
            String b1 = i.getFirstname();
            b1 = b1.toLowerCase();
            char b = b1.charAt(0);

            while (temp.next != null && (int) a < (int) b) {
                prev = temp;
                temp = temp.next;
            }
            a1 = temp.data.getFirstname();
            a1 = a1.toLowerCase();
            a = a1.charAt(0);
            if (temp == head && (int) b <= (int) a) {
                n.next = temp;
                head = n;
            } else if (temp.next == null && (int) a < (int) b) {
                temp.next = n;
            } else {
                n.next = temp;
                prev.next = n;
            }
        }
    }

    public void Reverse() {
        node next = head;
        node temp = head;
        node previous = head;
        while (temp != null) {
            if (temp == head) {
                next = temp.next;
                temp.next = null;
                previous = temp;
            } else {
                if (temp.next == null) {
                    head = temp;
                }
                next = temp.next;
                temp.next = previous;
                previous = temp;
            }
            temp = next;
        }
    }

}

class node {

    Contacts data;
    node next;

    node(Contacts d) {
        data = d;
    }
}
