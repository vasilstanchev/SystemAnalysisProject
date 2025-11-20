import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TransportTable {
    public List<List<TransportCell>> transportTable = new ArrayList<>();
    public List<Integer> capacityOfFactories = new ArrayList<>();
    public List<Integer> capacityOfWarehouses = new ArrayList<>();
    public int countOfFactories;
    public int countOfWarehouses;
    public void enterCells(){
        Scanner scanner = new Scanner(System.in);
        List<TransportCell> transportRow;

        for (int i = 0; i < countOfFactories; i++) {
            transportRow = new ArrayList<>();
            for (int j = 0; j < countOfWarehouses; j++) {
                String id = String.valueOf(i+1) + String.valueOf(j+1);
                System.out.println("Id: " + id);
                System.out.println("Enter transport value:");
                int transportCost = Integer.parseInt(scanner.nextLine());
                TransportCell transportCell = new TransportCell(id, transportCost);
                transportRow.add(transportCell);
            }
            transportTable.add(transportRow);
        }
    }
    public void enterCapacity(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the capacity of each of the warehouses:");
        for (int i = 0; i < countOfWarehouses; i++) {
            System.out.println("Warehouse "+ (i+1));
            capacityOfWarehouses.add(Integer.parseInt(sc.nextLine()));
        }

        System.out.println("Enter the capacity of each of the factories:");
        for (int i = 0; i < countOfFactories; i++) {
            System.out.println("Factory "+ (i+1));
            capacityOfFactories.add(Integer.parseInt(sc.nextLine()));
        }
    }
    public void determineFictitiousCapacity(){
        int sumFactories = 0, sumWarehouses = 0;
        List<TransportCell> transportRow = new ArrayList<>();

        for (int i = 0; i < countOfWarehouses; i++) {
            sumWarehouses += capacityOfWarehouses.get(i);
        }
        for (int i = 0; i < countOfFactories; i++) {
            sumFactories += capacityOfFactories.get(i);
        }

        if (sumWarehouses > sumFactories){
            for (int i = 0; i < countOfWarehouses; i++) {
                String id = String.valueOf(countOfFactories + 1) + String.valueOf(i+1);
                System.out.println("Id: " + id);
                TransportCell transportCell = new TransportCell(id, 0);
                transportRow.add(transportCell);
            }
            transportTable.add(transportRow);
            capacityOfFactories.add(sumWarehouses - sumFactories);
            System.out.println("Fictitious factory has been added");
        }
        else if (sumFactories>sumWarehouses){
            for (int i = 0; i < countOfFactories; i++) {
                String id = String.valueOf(i+1) + String.valueOf(countOfWarehouses + 1);
                System.out.println("Id: " + id);
                TransportCell transportCell = new TransportCell(id, 0);
                transportTable.get(i).add(transportCell);
            }
            capacityOfWarehouses.add(sumFactories - sumWarehouses);
            System.out.println("Fictitious warehouse has been added");
        }
    }

    public void showTransportTable(){
        System.out.println("Transport table:");
        for (int i = 0; i < transportTable.size(); i++) {
            for (int j = 0; j < transportTable.get(i).size(); j++) {
                System.out.printf("Id: %s, Value: %d, Transport cost: %d | ", transportTable.get(i).get(j).getId(), transportTable.get(i).get(j).getValue(), transportTable.get(i).get(j).getTransportCost());
            }
            System.out.printf("%d", capacityOfFactories.get(i));
            System.out.println();
        }
        for (int i = 0; i < countOfWarehouses; i++) {
            System.out.printf("%d                                |", capacityOfWarehouses.get(i));
        }
    }

    public void northwestCornerMethod(){
        int tempFactory, tempWarehouse;
        int warehousePointer = 0;
        List<Integer> copyCapacityOfFactories = new ArrayList<>(capacityOfFactories);
        List<Integer> copyCapacityOfWarehouses = new ArrayList<>(capacityOfWarehouses);
        for (int i = 0; i < transportTable.size(); i++) {
            tempFactory = copyCapacityOfFactories.get(i);
            for (int j = warehousePointer; j < transportTable.get(i).size(); j++) {
                tempWarehouse = copyCapacityOfWarehouses.get(j);
                if (tempFactory > tempWarehouse){
                    transportTable.get(i).get(j).setValue(tempWarehouse);
                    tempFactory = tempFactory - tempWarehouse;
                    copyCapacityOfFactories.set(i,tempFactory);
                    warehousePointer++;
                } else if (tempWarehouse > tempFactory) {
                    transportTable.get(i).get(j).setValue(tempFactory);
                    tempWarehouse = tempWarehouse - tempFactory;
                    copyCapacityOfWarehouses.set(j,tempWarehouse);
                    break;
                } else if (tempWarehouse == tempFactory) {
                    transportTable.get(i).get(j).setValue(tempFactory);
                }
            }
        }
    }

    public void setCountOfFactories(int countOfFactories) {
        this.countOfFactories = countOfFactories;
    }
    public void setCountOfWarehouses(int countOfWarehouses) {
        this.countOfWarehouses = countOfWarehouses;
    }
}
