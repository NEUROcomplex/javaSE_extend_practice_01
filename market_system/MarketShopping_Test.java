package market_system;

import java.util.ArrayList;
//v2.0
public class MarketShopping_Test {
    public static void main(String[] args) {
        ArrayList<GoodPurchase> list = new ArrayList<>();
        list.add(new GoodPurchase("001","少林核桃",15.5,"斤"));
        list.add(new GoodPurchase("002","尚康饼干",14.5,"包"));
        list.add(new GoodPurchase("003","移动硬盘",345.0,"个"));
        list.add(new GoodPurchase("004","高清无码",199.0,"G"));

        MarketShoppingSystem ms = new MarketShoppingSystem(list);
        ms.welcome();
    }
}
