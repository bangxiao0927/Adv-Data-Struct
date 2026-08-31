public class Company {
    private String name;
    private double stockPrice;

    public Company(String name, double stockPrice){
        this.name = name;
        this.stockPrice = stockPrice;
    }

    // +toString() : String - Returns the name of the company and the stockPrice.
	// +updateStockPrice(double) : void - Update the stockPrice.
    //         +getName() : String - Return the name of the company.
    @Override
    public String toString() {
        return "Name: " + this.name + ", Price: $" + String.format("%.2f", this.stockPrice);
    }

    public void updateStockPrice(double newPrice) {
        this.stockPrice = newPrice;
    }

    public String getName() {
        return this.name;
    }
}
