public class TransportTable {
    public TransportCell[][] transportTable;
    public int[] capacityOfFactories;
    public int[] capacityOfWarehouses;
    TransportTable(int countOfFactories, int countOfWarehouses){
        // this.determineFictitiousCapacity();
        transportTable = new TransportCell[countOfWarehouses][countOfFactories];
        for (int i = 0; i < countOfWarehouses; i++) {
            for (int j = 0; j < countOfFactories; j++) {

            }
        }
    }
    public void determineFictitiousCapacity(int countOfFactories, int countOfWarehouses){
        // tbd
    }
}
