package market_system;
//v2.0
public class GoodPurchase {
    private String id;
    private String name;
    private double price;
    private String unit;
    private int amount;
    private double payment;

    public GoodPurchase() {
    }

    public GoodPurchase(String id, String name, double price, String unit) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.unit = unit;
        //this.amount = amount;
        payment = price*amount;

    }

    public void printInfo(){
        StringBuilder s = new StringBuilder();
        s.append(id).append("\t\t").append(name).append("\t").append(price);

        int spaceNum = 7 - ((Double)price).toString().length();
        for (int i = 0; i < spaceNum; i++) {
            s.append(" ");
        }

        s.append(unit);
        System.out.println(s);
    }

    public void printPayment(){
        StringBuilder s = new StringBuilder();
        s.append(name).append("  ").append(price);

        int spaceNum = 7 - ((Double)price).toString().length();
        for (int i = 0; i < spaceNum; i++) {
            s.append(" ");
        }
        s.append(amount);

        spaceNum = 7 - ((Integer)amount).toString().length();
        for (int i = 0; i < spaceNum; i++) {
            s.append(" ");
        }
        s.append(payment);
        System.out.println(s);

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        payment = price*amount;//改变价格时更新应付款;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
        payment = price*amount;//改变数量时更新应付款;
    }

    public double getPayment() {
        return payment;
    }

    /*public void setPayment(double payment) {
        this.payment = payment;
    }*/
}
