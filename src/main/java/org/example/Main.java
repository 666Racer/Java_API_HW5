package org.example;

import java.util.*;

class Contacts { //создаём класс с HashMap
    public HashMap<String, HashSet<String>> contacts;

    public Contacts() {
        contacts = new HashMap<>(); //создаём саму телефонную книгу
    }

    public boolean addContact(String name, String number) { //добавление контакта
        if (contacts.containsKey(name)) { //проверка на повторяемость
            if(!contacts.get(name).contains(number)) return contacts.get(name).add(number); //добавление номера к существующему контакту
            else return false;
        }
        HashSet<String> temp = new HashSet<>(); //помещаем новый контакт в буферный HashSet
        temp.add(number); //в буфер только номер
        contacts.put(name, temp);
        return true;
    }

    public void print() { //вывод на печать
        HashMap<String, Integer> сontactsToPrint = new HashMap<>(); //
        contacts.forEach((k, v) -> {
            сontactsToPrint.put(k, v.size());
        });

        сontactsToPrint.entrySet().stream()
                .sorted(Map.Entry
                        .comparingByValue(Comparator.reverseOrder())) //сортировка компаратором по количеству номеров в стриме
                .forEach(e -> {
                    System.out.print(e.getKey() + "\n");
                    contacts.get(e.getKey()).forEach(System.out::println);
                });

    }
}


public class Main {
    /*Реализуйте структуру телефонной книги с помощью HashMap.
     Программа также должна учитывать, что во входной структуре
     будут повторяющиеся имена с разными телефонами, их необходимо
     считать, как одного человека с разными телефонами.
     Вывод должен быть отсортирован по убыванию числа телефонов. */
    public static void main(String[] args) {
        Contacts contacts = new Contacts();
        Scanner in = new Scanner(System.in);
        String person = "";

        // Заполняем людьми
        while (true) {
            System.out.println("Enter new record (FirstName MiddleName LastName, Phone): ");
            person = in.nextLine();
            if (person.equalsIgnoreCase("end")){
                System.out.println("-".repeat(15) + "EXIT" + "-".repeat(15));
                break;
            }
            String[] splittedPerson = person.split(", ");
            System.out.println(contacts
                    .addContact(splittedPerson[0], splittedPerson[1])? "SUCCESS" : "ERROR");
        }

        contacts.print();

    }
}
