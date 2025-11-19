import java.util.Scanner;

public class Main {
    public static void menu(TransportTable transportTable){
        Scanner scanner = new Scanner(System.in);
        int countOfFactories, countOfWarehouses;
        System.out.println("Enter a number for factories:");
        countOfFactories = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter a number for warehouses:");
        countOfWarehouses = Integer.parseInt(scanner.nextLine());
        transportTable.setCountOfFactories(countOfFactories);
        transportTable.setCountOfWarehouses(countOfWarehouses);

        transportTable.enterCells();
        transportTable.enterCapacity();
        transportTable.determineFictitiousCapacity();
    }
    public static void main(String[] args) {
        TransportTable transportTable = new TransportTable();
        menu(transportTable);
    }
}