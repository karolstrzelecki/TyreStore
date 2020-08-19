package strzelecki.karol.masprojekt;

import pl.nip24.client.InvoiceData;
import pl.nip24.client.NIP24Client;
import pl.nip24.client.Number;

import java.net.MalformedURLException;

public class Main {

    public static void main( String args[]){

        NIP24Client nip24 = null;
        try {
            nip24 = new NIP24Client("mHNHl9LVPRsS", "BMjAknLZIpVe");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InvoiceData invoice = nip24.getInvoiceData(Number.NIP, "9590168980");
        System.out.println(invoice);
        if (invoice != null) {
            System.out.println("NIP: " + invoice.getNIP());
            System.out.println("Nazwa: " + invoice.getName());
            System.out.println("Imię i nazwisko: " + invoice.getFirstName() + " " + invoice.getLastName());
            System.out.println("Adres: " + invoice.getPostCode() + " " + invoice.getPostCity() + " "
                    + invoice.getStreet() + " " + invoice.getStreetNumber());


        }
    }
}
