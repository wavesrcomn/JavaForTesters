package addressbook.generators;

import addressbook.model.ContactData;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter (names = "-c", description = "Contact count")
    public int count;

    @Parameter (names = "-f", description = "Target file")
    public String file;

    @Parameter (names = "-d", description = "Data format")
    public String format;


    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex){
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("csv")) {
            saveAsCsv(contacts, new File (file));
        } else if (format.equals("xml")) {
            saveAsXml(contacts, new File (file));
        } else if (format.equals("json")) {
            saveAsJson(contacts, new File (file));
        } else {
            System.out.println("Unrecognized format" + format);
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactData contact: contacts){
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s\n",
                    contact.getFirstname(),
                    contact.getLastname(),
                    contact.getAddress(),
                    contact.getHomePhone(),
                    contact.getMobilePhone(),
                    contact.getWorkPhone(),
                    contact.getEmail(),
                    contact.getEmail2()));
        }
        writer.close();
    }

    private static List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++){
            contacts.add(new ContactData()
                    .withFirstname("Дмитрий")
                    .withLastname("Ковалёв")
                    .withAddress("Пенза")
                    .withHomePhone("23252436")
                    .withMobilePhone("9093170708")
                    .withWorkPhone("8495346236")
                    .withEmail("w323@mail.ru")
                    .withEmail2("w3rurktyu@mail.ru")
                    .withPhoto("src/test/resources/XNBCvZKXHTg.jpg"));
        }
        return contacts;
    }
}